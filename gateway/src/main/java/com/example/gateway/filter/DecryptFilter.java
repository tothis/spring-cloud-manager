package com.example.gateway.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.example.gateway.constant.GlobalFilterOrderConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.example.gateway.filter.CacheBodyGatewayFilter.CACHE_REQUEST_BODY_OBJECT_KEY;

/**
 * 签名过滤器
 *
 * @author 李磊
 * @since 1.0
 */
@Component
@Slf4j
@ConditionalOnProperty(value = "app.decrypt.enabled"
        , havingValue = "true")
public class DecryptFilter implements GlobalFilter, Ordered {

    @Value("${app.decrypt.privateKey}")
    private String privateKey;

    @Value("${app.decrypt.publicKey}")
    private String publicKey;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 获取request body
        Flux<DataBuffer> cachedBody = exchange.getAttribute(CACHE_REQUEST_BODY_OBJECT_KEY);
        if (cachedBody != null) {
            HttpHeaders headers = request.getHeaders();
            List<String> decrypts = headers.get("decrypt");
            if (CollUtil.isNotEmpty(decrypts)) {
                String decrypt = decrypts.get(0);
                switch (request.getMethod()) {
                    case GET:
                    case DELETE:
                        MultiValueMap<String, String> queryParams = request.getQueryParams();
                        MapUtil.sort(queryParams);
                        decrypt(queryParams.toString(), decrypt);
                        break;
                    case PUT:
                    case POST:
                        request.getBody().subscribe(buffer -> {
                            byte[] bytes = new byte[buffer.readableByteCount()];
                            buffer.read(bytes);
                            DataBufferUtils.release(buffer);
                            decrypt(new String(bytes), decrypt);
                        });
                        break;
                    default:
                        break;
                }
            }
        }
        ServerHttpRequest.Builder builder = request.mutate();
        return chain.filter(exchange.mutate().request(builder.build()).build());
    }

    @Override
    public int getOrder() {
        return GlobalFilterOrderConstant.SIGN;
    }

    /**
     * 根据参数校验签名
     */
    private String decrypt(String params, String sign) {
        RSA rsa = new RSA(privateKey, publicKey);
        /*String paramsContent = URLUtil.encodeAll(params);
        // 私钥加密
        byte[] encryptBytes = rsa.encrypt(paramsContent.getBytes(), KeyType.PrivateKey);
        String encrypt = URLUtil.encodeAll(new String(encryptBytes, CharsetUtil.CHARSET_ISO_8859_1));
        System.out.println(encrypt);*/
        // 公钥解密
        byte[] decryptBytes = rsa.decrypt(URLUtil.decode(sign)
                        .getBytes(CharsetUtil.CHARSET_ISO_8859_1)
                , KeyType.PublicKey);
        String data = URLUtil.decode(new String(decryptBytes));
        log.info("解密数据为 -> ", data);
        return data;
    }
}