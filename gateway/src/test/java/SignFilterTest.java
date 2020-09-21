import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import org.junit.jupiter.api.Test;

/**
 * @author 李磊
 * @since 1.0
 */
class SignFilterTest {
    @Test
    void sign() {
        byte[] data = "测试数据".getBytes();
        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
        // 默认生成随机私钥
        // System.out.println(sign.getPrivateKey());
        System.out.println(sign.getPrivateKeyBase64());
        // 默认生成随机公钥
        // System.out.println(sign.getPublicKey());
        System.out.println(sign.getPublicKeyBase64());

        // 签名
        byte[] signed = sign.sign(data);
        // 验证签名
        boolean verify = sign.verify(data, signed);
        System.out.println(verify);

        Sign testSign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
        System.out.println(testSign.verify(data, signed));
    }

    @Test
    void rsa() {
        RSA rsa = new RSA(
                "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALLqqKLygu/++O7WxEqIv+r6q3HBP/ya3AEgNW6W1TeXBfWMBnCiw1r3oqF58EOVzTVzkgMYHOj39XN2E6Lvhxw45beEx8RPAJ6rinBQrJAzFLYHc5I8xRjj1NXHs2IQMhT9tyT6xMHGRtOCJ2311SQiBypEyFg8nXcvq7G4QSoJAgMBAAECgYANPQ5B0lpsaiwetg77qd8zgp1Fc0RqC0Zcofa6Klh3/sl+1//Q/UYhuMGUm6pywcaXnifPaU0YUuKbSsEctQcE6KUFiWpwTqXqumM0Pt2kFnwm4iRtKwJYqsNIt7AWvujOUopjIaD8ExAcm67dgbHmZ8ZiFFrNxeHp5DDPhEbEoQJBANvUB0hs42XjkG5wMmxcTo61u+WLrvQ7imAeW0/CSCqmYxtoYLd2+2z8PA/8Mtmt7u9AHvn99I/OqYf0PLi521kCQQDQW0ib6MHxloJnZGgzYAEEt2I42pTFuARrPxdQZdp7E0qNa7oc5ElLx7LlMKvvj/RSRAXa8cPkJmjsUmqAT94xAkAIPBYVwXZ3P6IOcQeChaavs+EZBTWFkqFpXlm/UCDR8C+wPCW7Waqrd3tsgd8Jxnk33ygJ51h89pnlLOIFbXjpAkB5YSRch20ss3TGjMzTWRNezwJugo/22yvf2oJXeO6dknB1EJP8rtUwS2chc4hGaEN6BVyv3vYynFm1ipG2rFCRAkEAhiPPYJ7KZ1l3Omf7gst/P1PXsmoGTUUyh2VkRXKOdzN0b/ge6peBFKilqYkOZQDRW8qQCY8vuxgDCHIt5dywiA=="
                , "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCy6qii8oLv/vju1sRKiL/q+qtxwT/8mtwBIDVultU3lwX1jAZwosNa96KhefBDlc01c5IDGBzo9/VzdhOi74ccOOW3hMfETwCeq4pwUKyQMxS2B3OSPMUY49TVx7NiEDIU/bck+sTBxkbTgidt9dUkIgcqRMhYPJ13L6uxuEEqCQIDAQAB"
        );
        // 获得私钥
        System.out.println(rsa.getPrivateKeyBase64());
        // 获得公钥
        System.out.println(rsa.getPublicKeyBase64());

        // 特殊字符需转码
        String content = URLUtil.encodeAll("key=value");

        // 公钥加密 私钥解密
        byte[] encrypt = rsa.encrypt(StrUtil.bytes(content), KeyType.PublicKey);
        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);
        System.out.println(content.equals(StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8)));

        // 私钥加密 公钥解密
        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes(content), KeyType.PrivateKey);
        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);
        System.out.println(content.equals(StrUtil.str(decrypt2, CharsetUtil.CHARSET_UTF_8)));
    }
}