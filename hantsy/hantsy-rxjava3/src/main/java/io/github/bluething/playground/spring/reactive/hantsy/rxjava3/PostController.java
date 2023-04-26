package io.github.bluething.playground.spring.reactive.hantsy.rxjava3;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

import java.util.UUID;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
    private final PostRepository repository;

    public PostController(PostRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "")
    public Observable<Post> all() {
        return this.repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Maybe<Post> get(@PathVariable(value = "id") UUID id) {
        return this.repository.findById(id);
    }

    @PostMapping(value = "")
    public Single<Post> create(Post post) {
        return this.repository.createPost(post);
    }
}
