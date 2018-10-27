var Rectangulo = Backbone.Model.extend({
	defaults: {
		alto: 4,
		ancho: 3,
	},
	area: function() {
		return this.get('alto') * this.get('ancho');
	},
	toTemplateJSON: function() {
		var json = this.toJSON();
		json.area = this.area();
		return json;
	}
});