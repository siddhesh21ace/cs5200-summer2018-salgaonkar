(function () {
    angular.module("Pokepedia")
        .controller("IndexController", indexController);

    function indexController(UserService, $location) {
        let vm = this;

        function init() {
            vm.isLocal = $location.$$absUrl.indexOf('localhost') > -1;
        }

        init();
    }
})();