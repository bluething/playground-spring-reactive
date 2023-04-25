package io.github.bluething.playground.spring.reactive.hantsy.vanilla.tomcat;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private Long id;
    private String title;
    private String content;
}
