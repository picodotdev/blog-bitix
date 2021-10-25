describe('modelo tareas', function() {
	var modelos;
		
	beforeEach(function() {
		modelos = [];
		modelos.push(tareaNoCompletada);
		modelos.push(tareaCompletada);
	});

	it('búsqueda completadas', function() {
		var modelo = new tareas.Tareas();
		modelo.reset(modelos);

		expect(1).toEqual(modelo.findCompletadas().length);				
	});
	it('eliminar completadas', function() {
		var modelo = new tareas.Tareas();
		modelo.reset(modelos);

		modelo.removeCompletadas();
			
		expect(1).toEqual(modelo.length);
		expect(0).toEqual(modelo.findCompletadas().length);
	});
});