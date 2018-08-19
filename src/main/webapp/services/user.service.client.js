(function () {
    angular
        .module("Pokepedia")
        .factory('UserService', userService);

    function userService($http) {
        return {
            "updateUser": updateUser,
            "findUserByCredentials": findUserByCredentials,
            "findUserById": findUserById,
            "findUserByUsername": findUserByUsername,
            "createUser": createUser,
            "deleteUser": deleteUser,
            "login": login,
            "isLoggedIn": isLoggedIn,
            "logout": logout,
            "findCurrentUser": findCurrentUser,
            "register": register,
            "isAdmin": isAdmin,
            "findAllUsers": findAllUsers,
            "updateUserByAdmin": updateUserByAdmin,
            "deleteUserByAdmin": deleteUserByAdmin,
            "getAllPlayers": getAllPlayers,
            "findPets": findPets,
            "addPet": addPet
        };

        function findPets(userId) {
            return $http.get("/api/person/" + userId + "/pet");
        }

        function register(user) {
            return $http.post("/api/register", user);
        }

        function findCurrentUser() {
            return $http.get("/api/person/current");
        }

        function logout() {
            return $http.post("/api/logout");
        }

        function login(user) {
            return $http.post("/api/login", user);
        }

        function isLoggedIn() {
            return $http.get("/api/isLoggedIn");
        }

        function deleteUser(userId) {
            return $http.delete('/api/user/' + userId);
        }

        function createUser(user) {
            return $http.post("/api/admin/user", user);
        }

        function findUserByUsername(username) {
            return $http.get("/api/user?username=" + username);
        }

        function findUserByCredentials(username, password) {
            return $http.get("/api/user?username=" + username + "&password=" + password);
        }

        function updateUser(userId, updatedUser) {
            return $http.put("/api/person/" + userId, updatedUser);
        }

        function findUserById(userID) {
            return $http.get("/api/person/" + userID);
        }

        function isAdmin() {
            return $http.get("/api/admin/confirm");
        }

        function updateUserByAdmin(user) {
            return $http.put("/api/admin/user/" + user._id, user);
        }

        function deleteUserByAdmin(userID) {
            return $http.delete('/api/admin/user/' + userID);
        }

        function findAllUsers() {
            return $http.get('/api/person');
        }

        function getAllPlayers() {
            return $http.get('/api/player');
        }

        function addPet(person, pet) {
            return $http.post("/api/person/" + person.id + "/pet/" + pet.id);
        }
    }
})();
