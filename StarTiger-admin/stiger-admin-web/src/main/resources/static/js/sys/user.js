let UserDetailModal = function () {
    let $modal = $("#user-detail-modal");
    let $form = $modal.find("#role-detail-form");
    let $saveBtn = $modal.find(".save-detail-btn");
    let $userFlow = $modal.find("[name='userFlow']");
    let $username = $form.find("[name='username']");
    let $shortName = $form.find("[name='shortName']");
    let $roleFlows = $form.find("[name='roleFlows']");

    function loadAllRoles() {
        let options = {
            type: "get",
            url: Proj.getContextPath() + "/sys/role/all",
            dataType: "json",
            _success: function (resp, _options) {
                $roleFlows.empty();
                $.each(resp.data, function (index, role) {
                    let $dom = $("<option/>");
                    $dom.val(role.roleFlow);
                    $dom.html("[" + role.roleCode + "]" + role.roleName);
                    $roleFlows.append($dom);
                    $roleFlows.bootstrapDualListbox("refresh");
                });
            }
        };
        return HttpUtil.ajaxReq(options);
    }

    function readUserInfo(userFlow) {
        let options = {
            type: "get",
            url: Proj.getContextPath() + "/sys/user/" + userFlow + "/info",
            dataType: "json",
            _success: function (resp, _options) {
                let userInfo = resp.data;
                $userFlow.val(userInfo.user.userFlow);
                $username.val(userInfo.user.username);
                $shortName.val(userInfo.user.shortName);
                $.each(userInfo.roles, function (index, userRole) {
                    let selectOption = $roleFlows.find("option[value='" + userRole.roleFlow + "']");
                    if (selectOption.length === 1) {
                        selectOption.prop("selected", true);
                    }
                });
                $roleFlows.bootstrapDualListbox("refresh");
            }
        };
        return HttpUtil.ajaxReq(options);
    }

    function renderRoleFlowsSelect() {
        $roleFlows.bootstrapDualListbox({
            filterTextClear: "清除筛选",
            filterPlaceHolder: "筛选",
            moveAllLabel: "添加所有",
            removeAllLabel: "移除所有",
            selectedListLabel: "已获得角色",
            nonSelectedListLabel: "未获得角色",
            selectorMinimalHeight: 300,
            infoText: "共 {0}",
            infoTextFiltered: "<span class='label label-warning'>筛选后</span> {0} 共 {1}",
            infoTextEmpty: "空"
        });
    }

    function clearForm() {
        $username.val("");
        $shortName.val("");
        $roleFlows.find("option").prop("selected", false);
        $roleFlows.bootstrapDualListbox("refresh");
    }

    function showModal() {
        $modal.modal("show");
    }

    function hideModal() {
        $modal.modal("hide");
    }

    function show(userFlow) {
        loadAllRoles().done(function () {
            if (userFlow && userFlow !== "") {
                readUserInfo(userFlow).done(showModal);
            } else {
                showModal();
            }
        });
    }

    function isCreate() {
        return !$userFlow.val() || $userFlow.val() === "";
    }

    return {
        init: function () {
            renderRoleFlowsSelect();
            $modal.on("hide.bs.modal", function () {
                clearForm();
            });
            $saveBtn.on("click", function () {
                let dataStr = $form.find(".simple-fields").fieldSerialize();
                if (Proj.isDev()) {
                    console.log(dataStr);
                }
                let methodType = isCreate() ? "post" : "put";
                let options = {
                    type: methodType,
                    url: "/sys/user",
                    data: dataStr,
                    dataType: "json",
                    _success: function (data) {
                        if (Proj.isDev()) {
                            console.log(data);
                        }
                        SysUser.searchUsers(1);
                        hideModal();
                        Proj.showToasts("success", "保存成功");
                    }
                };
                HttpUtil.ajaxReq(options);
            });
        },
        show: show
    }
}();
let SysUser = function () {

    let $userSearchForm = $("#user-search-form");
    let $searchUserRole = $userSearchForm.find("#search-user-role");

    let $usersContainer = $("#users-container");

    function handleRecords() {
        $usersContainer.find(".user-locked-state").each(function () {
            let $dom = $(this);
            let checkedState = $dom.prop('checked');
            $dom.data("cacheState", checkedState);
            $dom.bootstrapSwitch({
                state: checkedState,
                offText: "已锁定",
                onText: "未锁定",
                offColor: "danger",
                onColor: "success",
                onSwitchChange: function (event, state) {
                    let cacheState = $dom.data("cacheState");
                    if (state !== cacheState) {
                        let userFlow = $dom.parents("tr").data("userFlow");
                        let options = {
                            type: "put",
                            url: "/sys/user/" + userFlow + "/lock",
                            data: "unlocked=" + state,
                            dataType: "json",
                            _success: function (resp) {
                                $dom.data("cacheState", state);
                                search();
                            },
                            _error: function () {
                                $dom.bootstrapSwitch("state", !state);
                            }
                        };
                        HttpUtil.ajaxReq(options);
                    }
                }
            });
        });
        $usersContainer.find(".modify-user-btn").on("click", function () {
            let $dom = $(this);
            let userFlow = $dom.parents("tr").data("userFlow");
            UserDetailModal.show(userFlow);
        });
        $usersContainer.find(".delete-user-btn").on("click", function () {
            let $this = $(this);
            let userFlow = $this.parents("tr").data("userFlow");
            let username = $this.parents("tr").data("username");
            let shortName = $this.parents("tr").data("shortName");
            ConfirmModal.create({
                showRecordInfo: "删除&nbsp;[" + username + "]" + shortName,
                onConfirm: function () {
                    let options = {
                        type: "delete",
                        url: "/sys/user/" + userFlow,
                        dataType: "json",
                        _success: function (data) {
                            console.log(data);
                            search(1);
                            Proj.showToasts("success", "删除成功");
                        }
                    };
                    HttpUtil.ajaxReq(options);
                }
            });
        });
        $usersContainer.find(".reset-user-btn").on("click", function () {
            let $this = $(this);
            let userFlow = $this.parents("tr").data("userFlow");
            let username = $this.parents("tr").data("username");
            let shortName = $this.parents("tr").data("shortName");
            ConfirmModal.create({
                bodyHtml: "<div class='modal-body'>是否重置&nbsp;<strong class='h4 text-danger'>[" + username + "]" + shortName + "</strong>&nbsp;的登录密码？</div>",
                onConfirm: function () {
                    let options = {
                        type: "put",
                        url: "/sys/user/" + userFlow + "/resetPwd",
                        dataType: "json",
                        _success: function (data) {
                            console.log(data);
                            Proj.showToasts("success", "重置密码成功");
                        }
                    };
                    HttpUtil.ajaxReq(options);
                }
            });
        });
    }

    function search(page) {
        var url = "/sys/user/list";
        if (page) {
            url += ("?page=" + page);
        }
        let options = {
            url: url,
            dataType: "html",
            _success: function (data) {
                $usersContainer.html(data);
                handleRecords();
            }
        };
        let queryString = $userSearchForm.formSerialize();
        if (Proj.isDev()) {
            console.log(queryString);
        }
        if (queryString) {
            options.data = queryString;
        }
        HttpUtil.ajaxReq(options);
    }

    function render() {
        $searchUserRole.select2({
            language: "zh-CN",
            theme: "bootstrap4"
        });
    }

    function bindEvent() {
        $("#search-button").on("click", function () {
            search();
        });
        $("#create-button").on("click", function () {
            UserDetailModal.show();
        });
    }

    return {
        init: function () {
            render();
            bindEvent();
            search();
        },
        searchUsers: search
    }
}();
$(function () {
    SysUser.init();
    UserDetailModal.init();
});