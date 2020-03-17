var Proj = function () {

    var contextPath = $("#context-path").val();

    var runningMode = $("#runningMode").val();

    return {
        isDev: function () {
            return runningMode === "dev";
        },
        getContextPath: function () {
            return contextPath;
        }
    }
}();