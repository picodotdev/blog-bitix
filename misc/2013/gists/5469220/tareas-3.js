var TareaView = Backbone.View.extend({
	tagName: 'li',
	className: 'tarea',
	events: {
       	       	"change input[name='completada']": 'onChangeCompletada'
       	},
       	initialize: function() {
		_.bindAll(this);
        	
		this.model.on('change', this.render);
		this.model.on('remove', this.remove);
		this.render();
        },
    	render: function() {
    		var texto = render('#tarea-template', this.model.toPlantilla());
    		$(this.el).html(texto);
    	},
    	// Eventos
    	onChangeCompletada: function() {
    		var completada = $("input[name='completada']", this.el).is(':checked');
    		this.model.set('completada', completada);
    		this.model.save();
    	}
});