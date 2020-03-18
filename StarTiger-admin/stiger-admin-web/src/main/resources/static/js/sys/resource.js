var resourceDetailModal = function () {
    var $modal = $("#resource-detail-modal");
    var $form = $modal.find("#resource-detail-form");
    var $saveBtn = $form.find(".save-resource-detail-modal");
    var $closeBtn = $form.find(".close-resource-detail-modal");
    var $resFlowInput = $form.find("input[name='resFlow']");
    var $resNameInput = $form.find("input[name='resName']");
    var $resPathInput = $form.find("input[name='resPath']");
    return {
        loadDetail: function (sysResource) {
            $resFlowInput.val(sysResource.resFlow);
            $resNameInput.val(sysResource.resName);
            $resPathInput.val(sysResource.resPath);
        },
        isCreate: function () {
            return !$resFlowInput.val() || $resFlowInput.val() === "";
        },
        isModify: function () {
            return $resFlowInput.val() && $resFlowInput.val() !== "";
        },
        show: function () {
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

var SysRes = function () {

    var $resourceSearchForm = $("#resource-search-form");
    var $resourcesContainer = $("#resources-container");
    var $deleteResourceConfirmModal = $("#delete-resource-confirm-modal");

    var deleteResource = function (resFlow) {
        var options = {
            type: "delete",
            url: "/sys/resource/" + resFlow,
            callbackFunc: function (data) {
                if (Proj.isDev()) {
                    console.log(data);
                }
                searchResources(1);
                $deleteResourceConfirmModal.modal("hide");
            }
        };
        HttpUtil.ajaxReq(options);
    };

    var readyToDeleteResource = function (resFlow, resName) {
        $deleteResourceConfirmModal.find(".modal-body strong").html(resName);
        $deleteResourceConfirmModal.find(".confirm-delete-resource-confirm-modal").one("click", function () {
            deleteResource(resFlow);
        });
        $deleteResourceConfirmModal.modal("show");
    };

    var bindRecordEvents = function () {
        $resourcesContainer.find(".modify-resource-btn").on("click", function () {
            var $this = $(this);
            var resFlow = $this.parent("td").data("resFlow");
            var options = {
                type: "get",
                url: "/sys/resource/" + resFlow,
                callbackFunc: function (resp) {
                    resourceDetailModal.loadDetail(resp.data);
                    resourceDetailModal.show();
                }
            };
            HttpUtil.ajaxReq(options);
        });
        $resourcesContainer.find(".delete-resource-btn").on("click", function () {
            var $this = $(this);
            var resFlow = $this.parent("td").data("resFlow");
            var resName = $this.parent("td").data("resName");
            readyToDeleteResource(resFlow, resName);
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
                $resourcesContainer.find("tbody").html(data);
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
        $resourceSearchForm.find(".search-button").on("click", function () {
            searchResources(1);
        });
        /* ========================< create >======================== */
        $resourceSearchForm.find(".create-button").on("click", function () {
            resourceDetailModal.show();
        });
        /* ========================< modify >======================== */
        /* ========================< delete >======================== */
        $deleteResourceConfirmModal.find(".close-delete-resource-confirm-modal").on("click", function () {
            $deleteResourceConfirmModal.modal("hide");
        });
        $deleteResourceConfirmModal.on("hide.bs.modal", function () {
            $deleteResourceConfirmModal.find(".confirm-delete-resource-confirm-modal").off();
        });
    };

    return {
        init: function () {
            bindEvent();
            resourceDetailModal.init();
            searchResources(1);
        },
        searchResources: searchResources
    }
}();
$(function () {
    SysRes.init();
});