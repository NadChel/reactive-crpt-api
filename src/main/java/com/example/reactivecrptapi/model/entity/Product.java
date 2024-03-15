package com.example.reactivecrptapi.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/*
Since entity schemes are not specified, they match those of their respective DTOs
(in this case, ProductRequestDto)

Also, no data store-specific annotations, such as @Entity, are included as
no data store is specified as well
 */
@NoArgsConstructor
@Getter
@Setter
public class Product {
    private String certificateDocument;
    private LocalDate certificateDocumentDate;
    private String certificateDocumentNumber;
    private String ownerInn;
    private String producerInn;
    private LocalDate productionDate;
    private String tnvedCode;
    private String uitCode;
    private String uituCode;
}
