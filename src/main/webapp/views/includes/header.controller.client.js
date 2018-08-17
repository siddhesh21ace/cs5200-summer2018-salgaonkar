(function () {
    angular.module("Pokepedia")
        .controller("HeaderController", HeaderController);

    function HeaderController($location, UserService) {
        let vm = this;
        vm.isAdmin = isAdmin;
        vm.logout = logout;
        vm.location = $location;

        function init() {
            UserService.findCurrentUser()
                .then(response => {
                    vm.user = response.data;
                }, error => {
                    console.log(error);
                });
        }

        init();

        function isAdmin() {
            return (vm.user && vm.user.roles && vm.user.roles.indexOf('ADMIN') > -1);
        }

        function logout() {
            UserService.logout()
                .then(response => {
                    $location.url("/login");
                }, error => {
                    console.log(error);
                });
        }
    }
})();