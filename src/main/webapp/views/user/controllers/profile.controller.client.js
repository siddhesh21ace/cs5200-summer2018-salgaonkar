(function () {
    angular
        .module("Pokepedia")
        .controller("ProfileController", profileController);

    function profileController($routeParams, UserService, $location, CardService, PokemonService) {
        let vm = this;
        vm.user = {};

        vm.update = update;
        vm.deleteUser = deleteUser;
        vm.logout = logout;

        vm.collectedCards = [];
        vm.likedPokemons = [];

        function init() {
            UserService.findCurrentUser()
                .then(response => {
                    vm.user = response.data;
                    // remove later
                    $("#profile").fadeOut("slow");
                    $("#loading").delay(200).fadeOut("slow").remove();
                    // return CardService.findCardsByUser(vm.user._id);
                });
            // .then(response => {
            //     let cardIDs = response.data;
            //     cardIDs.forEach(obj => {
            //         CardService.findCardByTCGID(obj.tcgID)
            //             .then(response => {
            //                 vm.collectedCards.push(response.data.card);
            //             })
            //     });
            //     $("#profile").fadeOut("slow");
            //     $("#loading").delay(200).fadeOut("slow").remove();
            //     return LikeService.findLikedPokemonsByUser(vm.user._id);
            // })
            // .then(response => {
            //     let likeMapping = response.data;
            //     likeMapping.forEach(obj => {
            //         PokemonService.findPokemonById(obj.pokemon_id)
            //             .then(response => {
            //                 let poke = response.data;
            //                 return PokemonService.findPokemonByPokeId(poke.pokedex_number);
            //             })
            //             .then(response => {
            //                 let pokemon = response.data;
            //                 pokemon.img_id = padToThree(pokemon.id);
            //                 vm.likedPokemons.push(pokemon);
            //                 $("#profile").fadeOut("slow");
            //                 $("#loading").delay(200).fadeOut("slow").remove();
            //             });
            //     });
            // });
            $("#myModal1").modal('hide');
            $('body').removeClass('modal-open');
            $('.modal-backdrop').remove();
        }

        init();

        function padToThree(number) {
            if (number <= 999) {
                number = ("00" + number).slice(-3);
            }
            return number;
        }

        function logout() {
            UserService.logout()
                .then(response => {
                    $location.url("/login");
                });
        }

        function update(updatedUser) {
            UserService.updateUser(vm.user._id, updatedUser)
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

        function deleteUser() {
            let answer = confirm("Are you sure?");
            if (answer) {
                UserService.deleteUser(vm.user._id)
                    .then(response => {
                        $location.url("/login");
                    }, error => {
                        vm.error = 'Unable to delete user';
                    });
            }
        }

    }
})();
