(function () {
        angular.module("Pokepedia")
            .controller("HomeController", HomeController);

        function HomeController(TrainerService, UtilService) {
            let vm = this;
            vm.scores = [];

            /* Logic to get LeaderBoard */
            function init() {
                TrainerService.findAllTrainers()
                    .then(response => {
                        let trainers = response.data;
                        trainers.forEach(trainer => {
                            TrainerService.findBattlesByTrainer(trainer.id)
                                .then(response => {
                                    vm.battles = response.data;
                                    if (vm.battles && vm.battles.length > 0) {
                                        let count = 0;
                                        vm.battles.forEach(battle => {
                                            if (battle.trainerWon) {
                                                count++;
                                            }
                                        });
                                        let winPercentage = count / vm.battles.length;
                                        vm.scores.push({
                                            "username": trainer.username,
                                            "winPercentage": winPercentage,
                                            "played": vm.battles.length,
                                            "won": count,
                                            "points": trainer.points
                                        });
                                    }
                                }, UtilService.errorPromise);
                        });
                    }, UtilService.errorPromise);
            }

            init();
        }
    }
)();