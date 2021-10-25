package io.github.picodotdev.blogbitix.dddhexagonal.catalog.application.query;

...

@Component
public class GetEventQueryHandler implements QueryHandler<Event, GetEventQuery> {

    private EventRepository eventRepository;

    public GetEventQueryHandler(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event handle(GetEventQuery query) throws Exception {
        return eventRepository.findById(query.getEventId());
    }
}
