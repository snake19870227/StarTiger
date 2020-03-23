var RoleDetailModal = function () {
    var $modal = $("#role-detail-modal");
    var $closeBtn = $modal.find(".close-detail-modal-btn");
    var $saveBtn = $modal.find(".save-detail-btn");
    var $form = $modal.find("#role-detail-form");
    var $roleFlow = $form.find("[name='roleFlow']");
    var $roleCode = $form.find("[name='roleCode']");
    var $roleName = $form.find("[name='roleName']");
    var $allResFlows = $form.find("[name='allResFlows']");
    var $resFlows = $form.find("[name='resFlows']");
    var $addResources = $form.find(".add-resources");
    var $removeResources = $form.find(".remove-resources");
    function isCreate() {
        return !$roleFlow.val() || $roleFlow.val() === "";
    }
    function clearForm() {
        $form.clearForm();
        $roleFlow.val("");
        $resFlows.empty();
        $allResFlows.empty();
    }
    function hide() {
        clearForm();
        $modal.modal("hide");
    }
    function loadDetail(allResources, roleInfo) {
        if (allResources) {
            $.each(allResources, function (index, resource) {
                $allResFlows.append(
                    "<option value='" + resource.resFlow + "'>" + resource.resName + " [" + resource.resPath + "]" + "</option>"
                );
            });
        }
        if (roleInfo) {
            $roleFlow.val(roleInfo.role.roleFlow);
            $roleCode.val(roleInfo.role.roleCode);
            $roleName.val(roleInfo.role.roleName);
            $.each(roleInfo.resources, function (index, resource) {
                var option = $allResFlows.find("option[value='" + resource.resFlow + "']");
                $resFlows.append(option);
            });
        }
    }
    function show(roleFlow) {
        var options = {
            type: "get",
            url: "/sys/resource/all",
            dataType: "json",
            _success: function (resp) {
                if (roleFlow && roleFlow !== "") {
                    HttpUtil.ajaxReq({
                        type: "get",
                        url: "/sys/role/" + roleFlow,
                        dataType: "json",
                        _success: function (roleInfoResp) {
                            loadDetail(resp.data, roleInfoResp.data);
                            $modal.modal("show");
                        }
                    });
                } else {
                    loadDetail(resp.data);
                    $modal.modal("show");
                }
            }
        };
        HttpUtil.ajaxReq(options);
    }
    return {
        init: function () {
            $closeBtn.on("click", function () {
                hide();
            });
            $saveBtn.on("click", function () {
                var resFlowsFieldName = $resFlows.attr("name");
                var dataStr = $form.find(".simple-fields").fieldSerialize();
                $resFlows.find("option").each(function () {
                    var $this = $(this);
                    dataStr += ("&" + resFlowsFieldName + "=" + $this.attr("value"));
                });
                console.log(dataStr);
                var methodType = isCreate() ? "post" : "put";
                var options = {
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
            $addResources.on("click", function () {
                var selectedResFlows = $allResFlows.val();
                $.each(selectedResFlows, function (index, selectedResFlow) {
                    var selectedOption = $allResFlows.find("option[value='" + selectedResFlow + "']");
                    $resFlows.append(selectedOption);
                });
            });
            $removeResources.on("click", function () {
                var selectedResFlows = $resFlows.val();
                $.each(selectedResFlows, function (index, selectedResFlow) {
                    var selectedOption = $resFlows.find("option[value='" + selectedResFlow + "']");
                    $allResFlows.append(selectedOption);
                });
            });
        },
        show: show,
        hide: hide
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