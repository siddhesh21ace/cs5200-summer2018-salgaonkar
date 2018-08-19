(function () {
    angular
        .module("Pokepedia")
        .controller("TrainerController", trainerController);

    function trainerController($routeParams, UserService, PokemonService, TrainerService, UtilService,
                               PetService, $location) {
        let vm = this;
        vm.pokemons = [];

        vm.update = update;
        vm.getMoreInfo = getMoreInfo;
        vm.padToThree = padToThree;
        vm.makePrimary = makePrimary;
        vm.applyPotion = applyPotion;
        vm.feedBerries = feedBerries;

        function init() {
            UserService.findCurrentUser()
                .then(response => {
                    vm.user = response.data;
                    return TrainerService.findTrainerById(vm.user.id);
                }, (error) => {
                    console.log(error);
                })
                .then(response => {
                    let trainerData = response.data;
                    vm.user.coins = trainerData.coins;
                    vm.user.points = trainerData.points;
                    vm.user.berries = trainerData.berries;
                    vm.user.potion = trainerData.potion;

                    return UserService.findPets(vm.user.id);
                }, (error) => {
                    console.log(error);
                })
                .then(response => {
                    vm.user.pets = response.data;
                    return TrainerService.findBattlesByTrainer(vm.user.id);
                }, (error) => {
                    console.log(error);
                })
                .then(response => {
                    vm.user.battles = response.data;
                    $("#profile").fadeOut("slow");
                    $("#loading").delay(200).fadeOut("slow").remove();
                })
            ;

            $("#myModal1").modal('hide');
            $('body').removeClass('modal-open');
            $('.modal-backdrop').remove();
        }

        init();

        function update() {
            UserService.updateUser(vm.user.id, vm.user)
                .then(response => {
                    let user = response.data;
                    if (user === null) {
                        vm.error = "Unable to update user";
                    } else {
                        vm.success = "User successfully updated";
                    }
                }, error => {
                    vm.error = "Unable to update user";
                });
        }

        function getMoreInfo(pokedex_no) {
            $location.url('/pokemon-info/' + pokedex_no);
        }

        function padToThree(val) {
            return UtilService.padToThree(val);
        }

        function makePrimary(id) {
            vm.user.pets.forEach(pet => {
                if (pet.id === id) {
                    pet.is_primary = true;
                } else {
                    pet.is_primary = false;
                }
            })
        }

        function applyPotion(pet) {
            let potionCount = vm.user.potion;
            vm.success = "";
            vm.error = "";

            if (potionCount < 10) {
                vm.error = "Insufficient Potion";
                return;
            }

            pet.level += 1;
            PetService.updatePetForPerson(pet, vm.user.id)
                .then(response => {
                    vm.user.potion -= 10;
                    return TrainerService.updateTrainer(vm.user)
                }, error => {
                    console.log(error);
                })
                .then(response => {
                    vm.success = "Successfully Applied";
                }, error => {
                    console.log(error);
                })
        }

        function feedBerries(pet) {
            let berriesCount = vm.user.berries;
            vm.success = "";
            vm.error = "";

            if (berriesCount < 5) {
                vm.error = "Insufficient Berries";
                return;
            }

            pet.experience += 10;
            PetService.updatePetForPerson(pet, vm.user.id)
                .then(response => {
                    vm.user.berries -= 5;
                    return TrainerService.updateTrainer(vm.user)
                }, error => {
                    console.log(error);
                })
                .then(response => {
                    vm.success = "Successfully Applied";
                }, error => {
                    console.log(error);
                })

        }

    }
})();
