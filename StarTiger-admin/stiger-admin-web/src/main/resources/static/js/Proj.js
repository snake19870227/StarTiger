var Proj = function () {
    return {
        isDev: function () {
            return $("#runningMode").val() === "dev";
        }
    }
}();