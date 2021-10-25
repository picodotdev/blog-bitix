describe('tareas', function() {
	it('marcar como completada', function() {
		var vista = new tareas.TareaView({model: tareaNoCompletada});
				
		var input = $("input[name='completada']", vista.el);
		input.attr('checked', true);
		input.trigger('change');
				
		expect(tareaNoCompletada.get('completada')).toBeTruthy();
		expect(1).toEqual(requests.length);
	});
	it('marcar como no completada', function() {
		var vista = new tareas.TareaView({model: tareaCompletada});
			
		var input = $("input[name='completada']", vista.el);
		input.attr('checked', false);
		input.trigger('change');
				
		expect(tareaCompletada.get('completada')).toBeFalsy();
		expect(1).toEqual(requests.length);
	});
});