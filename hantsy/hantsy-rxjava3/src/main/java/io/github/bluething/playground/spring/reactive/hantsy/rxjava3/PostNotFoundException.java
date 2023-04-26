package io.github.bluething.playground.spring.reactive.hantsy.rxjava3;

import java.util.UUID;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(UUID id) {
        super("Post #" + id + " was not found!");
    }
}
