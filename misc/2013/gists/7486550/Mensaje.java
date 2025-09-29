package com.blogspot.elblogdepicodev.misc;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class Mensaje {

	private String plantilla;
	private Locale locale;
	private Set<String> desinatarios;
	private String asunto;
	private Map datos;

	public Mensaje(String plantilla, Locale locale, String destinatario, String asunto, Map datos) {
		this(plantilla, locale, Collections.singleton(destinatario), asunto, datos);
	}

	public Mensaje(String plantilla, Locale locale, Set<String> destinatarios, String asunto, Map datos) {
		this.plantilla = plantilla;
		this.locale = locale;
		this.desinatarios = destinatarios;
		this.asunto = asunto;
		this.datos = datos;
	}

	public String getPlantilla() {
		return plantilla;
	}

	public Locale getLocale() {
		return locale;
	}

	public Set<String> getDesinatarios() {
		return desinatarios;
	}

	public String getAsunto() {
		return asunto;
	}

	public Map getDatos() {
		return datos;
	}
}