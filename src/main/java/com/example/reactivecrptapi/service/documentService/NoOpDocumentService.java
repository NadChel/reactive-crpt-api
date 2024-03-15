package com.example.reactivecrptapi.service.documentService;

import com.example.reactivecrptapi.model.entity.Document;
import org.springframework.stereotype.Component;

/**
 * A no-op implementation of {@link DocumentService} that simply returns the input argument as is
 */
@Component
public class NoOpDocumentService implements DocumentService {
    @Override
    public Document save(Document document) {
        return document;
    }
}
