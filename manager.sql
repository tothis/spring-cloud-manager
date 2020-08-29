create table `task`
(
    `id`          bigint not null auto_increment,
    `task_name`   char(10)   default null comment '任务名称',
    `task_state`  bit(1)     default null comment '任务状态 0:关闭(默认) 1:正常',
    `create_by`   bigint     default null comment '创建者',
    `create_date` datetime   default null comment '创建时间',
    `update_by`   bigint     default null comment '修改者',
    `update_date` datetime   default null comment '修改时间',
    `state`       tinyint(1) default null comment '状态 0:正常(默认) 1:已删除',
    primary key (`id`)
) comment ='任务';