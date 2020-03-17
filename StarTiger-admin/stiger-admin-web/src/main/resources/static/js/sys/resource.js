var SysRes = function () {

    var $resourceSearchForm = $("#resource-search-form");
    var $resourcesContainer = $("#resources-container");
    var $createResourceModal = $("#create-resource-modal");
    var $createResourceForm = $createResourceModal.find("#create-resource-form");

    var searchResources = function (page, queryString) {
        var url = "/sys/resource/list";
        if (page) {
            url += ("?page=" + page);
        }
        var options = {
            url: url,
            contentType: "text/html",
            callbackFunc: function (data) {
                $resourcesContainer.find("tbody").html(data);
            }
        };
        if (queryString) {
            options.data = queryString;
        }
        HttpUtil.ajaxReq(options);
    };

    var bindEvent = function () {
        $resourceSearchForm.find(".search-button").on("click", function () {
            var queryString = $resourceSearchForm.formSerialize();
            searchResources(1, queryString);
        });
        $resourceSearchForm.find(".create-button").on("click", function () {
            $createResourceModal.modal("show");
        });
        $(".close-create-resource-modal").on("click", function () {
            $createResourceForm.clearForm();
            $createResourceModal.modal("hide");
        });
    };

    return {
        init: function () {
            bindEvent();
            this.searchResources(1);
        },
        searchResources: function (page) {
            searchResources(page);
        }
    }
}();
$(function () {
    SysRes.init();
});