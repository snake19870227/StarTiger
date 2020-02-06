CREATE SCHEMA `StigerMallAccount` DEFAULT CHARACTER SET utf8mb4 ;

create table `StigerMallAccount`.mall_account
(
    account_id         varchar(32)  not null comment '账户id',
    account_name       varchar(50)  not null comment '账户登录名',
    account_password   varchar(100)          comment '账户登录密码',
    primary key (account_id)
);