/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/11/26 9:47:26                           */
/*==============================================================*/


drop table if exists SSO_ORGANIZATION;

drop table if exists SSO_PERMISSION;

drop table if exists SSO_ROLE;

drop table if exists SSO_ROLE_RES;

drop table if exists SSO_SYSTEM;

drop table if exists SSO_USER;

drop table if exists SSO_USER_ORG;

drop table if exists SSO_USER_ROLE;

drop table if exists SSO_USER_SYSTEM;

drop table if exists SYS_AREA;

drop table if exists SYS_JOB;

drop table if exists SYS_UNIVERSAL_LOG;

drop table if exists SYS_USER;

/*==============================================================*/
/* Table: SSO_ORGANIZATION                                      */
/*==============================================================*/
create table SSO_ORGANIZATION
(
   id                   bigint not null auto_increment,
   parent_Id            bigint,
   parent_Ids           varchar(2000),
   name                 varchar(32),
   sort                 int,
   type                 char(1),
   grade                char(1),
   address              varchar(100),
   zip_code             varchar(12),
   principal            varchar(32),
   phone                varchar(12),
   fax                  varchar(20),
   email                varchar(50),
   available            bit,
   system_id            bigint not null,
   system_name          varchar(32) not null,
   create_user          bigint,
   create_time          datetime,
   update_user          bigint,
   update_time          datetime,
   primary key (id)
);

alter table SSO_ORGANIZATION comment '组织机构表';

alter table SSO_ORGANIZATION modify column id bigint comment 'id';

alter table SSO_ORGANIZATION modify column parent_Id bigint comment '父编号';

alter table SSO_ORGANIZATION modify column parent_Ids varchar(2000) comment '祖先编号';

alter table SSO_ORGANIZATION modify column name varchar(32) comment '名称';

alter table SSO_ORGANIZATION modify column sort int comment '排序';

alter table SSO_ORGANIZATION modify column type char(1) comment '机构类型';

alter table SSO_ORGANIZATION modify column grade char(1) comment '机构等级';

alter table SSO_ORGANIZATION modify column address varchar(100) comment '联系地址';

alter table SSO_ORGANIZATION modify column zip_code varchar(12) comment '邮政编码';

alter table SSO_ORGANIZATION modify column principal varchar(32) comment '负责人';

alter table SSO_ORGANIZATION modify column phone varchar(12) comment '电话';

alter table SSO_ORGANIZATION modify column fax varchar(20) comment '传真';

alter table SSO_ORGANIZATION modify column email varchar(50) comment '邮箱';

alter table SSO_ORGANIZATION modify column available bit comment '可用标示';

alter table SSO_ORGANIZATION modify column system_id bigint comment '系统Id';

alter table SSO_ORGANIZATION modify column system_name varchar(32) comment '系统名称';

alter table SSO_ORGANIZATION modify column create_user bigint comment '创建人';

alter table SSO_ORGANIZATION modify column create_time datetime comment '创建时间';

alter table SSO_ORGANIZATION modify column update_user bigint comment '修改人';

alter table SSO_ORGANIZATION modify column update_time datetime comment '修改时间';

/*==============================================================*/
/* Table: SSO_PERMISSION                                        */
/*==============================================================*/
create table SSO_PERMISSION
(
   id                   bigint not null auto_increment,
   parent_Id            bigint not null,
   name                 varchar(32) not null,
   code                 varchar(32),
   type                 varchar(10) not null,
   permission           varchar(60) not null,
   is_show              bit not null,
   location             varchar(32) not null,
   system_id            bigint not null,
   system_name          varchar(32) not null,
   detail_explain       varchar(100),
   available            bit not null,
   create_user          bigint,
   create_time          datetime,
   update_user          bigint,
   update_time          datetime,
   primary key (id)
);

alter table SSO_PERMISSION comment '权限表';

alter table SSO_PERMISSION modify column id bigint comment 'id';

alter table SSO_PERMISSION modify column parent_Id bigint comment '父级资源Id';

alter table SSO_PERMISSION modify column name varchar(32) comment '资源名';

alter table SSO_PERMISSION modify column code varchar(32) comment '资源编码';

alter table SSO_PERMISSION modify column type varchar(10) comment '资源类型';

alter table SSO_PERMISSION modify column permission varchar(60) comment '权限字符串';

alter table SSO_PERMISSION modify column is_show bit comment '页面是否显示';

alter table SSO_PERMISSION modify column location varchar(32) comment '页面显示位置';

alter table SSO_PERMISSION modify column system_id bigint comment '系统Id';

alter table SSO_PERMISSION modify column system_name varchar(32) comment '系统名称';

alter table SSO_PERMISSION modify column detail_explain varchar(100) comment '资源说明';

alter table SSO_PERMISSION modify column available bit comment '是否可用';

alter table SSO_PERMISSION modify column create_user bigint comment '创建人';

alter table SSO_PERMISSION modify column create_time datetime comment '创建时间';

alter table SSO_PERMISSION modify column update_user bigint comment '修改人';

alter table SSO_PERMISSION modify column update_time datetime comment '修改时间';

/*==============================================================*/
/* Table: SSO_ROLE                                              */
/*==============================================================*/
create table SSO_ROLE
(
   id                   bigint not null auto_increment,
   name                 varchar(32) not null,
   code                 varchar(32),
   system_id            bigint not null,
   system_name          varchar(32),
   detail_explain       varchar(100),
   available            bit,
   create_user          bigint,
   create_time          datetime,
   update_user          bigint,
   update_time          datetime,
   primary key (id)
);

alter table SSO_ROLE comment '角色表';

alter table SSO_ROLE modify column id bigint comment 'id';

alter table SSO_ROLE modify column name varchar(32) comment '角色名';

alter table SSO_ROLE modify column code varchar(32) comment '角色编码';

alter table SSO_ROLE modify column system_id bigint comment '系统Id';

alter table SSO_ROLE modify column system_name varchar(32) comment '系统名称';

alter table SSO_ROLE modify column detail_explain varchar(100) comment '角色说明';

alter table SSO_ROLE modify column available bit comment '是否可用';

alter table SSO_ROLE modify column create_user bigint comment '创建人';

alter table SSO_ROLE modify column create_time datetime comment '创建时间';

alter table SSO_ROLE modify column update_user bigint comment '修改人';

alter table SSO_ROLE modify column update_time datetime comment '修改时间';

/*==============================================================*/
/* Table: SSO_ROLE_RES                                          */
/*==============================================================*/
create table SSO_ROLE_RES
(
   id                   bigint not null auto_increment,
   role_id              bigint not null,
   resource_id          bigint not null,
   system_id            bigint not null,
   system_name          varchar(32),
   available            bit,
   create_user          bigint,
   create_time          datetime,
   update_user          bigint,
   update_time          datetime,
   primary key (id)
);

alter table SSO_ROLE_RES comment '角色-资源表';

alter table SSO_ROLE_RES modify column id bigint comment 'id';

alter table SSO_ROLE_RES modify column role_id bigint comment '角色Id';

alter table SSO_ROLE_RES modify column resource_id bigint comment '资源Id';

alter table SSO_ROLE_RES modify column system_id bigint comment '系统Id';

alter table SSO_ROLE_RES modify column system_name varchar(32) comment '系统名称';

alter table SSO_ROLE_RES modify column available bit comment '是否可用';

alter table SSO_ROLE_RES modify column create_user bigint comment '创建人';

alter table SSO_ROLE_RES modify column create_time datetime comment '创建时间';

alter table SSO_ROLE_RES modify column update_user bigint comment '修改人';

alter table SSO_ROLE_RES modify column update_time datetime comment '修改时间';

/*==============================================================*/
/* Table: SSO_SYSTEM                                            */
/*==============================================================*/
create table SSO_SYSTEM
(
   id                   bigint not null auto_increment,
   name                 varchar(32) not null,
   system_code          varchar(32),
   detail_explain       varchar(2000),
   available            bit,
   create_user          bigint,
   create_time          datetime,
   update_user          bigint,
   update_time          datetime,
   primary key (id)
);

alter table SSO_SYSTEM comment '系统表';

alter table SSO_SYSTEM modify column id bigint comment 'ID';

alter table SSO_SYSTEM modify column name varchar(32) comment '系统名称';

alter table SSO_SYSTEM modify column system_code varchar(32) comment '系统编码';

alter table SSO_SYSTEM modify column detail_explain varchar(2000) comment '系统描述';

alter table SSO_SYSTEM modify column available bit comment '是否可用';

alter table SSO_SYSTEM modify column create_user bigint comment '创建人';

alter table SSO_SYSTEM modify column create_time datetime comment '创建时间';

alter table SSO_SYSTEM modify column update_user bigint comment '修改人';

alter table SSO_SYSTEM modify column update_time datetime comment '修改时间';

/*==============================================================*/
/* Table: SSO_USER                                              */
/*==============================================================*/
create table SSO_USER
(
   ID                   bigint not null auto_increment,
   user_number          bigint,
   login_name           varchar (32) not null,
   real_name            varchar (32),
   password             varchar (64),
   sex                  INT not null,
   birthday             varchar(20) not null,
   Id_card              varchar(50),
   position             varchar(20),
   phone                varchar (32) not null,
   email                varchar(50),
   join_time            datetime,
   last_login           datetime,
   last_ip              varchar(32),
   salt                 varchar(50),
   sum_login            bigint,
   online_time          varchar(100),
   detail_explain       varchar(100),
   available            bit,
   create_user          bigint,
   create_time          datetime,
   update_user          bigint,
   update_time          datetime,
   primary key (ID)
);

alter table SSO_USER comment '用户表';

alter table SSO_USER modify column ID bigint comment '主键ID';

alter table SSO_USER modify column user_number bigint comment '员工编号';

alter table SSO_USER modify column login_name varchar (32) comment '员工姓名';

alter table SSO_USER modify column real_name varchar (32) comment '员工真实名称';

alter table SSO_USER modify column password varchar (64) comment '密码';

alter table SSO_USER modify column sex INT comment '性别(0女,1男,2未知)';

alter table SSO_USER modify column birthday varchar(20) comment '生日';

alter table SSO_USER modify column Id_card varchar(50) comment '身份证号';

alter table SSO_USER modify column position varchar(20) comment '员工职位';

alter table SSO_USER modify column phone varchar (32) comment '电话号码';

alter table SSO_USER modify column email varchar(50) comment '电子邮件';

alter table SSO_USER modify column join_time datetime comment '入职时间';

alter table SSO_USER modify column last_login datetime comment '上次登录时间';

alter table SSO_USER modify column last_ip varchar(32) comment '上次登录IP地址';

alter table SSO_USER modify column salt varchar(50) comment '加密盐';

alter table SSO_USER modify column sum_login bigint comment '总登录次数';

alter table SSO_USER modify column online_time varchar(100) comment '总在线时长';

alter table SSO_USER modify column detail_explain varchar(100) comment '备注';

alter table SSO_USER modify column available bit comment '是否可用';

alter table SSO_USER modify column create_user bigint comment '创建人';

alter table SSO_USER modify column create_time datetime comment '创建时间';

alter table SSO_USER modify column update_user bigint comment '修改人';

alter table SSO_USER modify column update_time datetime comment '修改时间';

/*==============================================================*/
/* Table: SSO_USER_ORG                                          */
/*==============================================================*/
create table SSO_USER_ORG
(
   id                   bigint not null auto_increment,
   user_id              bigint not null,
   user_login_name      varchar(32),
   organization_id      bigint not null,
   organization_name    varchar(32),
   available            bit,
   system_id            bigint not null,
   system_name          varchar(32),
   create_user          bigint,
   create_time          datetime,
   update_user          bigint,
   update_time          datetime,
   primary key (id)
);

alter table SSO_USER_ORG comment '用户-组织机构表';

alter table SSO_USER_ORG modify column id bigint comment 'id';

alter table SSO_USER_ORG modify column user_id bigint comment '用户Id';

alter table SSO_USER_ORG modify column user_login_name varchar(32) comment '用户登录名';

alter table SSO_USER_ORG modify column organization_id bigint comment '组织机构Id';

alter table SSO_USER_ORG modify column organization_name varchar(32) comment '组织机构名称';

alter table SSO_USER_ORG modify column available bit comment '是否可用';

alter table SSO_USER_ORG modify column system_id bigint comment '系统Id';

alter table SSO_USER_ORG modify column system_name varchar(32) comment '系统名称';

alter table SSO_USER_ORG modify column create_user bigint comment '创建人';

alter table SSO_USER_ORG modify column create_time datetime comment '创建时间';

alter table SSO_USER_ORG modify column update_user bigint comment '修改人';

alter table SSO_USER_ORG modify column update_time datetime comment '修改时间';

/*==============================================================*/
/* Table: SSO_USER_ROLE                                         */
/*==============================================================*/
create table SSO_USER_ROLE
(
   id                   bigint not null auto_increment,
   user_id              bigint not null,
   user_login_name      varchar(32),
   role_id              bigint not null,
   role_name            varchar(32),
   system_id            bigint not null,
   system_name          varchar(32),
   available            bit,
   create_user          bigint,
   create_time          datetime,
   update_user          varchar(32),
   update_time          datetime,
   primary key (id)
);

alter table SSO_USER_ROLE comment '用户-角色表';

alter table SSO_USER_ROLE modify column id bigint comment 'id';

alter table SSO_USER_ROLE modify column user_id bigint comment '用户Id';

alter table SSO_USER_ROLE modify column user_login_name varchar(32) comment '用户登录名';

alter table SSO_USER_ROLE modify column role_id bigint comment '角色Id';

alter table SSO_USER_ROLE modify column role_name varchar(32) comment '角色名';

alter table SSO_USER_ROLE modify column system_id bigint comment '系统Id';

alter table SSO_USER_ROLE modify column system_name varchar(32) comment '系统名称';

alter table SSO_USER_ROLE modify column available bit comment '是否可用';

alter table SSO_USER_ROLE modify column create_user bigint comment '创建人';

alter table SSO_USER_ROLE modify column create_time datetime comment '创建时间';

alter table SSO_USER_ROLE modify column update_user varchar(32) comment '修改人';

alter table SSO_USER_ROLE modify column update_time datetime comment '修改时间';

/*==============================================================*/
/* Table: SSO_USER_SYSTEM                                       */
/*==============================================================*/
create table SSO_USER_SYSTEM
(
   id                   bigint not null auto_increment,
   user_id              bigint not null,
   user_login_name      varchar(32),
   system_id            bigint not null,
   system_name          varchar(32),
   create_user          bigint,
   create_time          datetime,
   update_user          bigint,
   update_time          datetime,
   primary key (id)
);

alter table SSO_USER_SYSTEM comment '用户-系统表';

alter table SSO_USER_SYSTEM modify column id bigint comment 'id';

alter table SSO_USER_SYSTEM modify column user_id bigint comment '用户Id';

alter table SSO_USER_SYSTEM modify column user_login_name varchar(32) comment '用户登录名';

alter table SSO_USER_SYSTEM modify column system_id bigint comment '系统id';

alter table SSO_USER_SYSTEM modify column system_name varchar(32) comment '系统名称';

alter table SSO_USER_SYSTEM modify column create_user bigint comment '创建人';

alter table SSO_USER_SYSTEM modify column create_time datetime comment '创建时间';

alter table SSO_USER_SYSTEM modify column update_user bigint comment '修改人';

alter table SSO_USER_SYSTEM modify column update_time datetime comment '修改时间';

/*==============================================================*/
/* Table: SYS_AREA                                              */
/*==============================================================*/
create table SYS_AREA
(
   id                   bigint not null auto_increment,
   parent_id            bigint,
   parent_ids           varchar(2000),
   name                 varchar(50),
   sort                 int,
   code                 varchar(20),
   type                 char(1),
   remarks              varchar(2000),
   enable_flag          bit,
   create_user          bigint,
   create_time          datetime,
   update_user          bigint,
   update_time          datetime,
   primary key (id)
);

alter table SYS_AREA comment '区域表';

alter table SYS_AREA modify column id bigint comment '主键ID';

alter table SYS_AREA modify column parent_id bigint comment '父级编号';

alter table SYS_AREA modify column parent_ids varchar(2000) comment '所有父级编号';

alter table SYS_AREA modify column name varchar(50) comment '名称';

alter table SYS_AREA modify column sort int comment '排序';

alter table SYS_AREA modify column code varchar(20) comment '区域编码';

alter table SYS_AREA modify column type char(1) comment '区域类型';

alter table SYS_AREA modify column remarks varchar(2000) comment '备注信息';

alter table SYS_AREA modify column enable_flag bit comment '可用标示';

alter table SYS_AREA modify column create_user bigint comment '创建人';

alter table SYS_AREA modify column create_time datetime comment '创建时间';

alter table SYS_AREA modify column update_user bigint comment '更新人';

alter table SYS_AREA modify column update_time datetime comment '更新时间';

/*==============================================================*/
/* Table: SYS_JOB                                               */
/*==============================================================*/
create table SYS_JOB
(
   id                   bigint not null auto_increment,
   name                 varchar(50),
   status               bit,
   cron_expression      varchar(50),
   class_name           varchar(100),
   detail_explain       varchar(300),
   create_user          bigint,
   create_time          datetime,
   update_user          bigint,
   update_time          datetime,
   primary key (id)
);

alter table SYS_JOB comment 'quartz调度表';

alter table SYS_JOB modify column id bigint comment 'id';

alter table SYS_JOB modify column name varchar(50) comment '任务名称';

alter table SYS_JOB modify column status bit comment '任务状态';

alter table SYS_JOB modify column cron_expression varchar(50) comment '任务运行时间表达式';

alter table SYS_JOB modify column class_name varchar(100) comment '对应任务类';

alter table SYS_JOB modify column detail_explain varchar(300) comment '任务描述';

alter table SYS_JOB modify column create_user bigint comment '创建人';

alter table SYS_JOB modify column create_time datetime comment '创建时间';

alter table SYS_JOB modify column update_user bigint comment '修改人';

alter table SYS_JOB modify column update_time datetime comment '修改时间';

/*==============================================================*/
/* Table: SYS_UNIVERSAL_LOG                                     */
/*==============================================================*/
create table SYS_UNIVERSAL_LOG
(
   id                   bigint not null auto_increment,
   module_name          varchar(32) not null,
   feature_name         varchar(32),
   feature_path         varchar(100) not null,
   request_param        text not null,
   request_result       varchar(10) not null,
   request_user         varchar(32) not null,
   request_time         datetime not null,
   request_pc_name      varchar(40),
   request_ip           varchar(32),
   extends1             varchar(30),
   extends2             varchar(30),
   extends3             varchar(30),
   extends4             varchar(30),
   extends5             varchar(30),
   primary key (id)
);

alter table SYS_UNIVERSAL_LOG comment '统一日志记录表';

alter table SYS_UNIVERSAL_LOG modify column id bigint comment 'id';

alter table SYS_UNIVERSAL_LOG modify column module_name varchar(32) comment '访问模块名';

alter table SYS_UNIVERSAL_LOG modify column feature_name varchar(32) comment '访问功能名';

alter table SYS_UNIVERSAL_LOG modify column feature_path varchar(100) comment '访问路径';

alter table SYS_UNIVERSAL_LOG modify column request_param text comment '请求所带参数';

alter table SYS_UNIVERSAL_LOG modify column request_result varchar(10) comment '响应结果';

alter table SYS_UNIVERSAL_LOG modify column request_user varchar(32) comment '请求人';

alter table SYS_UNIVERSAL_LOG modify column request_time datetime comment '请求时间';

alter table SYS_UNIVERSAL_LOG modify column request_pc_name varchar(40) comment '客户端PC名称';

alter table SYS_UNIVERSAL_LOG modify column request_ip varchar(32) comment '客户端IP';

alter table SYS_UNIVERSAL_LOG modify column extends1 varchar(30) comment '拓展字段1';

alter table SYS_UNIVERSAL_LOG modify column extends2 varchar(30) comment '拓展字段2';

alter table SYS_UNIVERSAL_LOG modify column extends3 varchar(30) comment '拓展字段3';

alter table SYS_UNIVERSAL_LOG modify column extends4 varchar(30) comment '拓展字段4';

alter table SYS_UNIVERSAL_LOG modify column extends5 varchar(30) comment '拓展字段5';

/*==============================================================*/
/* Table: SYS_USER                                              */
/*==============================================================*/
create table SYS_USER
(
   ID                   bigint not null auto_increment,
   login_name           varchar (32) not null,
   real_name            varchar (32),
   password             varchar (64),
   sex                  INT not null,
   birthday             varchar(20) not null,
   phone                varchar (32) not null,
   email                varchar(50),
   last_login           datetime,
   last_ip              varchar(32),
   available            bit,
   create_user          bigint,
   create_time          datetime,
   update_user          bigint,
   update_time          datetime,
   primary key (ID)
);

alter table SYS_USER comment '系统用户表';

alter table SYS_USER modify column ID bigint comment '主键ID';

alter table SYS_USER modify column login_name varchar (32) comment '员工姓名';

alter table SYS_USER modify column real_name varchar (32) comment '员工真实名称';

alter table SYS_USER modify column password varchar (64) comment '密码';

alter table SYS_USER modify column sex INT comment '性别(0女,1男,2未知)';

alter table SYS_USER modify column birthday varchar(20) comment '生日';

alter table SYS_USER modify column phone varchar (32) comment '电话号码';

alter table SYS_USER modify column email varchar(50) comment '电子邮件';

alter table SYS_USER modify column last_login datetime comment '上次登录时间';

alter table SYS_USER modify column last_ip varchar(32) comment '上次登录IP地址';

alter table SYS_USER modify column available bit comment '是否可用';

alter table SYS_USER modify column create_user bigint comment '创建人';

alter table SYS_USER modify column create_time datetime comment '创建时间';

alter table SYS_USER modify column update_user bigint comment '修改人';

alter table SYS_USER modify column update_time datetime comment '修改时间';

