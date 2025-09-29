describe('modelo tarea', function() { 
	it('no completado', function() {
		var t = tareaNoCompletada.toPlantilla();
		expect(t.descripcion).toEqual('Tarea');
		expect(t.completada).toBeFalsy();
		expect(t.attrs.checked).toBeNull();
		expect(t.attrs.completada).toBeNull();
	});
	it('completado', function() {
		var t = tareaCompletada.toPlantilla();
		expect(t.descripcion).toEqual('Tarea');
		expect(t.completada).toBeTruthy();
		expect(t.attrs.checked).toEqual('checked');
		expect(t.attrs.completada).toEqual('completada');
	});
});