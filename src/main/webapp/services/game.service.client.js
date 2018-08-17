(function () {
    angular
        .module("Pokepedia")
        .factory("GameService", gameService);

    function gameService($http) {
        var api = {
            "createGame": createGame,
            "updateGame": updateGame,
            "findGamesByUser": findGamesByUser
        };
        return api;

        function createGame(userID, game) {
            //delete game.player1Turn;
            return $http.post("/api/user/" + userID + "/game", game);
        }

        function findGamesByUser(userID) {
            return $http.get("/api/user/" + userID + "/game");
        }

        function updateGame(gameID, updatedGame) {
            return $http.put("/api/game/" + gameID, updatedGame);
        }
    }
})();