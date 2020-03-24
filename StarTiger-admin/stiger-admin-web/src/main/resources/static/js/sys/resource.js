let resourceDetailModal = function () {
    let $modal = $("#resource-detail-modal");
    let $form = $modal.find("#resource-detail-form");
    let $saveBtn = $form.find(".save-resource-detail-modal");
    let $resFlowInput = $form.find("input[name='resFlow']");
    let $resNameInput = $form.find("input[name='resName']");
    let $resPathInput = $form.find("input[name='resPath']");

    let validator = createFormValidator();

    function clearForm() {
        $form.clearForm();
        $resFlowInput.val("");
        validator.resetForm();
    }

    function isCreate() {
        return !$resFlowInput.val() || $resFlowInput.val() === "";
    }

    function showModal(sysResource) {
        if (sysResource) {
            $resFlowInput.val(sysResource.resFlow);
            $resNameInput.val(sysResource.resName);
            $resPathInput.val(sysResource.resPath);
        }
        $modal.modal("show");
    }

    function hideModal() {
        $modal.modal("hide");
    }

    function createFormValidator() {
        return $form.validate({
            rules: {
                resName: {
                    required: true,
                    maxlength: 20
                },
                resPath: {
                    required: true
                }
            },
            errorElement: 'span',
            errorPlacement: function (error, element) {
                error.addClass('invalid-feedback');
                element.closest('.form-group').append(error);
            },
            highlight: function (element, errorClass, validClass) {
                $(element).addClass('is-invalid');
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).removeClass('is-invalid');
            }
        });
    }

    return {
        init: function () {
            $modal.on("hide.bs.modal", function () {
                clearForm();
            });
            $saveBtn.on("click", function () {
                if ($form.valid()) {
                    let methodType = isCreate() ? "post" : "put";
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
                            hideModal();
                            Proj.showToasts("success", "保存成功");
                        }
                    };
                    HttpUtil.ajaxReq(options);
                }
            });
        },
        show: showModal
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