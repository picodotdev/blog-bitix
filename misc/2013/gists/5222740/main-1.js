var datos = {
  nombre: 'picodotdev'		
};

var plantilla = '¡Hola {{nombre}}!';

var texto = Mustache.render(plantilla, datos);

assert(texto == '¡Hola picodotdev!');