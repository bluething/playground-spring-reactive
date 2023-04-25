package io.github.bluething.playground.spring.reactive.hantsy.vanilla.netty;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
    private final PostRepository repository;

    public PostController(PostRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "")
    public Flux<Post> all() {
        return this.repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<Post> get(@PathVariable(value = "id") Long id) {
        return this.repository.findById(id);
    }

    @PostMapping(value = "")
    public Mono<Post> create(Post post) {
        return this.repository.createPost(post);
    }
}
