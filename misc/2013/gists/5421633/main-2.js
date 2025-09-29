var Vista = Backbone.View.extend({
	el: '#area',
	events: {
		"change input[name='alto']": 'onChangeAlto',
		"change input[name='ancho']": 'onChangeAncho'
	},

	initialize: function() {
		_.bindAll(this, 'render', 'onChangeAlto', 'onChangeAncho');
			
		this.model = new Rectangulo();			
		this.model.on('change', this.render);

		this.render();
	},

	onChangeAlto: function() {
		var v = parseInt($("input[name='alto']", this.el).val());
		this.model.set('alto', v);
	},

	onChangeAncho: function() {
		var v = parseInt($("input[name='ancho']", this.el).val());
		this.model.set('ancho', v);
	},

	render: function() {
		$("input[name='alto']", this.el).val(this.model.get('alto'));
		$("input[name='ancho']", this.el).val(this.model.get('ancho'));

		var texto = Mustache.render('El área de un rectángulo de alto {{alto}} y ancho {{ancho}} es <b>{{area}}</b>', this.model.toTemplateJSON());
		$('#resultado', this.el).html(texto);
	},
});