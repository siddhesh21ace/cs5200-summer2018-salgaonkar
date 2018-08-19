(function () {
    angular
        .module("Pokepedia")
        .config(configuration)
        .run(setPageTitle);

    function configuration($routeProvider) {
        $routeProvider
            .when("/", {
                templateUrl: 'views/landing-page/templates/home.view.client.html',
                controller: 'HomeController',
                controllerAs: 'model',
                title: 'Home'
            })
            .when("/login", {
                templateUrl: 'views/user/templates/login.view.client.html',
                controller: 'LoginController',
                controllerAs: 'model',
                title: 'Login'
            })
            .when("/register", {
                templateUrl: 'views/user/templates/register.view.client.html',
                controller: 'RegisterController',
                controllerAs: 'model',
                title: 'Register'
            })
            .when("/trainer/:tid", {
                templateUrl: 'views/user/templates/trainer.view.client.html',
                controller: 'TrainerController',
                controllerAs: 'model',
                title: 'Profile',
                resolve: {
                    isLoggedIn: isLoggedIn
                }
            })
            .when("/trainer/:tid/order", {
                templateUrl: 'views/order/templates/order.view.client.html',
                controller: 'OrderController',
                controllerAs: 'model',
                title: 'Order',
                resolve: {
                    isLoggedIn: isLoggedIn
                }
            })
            .when("/gym-leader/:gid", {
                templateUrl: 'views/user/templates/gym-leader.view.client.html',
                controller: 'GymLeaderController',
                controllerAs: 'model',
                title: 'Profile',
                resolve: {
                    isLoggedIn: isLoggedIn
                }
            })
            .when("/pokedex", {
                templateUrl: 'views/pokedex/templates/pokedex.view.client.html',
                controller: 'PokedexController',
                controllerAs: 'model',
                title: 'Pokédex'
            })
            .when("/pokemon-info/:pokemon", {
                templateUrl: 'views/pokedex/templates/pokemon-info.view.client.html',
                controller: "PokemonInfoController",
                controllerAs: 'model',
                title: 'Pokémon Details'
            })
            .when("/trainer/:tid/gym-leader", {
                templateUrl: 'views/user/templates/gym-leader-list.view.client.html',
                controller: 'GymLeaderListController',
                controllerAs: 'model',
                title: 'Gym Leaders',
                resolve: {
                    isLoggedIn: isLoggedIn
                }
            })
            .when("/trainer/:tid/gym-leader/:gid", {
                templateUrl: 'views/pokemon/templates/game.view.client.html',
                controller: 'GameController',
                controllerAs: 'model',
                title: 'Game',
                resolve: {
                    isLoggedIn: isLoggedIn
                }
            })
            .when("/admin", {
                templateUrl: 'views/user/templates/admin.view.client.html',
                controller: 'AdminController',
                controllerAs: 'model',
                resolve: {
                    checkAdmin: checkAdmin
                },
                title: 'Admin'
            })
            .otherwise({
                redirectTo: "/"
            });
    }

    function checkAdmin($q, UserService, $location) {
        let deferred = $q.defer();
        UserService
            .isAdmin()
            .then(response => {
                let user = response.data;
                if (user) {
                    deferred.resolve();
                } else {
                    deferred.reject();
                    $location.url('/login');
                }
            });
        return deferred.promise;
    }

    /*Check if user is logged in or not*/
    function isLoggedIn($q, UserService, $location, $rootScope) {
        let deferred = $q.defer();
        UserService
            .isLoggedIn()
            .then(response => {
                    $rootScope.errorMessage = null;
                    let user = response.data;
                    if (user !== '0') {
                        deferred.resolve();
                    } else {
                        deferred.reject();
                        $location.url("/login");
                    }
                }
            );
        return deferred.promise;
    }

    /*Set Page Title*/
    function setPageTitle($rootScope, $location) {
        $rootScope._ = _;
        $rootScope.$on('$routeChangeSuccess', (event, current) => {
            $rootScope.title = current.$$route.title;
            $rootScope.isLocal = $location.$$absUrl.indexOf('localhost') > -1;
        });
    }

})();