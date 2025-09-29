package com.blogspot.elblogdepicodev.services;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CallableServiceImpl implements CallableService<Object> {

	private static Logger logger = LoggerFactory.getLogger(CallableServiceImpl.class);
 
	private ExecutorService executorService;

	public CallableServiceImpl(ExecutorService executorService) {
		this.executorService = executorService;
	}
 
	public ExecutorService getExecutorService() {
		return executorService;
	}


	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

	@Override
	public void submit(Callable<Object> tarea) {
		logger.info("Añadiendo una tarea a la cola (Clase: {})", tarea.getClass().getName());
		executorService.submit(tarea);
	}
 
	@Override
	public void submit(Collection<Callable<Object>> tareas) throws InterruptedException {
		logger.info("Añadiendo {} tareas a la cola", tareas.size());
		executorService.invokeAll(tareas);
	}
}