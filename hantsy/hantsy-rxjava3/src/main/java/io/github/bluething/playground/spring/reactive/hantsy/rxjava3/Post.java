package io.github.bluething.playground.spring.reactive.hantsy.rxjava3;

import lombok.*;

import java.util.UUID;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private UUID id;
    private String title;
    private String content;
}
