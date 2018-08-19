(function () {
    angular
        .module("Pokepedia")
        .factory("BattleService", battleService);

    function battleService($http) {
        return {
            "createBattle": createBattle,
            "updateBattle": updateBattle
        };

        function createBattle(trainerId, gymLeaderId) {
            return $http.post("/api/trainer/" + trainerId + "/gymLeader/" + gymLeaderId, {});
        }

        function updateBattle(trainerId, gymLeaderId) {
            return $http.put("/api/trainer/" + trainerId + "/gymLeader/" + gymLeaderId, {});
        }

    }
})();