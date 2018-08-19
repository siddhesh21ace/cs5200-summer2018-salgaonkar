(function () {
    angular.module("Pokepedia")
        .service("CardService", cardService);

    function cardService($http) {
        return {
            "fetchAllCards": fetchAllCards,
            "fetchCardByPokedexNumber": fetchCardByPokedexNumber
        };

        function fetchAllCards(cardCriteria) {
            return $http.post("/pokemontcgapi/card", cardCriteria);
        }

        function fetchCardByPokedexNumber(cardCriteria) {
            return $http.post("/pokemontcgapi/card", cardCriteria);
        }
    }
})();