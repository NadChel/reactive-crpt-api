package com.example.reactivecrptapi.handler;

import com.example.reactivecrptapi.model.dto.DocumentRequestDto;
import com.example.reactivecrptapi.mapper.DocumentMapper;
import com.example.reactivecrptapi.service.documentService.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class BasicCrptHandler implements CrptHandler {
    private final DocumentMapper mapper;
    private final DocumentService documentService;

    public BasicCrptHandler(DocumentMapper mapper,
                            DocumentService documentService) {
        this.mapper = mapper;
        this.documentService = documentService;
    }

    @Override
    public Mono<ServerResponse> postDocument(ServerRequest request) {
        return request.bodyToMono(DocumentRequestDto.class)
                .map(mapper::toDocument)
                .map(documentService::save)
                .flatMap(d -> ServerResponse.status(HttpStatus.CREATED)
                        .bodyValue("The document has been persisted"));
    }
}
