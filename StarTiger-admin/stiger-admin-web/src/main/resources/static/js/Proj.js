var Proj = function () {

    var toastTypes = ["info", "success", "warning", "danger"];
    var toastTitle = ["提示", "成功", "警告", "错误"];
    var toastIcons = ["fa-info-circle", "fa-check-circle", "fa-exclamation-circle", "fa-exclamation-triangle"];

    var contextPath = $("#context-path").val();

    var runningMode = $("#runningMode").val();

    function isDev() {
        return runningMode === "dev";
    }

    $("body").on("removed.lte.toasts", function (event) {
        if (isDev()) {
            console.log(event);
        }
    });

    return {
        isDev: function () {
            return isDev();
        },
        getContextPath: function () {
            return contextPath;
        },
        showToasts: function (type, body) {
            var options = {
                class: "bg-" + toastTypes[0],
                title: toastTitle[0],
                autohide: true,
                delay: 3000,
                body: body,
                icon: "fas fa-lg " + toastIcons[0]
            };
            if (type && type !== "") {
                var index = toastTypes.indexOf(type);
                if (index < 0) {
                    console.error("invalid toast type [" + type + "]");
                    return;
                }
                options.class = "bg-" + type;
                options.title = toastTitle[index];
                options.icon = "fas fa-lg " + toastIcons[index];
            }
            $(document).Toasts('create', options);
        }
    }
}();