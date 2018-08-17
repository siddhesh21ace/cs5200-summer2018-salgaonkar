(function () {
    angular
        .module('Pokepedia')
        .directive('theater', theaterDir);

    function theaterDir() {
        function linkFunc(scope, element) {
            let id = element.attr("id");
            let theater = theaterJS();

            theater
                .on('type:start, erase:start', function () {
                    let actor = theater.getCurrentActor();
                    actor.$element.classList.add('blinking-caret');
                })
                .on('type:end, erase:end', function () {
                    let actor = theater.getCurrentActor();
                    actor.$element.classList.remove('blinking-caret');
                });

            theater
                .addActor(id, {accuracy: 0.6, speed: 0.6, invincibility: 20})
                .addScene(id + ":Gotta Catch 'Em All - ", 500)
                .addScene('Pikachu', 700, -7)
                .addScene('Charizard', 900, -9)
                .addScene('Squirtle', 800, -8)
                .addScene('Bulbasaur', 900, -9)
                .addScene('Mewtwo', 600, -6)
                .addScene('Dragonite', 900, -9)
                .addScene('Umbreon', 700, -7)
                .addScene('Magmar', 600, -6)
                .addScene('Rayquaza', 800, -8)
                .addScene(theater.replay);
        }

        return {
            link: linkFunc
        };
    }
})();
