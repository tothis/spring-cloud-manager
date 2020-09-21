create table `app_version`
(
    `id`          bigint(20) unsigned not null auto_increment,
    `code`        varchar(60)         default '' comment '版本号',
    `os_type`     tinyint(1) unsigned default null comment '系统类型 0:安卓 1:ios',
    `create_by`   bigint(20) unsigned default null comment '创建者',
    `create_date` datetime            default null comment '创建时间',
    `update_by`   bigint(20) unsigned default null comment '修改者',
    `update_date` datetime            default null comment '修改时间',
    `state`       tinyint(1) unsigned default null comment '状态 0:正常 1:已删除',
    primary key (`id`)
) comment = '系统版本';

create table `task`
(
    `id`               bigint unsigned not null auto_increment,
    `task_name`        char(10)         default '' comment '任务名称',
    `task_state`       bit(1)           default null comment '任务状态 0:关闭(默认) 1:正常',
    `create_by`        bigint unsigned  default null comment '创建者',
    `create_date_time` datetime         default null comment '创建时间',
    `update_by`        bigint unsigned  default null comment '修改者',
    `update_date_time` datetime         default null comment '修改时间',
    `state`            tinyint unsigned default null comment '数据状态 0:正常(默认) 1:已删除',
    primary key (`id`)
) comment = '任务';

create table `user`
(
    `id`               bigint unsigned not null auto_increment,
    `user_name`        char(10)         default '' comment '用户昵称 4-10个字符',
    `password`         char(32)         default '' comment '用户密码密文',
    `create_by`        bigint unsigned  default null comment '创建者',
    `create_date_time` datetime         default null comment '创建时间',
    `update_by`        bigint unsigned  default null comment '修改者',
    `update_date_time` datetime         default null comment '修改时间',
    `state`            tinyint unsigned default null comment '数据状态 0:正常(默认) 1:已删除',
    primary key (`id`)
) comment = '用户';