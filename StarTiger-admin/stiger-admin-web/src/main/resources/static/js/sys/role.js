let RoleDetailModal = function () {
    let $modal = $("#role-detail-modal");
    let $closeBtn = $modal.find(".close-detail-modal-btn");
    let $saveBtn = $modal.find(".save-detail-btn");
    let $form = $modal.find("#role-detail-form");
    let $roleFlow = $form.find("[name='roleFlow']");
    let $roleCode = $form.find("[name='roleCode']");
    let $roleName = $form.find("[name='roleName']");
    let $resFlows = $form.find("[name='resFlows']");

    let resFlowsBox = DualListbox.create($resFlows);

    function isCreate() {
        return !$roleFlow.val() || $roleFlow.val() === "";
    }

    function clearForm() {
        $form.clearForm();
        $roleFlow.val("");
        resFlowsBox.clear();
    }

    function showModal() {
        $modal.modal("show");
    }

    function hideModal() {
        $modal.modal("hide");
    }

    function loadAllResources() {
        let options = {
            type: "get",
            url: "/sys/resource/all",
            dataType: "json",
            _success: function (resp, _options) {
                let options = [];
                $.each(resp.data, function (index, resource) {
                    options.push({
                        value: resource.resFlow,
                        text: resource.resName + " [" + resource.resPath + "]",
                        selected: false
                    });
                });
                resFlowsBox.setOptions(options);
            }
        };
        return HttpUtil.ajaxReq(options);
    }

    function loadRoleResources(roleFlow) {
        let options = {
            type: "get",
            url: "/sys/role/" + roleFlow + "/info",
            dataType: "json",
            _success: function (resp, _options) {
                $roleFlow.val(resp.data.role.roleFlow);
                $roleCode.val(resp.data.role.roleCode);
                $roleName.val(resp.data.role.roleName);
                $.each(resp.data.resources, function (index, resource) {
                    resFlowsBox.select(resource.resFlow);
                });
            }
        };
        return HttpUtil.ajaxReq(options);
    }

    function show(roleFlow) {
        loadAllResources().done(function () {
            if (roleFlow && roleFlow !== "") {
                loadRoleResources(roleFlow).done(showModal);
            } else {
                showModal();
            }
        });
    }

    return {
        init: function () {
            $modal.on("hide.bs.modal", function () {
                clearForm();
            });
            $saveBtn.on("click", function () {
                let dataStr = $form.formSerialize();
                if (Proj.isDev()) {
                    console.log(dataStr);
                }
                let methodType = isCreate() ? "post" : "put";
                let options = {
                    type: methodType,
                    url: "/sys/role",
                    data: dataStr,
                    dataType: "json",
                    _success: function (data) {
                        if (Proj.isDev()) {
                            console.log(data);
                        }
                        SysRole.searchRoles(1);
                        hide();
                        Proj.showToasts("success", "保存成功");
                    }
                };
                HttpUtil.ajaxReq(options);
            });
        },
        show: show,
        hide: hideModal
    }
}();
var SysRole = function () {

    var $roleSearchForm = $("#role-search-form");
    var $roleContainer = $("#roles-container");

    function bindRecordEvents() {
        $roleContainer.find(".modify-resource-btn").on("click", function () {
            var $this = $(this);
            var roleFlow = $this.parent("td").data("roleFlow");
            RoleDetailModal.show(roleFlow);
        });
        $roleContainer.find(".delete-resource-btn").on("click", function () {
            let $this = $(this);
            let roleFlow = $this.parent("td").data("roleFlow");
            let roleCode = $this.parent("td").data("roleCode");
            let roleName = $this.parent("td").data("roleName");
            ConfirmModal.create({
                showRecordInfo: "删除&nbsp;" + roleCode + "-" + roleName,
                onConfirm: function () {
                    var options = {
                        type: "delete",
                        url: "/sys/role/" + roleFlow,
                        dataType: "json",
                        _success: function (data) {
                            console.log(data);
                            searchRoles(1);
                            Proj.showToasts("success", "删除成功");
                        }
                    };
                    HttpUtil.ajaxReq(options);
                }
            });
        });
    }

    function searchRoles(page) {
        var url = "/sys/role/list";
        if (page) {
            url += ("?page=" + page);
        }
        var options = {
            url: url,
            dataType: "html",
            _success: function (data) {
                $roleContainer.html(data);
                bindRecordEvents();
            }
        };
        var queryString = $roleSearchForm.formSerialize();
        if (queryString) {
            options.data = queryString;
        }
        HttpUtil.ajaxReq(options);
    }

    var bindEvent = function () {
        /* ========================< search >======================== */
        $("#search-button").on("click", function () {
            searchRoles(1);
        });
        /* ========================< create >======================== */
        $("#create-button").on("click", function () {
            RoleDetailModal.show();
        });
    };

    return {
        init: function () {
            bindEvent();
            searchRoles(1);
        },
        searchRoles: searchRoles
    }
}();
$(function () {
    SysRole.init();
    RoleDetailModal.init();
});