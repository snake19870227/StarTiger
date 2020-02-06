CREATE SCHEMA `StigerMallAccount` DEFAULT CHARACTER SET utf8mb4 ;

create table `StigerMallAccount`.mall_account
(
    account_id         varchar(32)  not null comment '账户id',
    account_name       varchar(50)  not null comment '账户登录名',
    account_password   varchar(100)          comment '账户登录密码',
    primary key (account_id)
);

CREATE SCHEMA `StigerMallGoods` DEFAULT CHARACTER SET utf8mb4 ;

create table `StigerMallGoods`.mall_goods
(
    goods_id     varchar(32)   not null comment '商品id',
    goods_name   varchar(500)  not null comment '商品名称',
    goods_price  decimal(8, 2) not null comment '商品价格',
    primary key (goods_id)
);