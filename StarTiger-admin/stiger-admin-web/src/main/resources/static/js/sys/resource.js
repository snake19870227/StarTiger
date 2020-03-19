var resourceDetailModal = function () {
    var $modal = $("#resource-detail-modal");
    var $form = $modal.find("#resource-detail-form");
    var $saveBtn = $form.find(".save-resource-detail-modal");
    var $closeBtn = $form.find(".close-resource-detail-modal");
    var $resFlowInput = $form.find("input[name='resFlow']");
    var $resNameInput = $form.find("input[name='resName']");
    var $resPathInput = $form.find("input[name='resPath']");
    return {
        isCreate: function () {
            return !$resFlowInput.val() || $resFlowInput.val() === "";
        },
        isModify: function () {
            return $resFlowInput.val() && $resFlowInput.val() !== "";
        },
        show: function (sysResource) {
            if (sysResource) {
                $resFlowInput.val(sysResource.resFlow);
                $resNameInput.val(sysResource.resName);
                $resPathInput.val(sysResource.resPath);
            }
            $modal.modal("show");
        },
        hide: function () {
            $modal.modal("hide");
        },
        init: function () {
            try {
                var _this = this;
                $modal.off("hide.bs.modal");
                $modal.on("hide.bs.modal", function () {
                    $form.clearForm();
                    $resFlowInput.val("");
                });
                $saveBtn.on("click", function () {
                    var methodType = _this.isCreate() ? "post" : "put";
                    var options = {
                        type: methodType,
                        url: "/sys/resource",
                        data: $form.formSerialize(),
                        callbackFunc: function (data) {
                            if (Proj.isDev()) {
                                console.log(data);
                            }
                            SysRes.searchResources(1);
                            _this.hide();
                            Proj.showToasts("success", "保存成功");
                        }
                    };
                    HttpUtil.ajaxReq(options);
                });
                $closeBtn.on("click", function () {
                    _this.hide();
                });
            } catch (e) {
                console.error(e);
            }
        }
    }
}();

var resourceDeleteConfirmModal = function () {
    var $modal = $("#delete-resource-confirm-modal");
    function deleteResource(resFlow) {
        var options = {
            type: "delete",
            url: "/sys/resource/" + resFlow,
            callbackFunc: function (data) {
                if (Proj.isDev()) {
                    console.log(data);
                }
                SysRes.searchResources(1);
                $modal.modal("hide");
                Proj.showToasts("success", "删除成功");
            }
        };
        HttpUtil.ajaxReq(options);
    }
    return {
        show: function (resName, resFlow) {
            $modal.find(".modal-body strong").html(resName);
            $modal.find(".confirm-delete-resource-confirm-modal").one("click", function () {
                deleteResource(resFlow);
            });
            $modal.modal("show");
        },
        hide: function () {
            $modal.modal("hide");
        },
        init: function () {
            var _this = this;
            $modal.on("hide.bs.modal", function () {
                $modal.find(".confirm-delete-resource-confirm-modal").off();
            });
            $modal.find(".close-delete-resource-confirm-modal").on("click", function () {
                $modal.modal("hide");
            });
        }
    }
}();

var SysRes = function () {

    var $resourceSearchForm = $("#resource-search-form");
    var $resourcesContainer = $("#resources-container");

    var bindRecordEvents = function () {
        $resourcesContainer.find(".modify-resource-btn").on("click", function () {
            var $this = $(this);
            var resFlow = $this.parent("td").data("resFlow");
            var options = {
                type: "get",
                url: "/sys/resource/" + resFlow,
                callbackFunc: function (resp) {
                    resourceDetailModal.show(resp.data);
                }
            };
            HttpUtil.ajaxReq(options);
        });
        $resourcesContainer.find(".delete-resource-btn").on("click", function () {
            var $this = $(this);
            var resFlow = $this.parent("td").data("resFlow");
            var resName = $this.parent("td").data("resName");
            resourceDeleteConfirmModal.show(resName, resFlow);
        });
    };

    var searchResources = function (page) {
        var url = "/sys/resource/list";
        if (page) {
            url += ("?page=" + page);
        }
        var options = {
            url: url,
            contentType: "text/html",
            callbackFunc: function (data) {
                $resourcesContainer.html(data);
                bindRecordEvents();
            }
        };
        var queryString = $resourceSearchForm.formSerialize();
        if (queryString) {
            options.data = queryString;
        }
        HttpUtil.ajaxReq(options);
    };

    var bindEvent = function () {
        /* ========================< search >======================== */
        $("#search-button").on("click", function () {
            searchResources(1);
        });
        /* ========================< create >======================== */
        $("#create-button").on("click", function () {
            resourceDetailModal.show();
        });
    };

    return {
        init: function () {
            bindEvent();
            searchResources(1);
        },
        searchResources: searchResources
    }
}();
$(function () {
    resourceDetailModal.init();
    SysRes.init();
});