package es.com.blogspot.elblogdepicodev.resteasy;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public class HelloWorldResourceImpl implements HelloWorldResource {

	@Override
	public String getSaluda() {
		return "¡Hola mundo!";
	}
	
	@Override
	public String getSaludaA(String nombre) {
		return MessageFormat.format("¡Hola {0}!", nombre);
	}
}