create table `app_version`
(
    `id`               bigint(20) unsigned not null auto_increment,
    `code`             varchar(60)         default '' comment '版本号',
    `os_type`          tinyint(1) unsigned default null comment '系统类型 0:安卓 1:iOS',
    `create_by`        bigint(20) unsigned default null comment '创建者',
    `create_date_time` datetime            default null comment '创建时间',
    `update_by`        bigint(20) unsigned default null comment '修改者',
    `update_date_time` datetime            default null comment '修改时间',
    `state`            tinyint(1) unsigned default null comment '状态 0:正常 1:已删除',
    primary key (`id`)
) comment = '系统版本';

create table `task`
(
    `id`               bigint unsigned not null auto_increment,
    `task_name`        char(10)         default '' comment '项目名称 必填 最多10字',
    `task_state`       bit(1)           default null comment '任务状态 0:关闭 1:开启(默认)',
    `description`      varchar(200)     default '' comment '任务说明 选填 10~100字',
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
    `user_name`        char(10)         default '' comment '用户名称 必填 4~10字',
    `password`         char(32)         default '' comment '用户密码 必填',
    `create_by`        bigint unsigned  default null comment '创建者',
    `create_date_time` datetime         default null comment '创建时间',
    `update_by`        bigint unsigned  default null comment '修改者',
    `update_date_time` datetime         default null comment '修改时间',
    `state`            tinyint unsigned default null comment '数据状态 0:正常(默认) 1:已删除',
    primary key (`id`)
) comment = '用户';

create table `dict_type`
(
    `id`               bigint unsigned not null auto_increment,
    `name`             varchar(50)      default '' comment '字典名称',
    `create_by`        bigint unsigned  default null comment '创建者',
    `create_date_time` datetime         default null comment '创建时间',
    `update_by`        bigint unsigned  default null comment '修改者',
    `update_date_time` datetime         default null comment '修改时间',
    `state`            tinyint unsigned default null comment '状态 0:正常 1:已删除',
    primary key (`id`)
) comment = '字典类型';

create table `dict`
(
    `id`               bigint unsigned not null auto_increment,
    `dict_type_id`     bigint           default null comment '字典类型id',
    `label`            varchar(50)      default '' comment '字典名称',
    `value`            varchar(50)      default '' comment '字典值',
    `create_by`        bigint unsigned  default null comment '创建者',
    `create_date_time` datetime         default null comment '创建时间',
    `update_by`        bigint unsigned  default null comment '修改者',
    `update_date_time` datetime         default null comment '修改时间',
    `state`            tinyint unsigned default null comment '状态 0:正常 1:已删除',
    primary key (`id`)
) comment ='字典';