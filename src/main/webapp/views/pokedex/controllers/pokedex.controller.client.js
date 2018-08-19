(function () {
    angular.module('Pokepedia')
        .controller("PokedexController", pokedexController);

    function pokedexController($location, UserService, PokemonService) {
        let vm = this;

        vm.getResults = getResults;
        vm.getMoreInfo = getMoreInfo;
        vm.selectPokemon = selectPokemon;
        vm.resetResult = resetResult;
        vm.pokemon = {};
        vm.searchResults = {};
        vm.searchResults.name = "";
        vm.selectedPokemon = "";
        vm.pokemonThumbs = [];
        vm.testData = [];
        vm.loadMore = "Loading More Data .....";

        function resetResult() {
            vm.searchResults = {};
            vm.searchResults.name = "";
        }

        function init() {
            let matches = [];
            let pokemons = [];
            let display = [];

            PokemonService.fetchAllPokemons()
                .then(response => {
                    pokemons = response.data.results;
                    pokemons = pokemons.slice(0, 802);
                    pokemons.forEach(pokemon => {
                        pokemon.image = "http://assets.pokemon.com/assets/cms2/img/pokedex/detail/" +
                            padToThree(pokemon.id) + ".png";
                        display.push(pokemon);
                    });

                    $("#loading").delay(200).fadeOut("slow").remove();
                    $("#pokedex-gif").fadeOut("slow");
                });

            vm.pokemonThumbs = display;

            UserService.findCurrentUser()
                .then(response => {
                    vm.user = response.data;
                });

            PokemonService.findAllPokemons()
                .then(response => {
                    vm.pokemons = response;
                    let allPokemons = response.data;

                    let j = 0;
                    for (let p in allPokemons) {
                        if (allPokemons.hasOwnProperty(p)) {
                            let pokemon = {};
                            pokemon.name = allPokemons[p].name;
                            pokemon.pokedex_number = allPokemons[p].pokedex_number;
                            pokemon.id = allPokemons[p].id;
                            pokemon.url = "http://assets.pokemon.com//assets/cms2/img/pokedex/detail/" +
                                String(padToThree(pokemon.pokedex_number)) + ".png";

                            if (j < 802) {
                                matches.push(pokemon);
                                j++;
                            }
                        }
                    }
                }, error => {
                    vm.error = "Result Not found";
                });
            vm.matches = matches;
        }

        function padToThree(number) {
            if (number <= 999) {
                number = ("00" + number).slice(-3);
            }
            return number;
        }

        init();

        function selectPokemon(pokemon) {
            if (pokemon) {
                vm.pokemon = pokemon;
                getResults(pokemon);
            }
        }

        function getResults(searchTerm) {
            vm.searchResults = searchTerm.originalObject;
            vm.searchResults.img = vm.searchResults.url;
        }

        function getMoreInfo(data) {
            $location.url('/pokemon-info/' + data.id);
        }

    }

})();
