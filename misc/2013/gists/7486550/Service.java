package com.blogspot.elblogdepicodev.services;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

...

public interface Service {

	...
	
	public CrudServiceDAO getCrudServiceDAO();

	public DataService getDataService();

	public CacheManager getCacheManager();

	public FreeMarkerService getFreemarkerService();

	public CallableService getCallableService();
	
	public SchedulerService getSchedulerService();

	//
	...

	//
	public void actualizarDatos(Evento evento);

	//
	public void enviarMail(String layout, String plantilla, Locale locale, String destinatario, String asunto, Map datos);

	public void enviarMail(String layout, String plantilla, Locale locale, Set destinatarios, String asunto, Map datos);
}