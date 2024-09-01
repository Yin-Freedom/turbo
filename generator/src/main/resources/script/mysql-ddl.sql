-- DROP TABLE IF EXISTS `generator_template`;
CREATE TABLE IF NOT EXISTS generator_template (
    id bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    template_name varchar(128) NOT NULL DEFAULT '' COMMENT '模板名',
    template_value text COMMENT '模板内容',
    creator varchar(128) NOT NULL DEFAULT '' COMMENT '创建人',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表';