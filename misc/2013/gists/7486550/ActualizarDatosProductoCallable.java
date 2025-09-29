package com.blogspot.elblogdepicodev.services.commands;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blogspot.elblogdepicodev.domain.Producto;
import com.blogspot.elblogdepicodev.services.Service;

class ActualizarDatosProductoCallable implements Callable<Object> {

	private static Logger logger = LoggerFactory.getLogger(ActualizarDatosProductoCallable.class);

	private Service service;
	private Producto producto;

	public ActualizarDatosProductoCallable(Service service, Producto producto) {
		this.service = service;
		this.producto = producto;
	}

	@Override
	public Object call() {
		long start = System.currentTimeMillis();

		logger.info("Actualizando datos del producto ({})", producto.getId());
  
		Producto p = service.getCrudServiceDAO().get(Producto.class, producto.getId());
  
		// Recalcular datos
		...
  
		service.getCrudServiceDAO().update(p);
  
		long end = System.currentTimeMillis();
		logger.info("Datos del producto actualizados ({}) [{} ms]", p.getId(), end - start);
  
		return null;
	}
}