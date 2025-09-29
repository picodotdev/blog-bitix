var datos = {
  nombres: ['Luis', 'Juan', 'Pedro'];
};

var plantilla = 'Hola:\n{{#nombres}}{{.}}\n{{/nombres}}';

var texto = Mustache.render(plantilla, datos);

assert(texto == 'Hola:\nLuis\nJuan\nPedro\n');