require(['jquery', 'lozad', 'jquery-blueimp-gallery'], function ($, lozad, blueimp) {webp
    function relUrl(path) {
        return $('meta[name="base"]').attr('href') + path;
    }

    function initBackground() {
        // Background
        function random(min, max) {
            return Math.floor(Math.random() * (max - min + 1) + min);
        }

        var spec = {
            backgrounds: [
                'asanoha.webp',
                'back.webp',
                'circles-and-roundabouts.webp',
                'confectionary.webp',
                'contemporary_china_2.webp',
                'corrugation.webp',
                'contemporary_china.webp',
                'diamond_upholstery.webp',
                'dimension.webp',
                'doodles.webp',
                'eight_horns.webp',
                'escheresque.webp',
                'geometry2.webp',
                'geometry.webp',
                'gplay.webp',
                'grey.webp',
                'lyonnette.webp',
                'memphis-colorful.webp',
                'paisley.webp',
                'photography.webp',
                'playstation.webp',
                'pow-star.webp',
                'psychedelic.webp',
                'pyramid.webp',
                'reticular_tissue.webp',
                'sayagata.webp',
                'skulls.webp',
                'swirl.webp',
                'symphony.webp',
                'tiny_grid.webp',
                'topography.webp',
                'tree_bark.webp',
                'upfeathers.webp'
            ]
        };

        var i = random(0, spec.backgrounds.length - 1);
        var b = spec.backgrounds[i];
        $('body').css('background-image', 'url("' + relUrl('assets/images/backgrounds/' + b) + '")');
    }

    function initSearch() {
        $("form[name='query']").on('submit', function() {
            var query = $("input[name='q']", "form[name='query']").val();
            var site = $("form[name='search']").data('site');
            $("input[name='q']", "form[name='search']").val(query + ' site:' + site);
            $("form[name='search']").submit();
            return false;
        });
    }

    function initLazyload() {
        var observer = lozad('.lozad', {
            rootMargin: '50px 0px'
        });

        var disqusObserver = lozad('#disqus_thread', {
            rootMargin: '50px 0px',
            load: function(el) {
                $.ajax({
                    url: '//' + disqus_shortname + '.disqus.com/' + disqus_script,
                    async: true,
                    cache: true,
                    dataType: 'script',
                    success: function() {}
                });
            }
        });

        var shareThisObserver = lozad('div.sharethis-inline-share-buttons', {
            rootMargin: '50px 0px',
            load: function(el) {
                $.ajax({
                    url: '//platform-api.sharethis.com/js/sharethis.js#property=5920c4ce1bd0670011e06acd&product=inline-share-buttons',
                    async: true,
                    cache: true,
                    dataType: 'script',
                    success: function() {}
                });
            }
        });

        var twitterObserver = lozad('blockquote.twitter-tweet', {
            rootMargin: '50px 0px',
            load: function(el) {
                $.ajax({
                    url: '//platform.twitter.com/widgets.js',
                    async: true,
                    cache: true,
                    dataType: 'script',
                    success: function() {}
                });
            }
        });

        observer.observe();
        disqusObserver.observe();
        shareThisObserver.observe();
        twitterObserver.observe();
    }

    initBackground();
    initSearch();
    initLazyload();
});

