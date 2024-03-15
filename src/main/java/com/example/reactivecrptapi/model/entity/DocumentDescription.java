package com.example.reactivecrptapi.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
Since entity schemes are not specified, they match those of their respective DTOs
(in this case, DocumentDescriptionDto)

Also, no data store-specific annotations, such as @Entity, are included as
no data store is specified as well
 */
@NoArgsConstructor
@Getter
@Setter
public class DocumentDescription {
    private String participantInn;
}
