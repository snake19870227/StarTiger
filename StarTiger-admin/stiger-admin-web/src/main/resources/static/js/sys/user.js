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
                            url: "/sys/user/lock/" + userFlow,
                            data: "unlocked=" + state,
                            headers: {
                                Accept: "application/json"
                            },
                            callbackFunc: function (resp) {
                                $dom.data("cacheState", state);
                                search();
                            },
                            onError: function (resp) {
                                $dom.bootstrapSwitch("state", !state);
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                this.onError();
                            }
                        };
                        HttpUtil.ajaxReq(options);
                    }
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
            headers: {
                Accept: "text/html"
            },
            callbackFunc: function (data) {
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
});