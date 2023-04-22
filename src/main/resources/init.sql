--创建数据库

CREATE
DATABASE IF NOT EXISTS url_0 DEFAULT CHARACTER SET utf8;
CREATE
DATABASE IF NOT EXISTS url_1 DEFAULT CHARACTER SET utf8;
CREATE
DATABASE IF NOT EXISTS url_2 DEFAULT CHARACTER SET utf8;
CREATE
DATABASE IF NOT EXISTS url_3 DEFAULT CHARACTER SET utf8;
CREATE
DATABASE IF NOT EXISTS url_4 DEFAULT CHARACTER SET utf8;


--在每个数据库中执行以下语句
CREATE TABLE `url_mapping_0`
(
    `id`          bigint(20) AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    `short_url`   varchar(6)   NOT NULL COMMENT '短链key',
    `long_url`    varchar(512) NOT NULL COMMENT '原始url',
    `expire_time` datetime COMMENT 'key失效日期',
    `creat_time`  datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
    UNIQUE KEY `short_url` (`short_url`)
);

CREATE TABLE `url_mapping_1`
(
    `id`          bigint(20) AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    `short_url`   varchar(6)   NOT NULL COMMENT '短链key',
    `long_url`    varchar(512) NOT NULL COMMENT '原始url',
    `expire_time` datetime COMMENT 'key失效日期',
    `creat_time`  datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
    UNIQUE KEY `short_url` (`short_url`)
);


CREATE TABLE `url_mapping_2`
(
    `id`          bigint(20) AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    `short_url`   varchar(6)   NOT NULL COMMENT '短链key',
    `long_url`    varchar(512) NOT NULL COMMENT '原始url',
    `expire_time` datetime COMMENT 'key失效日期',
    `creat_time`  datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
    UNIQUE KEY `short_url` (`short_url`)
);


CREATE TABLE `url_mapping_3`
(
    `id`          bigint(20) AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    `short_url`   varchar(6)   NOT NULL COMMENT '短链key',
    `long_url`    varchar(512) NOT NULL COMMENT '原始url',
    `expire_time` datetime COMMENT 'key失效日期',
    `creat_time`  datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
    UNIQUE KEY `short_url` (`short_url`)
);
