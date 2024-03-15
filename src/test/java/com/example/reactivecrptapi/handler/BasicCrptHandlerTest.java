package com.example.reactivecrptapi.handler;

import com.example.reactivecrptapi.mapper.DocumentMapper;
import com.example.reactivecrptapi.model.dto.DocumentRequestDto;
import com.example.reactivecrptapi.model.entity.Document;
import com.example.reactivecrptapi.service.documentService.DocumentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.reactive.function.server.MockServerRequest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class BasicCrptHandlerTest {
    @Mock
    DocumentService documentServiceMock;
    @Mock
    DocumentMapper documentMapperMock;
    @InjectMocks
    BasicCrptHandler handler;
    @Test
    void postDocument() {
        DocumentRequestDto dto = new DocumentRequestDto();
        ServerRequest request = MockServerRequest.builder()
                .body(Mono.just(dto));
        Document document = new Document();
        given(documentMapperMock.toDocument(dto)).willReturn(document);
        given(documentServiceMock.save(document)).willReturn(document);

        StepVerifier.create(handler.postDocument(request))
                .assertNext(this::assertResponseCorrect)
                .verifyComplete();
        then(documentServiceMock).should().save(document);
    }

    private void assertResponseCorrect(ServerResponse response) {
        assertThat(response.statusCode().is2xxSuccessful()).isTrue();

        String bodyValue = getBodyValue(response);
        assertThat(bodyValue).isNotBlank();
    }

    private String getBodyValue(ServerResponse response) {
        return (String) ReflectionTestUtils.getField(response, "entity");
    }
}