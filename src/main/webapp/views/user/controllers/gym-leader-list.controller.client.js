(function () {
    angular
        .module("Pokepedia")
        .controller("GymLeaderListController", gymLeaderListController);

    function gymLeaderListController($routeParams, UserService, PokemonService, GymLeaderService, UtilService, $location) {
        let vm = this;
        vm.pokemons = [];

        function init() {
            UserService.findCurrentUser()
                .then(response => {
                    vm.user = response.data;
                    return GymLeaderService.findAllGymLeaders();
                }, (error) => {
                    console.log(error);
                })
                .then(response => {
                    vm.gymLeaders = response.data;
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

    }
})();
