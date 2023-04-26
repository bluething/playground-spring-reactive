package io.github.bluething.playground.spring.reactive.hantsy.router.function;

import java.util.UUID;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long id) {
        super("Post #" + id + " was not found!");
    }
}
