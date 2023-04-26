package io.github.bluething.playground.spring.reactive.hantsy.rxjava3;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.stereotype.Component;

import java.util.*;

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

    Observable<Post> findAll() {
        return Observable.fromIterable(DATA);
    }

    Maybe<Post> findById(UUID id) {
        return findAll()
                .filter(p -> p.getId().equals(id))
                .singleElement()
                .switchIfEmpty(Maybe.error(new PostNotFoundException(id)));
    }

    Single<Post> createPost(Post post) {
        Post saved = Post.builder().id(UUID.randomUUID()).title(post.getTitle()).content(post.getContent()).build();
        DATA.add(saved);
        return Single.just(saved);
    }
}
