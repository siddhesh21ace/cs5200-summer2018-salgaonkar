(function () {
        angular.module("Pokepedia")
            .controller("GameController", gameController);

        function gameController(CardService, BattleService, UserService, TrainerService,
                                $route, $timeout, $routeParams, PetService) {
            let vm = this;

            vm.player1 = {};
            vm.player2 = {};

            vm.player1.cards = [];
            vm.player2.cards = [];
            vm.cards = [];

            vm.game = {};
            vm.game.state = 0;
            vm.game.player1Turn = true;
            vm.game.userCards = [];
            vm.game.botCards = [];
            vm.game.userWon = false;

            vm.gameID = 0;
            vm.user = {};
            vm.attack1 = attack1;
            vm.isNumber = isNumber;

            vm.showActiveCardDetails2 = showActiveCardDetails2;
            vm.showActiveCardDetails1 = showActiveCardDetails1;
            vm.getDamage = getDamage;
            vm.getHp = getHp;
            vm.startGame = startGame;
            vm.startNewGame = startNewGame;
            vm.playAudio = playAudio;
            vm.stopAudio = stopAudio;

            vm.activeCard = {};
            vm.winner = {};
            vm.goku = false;
            vm.themeSong = "";

            vm.trainerId = $routeParams['tid'];
            vm.gymLeaderId = $routeParams['gid'];

            vm.trainerPrimaryPet = {};
            vm.gymLeaderPrimaryPet = {};

            function isNumber(damage) {
                if (damage === '') {
                    return false;
                } else return !isNaN(damage);
            }

            function showActiveCardDetails1(card) {
                vm.activeCard1 = card;
            }

            function showActiveCardDetails2(card) {
                vm.activeCard2 = card;
            }

            function playAudio() {
                vm.themeSong = new Audio("../../../audio/pokemon-theme.mp3");
                vm.themeSong.play();
            }

            function stopAudio() {
                vm.themeSong.pause();
                vm.themeSong.currentTime = 0;
            }

            function errorPromise() {
                return error => {
                    console.log(error);
                    vm.error = error.data;
                }
            }

            function init() {
                UserService.findCurrentUser()
                    .then(response => {
                        vm.user = response.data;
                        return UserService.findPets(vm.user.id);
                    }, errorPromise)
                    .then(response => {
                        let pets = response.data;
                        let primaryPetPokedexNumber = 1;
                        pets.forEach(pet => {
                            if (pet.is_primary) {
                                vm.trainerPrimaryPet = pet;
                                primaryPetPokedexNumber = pet.pokedex_no;
                            }
                        });
                        return CardService.fetchCardByPokedexNumber({
                            "pokedexNumber": primaryPetPokedexNumber
                        })
                    }, errorPromise)
                    .then(response => {
                        let card = response.data.cards[0];
                        card.attacks = normalizeAttacks(card.attacks, vm.trainerPrimaryPet.level);
                        card.hp = vm.trainerPrimaryPet.experience;
                        vm.player1.cards.push({
                            "isAlive": true,
                            "details": card
                        });
                        vm.game.userCards.push(card.id);

                        return UserService.findUserById(vm.gymLeaderId);
                    }, errorPromise)
                    .then(response => {
                        vm.gymLeader = response.data;
                        return UserService.findPets(vm.gymLeaderId);
                    }, errorPromise)
                    .then(response => {
                        let pets = response.data;
                        let primaryPetPokedexNumber = 1;
                        pets.forEach(pet => {
                            if (pet.is_primary) {
                                primaryPetPokedexNumber = pet.pokedex_no;
                                vm.gymLeaderPrimaryPet = pet;
                            }
                        });
                        return CardService.fetchCardByPokedexNumber({
                            "pokedexNumber": primaryPetPokedexNumber
                        })
                    }, errorPromise)
                    .then(response => {
                        let card = response.data.cards[0];
                        card.attacks = normalizeAttacks(card.attacks, vm.gymLeaderPrimaryPet.level);
                        card.hp = vm.gymLeaderPrimaryPet.experience;
                        vm.player2.cards.push({
                            "isAlive": true,
                            "details": card
                        });
                        vm.game.botCards.push(card.id);

                        vm.player1.current = vm.player1.cards[0];
                        showActiveCardDetails1(vm.player1.current);
                        vm.player2.current = vm.player2.cards[0];
                        showActiveCardDetails2(vm.player2.current);
                        return BattleService.createBattle(vm.trainerId, vm.gymLeaderId);
                    }, errorPromise)
                    .then(response => {
                        console.log(response);

                        $("#profile").fadeOut("slow");
                        $("#loading").delay(100).fadeOut("slow").remove();

                        $('#myModal').modal('show');
                        $('#myModal').modal({
                            backdrop: 'static',
                            keyboard: false
                        });
                    }, errorPromise);

                $("#myModal1").modal('hide');
                $('body').removeClass('modal-open');
                $('.modal-backdrop').remove();
            }

            function updateBattle() {
                BattleService.updateBattle(vm.trainerId, vm.gymLeaderId)
                    .then(response => {
                        console.log(response.data);
                    }, errorPromise);
            }

            function normalizeAttacks(attacks, level) {
                attacks && attacks.forEach(attack => {
                    if (attack.damage === '' || isNaN(attack.damage)) {
                        attack.damage = 25;
                    } else if (attack.damage > 40) {
                        attack.damage = 40;
                    }
                    attack.damage = Number(attack.damage);
                    attack.damage = Math.ceil(attack.damage + level * 0.1 * attack.damage);
                });
                return attacks;
            }

            init();

            function startGame() {
                vm.game.state = 1;
                $("#myModal").modal('hide');
            }

            function startNewGame() {
                $("#myModal1").modal('hide');
                $('body').removeClass('modal-open');
                $('.modal-backdrop').remove();
                $route.reload();
            }

            function giftPokemon() {
                TrainerService.findTrainerById(vm.trainerId)
                    .then(response => {
                        let trainer = response.data;
                        trainer.points += 20;
                        return TrainerService.updateTrainer(trainer);
                    }, errorPromise)
                    .then(response => {
                        CardService.fetchAllCards({})
                            .then(response => {
                                let cards = response.data.cards;
                                vm.card = cards[Math.floor(Math.random() * cards.length)];
                                vm.url = vm.card.imageUrl;

                                let pet = {
                                    "experience": 100,
                                    "level": 1,
                                    "is_primary": false,
                                    "pokedex_no": vm.card.nationalPokedexNumber
                                };

                                return PetService.createPet(pet);
                            }, errorPromise)
                            .then(response => {
                                return UserService.addPet(vm.user, response.data)
                            }, errorPromise)
                            .then(response => {
                                console.log(response.data);
                            }, errorPromise);
                    }, errorPromise);
            }

            function attack1(damage) {
                let hp = vm.player2.current.details.hp;
                vm.player2.current.details.hp = vm.player2.current.details.hp - damage;

                if (vm.player2.current.details.hp <= 0) {
                    vm.player2.current.isAlive = false;
                    // showActiveCardDetails2(vm.player2.current);
                    vm.winner = 1;
                    vm.game.userWon = true;
                    updateBattle();
                    giftPokemon();
                    vm.game.state = 2;
                    $('#myModal1').modal('show');
                } else {
                    vm.game.player1Turn = false;
                    vm.goku = true;
                    vm.computerMessage = "Computer Deciding an Attack !!";
                    let attack = getDamage(vm.activeCard2.details.attacks);
                    vm.getMaxAttackDamage = attack.damage;
                    vm.selectedAttack = attack.name;

                    $timeout(
                        function attack2() {
                            let hp = vm.player1.current.details.hp;
                            vm.computerMessage = "Attack " + vm.selectedAttack + " Selected";
                            vm.player1.current.details.hp = vm.player1.current.details.hp - vm.getMaxAttackDamage;

                            if (vm.player1.current.details.hp <= 0) {
                                vm.player1.current.isAlive = false;
                                console.log("Game Over - Player 2 won");
                                vm.winner = 2;
                                vm.game.state = 2;
                                $('#myModal1').modal('show');
                            } else {
                                $timeout(() => {
                                    vm.goku = false;
                                    vm.game.player1Turn = true;
                                }, 1000);
                            }
                        }, 3000);
                }
            }

            function getDamage(activePlayerAttacks) {
                let pickMaxAttack = activePlayerAttacks[0];

                for (let a in activePlayerAttacks) {
                    if (activePlayerAttacks.hasOwnProperty(a)) {
                        if (activePlayerAttacks[a].damage > pickMaxAttack.damage)
                            pickMaxAttack = activePlayerAttacks[a];
                    }
                }

                return pickMaxAttack;
            }

            function getHp(stat) {
                return (stat / 100) * 100;
            }

        }
    }

)();