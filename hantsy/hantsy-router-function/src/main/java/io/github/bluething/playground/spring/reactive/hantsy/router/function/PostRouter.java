package io.github.bluething.playground.spring.reactive.hantsy.router.function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
class PostRouter {
    @Bean
    RouterFunction<ServerResponse> router(PostHandler handler) {
        return RouterFunctions.route(GET("/posts"), handler::all)
                .andRoute(POST("/posts"), handler::create)
                .andRoute(GET("/posts/{id}"), handler::get);
    }
}
