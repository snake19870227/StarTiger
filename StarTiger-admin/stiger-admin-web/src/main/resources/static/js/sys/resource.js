var SysRes = function () {

    var $resourceSearchForm = $("#resource-search-form");
    var $resourcesContainer = $("#resources-container");
    var $createResourceModal = $("#create-resource-modal");
    var $createResourceForm = $createResourceModal.find("#create-resource-form");
    var $deleteResourceConfirmModal = $("#delete-resource-confirm-modal");

    var createResource = function () {
        var options = {
            type: "post",
            url: "/sys/resource",
            data: $createResourceForm.formSerialize(),
            callbackFunc: function (data) {
                if (Proj.isDev()) {
                    console.log(data);
                }
                searchResources(1);
                $createResourceModal.modal("hide");
            }
        };
        HttpUtil.ajaxReq(options);
    };

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
        $resourceSearchForm.find(".create-button").on("click", function () {
            $createResourceModal.modal("show");
        });
        /* ========================< create >======================== */
        $createResourceModal.on("hide.bs.modal", function () {
            $createResourceForm.clearForm();
        });
        $createResourceForm.find(".save-create-resource-modal").on("click", function () {
            createResource();
        });
        $createResourceForm.find(".close-create-resource-modal").on("click", function () {
            $createResourceModal.modal("hide");
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
            searchResources(1);
        }
    }
}();
$(function () {
    SysRes.init();
});