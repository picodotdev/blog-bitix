package io.github.picodotdev.blogbitix.dddhexagonal.catalog.application.command;

...

public class CreateEventCommand extends Command {

    private EventSchedule eventSchedule;

    private CreateEventCommand(EventSchedule eventSchedule) {
        this.eventSchedule = eventSchedule;
    }

    public EventSchedule getEventSchedule() {
        return eventSchedule;
    }

    public static class Builder {

        private EventSchedule eventSchedule;

        public static Builder getInstance() {
            return new Builder();
        }

        public Builder eventSchedule(EventSchedule eventSchedule) {
            this.eventSchedule = eventSchedule;
            return this;
        }

        public CreateEventCommand build() {
            return new CreateEventCommand(eventSchedule);
        }
    }
}
