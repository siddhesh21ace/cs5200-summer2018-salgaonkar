(function () {
    angular.module("Pokepedia")
        .service("PokemonTypeService", pokemonTypeService);

    function pokemonTypeService($http) {
        var endPoint = "/api/card?";
        var setsURL = "https://api.pokemontcg.io/v1/sets";

        var api = {
            "getAllSets": getAllSets,
            "getPokemonsBySet": getPokemonsBySet
        }
        return api;

        /* Testing Purpose for now */
        function getAllSets() {
            return $http.get(setsURL);
        }

        function getPokemonsBySet(setCode) {
            return $http.get(endPoint + "setCode=" + setCode);
        }
    }
})();