package io.github.picodotdev.blogbitix.dddhexagonal.catalog.application.query;

...

@Component
public class GetEventQueryHandler implements QueryHandler<Event, GetEventQuery> {

    private GetEventUseCase useCase;

    public GetEventQueryHandler(GetEventUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public Event handle(GetEventQuery query) throws Exception {
        return useCase.handle(query.getEventId());
    }
}
