(function () {
    angular
        .module("Pokepedia")
        .controller("RegisterController", registerController);

    function registerController(UserService, $location, $timeout) {
        let vm = this;
        vm.user = {};

        vm.register = register;

        function register() {
            if (vm.user) {
                UserService.register(vm.user)
                    .then(response => {
                        let user = response.data;
                        $timeout(() => {
                            $location.url("/user/" + user.id);
                        });
                    }, error => {
                        vm.error = error.data.message;
                    });

            } else {
                vm.error = 'Please enter all the details';
            }
        }

    }
})();