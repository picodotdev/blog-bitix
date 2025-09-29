$ tree src/
src/
├── main
│   ├── java
│   │   └── io
│   │       └── github
│   │           └── picodotdev
│   │               └── blogbitix
│   │                   └── dddhexagonal
│   │                       └── catalog
│   │                           ├── application
│   │                           │   ├── command
│   │                           │   │   ├── CreateEventCommandHandler.java
│   │                           │   │   └── CreateEventCommand.java
│   │                           │   ├── commandbus
│   │                           │   │   ├── CommandBus.java
│   │                           │   │   ├── CommandHandler.java
│   │                           │   │   └── Command.java
│   │                           │   ├── query
│   │                           │   │   ├── GetEventQueryHandler.java
│   │                           │   │   └── GetEventQuery.java
│   │                           │   ├── querybus
│   │                           │   │   ├── QueryBus.java
│   │                           │   │   ├── QueryHandler.java
│   │                           │   │   └── Query.java
│   │                           │   └── usecases
│   │                           │       ├── CreateEventUseCase.java
│   │                           │       └── GetEventUseCase.java
│   │                           ├── domain
│   │                           │   ├── model
│   │                           │   │   └── event
│   │                           │   │       ├── EventCancelledDomainEvent.java
│   │                           │   │       ├── EventCreatedDomainEvent.java
│   │                           │   │       ├── EventDate.java
│   │                           │   │       ├── EventDateRescheduledDomainEvent.java
│   │                           │   │       ├── EventId.java
│   │                           │   │       ├── Event.java
│   │                           │   │       ├── EventRepository.java
│   │                           │   │       ├── EventSchedule.java
│   │                           │   │       └── exceptions
│   │                           │   │           ├── EndDateIsBeforeStartDate.java
│   │                           │   │           └── InvalidDate.java
│   │                           │   └── shared
│   │                           │       └── domaineventbus
│   │                           │           ├── DomainEventBus.java
│   │                           │           ├── DomainEventCollection.java
│   │                           │           ├── DomainEventId.java
│   │                           │           └── DomainEvent.java
│   │                           ├── infrastructure
│   │                           │   ├── InMemoryEventRepository.java
│   │                           │   ├── rest
│   │                           │   │   ├── controllers
│   │                           │   │   │   └── EventController.java
│   │                           │   │   ├── converters
│   │                           │   │   │   ├── EventDateConverter.java
│   │                           │   │   │   └── EventIdConverter.java
│   │                           │   │   ├── exceptions
│   │                           │   │   │   ├── CustomRestExceptionHandler.java
│   │                           │   │   │   └── Error.java
│   │                           │   │   ├── serializer
│   │                           │   │   │   ├── EventDateSerializer.java
│   │                           │   │   │   ├── EventIdSerializer.java
│   │                           │   │   │   ├── EventScheduleSerializer.java
│   │                           │   │   │   └── EventSerializer.java
│   │                           │   │   └── spring
│   │                           │   │       └── SpringRestConfiguration.java
│   │                           │   └── spring
│   │                           │       ├── SpringCommandBus.java
│   │                           │       ├── SpringEventBus.java
│   │                           │       └── SpringQueryBus.java
│   │                           └── Main.java
│   └── resources
│       └── application.yml
└── test
    └── java

31 directories, 42 files