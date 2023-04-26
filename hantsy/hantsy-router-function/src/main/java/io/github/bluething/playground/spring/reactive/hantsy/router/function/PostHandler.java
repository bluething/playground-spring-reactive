package io.github.bluething.playground.spring.reactive.hantsy.router.function;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class PostHandler {
    private final PostRepository repository;

    Mono<ServerResponse> all(ServerRequest request) {
        return ServerResponse.ok().body(this.repository.findAll(), Post.class);
    }

    Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(Post.class)
                .flatMap(post -> this.repository.createPost(post))
                .flatMap(post -> ServerResponse.created(URI.create("/posts/" + post.getId())).build());
    }

    Mono<ServerResponse> get(ServerRequest request) {
        return this.repository.findById(Long.valueOf(request.pathVariable("id")))
                .flatMap(post -> ServerResponse.ok().body(Mono.just(post), Post.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
