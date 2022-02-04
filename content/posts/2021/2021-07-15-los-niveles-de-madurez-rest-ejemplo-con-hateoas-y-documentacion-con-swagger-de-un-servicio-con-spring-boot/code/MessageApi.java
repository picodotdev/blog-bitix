package io.github.picodotdev.blogbitix.springresthateoas;

...

@Tag(name = "message", description = "the message API")
@RequestMapping(value = "/message", produces = { "application/hal+json" })
public interface MessageApi {

	@Operation(summary = "Get resource links", description = "Returns resource links", responses = {
			@ApiResponse(responseCode = "200", description = "Successful operation",
				links = {
					@Link(name = "self", operationId = "self"), 
					@Link(name = "getAll", operationId = "getAll"), 
					@Link(name = "getById", operationId = "getById"), 
					@Link(name = "add", operationId = "add"), 
					@Link(name = "deleteById", operationId = "deleteById")
			})
	})
	@GetMapping(value = "/index")
	ResponseEntity<CollectionModel<EntityModel<Message>>> index();

	@Operation(summary = "Get all messages", description = "Returns all messages", responses = {
		@ApiResponse(responseCode = "200", description = "Successful operation",
				links = { @Link(name = "self", operationId = "self"), @Link(name = "add", operationId = "add") })
	})
	@GetMapping(value = "")
	ResponseEntity<CollectionModel<EntityModel<Message>>> getAll();

	@Operation(summary = "Get a message by id", description = "Return a message", responses = {
		@ApiResponse(responseCode = "200", description = "Successful operation",
				links = { @Link(name = "self", operationId = "self"), @Link(name = "deleteById", operationId = "deleteById") }),
		@ApiResponse(responseCode = "400", description = "Invalid id supplied"),
		@ApiResponse(responseCode = "404", description = "Message not found")
	})
	@GetMapping(value = "/{id}")
	ResponseEntity<EntityModel<Message>> getById(@Parameter(description = "Id of message to return", required = true) @PathVariable("id") Long id);

	@Operation(summary = "Adds a message", description = "Add a message")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Invalid data"),
			@ApiResponse(responseCode = "409", description = "Already exists") })
	@PostMapping(value = "")
	ResponseEntity<Void> add(@Parameter(description = "Message to add", required = true) @RequestBody Message message);

	@Operation(summary = "Deletes a message by id", description = "Delete a message")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied"),
			@ApiResponse(responseCode = "404", description = "Message not found") })
	@DeleteMapping(value = "/{id}")
	ResponseEntity<Void> deleteById(@Parameter(description = "Id of message to delete", required = true) @PathVariable("id") Long id);
}
