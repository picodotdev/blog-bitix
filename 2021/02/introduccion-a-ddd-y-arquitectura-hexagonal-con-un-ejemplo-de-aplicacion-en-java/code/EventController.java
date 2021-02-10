package io.github.picodotdev.blogbitix.dddhexagonal.catalog.infrastructure.rest.controllers;

...

@RestController
@RequestMapping("/event")
public class EventController {

    private CommandBus commandBus;
    private QueryBus queryBus;

    public EventController(QueryBus queryBus, CommandBus commandBus) {
        this.queryBus = queryBus;
        this.commandBus = commandBus;
    }

    @PostMapping
    public ResponseEntity<String> createEvent(@RequestParam("startDate") EventDate startDate, @RequestParam("endDate") EventDate endDate) throws Exception {
        CreateEventCommand command = CreateEventCommand.Builder.getInstance()
                .eventSchedule(EventSchedule.valueOf(startDate, startDate))
                .build();
        commandBus.handle(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Event> getEvent(@PathVariable("id") EventId eventId) throws Exception {
        GetEventQuery command = GetEventQuery.Builder.getInstance()
                .eventId(eventId)
                .build();
        Event event = queryBus.handle(command);
        if (event == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(event);
    }
}
