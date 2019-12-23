DROP TABLE IF EXISTS sys_user;

CREATE TABLE sys_user
(
    user_id         varchar(50) NOT NULL,
    username        varchar(50),
    encode_password varchar(500),
    PRIMARY KEY (user_id)
);

CREATE TABLE sys_role
(
    role_id   varchar(50),
    role_code varchar(50),
    role_name varchar(50),
    PRIMARY KEY (role_id)
);

CREATE TABLE sys_user_role
(
    user_role_id varchar(50) NOT NULL,
    user_id      varchar(50),
    role_id      varchar(50),
    role_code    varchar(50),
    PRIMARY KEY (user_role_id)
);

CREATE TABLE sys_resource
(
    res_id   varchar(50),
    res_code varchar(50),
    res_name varchar(50),
    res_path varchar(500),
    PRIMARY KEY (res_id)
);

CREATE TABLE sys_role_resource
(
    role_res_id varchar(50) not null,
    role_id     varchar(50),
    role_code   varchar(50),
    res_id      varchar(50),
    res_code    varchar(50),
    PRIMARY KEY (role_res_id)
);
