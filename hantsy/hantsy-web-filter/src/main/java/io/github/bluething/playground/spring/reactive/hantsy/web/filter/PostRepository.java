package io.github.bluething.playground.spring.reactive.hantsy.web.filter;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PostRepository {
    private static final List<Post> DATA = new ArrayList<>();

    static {
        DATA.add(Post.builder()
                .id(UUID.randomUUID())
                .title("Post One")
                .content("content of post one").build());
        DATA.add(Post.builder()
                .id(UUID.randomUUID())
                .title("Post Two")
                .content("content of post two").build());
    }

    Flux<Post> findAll() {
        return Flux.fromIterable(DATA);
    }

    Mono<Post> findById(UUID id) {
        return Mono.justOrEmpty(
                DATA.stream()
                        .filter(p -> p.getId().equals(id))
                        .findFirst()
                        .orElseThrow(() -> new PostNotFoundException(id)));
    }

    Mono<Post> createPost(Post post) {
        Post saved = Post.builder().id(UUID.randomUUID()).title(post.getTitle()).content(post.getContent()).build();
        DATA.add(saved);
        return Mono.just(saved);
    }
}
