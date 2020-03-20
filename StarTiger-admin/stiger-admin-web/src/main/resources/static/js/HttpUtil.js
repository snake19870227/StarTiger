var RespCode = function () {
    var def = function (resp, options) {
        if (Proj.isDev()) {
            console.group(options.url);
            console.dir(resp);
            console.groupEnd();
        }
    };
    var defSuccess = function (resp, func, options) {
        def(resp, options);
        if (func && $.type(func) === "function") {
            func(resp);
        }
    };
    var defError = function (resp, func, options) {
        def(resp, options);
        Proj.showToasts("danger", "[" + resp.respCode + "]" + resp.respMessage);
    };
    return {
        code0000: defSuccess,
        code9999: defError,
        code9998: defError,
        code9997: defError,
        code1001: defError,
        code2001: function (resp, func, options) {
            def(resp, options);
            Proj.showToasts("warning", "[" + resp.respCode + "]" + resp.respMessage);
            setTimeout(function () {
                window.location.href = Proj.getContextPath() + "/login?expire";
            }, 3000);
        },
        code2002: defError
    }
}();
var HttpUtil = function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    return {
        ajaxReq: function (obj) {
            var headers = {};
            headers[header] = token;
            if (obj.headers) {
                $.extend(headers, obj.headers);
            }
            $.ajax({
                type: obj.type || "get",
                url: Proj.getContextPath() + obj.url,
                data: obj.data,
                cache: obj.cache || false,
                contentType: obj.contentType || "application/x-www-form-urlencoded",
                dataType: obj.dataType || "text",
                async: obj.async || true,
                headers: headers,
                beforeSend: function (XMLHttpRequest) {
                    if (obj.beforeSend && $.type(obj.beforeSend) === "function") {
                        obj.beforeSend(XMLHttpRequest);
                    }
                },
                success: function (data, textStatus, XMLHttpRequest) {
                    var dataObj = {};
                    var isJsonObject = false;
                    if (typeof data === "object") {
                        dataObj = data;
                        isJsonObject = true;
                    } else {
                        try {
                            dataObj = JSON.parse(data);
                            isJsonObject = true;
                        } catch (err) {
                            if (Proj.isDev()) {
                                console.warn(err.message);
                            }
                            isJsonObject = false;
                        }
                    }
                    if (isJsonObject) {
                        var codeFunc = RespCode["code" + dataObj.respCode];
                        if (codeFunc && $.type(codeFunc) === "function") {
                            codeFunc(dataObj, obj.callbackFunc, this);
                        }
                    } else {
                        if (obj.callbackFunc && $.type(obj.callbackFunc) === "function") {
                            obj.callbackFunc(data);
                        }
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    if (obj.error && $.type(obj.error) === "function") {
                        obj.error(XMLHttpRequest, textStatus, errorThrown);
                    }
                },
                complete: function (XMLHttpRequest, textStatus) {
                    if (obj.complete && $.type(obj.complete) === "function") {
                        obj.complete(XMLHttpRequest, textStatus);
                    }
                },
                statusCode: {
                    400: function () {
                        Proj.showToasts("danger", "无效的请求");
                    },
                    404: function () {
                        Proj.showToasts("danger", "未找到本次请求的页面或功能");
                    },
                    500: function () {
                        Proj.showToasts("danger", "服务器处理失败[" + this.url + "]");
                    }
                }
            });
        }
    }
}();