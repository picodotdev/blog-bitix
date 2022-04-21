require(['jquery', 'lozad', 'cookieconsent'], function($, lozad, cookieconsent) {
    function initAnalytics() {
        ga('send', 'event', 'client', 'protocol', window.location.protocol.replace(new RegExp(':|/', 'gi'), ''), {'nonInteraction': 1});

        $('header nav a, footer div.menu a').on('click', function() {
            ga('send', 'event', 'link', 'click', 'menu');
        });

        $('ul.recents a').on('click', function() {
            ga('send', 'event', 'link', 'click', 'recent');
        });

        $('div.featured a').on('click', function() {
            ga('send', 'event', 'link', 'click', 'featured');
        });

        $('div.links a').on('click', function() {
            ga('send', 'event', 'link', 'click', 'links');
        });

        $('ins.adsbygoogle').on('mouseenter', function() {
            ga('send', 'event', 'ad', 'enter', $(this).attr('data-type'), {'nonInteraction': 1});
        });

        $('ins.adsbygoogle').on('click', function() {
            ga('send', 'event', 'ad', 'click', $(this).attr('data-type'));
        });

        $('div.sharethis').on('click', function() {
            ga('send', 'event', 'share-this', 'click', 'default');
        });

        $('div.related-article a').on('click', function() {
            ga('send', 'event', 'link', 'click', 'related');
        });

        $('ol.serie-articles a').on('click', function() {
            ga('send', 'event', 'link', 'click', 'serie');
        });

        var pageBottomObserver = lozad('#pageBottom', {
            rootMargin: '50px 0px',
            load: function(el) {
                const element = $('#pageBottom');
                if (element.attr('data-lozad') === 'observed') {
                    return;
                }
                element.attr('data-lozad', 'observed');
                ga('send', 'event', 'page', 'show', 'bottom', {'nonInteraction': 1});
            }
        });
        pageBottomObserver.observe();

        // Fix Google Analytics 4 image click for blueimp/Gallery
        $('a[data-gallery] img').on('click', function(event) {
            event.preventDefault();
        });
    }

    function initAdsense() {
        var n = $('body .container ins.adsbygoogle').length;
        for (var i = 0; i < n; ++i) {
            try {
                (adsbygoogle = window.adsbygoogle || []).push({});
            } catch (e) {}
        }

        checkAdblock();
    }

    function checkAdblock() {
        setTimeout(function() {
            var ads = $('body .container ins.adsbygoogle');
            var adblock = (ads.length > 0 && ads.html().replace(/\s/g, '').length == 0);
            ga('send', 'event', 'client', 'adblock', (adblock) ? 'true' : 'false', {'nonInteraction': 1});

            if (adblock) {
                var ad = ads.filter('[data-type="billboard"], [data-type="leaderboard"]').first();
                var html = [
                    '<div class="adblock-container">',
                    ' <div class="adblock">',
                    '  <p class="text-center"><strong><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> Parece que tienes activado un bloqueador de anuncios</strong></p>',
                    '  <p>Los anuncios de este blog <strong>no son intrusivos</strong> y con ellos hago <a href="https://picodotdev.github.io/blog-bitix/2015/12/yo-apoyo-al-software-libre-tu-tambien/">pequeñas donaciones al software libre</a>.</p>',
                    '  <p>Si no es por privacidad considera <a href="https://adblockplus.org/es/faq_basics#disable" target="_blank">desactivar el bloqueador de anuncios</a> en <strong>Blog Bitix</strong>.</p>',
                    ' </div>',
                    '</div>'
                ].join('');
                var dom = $(html);
                var element = $(ad).before(dom);
            }
        }, 3000);
    }

    function initCookieConsent() {
        cookieconsent.initialise({
            "palette": {
                "popup": {
                    "background": "#000000",
                    "text": "#ffffff"
                },
                "button": {
                    "background": "#61b135",
                    "text": "#000000"
                }
            },
            "theme": "classic",
            "content": {
                "message": "Esta bitácora te informa de la obviedad de que utiliza «cookies», propias y de servicios como Google Analytics y Google AdSense entre otros.",
                "dismiss": "Aceptar",
                "allow": "Aceptar",
                "link": "Política de cookies"
            }
        });
    }

    initAnalytics();
    initAdsense();
    initCookieConsent();
});