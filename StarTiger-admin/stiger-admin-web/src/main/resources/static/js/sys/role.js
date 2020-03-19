var SysRole = function () {

    var $roleSearchForm = $("#role-search-form");
    var $roleSearchForm = $("#role-search-form");
    var $roleContainer = $("#roles-container");

    function bindRecordEvents() {

    }

    function searchRoles(page) {
        var url = "/sys/role/list";
        if (page) {
            url += ("?page=" + page);
        }
        var options = {
            url: url,
            contentType: "text/html",
            callbackFunc: function (data) {
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
        $roleSearchForm.find(".search-button").on("click", function () {
            searchRoles(1);
        });
        /* ========================< create >======================== */
        $roleSearchForm.find(".create-button").on("click", function () {
            // resourceDetailModal.show();
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
});