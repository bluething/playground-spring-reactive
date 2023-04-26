package io.github.bluething.playground.spring.reactive.hantsy.multipart;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MultipartControllerTest {
    WebTestClient webTestClient;

    @BeforeEach
    void beforeEach() {
        webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
    }

    private MultiValueMap<String, HttpEntity<?>> generateBody() {
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("fileParts", new ClassPathResource("/foo.txt", MultipartControllerTest.class));
        return bodyBuilder.build();
    }

    @Test
    void testUpload() throws IOException {
        byte[] result = webTestClient
                .post()
                .uri("/uploads")
                .bodyValue(generateBody())
                .exchange()
                .expectStatus().isOk()
                .expectBody().returnResult().getResponseBody();

        ObjectMapper objectMapper = new ObjectMapper();
        Map bodyMap = objectMapper.readValue(result, Map.class);

        String fileId = (String) bodyMap.get("id");
        assertNotNull(fileId);
    }
}
