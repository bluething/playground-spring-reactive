package io.github.bluething.playground.spring.reactive.hantsy.router.function;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

@Component
public class PostRepository {
    private static final Map<Long, Post> DATA = new HashMap<>();
    private static Long ID_COUNTER = 1L;

    static {
        Arrays.asList("First Post", "Second Post")
                .stream()
                .forEach(title -> {
                    long id = ID_COUNTER++;
                    DATA.put(id++, Post.builder()
                            .id(id)
                            .title(title)
                            .content("content of " + title)
                            .build());
                });
    }

    Flux<Post> findAll() {
        return Flux.fromIterable(DATA.values());
    }

    Mono<Post> findById(Long id) {
        return Mono.justOrEmpty(
                DATA.get(id))
                .switchIfEmpty(Mono.error(new PostNotFoundException(id)));
    }

    Mono<Post> createPost(Post post) {
        long id = ID_COUNTER++;
        Post saved = Post.builder().id(id).title(post.getTitle()).content(post.getContent()).build();
        DATA.put(id, saved);
        return Mono.just(saved);
    }
}
