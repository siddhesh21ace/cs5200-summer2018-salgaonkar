(function () {
    angular
        .module('Pokepedia')
        .directive('pageScroll', pageScrollDir);

    function pageScrollDir() {
        function linkFunc(scope, element) {
            element.on('click', function () {
                $(".navbar-toggle:visible").click();
                if (location.pathname.replace(/^\//, '') === this.pathname.replace(/^\//, '')
                    && location.hostname === this.hostname) {
                    let target = $(this.hash);
                    target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
                    if (target.length) {
                        $('html,body').animate({
                            scrollTop: target.offset().top - 40
                        }, 900);
                        return false;
                    }
                }
            });
        }

        return {
            link: linkFunc
        };
    }
})();
