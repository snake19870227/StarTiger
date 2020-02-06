insert into `StigerMallAccount`.mall_account
values (replace(uuid(),'-',''), 'stiger', '{noop}123456');
commit;

insert into `StigerMallGoods`.mall_goods
values (replace(uuid(),'-',''), '商品1', 10.5);
insert into `StigerMallGoods`.mall_goods
values (replace(uuid(),'-',''), '商品2', 14.5);
insert into `StigerMallGoods`.mall_goods
values (replace(uuid(),'-',''), '商品3', 5.5);
insert into `StigerMallGoods`.mall_goods
values (replace(uuid(),'-',''), '商品4', 10.3);
insert into `StigerMallGoods`.mall_goods
values (replace(uuid(),'-',''), '商品5', 1.8);
commit;