var datos = {
  nombre: null
};

var plantilla = '¡Hola{{#nombre}} {{nombre}}{{/nombre}}!';

var texto = Mustache.render(plantilla, datos);

assert(texto == '¡Hola!');