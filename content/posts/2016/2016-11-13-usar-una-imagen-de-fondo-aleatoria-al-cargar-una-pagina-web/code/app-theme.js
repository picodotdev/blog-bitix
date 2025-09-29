define(['jquery'], function($) {
    // ...
    function random(min, max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    }

    var spec = {
        backgrounds: [
           'confectionary.webp',
           'dimension.webp',
           'skulls.webp',
           'tree_bark.webp',
           'contemporary_china_2.webp',
           'eight_horns.webp',
           'swirl_pattern.webp',
           'upfeathers.webp',
           'contemporary_china.webp',
           'paisley.webp',
           'symphony.webp'
        ]
    };

    var i = random(0, spec.backgrounds.length - 1);
    var b = spec.backgrounds[i];
    $('body').css('background-image', 'url("assets/images/backgrounds/' + b + '")');
    // ...
});