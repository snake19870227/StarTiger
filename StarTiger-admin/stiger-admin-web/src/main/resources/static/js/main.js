function req(url) {
    HttpUtil.ajaxReq({
        url: url,
        dataType: "json",
        callbackFunc: function (resp) {
            $("#txt").val(resp.data);
        }
    });
}