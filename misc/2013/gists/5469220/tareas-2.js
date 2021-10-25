var Tareas = Backbone.Collection.extend({
	url: 'rest/tareas',
	model: Tarea,
	findCompletadas: function() {
		return this.models.filter(function(o) {
			return o.get('completada');
		});
	},
	removeCompletadas: function() {
		_.each(this.findCompletadas(), function(o) {
			o.destroy();
		});
	}
});