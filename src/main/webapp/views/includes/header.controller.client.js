(function () {
    angular.module("Pokepedia")
        .controller("HeaderController", HeaderController);

    function HeaderController($location, UserService) {
        let vm = this;
        vm.isAdmin = isAdmin;
        vm.logout = logout;
        vm.location = $location;
        vm.userPath = "";

        function init() {
            UserService.findCurrentUser()
                .then(response => {
                    vm.user = response.data;
                    if (vm.user.role === 'Admin') {
                        vm.userPath = "admin/" + vm.user.id;
                    } else if (vm.user.role === 'Trainer') {
                        vm.userPath = "trainer/" + vm.user.id;
                    } else {
                        vm.userPath = "gym-leader/" + vm.user.id;
                    }
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