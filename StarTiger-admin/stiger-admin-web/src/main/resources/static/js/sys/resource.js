let resourceDetailModal = function () {
    let $modal = $("#resource-detail-modal");
    let $form = $modal.find("#resource-detail-form");
    let $saveBtn = $form.find(".save-resource-detail-modal");
    let $closeBtn = $form.find(".close-resource-detail-modal");
    let $resFlowInput = $form.find("input[name='resFlow']");
    let $resNameInput = $form.find("input[name='resName']");
    let $resPathInput = $form.find("input[name='resPath']");
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
                let _this = this;
                $modal.off("hide.bs.modal");
                $modal.on("hide.bs.modal", function () {
                    $form.clearForm();
                    $resFlowInput.val("");
                });
                $saveBtn.on("click", function () {
                    let methodType = _this.isCreate() ? "post" : "put";
                    let options = {
                        type: methodType,
                        url: "/sys/resource",
                        data: $form.formSerialize(),
                        dataType: "json",
                        _success: function (data) {
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

let SysRes = function () {

    let $resourceSearchForm = $("#resource-search-form");
    let $resourcesContainer = $("#resources-container");

    let bindRecordEvents = function () {
        $resourcesContainer.find(".modify-resource-btn").on("click", function () {
            let $this = $(this);
            let resFlow = $this.parent("td").data("resFlow");
            let options = {
                type: "get",
                url: "/sys/resource/" + resFlow,
                dataType: "json",
                _success: function (resp) {
                    resourceDetailModal.show(resp.data);
                }
            };
            HttpUtil.ajaxReq(options);
        });
        $resourcesContainer.find(".delete-resource-btn").on("click", function () {
            let $this = $(this);
            let resFlow = $this.parent("td").data("resFlow");
            let resName = $this.parent("td").data("resName");
            ConfirmModal.create({
                bodyHtml: "删除&nbsp;" + resName,
                onConfirm: function () {
                    let options = {
                        type: "delete",
                        url: "/sys/resource/" + resFlow,
                        dataType: "json",
                        _success: function (data) {
                            console.log(data);
                            searchResources(1);
                            Proj.showToasts("success", "删除成功");
                        }
                    };
                    HttpUtil.ajaxReq(options);
                }
            });
        });
    };

    let searchResources = function (page) {
        let url = "/sys/resource/list";
        if (page) {
            url += ("?page=" + page);
        }
        let options = {
            url: url,
            dataType: "html",
            _success: function (data) {
                $resourcesContainer.html(data);
                bindRecordEvents();
            }
        };
        let queryString = $resourceSearchForm.formSerialize();
        if (queryString) {
            options.data = queryString;
        }
        HttpUtil.ajaxReq(options);
    };

    let bindEvent = function () {
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