package com.example.reactivecrptapi.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/*
See the comment on Document
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
