drop table if exists sys_cfg;
create table sys_cfg
(
    cfg_code  varchar(50) not null,
    cfg_value varchar(4000),
    primary key (cfg_code)
);

drop table if exists sys_org;
create table sys_org
(
    org_id   varchar(32) not null,
    org_code varchar(50),
    org_name varchar(500),
    primary key (org_id)
);

drop table if exists sys_user;
create table sys_user
(
    user_id         varchar(32) not null,
    username        varchar(50),
    encode_password varchar(500),
    primary key (user_id)
);

drop table if exists sys_role;
create table sys_role
(
    role_id   varchar(32) not null,
    role_code varchar(50),
    role_name varchar(50),
    primary key (role_id)
);

drop table if exists sys_user_role;
create table sys_user_role
(
    user_role_id varchar(32) not null,
    user_id      varchar(32),
    role_id      varchar(32),
    role_code    varchar(50),
    primary key (user_role_id)
);

drop table if exists sys_resource;
create table sys_resource
(
    res_id   varchar(32) not null,
    res_code varchar(50),
    res_name varchar(50),
    res_path varchar(500),
    primary key (res_id)
);

drop table if exists sys_role_resource;
create table sys_role_resource
(
    role_res_id varchar(32) not null,
    role_id     varchar(32),
    role_code   varchar(50),
    res_id      varchar(32),
    res_code    varchar(50),
    primary key (role_res_id)
);