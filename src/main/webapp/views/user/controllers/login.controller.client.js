(function () {
    angular
        .module("Pokepedia")
        .controller("LoginController", loginController);

    function loginController(UserService, $location) {
        let vm = this;
        vm.login = login;

        function init() {
            UserService.findCurrentUser()
                .then(response => {
                    vm.user = response.data;
                });
        }

        init();

        function login(user) {
            if (user) {
                UserService.login(user)
                    .then(response => {
                        let loggedInUser = response.data;
                        if (!loggedInUser) {
                            vm.error = 'User not found';
                        } else if (loggedInUser.role === 'Admin') {
                            $location.url('/admin');
                        } else if (loggedInUser.role === 'Trainer') {
                            // to trainer
                            $location.url('/trainer/' + loggedInUser.id);
                        } else {
                            $location.url('/gym-leader/' + loggedInUser.id);
                        }
                    }, err => {
                        vm.error = err.data.message;
                    });
            } else {
                vm.error = 'Please enter correct credentials';
            }
        }
    }
})();