package io.github.bluething.playground.spring.reactive.hantsy.vanilla.tomcat;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        return Mono.just(DATA.get(id));
    }

    Mono<Post> createPost(Post post) {
        long id = ID_COUNTER++;
        post.setId(id);
        DATA.put(id, post);
        return Mono.just(post);
    }
}
