describe('tareasapp', function() {
	var vista;
				
	beforeEach(function() {
		vista = new tareas.TareasApp();
	});
				
	it('inicializar lista tareas', function() {
		vista.resetTareas([tareaNoCompletada, tareaCompletada]);					

		expect(2).toEqual($('#lista-tareas', vista.el).children().length);
		expect('1 tareas de 2 completadas').toEqual(trim($('#estado', vista.el).html()));
	});
	it('añadir una tarea', function() {
		vista.addTarea(tareaNoCompletada);
					
		expect(1).toEqual($('#lista-tareas', vista.el).children().length);
	});
	it('nueva tarea', function() {
		var input = $("input[name='nuevaTarea']", vista.el);					
		var e = $.Event('keypress');
		e.which = 13;
					
		input.val('Tarea');
		input.trigger(e);

		expect(1).toEqual($('#lista-tareas', vista.el).children().length);
		expect('').toEqual(input.val());
		expect(1).toEqual(server.requests.length);
	});
	it('limpiar tareas completadas', function() {
		vista.resetTareas([tareaNoCompletada, tareaCompletada]);
					
		var input = $("input[name='limpiar']", vista.el);									
		input.trigger('click');					
					
		expect('disabled').toEqual(input.attr('disabled'));
		expect(1).toEqual($('#lista-tareas', vista.el).children().length);
	});
	it('botón limpiar con tareas completadas', function() {
		vista.resetTareas([tareaCompletada]);
										
		var input = $("input[name='limpiar']", vista.el);
			
		expect(input.attr('disabled')).toBeUndefined();
	});
	it('botón limpiar con tareas no completadas', function() {
		vista.resetTareas([tareaNoCompletada]);
			
		var input = $("input[name='limpiar']", vista.el);
					
		expect('disabled').toEqual(input.attr('disabled'));
	});
	it('botón limpiar sin tareas', function() {
		var input = $("input[name='limpiar']", vista.el);
		expect('disabled').toEqual(input.attr('disabled'));
	});
});