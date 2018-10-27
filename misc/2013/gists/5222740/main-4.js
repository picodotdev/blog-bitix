var datos = {
  nombres: ['Luis', 'Juan', 'Pedro']
};

var plantilla = 'Hola:\n{{#nombres}}{{>nombre}}{{/nombres}}';
var parciales = {nombre: '{{.}}\n'};

var texto = Mustache.render(plantilla, datos, parciales);

assert(texto == 'Hola:\nLuis\nJuan\nPedro\n');