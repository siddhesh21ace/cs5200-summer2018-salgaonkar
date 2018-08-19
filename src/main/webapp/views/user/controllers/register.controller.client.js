(function () {
    angular
        .module("Pokepedia")
        .controller("RegisterController", registerController);

    function registerController(UserService, CardService, PetService, TrainerService, $location, $timeout) {
        let vm = this;
        vm.user = {};

        vm.register = register;

        function register() {
            if (vm.user) {
                let user;
                UserService.register(vm.user)
                    .then(response => {
                        user = response.data;
                        return TrainerService.findTrainerById(user.id);
                    }, error => {
                        vm.error = error.data.message;
                    })
                    .then(response => {
                        user.role = response.data.role;
                        if (!user) {
                            vm.error = 'User not found';
                        } else if (user.role === 'Admin') {
                            $location.url('/admin');
                        } else if (user.role === 'Trainer') {
                            return giftCard(user);
                        } else {
                            $location.url('/gym-leader/' + user.id);
                        }
                    }, error => {
                        vm.error = error.data.message;
                    })
                    .then(response => {
                        $location.url('/trainer/' + user.id);
                    }, error => {
                        vm.error = error.data.message;
                    })
            } else {
                vm.error = 'Please enter all the details';
            }
        }

        function giftCard(user) {
            return CardService.fetchAllCards({})
                .then(response => {
                    let cards = response.data.cards;
                    cards.forEach(card => {
                        card.attacks && card.attacks.forEach(attack => {
                            if (attack.damage === '' || isNaN(attack.damage)) {
                                attack.damage = "50";
                            }
                        });
                    });
                    vm.card = cards[Math.floor(Math.random() * cards.length)];
                    let pet = {
                        "experience": 100,
                        "level": 1,
                        "is_primary": true,
                        "pokedex_no": vm.card.nationalPokedexNumber
                    };
                    return PetService.createPet(pet);
                }, error => {
                    console.log(error);
                })
                .then(response => {
                    return UserService.addPet(user, response.data)
                }, error => {
                    console.log(error);
                })
        }
    }
})();