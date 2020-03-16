drop procedure if exists stigeradmin.init;
create procedure stigeradmin.init()
begin
    set @root_flow = '00000000000000000000000000000000';
    set @super_role_flow = '00000000000000000000000000000000';
    set @super_resource_flow = '00000000000000000000000000000000';

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
    values (replace(uuid(), '-', ''), @super_role_flow, @super_resource_flow);

    insert into sys_user (user_flow, username, encode_password)
    values (@root_flow, 'root', '{noop}123456');

    insert into sys_user_role (user_role_flow, user_flow, role_flow)
    values (replace(uuid(), '-', ''), @root_flow, @super_role_flow);

    set @menu_flow_xtgl = replace(uuid(), '-', '');
    set @menu_flow_zzjg = replace(uuid(), '-', '');
    set @menu_flow_zzjg_jggl = replace(uuid(), '-', '');
    set @menu_flow_zzjg_bmgl = replace(uuid(), '-', '');
    set @menu_flow_zzjg_yhgl = replace(uuid(), '-', '');
    set @menu_flow_qxgl = replace(uuid(), '-', '');
    set @menu_flow_qxgl_zygl = replace(uuid(), '-', '');
    set @menu_flow_qxgl_jsgl = replace(uuid(), '-', '');
    set @menu_flow_gngl = replace(uuid(), '-', '');
    set @menu_flow_gngl_cdgl = replace(uuid(), '-', '');
    set @menu_order = 88;

    truncate table sys_menu;
    truncate table sys_menu_resource;

    insert into sys_menu (menu_flow, parent_menu_flow, menu_level, menu_code, menu_name, menu_order)
    values (@menu_flow_xtgl, null, 1, 'xtgl', '系统管理', @menu_order);

    set @menu_order = @menu_order + 1;

    insert into sys_menu (menu_flow, parent_menu_flow, menu_level, menu_code, menu_name, menu_order)
    values (@menu_flow_zzjg, null, 1, 'zzjg', '组织架构', @menu_order);

    set @menu_order = @menu_order + 1;

    insert into sys_menu (menu_flow, parent_menu_flow, menu_level, menu_code, menu_name, menu_path, menu_order)
    values (@menu_flow_zzjg_jggl, @menu_flow_zzjg, 2, 'zzjg-jggl', '机构管理', '/org/main', @menu_order);

    set @menu_order = @menu_order + 1;

    insert into sys_menu (menu_flow, parent_menu_flow, menu_level, menu_code, menu_name, menu_path, menu_order)
    values (@menu_flow_zzjg_bmgl, @menu_flow_zzjg, 2, 'zzjg-bmgl', '部门管理', '/dept/main', @menu_order);

    set @menu_order = @menu_order + 1;

    insert into sys_menu (menu_flow, parent_menu_flow, menu_level, menu_code, menu_name, menu_path, menu_order)
    values (@menu_flow_zzjg_yhgl, @menu_flow_zzjg, 2, 'zzjg-yhgl', '用户管理', '/user/main', @menu_order);

    set @menu_order = @menu_order + 1;

    insert into sys_menu (menu_flow, parent_menu_flow, menu_level, menu_code, menu_name, menu_order)
    values (@menu_flow_qxgl, null, 1, 'qxgl', '权限管理', @menu_order);

    set @menu_order = @menu_order + 1;

    insert into sys_menu (menu_flow, parent_menu_flow, menu_level, menu_code, menu_name, menu_path, menu_order)
    values (@menu_flow_qxgl_zygl, @menu_flow_qxgl, 2, 'qxgl-zygl', '资源管理', '/resource/main', @menu_order);

    set @menu_order = @menu_order + 1;

    insert into sys_menu (menu_flow, parent_menu_flow, menu_level, menu_code, menu_name, menu_path, menu_order)
    values (@menu_flow_qxgl_jsgl, @menu_flow_qxgl, 2, 'qxgl-jsgl', '角色管理', '/role/main', @menu_order);

    set @menu_order = @menu_order + 1;

    insert into sys_menu (menu_flow, parent_menu_flow, menu_level, menu_code, menu_name, menu_order)
    values (@menu_flow_gngl, null, 1, 'gngl', '功能管理', @menu_order);

    set @menu_order = @menu_order + 1;

    insert into sys_menu (menu_flow, parent_menu_flow, menu_level, menu_code, menu_name, menu_path, menu_order)
    values (@menu_flow_gngl_cdgl, @menu_flow_gngl, 2, 'gngl-cdgl', '菜单管理', '/menu/main', @menu_order);

    set @menu_order = @menu_order + 1;

end;
call stigeradmin.init();