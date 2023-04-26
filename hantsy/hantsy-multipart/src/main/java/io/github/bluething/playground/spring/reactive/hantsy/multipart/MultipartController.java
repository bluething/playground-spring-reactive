package io.github.bluething.playground.spring.reactive.hantsy.multipart;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/uploads")
public class MultipartController {
    Mono<String> requestBodyFlux(@RequestBody Flux<Part> parts) {
        return partFluxDescription(parts);
    }

    private static Mono<String> partFluxDescription(Flux<? extends Part> partsFlux) {
        return partsFlux.log().collectList().map(MultipartController::partListDescription);
    }

    static String partListDescription(List<? extends Part> partsFlux) {
        return partsFlux.stream().map(MultipartController::partDescription)
                .collect(Collectors.joining(",", "[", "]"));
    }
    static String partDescription(Part part) {
        return part instanceof FilePart ? part.name() + ":" + ((FilePart) part).filename() : part.name();
    }
}
