package com.example.reactivecrptapi.config;

import com.example.reactivecrptapi.handler.BasicCrptHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@WebFluxTest(controllers = {RouterConfig.class, BasicCrptHandler.class})
@EnableMappers
@EnableService
@TestPropertySource(properties = """
        crpt.rate-limiter.capacity=1
        crpt.rate-limiter.duration=30m
        """)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class RouterConfigTest {
    @Autowired
    WebTestClient client;

    @Test
    void postDocumentRouterFunction_withinLimit_returns2xx() {
        client
                .post()
                .uri("/api/v3/lk/documents/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(getSampleJson())
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class).value(body -> assertThat(body).isNotEmpty());
    }

    @Test
    void postDocumentRouterFunction_beyondLimit_returns429() {
        client
                .post()
                .uri("/api/v3/lk/documents/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(getSampleJson())
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class).value(body -> assertThat(body).isNotEmpty());

        client
                .post()
                .uri("/api/v3/lk/documents/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(getSampleJson())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.TOO_MANY_REQUESTS)
                .expectBody(String.class).value(body -> assertThat(body).isNotEmpty());
    }

    private String getSampleJson() {
        return """
                {
                  "description": {
                    "participantInn": "string"
                  },
                  "doc_id": "string",
                  "doc_status": "string",
                  "doc_type": "LP_INTRODUCE_GOODS",
                  "importRequest": true,
                  "owner_inn": "string",
                  "participant_inn": "string",
                  "producer_inn": "string",
                  "production_date": "2020-01-23",
                  "production_type": "string",
                  "products": [
                    {
                      "certificate_document": "string",
                      "certificate_document_date": "2020-01-23",
                      "certificate_document_number": "string",
                      "owner_inn": "string",
                      "producer_inn": "string",
                      "production_date": "2020-01-23",
                      "tnved_code": "string",
                      "uit_code": "string",
                      "uitu_code": "string"
                    }
                  ],
                  "reg_date": "2020-01-23",
                  "reg_number": "string"
                }
                """;
    }
}