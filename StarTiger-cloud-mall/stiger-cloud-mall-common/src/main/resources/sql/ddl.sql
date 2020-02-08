CREATE SCHEMA `StigerMallAccount` DEFAULT CHARACTER SET utf8mb4 ;

drop table if exists `StigerMallAccount`.mall_account;
create table `StigerMallAccount`.mall_account
(
    account_id         varchar(32)  not null comment '账户id',
    account_name       varchar(50)  not null comment '账户登录名',
    account_password   varchar(100)          comment '账户登录密码',
    primary key (account_id)
);

CREATE SCHEMA `StigerMallGoods` DEFAULT CHARACTER SET utf8mb4 ;

drop table if exists `StigerMallAccount`.mall_goods;
create table `StigerMallGoods`.mall_goods
(
    goods_id     varchar(32)   not null comment '商品id',
    goods_name   varchar(500)  not null comment '商品名称',
    goods_price  decimal(8, 2) not null comment '商品价格',
    primary key (goods_id)
);

CREATE SCHEMA `StigerMallOrder` DEFAULT CHARACTER SET utf8mb4 ;

drop table if exists `StigerMallOrder`.mall_order;
create table `StigerMallOrder`.mall_order
(
    order_id        varchar(32)   not null comment '订单id',
    order_datetime  varchar(14)   not null comment '下单订单时间',
    order_price     decimal(8, 2)          comment '订单总价',
    primary key (order_id)
);

drop table if exists `StigerMallOrder`.mall_order_goods;
create table `StigerMallOrder`.mall_order_goods
(
    order_goods_id  varchar(32)   not null  comment '订单商品id',
    order_id        varchar(32)   not null  comment '订单id',
    goods_id        varchar(32)   not null  comment '商品id',
    goods_name      varchar(500)            comment '商品名称',
    goods_price     decimal(8, 2)           comment '商品单价',
    goods_num       int           default 1 comment '订单商品数量',
    price           decimal(8, 2)           comment '总价',
    primary key (order_goods_id)
);