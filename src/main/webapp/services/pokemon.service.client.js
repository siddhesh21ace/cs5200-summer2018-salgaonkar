(function () {
    angular.module("Pokepedia")
        .service("PokemonService", pokemonService);

    function pokemonService($http) {
        let endPoint = "/rest/api/card?";
        const setsURL = "https://api.pokemontcg.io/v1/sets";

        return {
            "fetchAllPokemons": fetchAllPokemons,
            "findAllPokemons": findAllPokemons,
            "findPokemonByPokeId": findPokemonByPokeId,
            "findPokemonById": findPokemonById,
            "findPokemonByName": findPokemonByName,
            "getAllSets": getAllSets,
            "getPokemonsBySet": getPokemonsBySet
        };

        function fetchAllPokemons() {
            return $http.get("/pokeapi/pokemon");
        }

        function findAllPokemons() {
            return $http.get("/api/pokemon");
        }

        function findPokemonByPokeId(pokemonID) {
            return $http.get("/pokeapi/pokemon/" + pokemonID);
        }

        function findPokemonById(pokemonID) {
            return $http.get("/api/pokemon/" + pokemonID);
        }

        function findPokemonByName(pokemonName) {
            return $http.get("/api/pokemon?name=" + pokemonName);
        }

        /* Testing Purpose for now */
        function getAllSets() {
            return $http.get(setsURL);
        }

        function getPokemonsBySet(setCode) {
            return $http.get(endPoint + "setCode=" + setCode);
        }
    }
})();