(function () {
    angular
        .module("Pokepedia")
        .factory('GymLeaderService', gymLeaderService);

    function gymLeaderService($http) {
        return {
            "findGymLeaderById": findGymLeaderById,
            "findBattles": findBattles
        };

        function findGymLeaderById(gymLeaderId) {
            return $http.get("/api/gymLeader/" + gymLeaderId);
        }

        function findBattles(gymLeaderId) {
            return $http.get("/api/gymLeader/" + gymLeaderId + "/battle");
        }
    }
})();
