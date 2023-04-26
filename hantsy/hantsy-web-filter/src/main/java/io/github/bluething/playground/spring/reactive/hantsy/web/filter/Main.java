package io.github.bluething.playground.spring.reactive.hantsy.web.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.netty.http.server.HttpServer;

@Configuration
@ComponentScan
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class Main {

    @Value("${server.port:8080}")
    private int port = 8080;

    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class)) {
            context.getBean(HttpServer.class)
                    .bindNow().onDispose().block();
        }
    }

    @Bean
    public HttpServer httpServer(ApplicationContext context) {
        HttpHandler httpHandler = WebHttpHandlerBuilder.applicationContext(context).build();
        ReactorHttpHandlerAdapter reactorHttpHandlerAdapter = new ReactorHttpHandlerAdapter(httpHandler);
        return HttpServer.create()
                .host("localhost")
                .port(port)
                .handle(reactorHttpHandlerAdapter);
    }
}