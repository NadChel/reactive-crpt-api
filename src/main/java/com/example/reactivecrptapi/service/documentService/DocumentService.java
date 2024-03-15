package com.example.reactivecrptapi.service.documentService;

import com.example.reactivecrptapi.model.entity.Document;

/**
 * A type for manipulating {@link Document} instances
 */
public interface DocumentService {
    /**
     * Persists a {@code Document} instance
     * <p>
     * This interface does not specify any data store and expects
     * implementations to frame the behavior of this method as appropriate
     *
     * @param document document to persist
     * @return a persisted document, may have a new id
     */
    Document save(Document document);
}
