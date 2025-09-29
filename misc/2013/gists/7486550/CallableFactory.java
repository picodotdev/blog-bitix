package com.blogspot.elblogdepicodev.services.commands;

import java.util.concurrent.Callable;

import com.blogspot.elblogdepicodev.domain.Producto;
import com.blogspot.elblogdepicodev.misc.Mensaje;
import com.blogspot.elblogdepicodev.services.Service;

public class CallableFactory {

	private CallableFactory() {
	}

	public static Callable<Object> createActualizarDatosProductoCallable(Service service, Producto producto) {
		return new ActualizarDatosProductoCallable(service, producto);
	}

	public static Callable<Object> createEnviarEmailCallable(Service service, Mensaje mensaje) {
		return new EnviarEmailCallable(service, mensaje);
	}
}