(function () {
    angular
        .module('Pokepedia')
        .directive('statsBar', statsBarDir);

    function statsBarDir() {
        function linkFunc(scope, element) {
            $(document).ready(function () {
                let currentRating = element.data('current-rating');
                element.barrating('show', {
                    theme: 'bars-horizontal',
                    readonly: true,
                    initialRating: currentRating,
                    reverse: true,
                    showSelectedRating: true
                });
            });
        }

        return {
            link: linkFunc
        };
    }
})();
