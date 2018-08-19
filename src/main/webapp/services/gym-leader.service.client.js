(function () {
    angular
        .module("Pokepedia")
        .factory('GymLeaderService', gymLeaderService);

    function gymLeaderService($http) {
        return {
            "createGymLeader": createGymLeader,
            "findGymLeaderById": findGymLeaderById,
            "findBattlesByGymLeader": findBattlesByGymLeader,
            "findAllGymLeaders": findAllGymLeaders,
            "updateGymLeader": updateGymLeader,
            "deleteGymLeader": deleteGymLeader
        };

        function createGymLeader(gymLeader) {
            return $http.post("/api/gymLeader", gymLeader);
        }

        function findGymLeaderById(gymLeaderId) {
            return $http.get("/api/gymLeader/" + gymLeaderId);
        }

        function findBattlesByGymLeader(gymLeaderId) {
            return $http.get("/api/gymLeader/" + gymLeaderId + "/battle");
        }

        function findAllGymLeaders() {
            return $http.get("/api/gymLeader");
        }

        function updateGymLeader(gymLeader) {
            return $http.put("/api/gymLeader/" + gymLeader.id, gymLeader);
        }

        function deleteGymLeader(gymLeaderId) {
            return $http.delete("/api/gymLeader/" + gymLeaderId);
        }
    }
})();
