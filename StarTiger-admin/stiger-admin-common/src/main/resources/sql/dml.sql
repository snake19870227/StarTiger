insert into sys_user (user_id, username, encode_password)
values ('snake', 'snake', '{noop}123456');
insert into sys_user (user_id, username, encode_password)
values ('admin', 'admin', '{noop}123456');

insert into sys_user_role (user_role_id, user_id, role_id, role_code)
values ('UR1', 'admin', 'Admin', 'ROLE_Admin');
insert into sys_user_role (user_role_id, user_id, role_id, role_code)
values ('UR2', 'snake', 'Admin', 'ROLE_Admin');
insert into sys_user_role (user_role_id, user_id, role_id, role_code)
values ('UR3', 'admin', 'Admin2', 'ROLE_Admin2');

--insert into sys_resource (res_id, res_code, res_name, res_path)
--values ('index', 'index', '入口', '/');
--insert into sys_resource (res_id, res_code, res_name, res_path)
--values ('main', 'main', '主页', '/main');
insert into sys_resource (res_id, res_code, res_name, res_path)
values ('sayHello', 'sayHello', '打招呼', '/sayHello');
insert into sys_resource (res_id, res_code, res_name, res_path)
values ('RS1', 'RS1', '资源1', '/res1');
insert into sys_resource (res_id, res_code, res_name, res_path)
values ('RS2', 'RS2', '资源2', '/res2');
insert into sys_resource (res_id, res_code, res_name, res_path)
values ('RS3', 'RS3', '资源3', '/res3');

--insert into sys_role_resource (role_res_id, role_id, role_code, res_id, res_code)
--values ('RR4', 'R1', 'ROLE_Admin', 'index', 'index');
--insert into sys_role_resource (role_res_id, role_id, role_code, res_id, res_code)
--values ('RR5', 'R1', 'ROLE_Admin', 'main', 'main');
insert into sys_role_resource (role_res_id, role_id, role_code, res_id, res_code)
values ('RR6', 'Admin', 'ROLE_Admin', 'sayHello', 'sayHello');
insert into sys_role_resource (role_res_id, role_id, role_code, res_id, res_code)
values ('RR1', 'Admin', 'ROLE_Admin', 'RS1', 'RS1');
insert into sys_role_resource (role_res_id, role_id, role_code, res_id, res_code)
values ('RR2', 'Admin2', 'ROLE_Admin2', 'RS1', 'RS1');
insert into sys_role_resource (role_res_id, role_id, role_code, res_id, res_code)
values ('RR3', 'Admin2', 'ROLE_Admin2', 'RS2', 'RS2');