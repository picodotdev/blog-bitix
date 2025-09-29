package com.blogspot.elblogdepicodev.services;

...

public class ServiceImpl implements Service {

	private static Logger logger = LoggerFactory.getLogger(ServiceImpl.class);

	private CrudServiceDAO crudServiceDAO;
	private DataService dataService;
	private CacheManager cacheManager;
	private FreeMarkerService freemarkerService;
	private CallableService callableService;
	private SchedulerService schedulerService;

	...

	public ServiceImpl(CrudServiceDAO crudServiceDAO, DataService dataService, CacheManager cacheManager, FreeMarkerService freemarkerService,
			CallableService callableService, SchedulerService schedulerService, DatosPlantillaSource datosPlantillaSource) {
		this.crudServiceDAO = crudServiceDAO;
		this.dataService = dataService;
		this.cacheManager = cacheManager;
		this.freemarkerService = freemarkerService;
		this.callableService = callableService;
		this.schedulerService = schedulerService;

		this.datosPlantillaSource = datosPlantillaSource;
	}

	@Override
	public CrudServiceDAO getCrudServiceDAO() {
		return crudServiceDAO;
	}

	@Override
	public DataService getDataService() {
		return dataService;
	}

	@Override
	public CacheManager getCacheManager() {
		return cacheManager;
	}

	@Override
	public FreeMarkerService getFreemarkerService() {
		return freemarkerService;
	}

	@Override
	public CallableService getCallableService() {
		return callableService;
	}

	@Override
	public SchedulerService getSchedulerService() {
		return schedulerService;
	}

	...

	@Override
	public void reinicializarContrasena(Usuario usuario) {
		logger.info("Enviando solicitud de reinicialización de contraseña para el usuario {}", usuario.getId());

		// Preparar la reinicialización de la contraseña
		usuario.setTokenReinicializarContrasena(RandomStringUtils.randomAlphanumeric(Constantes.LONGITUD_TOKEN));
		usuario.setFechaSolicitudReinicializarContrasena(DateTime.now());
		crudServiceDAO.update(usuario);

		// Enviar correo electrónico de solicitud
		Locale l = Utilidades.decodeLocale(usuario.getIdioma());
		Map datos = Utilidades.map("usuario", usuario);
		enviarMail(LAYOUT_PRINCIPAL, PLANTILLA_EMAIL_SOLICITUD_REINICIALIZAR_CONTRASENA, l, usuario.getEmail(), "Solicitud_de_reinicializacion_de_contrasena", datos);
	}

	@Override
	public boolean confirmarReinicializarContrasena(Usuario usuario, String token) {
		// Validar el cambio de contraseña
		DateTime d = DateTime.now().minusDays(Constantes.DIAS_VALIDEZ_SOLICITUD_CAMBIO_CONTRASENA);

		boolean b1 = usuario.getTokenReinicializarContrasena() != null && usuario.getTokenReinicializarContrasena().equals(token);
		boolean b2 = usuario.getFechaSolicitudReinicializarContrasena() != null && usuario.getFechaSolicitudReinicializarContrasena().isAfter(d);
		boolean b = b1 && b2;
		if (b) {
			logger.info("Reinicializando contraseña para el usuario {}", usuario.getId());

			// Realizar el cambio de contraseña
			String contrasena = RandomStringUtils.randomAlphanumeric(Constantes.LONGITUD_TOKEN);

			usuario.setTokenReinicializarContrasena(null);
			usuario.setFechaSolicitudReinicializarContrasena(null);
			usuario.setContrasena(DigestUtils.md5Hex(contrasena));
			crudServiceDAO.update(usuario);

			// Enviar correo electrónico de confirmación
			Locale l = Utilidades.decodeLocale(usuario.getIdioma());
			Map datos = Utilidades.map("usuario", usuario, "contrasena", usuario.getContrasena());
			enviarMail(LAYOUT_PRINCIPAL, PLANTILLA_EMAIL_CONTRASENA_REINICIALIZADA, l, usuario.getEmail(), "Contrasena_reinicizalizada", datos);
		} else {
			logger.info("Reinicializanción de contraseña para el usuario {} fallida (Token: {}, Fecha: {})", new Object[] { usuario.getId(), b1, b2 });
		}

		return b;
	}

	...

	@Override
	public void actualizarDatos(Evento evento) {
		Callable c = CallableFactory.createActualizarDatosEventoCallable(this, evento);
		callableService.submit(c);
	}

	...

	public void enviarMail(String layout, String plantilla, Locale locale, String destinatario, String asunto, Map datos) {
		enviarMail(layout, plantilla, locale, Collections.singleton(destinatario), asunto, datos);
	}

	public void enviarMail(String layout, String plantilla, Locale locale, Set destinatarios, String asunto, Map datos) {
		// Enviar correo electrónico de la solicitud
		String a = ServiciosUtils.getBundle(locale).getString(asunto);

		DatosPlantilla ddp = datosPlantillaSource.getDatos(layout);
		DatosPlantilla dp = datosPlantillaSource.getDatos(plantilla);

		dp.setDatosPlantilla(ddp.getMap());
		dp.setDatos(datos);

		Mensaje m = new Mensaje(plantilla, locale, destinatarios, a, dp.getMap());

		Callable c = CallableFactory.createEnviarEmailCallable(this, m);
		callableService.submit(c);
	}

	...
}