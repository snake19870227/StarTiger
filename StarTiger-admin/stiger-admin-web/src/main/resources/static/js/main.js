function req(url) {
    HttpUtil.ajaxReq({
        url: url,
        dataType: "json",
        callbackFunc: function (resp) {
            $("#txt").val(resp.data);
        }
    });
}

function load(url) {
    // $("#area").load(url);
    HttpUtil.ajaxReq({
        url: url,
        dataType: "html",
        callbackFunc: function (resp) {
            $("#area").html(resp);
        }
    });
}