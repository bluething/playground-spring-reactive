package io.github.bluething.playground.spring.reactive.hantsy.web.filter;

import java.util.UUID;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(UUID id) {
        super("Post #" + id + " was not found!");
    }
}
