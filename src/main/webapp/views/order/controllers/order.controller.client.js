(function () {
    angular
        .module("Pokepedia")
        .controller("OrderController", orderController);

    function orderController($routeParams, UserService, PokemonService, TrainerService, UtilService,
                             ItemService) {
        let vm = this;
        vm.items = [];

        vm.order = order;

        function init() {
            UserService.findCurrentUser()
                .then(response => {
                    vm.user = response.data;
                    return TrainerService.findTrainerById(vm.user.id);
                }, (error) => {
                    console.log(error);
                })
                .then(response => {
                    let trainerData = response.data;
                    vm.user.coins = trainerData.coins;
                    vm.user.points = trainerData.points;
                    vm.user.berries = trainerData.berries;
                    vm.user.potion = trainerData.potion;

                    return ItemService.findAllItems();
                }, (error) => {
                    console.log(error);
                })
                .then(response => {
                    vm.items = response.data;
                    $("#profile").fadeOut("slow");
                    $("#loading").delay(200).fadeOut("slow").remove();
                }, (error) => {
                    console.log(error);
                });

            $("#myModal1").modal('hide');
            $('body').removeClass('modal-open');
            $('.modal-backdrop').remove();
        }

        init();

        function order(items) {
            vm.error = "";
            vm.success = "";

            console.log(items);

            let cost = 0.0;
            let berriesCount = 0;
            let potionCount = 0;

            items.forEach(item => {
                cost += item.quantity * item.price;
                if (item.itemType === "BERRY") {
                    berriesCount = Number(item.quantity);
                } else {
                    potionCount = Number(item.quantity);
                }
            });

            if (cost > vm.user.coins) {
                vm.error = "Insufficient Balance";
                return;
            }

            ItemService.createOrderFromBundle(items)
                .then(response => {
                    console.log(response.data);
                    let order = response.data;
                    return TrainerService.addOrder(vm.user, order);
                }, error => {
                    console.log(error);
                })
                .then(response => {
                    console.log(response.data);
                    vm.user.coins -= cost;
                    vm.user.berries += berriesCount;
                    vm.user.potion += potionCount;
                    return TrainerService.updateTrainer(vm.user);
                }, error => {
                    console.log(error);
                })
                .then(response => {
                    console.log(response);
                    vm.success = "Order placed successfully";
                })
        }

    }
})();
