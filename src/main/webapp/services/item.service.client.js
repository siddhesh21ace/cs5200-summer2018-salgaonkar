(function () {
    angular
        .module("Pokepedia")
        .factory('ItemService', itemService);

    function itemService($http) {
        return {
            "findAllItems": findAllItems,
            "createOrderFromBundle": createOrderFromBundle
        };

        function findAllItems() {
            return $http.get("/api/item");
        }

        function createOrderFromBundle(items) {
            return $http.post("/api/order/bundle", items);
        }
    }
})();
