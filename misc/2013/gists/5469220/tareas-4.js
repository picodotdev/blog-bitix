var TareasApp = Backbone.View.extend({
	events: {
       	       	"keypress input[name='nuevaTarea']": 'onKeypressNuevaTarea',
       	       	"click input[name='limpiar']": 'onClickLimpiar'
   	},
        initialize: function() {
		_.bindAll(this);
        	
		this.tareas = new Tareas();			
		this.tareas.on('add', this.render);
		this.tareas.on('remove', this.render);
		this.tareas.on('change', this.render);
		this.tareas.on('reset', this.reset);

    	       	var texto = render('#tareas-template', null);
       	    	$(this.el).html(texto);
		this.render();
        },
    	render: function() {
    		var completadas = this.tareas.findCompletadas().length; 
    		var total = this.tareas.length;
    		
    		// Habilitar/deshabilitar el botón de limpiar tareas completadas
    		if (completadas == 0) {
    			$("input[name='limpiar']", this.el).attr('disabled', 'disabled');
    		} else {
    			$("input[name='limpiar']", this.el).removeAttr('disabled');
    		}
    		
    		// Cambiar el mensaje de estado de las tareas
    		var texto = render('#estado-template', {completadas: completadas, total: total});
    		$('#estado', this.el).html(texto);
    	},
        reset: function() {
        	this.tareas.each(function(o) {
			var vista = new TareaView({model: o});
			$('#lista-tareas', this.el).append(vista.el);
		}, this);
        	this.render();
        },
    	// Métodos
    	addTarea: function(tarea) {
    		tarea.save();
    		this.tareas.add(tarea);
    		
    		var vista = new TareaView({model: tarea});
    		$('#lista-tareas', this.el).append(vista.el);
    	},
    	resetTareas: function(tareas) {
    		this.tareas.reset(tareas);
    	},
    	fetch: function() {
    		this.tareas.fetch();
    	},
    	// Eventos
    	onKeypressNuevaTarea: function(event) {
  		// Comprobar si la tecla pulsada es el return
    		if (event.which == 13) {
    			var input = $("input[name='nuevaTarea']", this.el);
    			var descripcion = input.val();
    			descripcion = trim(descripcion);

    			// Comprobar si se ha introducido descripción de la tarea
    			if (descripcion == '') {
    				return;
    			}
    			
    			// Añadir la tarea y limpiar el input
    			var tarea = new Tarea({ descripcion: descripcion, completada: false });
    			this.addTarea(tarea);
    			input.val('');
    		}
    	},
    	onClickLimpiar: function() {
    		this.tareas.removeCompletadas();
    	}
});