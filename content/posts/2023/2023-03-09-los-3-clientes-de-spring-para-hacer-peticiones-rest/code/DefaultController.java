package io.github.picdodotdev.blogbitix.springrestclients;

...

@RestController
@RequestMapping("/message")
public class DefaultController {

    @GetMapping(value = {"", "/", "/{name}"})
    public String message(@PathVariable(value = "name", required = false) String name) {
        return (name == null || name.isBlank()) ? "Hello World!" : String.format("Hello %s!", name);
    }
}
