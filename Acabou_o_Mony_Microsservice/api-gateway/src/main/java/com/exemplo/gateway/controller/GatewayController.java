package com.exemplo.gateway.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class GatewayController {

    private final WebClient webClient;

    public GatewayController() {
        this.webClient = WebClient.builder().build();
    }

    @RequestMapping("/{service}/**")
    public Mono<ResponseEntity<String>> proxy(
            @PathVariable String service,
            @RequestHeader HttpHeaders headers,
            @RequestParam(required = false) MultiValueMap<String, String> queryParams,
            @RequestBody(required = false) Mono<String> body,
            ServerHttpRequest request
    ) {
        String url = switch (service) {
            case "pedido-service", "pedido" -> "http://localhost:9090";
            case "produto-service", "produtos" -> "http://localhost:9091";
            case "contas-service", "clientes" -> "http://localhost:9092";
            case "transacao-service", "transacao" -> "http://localhost:9093";
            default -> null;
        };

        if (url == null) {
            return Mono.just(ResponseEntity.status(400).build());
        }

        String originalPath = request.getURI().getRawPath();
        String prefixToStrip = "/api/" + service;
        String path = originalPath.substring(prefixToStrip.length());
        String uri = url + path;

        if (queryParams != null && !queryParams.isEmpty()) {
            uri += "?" + queryParams.toSingleValueMap().entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .reduce((a, b) -> a + "&" + b)
                    .orElse("");
        }

        return webClient.method(request.getMethod())
                .uri(uri)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .body(body == null ? Mono.empty() : body, String.class)
                .retrieve()
                .toEntity(String.class);
    }
}
