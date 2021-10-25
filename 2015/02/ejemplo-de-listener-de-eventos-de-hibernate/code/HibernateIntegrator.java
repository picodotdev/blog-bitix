package es.com.blogspot.elblogdepicodev.plugintapestry.services.hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.metamodel.source.MetadataImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

import es.com.blogspot.elblogdepicodev.plugintapestry.services.spring.DummyService;

public class HibernateIntegrator implements Integrator {

	@Override
	public void integrate(Configuration configuration, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        // As you might expect, an EventListenerRegistry is the place with which event listeners are registered. It is a service
        // so we look it up using the service registry
        final EventListenerRegistry eventListenerRegistry = serviceRegistry.getService(EventListenerRegistry.class);

        ProductoEventAdapter pea = new ProductoEventAdapter();
        pea.setDummy(new DummyService());
        
        eventListenerRegistry.setListeners(EventType.PRE_INSERT, pea);
        eventListenerRegistry.setListeners(EventType.PRE_UPDATE, pea);
        eventListenerRegistry.setListeners(EventType.PRE_DELETE, pea);
        eventListenerRegistry.setListeners(EventType.POST_INSERT, pea);
        eventListenerRegistry.setListeners(EventType.POST_UPDATE, pea);
        eventListenerRegistry.setListeners(EventType.POST_INSERT, pea);
	}

	@Override
	public void integrate(MetadataImplementor metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
	}

	@Override
	public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
	}
}