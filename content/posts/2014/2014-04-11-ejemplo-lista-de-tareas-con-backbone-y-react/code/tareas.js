define('tareas', [ 'jquery', 'underscore', 'backbone', 'react', 'mustache', 'plantillas', 'i18n!i18n/nls/mensajes' ], function($, _, Backbone, React, Mustache, Plantillas, Mensajes) {
	function render(plantilla, datos, mensajes) {
		var d = datos || {};
		var m = mensajes || {};
		
		var vista = _.extend(d, {
			message: m
		});
		
		var p = Plantillas[plantilla];
		var pp = p();
		return pp(vista);
	}
	
	// An example generic Mixin that you can add to any component that should react
	// to changes in a Backbone component. The use cases we've identified thus far
	// are for Collections -- since they trigger a change event whenever any of
	// their constituent items are changed there's no need to reconcile for regular
	// models. One caveat: this relies on getBackboneModels() to always return the
	// same model instances throughout the lifecycle of the component. If you're
	// using this mixin correctly (it should be near the top of your component
	// hierarchy) this should not be an issue.
	var BackboneMixin = {
		componentDidMount: function() {
			// Whenever there may be a change in the Backbone data, trigger a reconcile.
			this.getBackboneModels().forEach(function(model) {
				model.on('add change remove reset', this.forceUpdate.bind(this, null), this);
			}, this);
		},
		componentWillUnmount: function() {
			// Ensure that we clean up any dangling references when the component is
			// destroyed.
			this.getBackboneModels().forEach(function(model) {
				model.off(null, null, this);
			}, this);
		}
	};

	var Tarea = Backbone.Model.extend({
		urlRoot : 'rest/tareas/tarea',
		defaults : {
			id : null,
			descripcion : '',
			completada : false
		},
		toogle: function() {
			this.set('completada', !this.get('completada'));
		}
	});

	var Tareas = Backbone.Collection.extend({
		url: 'rest/tareas',
		model: Tarea,
		findCompletadas: function() {
			return this.models.filter(function(tarea) {
				return tarea.get('completada');
			});
		},
		removeCompletadas: function() {
			_.each(this.findCompletadas(), function(tarea) {
				tarea.destroy();
			});
		}
	});

	var TareaComponent = React.createClass({
		componentDidMount: function() {
			var _this = this;
			this.ui = {
				completada: $('input[name=completada]', this.getDOMNode())
			};
			
			this.ui.completada.change(function(event) {
				_this.props.tarea.toogle();
				_this.props.tarea.save();
			});
		},		
		render: function() {
//			return (
//				<label className="checkbox">
//					<input type="checkbox" name="completada" checked={(this.props.tarea.get('completada'))?'checked':''}/> <span className={this.props.tarea.completada}>{this.props.tarea.get('descripcion')}</span>
//				</label>
//			);
			return React.DOM.label({className:'checkbox'},
				React.DOM.input({type:'checkbox', name:'completada', checked:(this.props.tarea.get('completada'))?'checked':''}),
				React.DOM.span({className:this.props.tarea.completada}, this.props.tarea.get('descripcion'))
			);
		}
	});
	
	var TareasComponent = React.createClass({
		render: function() {
		    var tareas = this.props.tareas.map(function(tarea) {
//		    	return (
//		    		<li><TareaComponent tarea={tarea}/></li>
//		    	);
				return React.DOM.li(null,
						TareaComponent({tarea:tarea})
				);
		    }, this);
//			return (
//				<ul>{tareas}</ul>				
//			);
			return React.DOM.ul(null, tareas);
		}
	});
	
	var EstadoComponent = React.createClass({
		render: function() {
			var d = this.getData();
			var m = {
				'COMPLETADAS_tareas_de_TOTAL_completadas': Mustache.render(Mensajes.COMPLETADAS_tareas_de_TOTAL_completadas, d),
				'Muy_bien_has_completado_todas_las_tareas': Mensajes.Muy_bien_has_completado_todas_las_tareas,
			};
			var estado = render('estado', d, m);
//			return (
//				<span className="estado">{estado}</span>
//			);
			return React.DOM.span({className:'estado'}, estado);
		},
		// Métodos
		getData: function() {
			var completadas = this.props.tareas.findCompletadas().length;
			var total = this.props.tareas.length;

			return {
				completadas: completadas,
				total: total
			};
		}
	});
	
	var TareasApp = React.createClass({
		mixins: [BackboneMixin],
		getBackboneModels: function() {
			return [this.state.tareas];
		},
		getInitialState: function() {
			return {tareas: new Tareas()};
		},
		componentDidMount: function() {
			var _this = this;
			this.ui = {
				nuevaTarea: $('input[name=nuevaTarea]', this.getDOMNode()),
				limpiar: $('input[name=limpiar]', this.getDOMNode())
			};
			
		    this.ui.nuevaTarea.focus();
		    
		    // Eventos
		    this.ui.nuevaTarea.keypress(function(event) {
				// Comprobar si la tecla pulsada es el return
				if (event.which == 13) {
					var descripcion = _this.ui.nuevaTarea.val().trim();

					// Comprobar si se ha introducido descripción de la tarea
					if (descripcion == '') {
						return;
					}

					// Añadir la tarea y limpiar el input
					var tarea = new Tarea({
						descripcion: descripcion,
						completada: false
					});
					_this.addTarea(tarea);
					_this.ui.nuevaTarea.val('');
				}
		    });
		    
		    this.ui.limpiar.click(function() {
				_this.removeTareasCompletadas();
		    });
		},
		render: function() {
//			return (
//				<div>
//					<h2>{Mensajes.Lista_de_tareas}</h2>
//					<input type="text" name="nuevaTarea" className="form-control" placeholder={Mensajes.Introduce_una_nueva_tarea} />
//					<TareasComponent tareas={this.state.tareas} />
//					<EstadoComponent tareas={this.state.tareas} />
//					<br/>
//					<input type="button" name="limpiar" value={Mensajes.Limpiar} disabled={(this.isTareasCompletadas())?null:'disabled'} className="btn" />
//				</div>
//			);
			return React.DOM.div(null,
					React.DOM.h2(null, Mensajes.Lista_de_tareas),
					React.DOM.input({type:'text', name:'nuevaTarea', className:'form-control', placeholder:Mensajes.Introduce_una_nueva_tarea}),
					TareasComponent({tareas:this.state.tareas}),
					EstadoComponent({tareas:this.state.tareas}),
					React.DOM.br(),
					React.DOM.input({type:'button', name:'limpiar', value:Mensajes.Limpiar, disabled:(this.isTareasCompletadas())?'':'disabled', className:'btn'})
			);
		},
		// Métodos
		isTareasCompletadas:function() {
			return this.state.tareas.findCompletadas().length > 0;
		},
		addTarea: function(tarea) {
			this.state.tareas.add(tarea);
			tarea.save();
		},
		removeTareasCompletadas: function() {
			this.state.tareas.removeCompletadas();
		},
		resetTareas: function(tareas) {
			this.state.tareas.reset(tareas);
		},
		fetch: function() {
			// Con reset:true solo se lanza un evento para todos los cambios que se produzcan en la colección
			this.state.tareas.fetch({reset:true});
		}
	});
	
	return {
		Tarea: Tarea,
		Tareas: Tareas,
		TareaComponent: TareaComponent,
		TareasComponent: TareasComponent,
		EstadoComponent: EstadoComponent,
		TareasApp: TareasApp
	};
});