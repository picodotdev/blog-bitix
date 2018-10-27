package com.blogspot.elblogdepicodev.services;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public interface CallableService<T> {

	public void submit(Callable<T> callable);
	public void submit(Collection<Callable<T>> callables) throws InterruptedException;
}