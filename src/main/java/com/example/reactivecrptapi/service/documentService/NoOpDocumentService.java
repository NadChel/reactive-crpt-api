package com.example.reactivecrptapi.service.documentService;

import com.example.reactivecrptapi.model.entity.Document;
import org.springframework.stereotype.Component;

/**
 * A no-op implementation of {@link DocumentService}
 * <p>
 * This implementation is intended to be used as a temporary plug at initial
 * stages of development
 */
@Component
public class NoOpDocumentService implements DocumentService {
    /**
     * A stub implementation that simply returns the input argument as is.
     *
     * @param document a document to "persist"
     * @return an unchanged input argument
     */
    @Override
    public Document save(Document document) {
        return document;
    }
}
