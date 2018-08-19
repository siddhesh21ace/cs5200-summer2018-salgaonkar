(function () {
    angular.module("Pokepedia")
        .service("UtilService", utilService);

    function utilService() {
        return {
            "padToThree": padToThree,
            "errorPromise": errorPromise
        };

        function padToThree(val) {
            if (val <= 999) {
                val = ("00" + val).slice(-3);
            }
            return val;
        }

        function errorPromise() {
            return error => {
                console.log(error);
                vm.error = error.data;
            }
        }
    }
})();