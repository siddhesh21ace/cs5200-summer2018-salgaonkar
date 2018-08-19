(function () {
    angular
        .module("Pokepedia")
        .factory('TrainerService', trainerService);

    function trainerService($http) {
        return {
            "createTrainer": createTrainer,
            "findTrainerById": findTrainerById,
            "updateTrainer": updateTrainer,
            "addOrder": addOrder,
            "findAllTrainers": findAllTrainers,
            "findBattlesByTrainer": findBattlesByTrainer,
            "deleteTrainer": deleteTrainer
        };

        function createTrainer(trainer) {
            return $http.post("/api/trainer", trainer);
        }

        function findTrainerById(trainerId) {
            return $http.get("/api/trainer/" + trainerId);
        }

        function findBattlesByTrainer(trainerId) {
            return $http.get("/api/trainer/" + trainerId + "/battle");
        }

        function updateTrainer(trainer) {
            return $http.put("/api/trainer/" + trainer.id, trainer);
        }

        function addOrder(trainer, order) {
            return $http.put("/api/trainer/" + trainer.id + "/order/" + order.id);
        }

        function findAllTrainers() {
            return $http.get("/api/trainer");
        }

        function deleteTrainer(trainerId) {
            return $http.delete("/api/trainer/" + trainerId);
        }

    }
})();
