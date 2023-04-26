package io.github.bluething.playground.spring.reactive.hantsy.web.filter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


@Component
public class SecurityWebFilter implements WebFilter {
    // ServerWebExchange -> we can deal with web request and do crossing-cut operations as we expected in the response
    // WebFilterChain == FilterChain in Servlet
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (!exchange.getRequest().getQueryParams().containsKey("user")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        }
        return chain.filter(exchange);
    }
}
