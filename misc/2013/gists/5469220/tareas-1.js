var Tarea = Backbone.Model.extend({
	urlRoot : 'rest/tareas/tarea',
	defaults: {
		id: null,
		descripcion: '',
		completada: false
	},
	toPlantilla: function() {
		var json = this.toJSON();
		json.attrs = {
			checked: (this.get('completada')?'checked':null),
			completada: (this.get('completada')?'completada':null)
		};
	    return json;			
	}
});