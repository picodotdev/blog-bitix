var require = {
    paths: {
        'jquery': '../libs/jquery-3.6.0.min',
        'bootstrap': '../libs/bootstrap-5.0.1-dist/js/bootstrap.min',
        'bowser': '../libs/bowser-2.10.0-es5',
        'lozad': '../libs/lozad-1.16.0.min',
        'blueimp-helper': '../libs/Gallery-3.3.0/js/blueimp-helper',
        'blueimp-gallery': '../libs/Gallery-3.3.0/js/blueimp-gallery',
        'blueimp-gallery-indicator': '../libs/Gallery-3.3.0/js/blueimp-gallery-indicator',
        'blueimp-gallery-video': '../libs/Gallery-3.3.0/js/blueimp-gallery-video',
        'blueimp-gallery-vimeo': './libs/Gallery-3.3.0/js/blueimp-gallery-vimeo',
        'blueimp-gallery-youtube': '../libs/Gallery-3.3.0/js/blueimp-gallery-youtube',
        'jquery-blueimp-gallery': '../libs/Gallery-3.3.0/js/jquery.blueimp-gallery',
        'cookieconsent': 'https://cdn.jsdelivr.net/npm/cookieconsent@3/build/cookieconsent.min'
    },
    shim: {
        'jquery': {
            exports: 'jQuery'
        },
        'blueimp-gallery': {
            exports: 'blueimp'
        },
        'cookieconsent': {
            exports: 'cookieconsent'
        },
    }
};