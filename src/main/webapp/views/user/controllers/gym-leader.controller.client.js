(function () {
    angular
        .module("Pokepedia")
        .controller("GymLeaderController", gymLeaderController);

    function gymLeaderController($routeParams, UserService, PokemonService, GymLeaderService, UtilService, $location) {
        let vm = this;
        vm.pokemons = [];
        vm.update = update;
        vm.getMoreInfo = getMoreInfo;
        vm.padToThree = padToThree;
        vm.makePrimary = makePrimary;

        function init() {
            UserService.findCurrentUser()
                .then(response => {
                    vm.user = response.data;
                    return GymLeaderService.findGymLeaderById(vm.user.id);
                }, (error) => {
                    console.log(error);
                })
                .then(response => {
                    let gymLeaderData = response.data;
                    vm.user.type = gymLeaderData.type;
                    vm.user.badge = gymLeaderData.badge;
                    vm.user.region = gymLeaderData.region;
                    return UserService.findPets(vm.user.id);
                }, (error) => {
                    console.log(error);
                })
                .then(response => {
                    vm.user.pets = response.data;
                    return GymLeaderService.findBattles(vm.user.id);
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
                        vm.message = "User successfully updated";
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

    }
})();
