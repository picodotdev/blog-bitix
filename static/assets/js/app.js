define(['jquery'], function($) {
  // Analytics
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

  $('div.share-this-begin').on('click', function() {
    ga('send', 'event', 'share-this', 'click', 'begin');
  });

  $('div.share-this-end').on('click', function() {
    ga('send', 'event', 'share-this', 'click', 'end');
  });

  setTimeout(function() {
    var ads = $('ins.adsbygoogle');
    var adblock = (ads.length > 0 && ads.html().replace(/\s/g, '').length == 0);
    ga('send', 'event', 'client', 'adblock', (adblock) ? 'true' : 'false', {'nonInteraction': 1});

    if (adblock) {
      ads.each(function(i, e) {
        var html = [
          '<div style="' + $(e).attr('style') + '; height: 100%; text-align: left;" class="adblock">',
          ' <p class="text-center"><strong><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> Parece que tienes activado un bloqueador de anuncios</strong></p>',
          ' <p>Los anuncios de este blog <strong>no son intrusivos</strong> y con ellos hago <a href="https://picodotdev.github.io/blog-bitix/2015/12/yo-apoyo-al-software-libre-tu-tambien/">pequeñas donaciones al software libre</a>. Considera <a href="https://adblockplus.org/es/faq_basics#disable" target="_blank">desactivar el bloqueador de anuncios</a> en <strong>Blog Bitix</strong>.</p>',
          ' <p></p>',
          '</div>'
        ].join('');
        var dom = $(html);
        var element = $(e).after(dom);
      });
    }
  }, 3000);
});