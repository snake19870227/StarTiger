drop procedure if exists stigeradmin.init;
create procedure stigeradmin.init()
begin
    set @root_flow='00000000000000000000000000000000';
    set @super_role_flow='00000000000000000000000000000000';
    set @super_resource_flow='00000000000000000000000000000000';

    truncate table sys_resource;
    truncate table sys_role;
    truncate table sys_role_resource;
    truncate table sys_user;
    truncate table sys_user_role;

    insert into sys_resource (res_flow, res_name, res_path)
     values (@super_resource_flow, 'ALL', '/**');
    insert into sys_role (role_flow, role_code, role_name)
     values (@super_role_flow, 'super_admin', '超级管理员');
    insert into sys_role_resource (role_res_flow, role_flow, res_flow)
     values (replace(uuid(),'-',''), @super_role_flow, @super_resource_flow);
    insert into sys_user (user_flow, username, encode_password)
     values (@root_flow, 'root', '{noop}123456');
    insert into sys_user_role (user_role_flow, user_flow, role_flow)
     values (replace(uuid(),'-',''), @root_flow, @super_role_flow);
end;
call stigeradmin.init();