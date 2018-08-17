(function () {
    angular
        .module('Pokepedia')
        .controller('AdminController', adminController);

    function adminController(UserService) {
        let vm = this;
        vm.user = {};

        vm.createUser = createUser;
        vm.deleteUser = deleteUser;
        vm.updateUser = updateUser;
        vm.selectUser = selectUser;

        function init() {
            findAllUsers();
        }

        init();

        function findAllUsers() {
            UserService
                .findAllUsers()
                .then(renderUsers, errorHandler);
        }

        function createUser(user) {
            UserService
                .createUser(user)
                .then(findAllUsers, errorHandler);
        }

        function updateUser(user) {
            UserService
                .updateUserByAdmin(user)
                .then(findAllUsers, errorHandler);
        }

        function deleteUser(userID) {
            UserService
                .deleteUserByAdmin(userID)
                .then(findAllUsers, errorHandler);
        }

        function selectUser(user) {
            UserService.findUserById(user._id)
                .then(function (response) {
                    vm.user = response.data;
                }, errorHandler);
        }

        function renderUsers(response) {
            let users = response.data;

            vm.users = users.filter(user => user.role.indexOf("Admin") === -1);
            vm.user = {}; // form reset
        }

        function errorHandler(error) {
            console.log(error);
        }
    }

})();