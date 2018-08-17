(function () {
    angular.module("Pokepedia")
        .controller("PokemonInfoController", pokemonInfoController);

    function pokemonInfoController($routeParams, UserService, PokemonService, $location) {
        document.body.scrollTop = document.documentElement.scrollTop = 0;

        let vm = this;
        vm.user = {};
        vm.pokemon = {};
        vm.loading = true;

        vm.getColorClass = getColorClass;
        vm.getMaxStat = getMaxStat;
        vm.getIndicator = getIndicator;

        vm.isLoggedInUser = isLoggedInUser;
        vm.getMoreInfo = getMoreInfo;

        function init() {
            $('[data-toggle="tooltip"]').tooltip();
            let pokemonId = $routeParams.pokemon;

            PokemonService.findPokemonByPokeId(pokemonId)
                .then(response => {
                    vm.pokemon = response.data;

                    let evoChain = vm.pokemon.species.evoChains;

                    for (e in evoChain) {
                        if (evoChain.hasOwnProperty(e)) {
                            let urlA = "http://assets.pokemon.com/assets/cms2/img/pokedex/detail/";
                            let id = evoChain[e].pokeId;

                            if (id.toString().length === 1) {
                                id = "00" + id;
                            } else if (id.toString().length === 2) {
                                id = "0" + id;
                            }

                            evoChain[e].img = urlA + id + ".png";
                        }
                    }

                    vm.pokemon.species.evoChains = evoChain;

                    let urlA = "http://assets.pokemon.com/assets/cms2/img/pokedex/full/";
                    let id = response.data.metaPokemon.id;

                    if (id.toString().length === 1) {
                        id = "00" + id;
                    } else if (id.toString().length === 2) {
                        id = "0" + id;
                    }

                    let imgUrl = urlA + id + ".png";
                    if (id > 802) {
                        imgUrl = "../images/image_not_available.png"
                    }

                    vm.pokemon.img = imgUrl;
                    return PokemonService.findPokemonById(pokemonId);
                }, error => {
                    vm.error = "Result Not found";
                    vm.loading = false;
                    // will fade out the whole DIV that covers the website.
                    $("#pokeball").fadeOut("slow");
                    $("#loading").delay(200).fadeOut("slow").remove();
                })
                .then(response => {
                    vm.pokemon.id = response.data.id;
                    vm.loading = false;
                    $("#pokeball").fadeOut("slow");
                    $("#loading").delay(200).fadeOut("slow").remove();
                });

            UserService.findCurrentUser()
                .then(response => {
                    vm.user = response.data;
                });
        }

        init();

        function isLoggedInUser() {
            return !!vm.user;
        }

        function getMoreInfo(data) {
            $location.url('/pokemon-info/' + data);
        }

        /* Extras */
        function getColorClass(type) {
            if (type === 'grass') {
                return "grass";
            } else if (type === 'poison') {
                return "poison";
            } else if (type === 'psychic') {
                return "psychic";
            } else if (type === 'bug') {
                return "bug";
            } else if (type === 'fire') {
                return "fire";
            } else if (type === 'ice') {
            } else if (type === 'water') {
                return "water";
            } else if (type === 'ice') {
                return "ice";
            } else if (type === 'ground') {
                return "ground";
            } else if (type === 'flying') {
                return "flying";
            } else if (type === 'electric') {
                return "electric";
            } else if (type === 'rock') {
                return "rock";
            } else if (type === 'fighting') {
                return "fighting";
            } else if (type === 'normal') {
                return "normal";
            }
        }

        function getMaxStat(stat) {
            if (stat >= 100) {
                return 150;
            } else {
                return 120;
            }
        }

        function getIndicator(stat) {
            return (stat / 200) * 100;
        }
    }

})();
