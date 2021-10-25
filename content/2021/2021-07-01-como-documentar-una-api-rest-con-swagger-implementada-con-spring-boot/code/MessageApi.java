package io.github.picodotdev.blogbitix.springrestswagger;

...

@Tag(name = "message", description = "the message API")
@RequestMapping("/message")
public interface MesssageApi {

	@Operation(summary = "Get all messages", description = "Returns all messages", responses = {
		@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Message.class))) }
	)
	@GetMapping(value = "", produces = { "application/json" })
	ResponseEntity<List<Message>> getAll();

	@Operation(summary = "Get a message by id", description = "Return a message", responses = {
		@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Message.class))),
		@ApiResponse(responseCode = "400", description = "Invalid id supplied"),
		@ApiResponse(responseCode = "404", description = "Message not found") }
	)
	@GetMapping(value = "/{id}", produces = { "application/json" })
	ResponseEntity<Message> getById(@Parameter(description = "Id of message to return", required = true) @PathVariable("id") Long id);

	@Operation(summary = "Adds a message", description = "Add a message", responses = {
		@ApiResponse(responseCode = "200", description = "Successful operation"),
		@ApiResponse(responseCode = "400", description = "Invalid data"),
		@ApiResponse(responseCode = "409", description = "Already exists") }
	)
	@PutMapping(value = "", produces = { "application/json" })
	ResponseEntity<Void> add(@Parameter(description = "Message to add", required = true) @RequestBody Message message);

	@Operation(summary = "Deletes a message by id", description = "Delete a message", responses = {
		@ApiResponse(responseCode = "200", description = "Successful operation"),
		@ApiResponse(responseCode = "400", description = "Invalid id supplied"),
		@ApiResponse(responseCode = "404", description = "Message not found") }
	)
	@DeleteMapping(value = "/{id}", produces = { "application/json" })
	ResponseEntity<Void> deleteById(@Parameter(description = "Id of message to delete", required = true) @PathVariable("id") Long id);
}
