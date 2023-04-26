package io.github.bluething.playground.spring.reactive.hantsy.client;

import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

public class DemoWebClient {
    public static void main(String[] args) throws IOException {
        WebClient webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector())
                .codecs(clientCodecConfigurer -> {
                    clientCodecConfigurer.customCodecs().register(new Jackson2JsonDecoder());
                    clientCodecConfigurer.customCodecs().register(new Jackson2JsonEncoder());
                })
                .exchangeStrategies(ExchangeStrategies.withDefaults())
                .baseUrl("http://localhost:8080")
                .build();

        webClient
                .get()
                .uri("/posts")
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(Post.class))
                .log()
                .subscribe(post -> System.out.println("Post: " + post));
        System.out.println("Client is started!");
        System.in.read();
    }
}
