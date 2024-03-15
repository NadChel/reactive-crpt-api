package com.example.reactivecrptapi.model.entity;

import com.example.reactivecrptapi.model.enumeration.DocumentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/*
Since entity schemes are not specified, they match those of their respective DTOs
(in this case, DocumentRequestDto)

Also, no data store-specific annotations, such as @Entity, are included as
no data store is specified as well
 */
@NoArgsConstructor
@Setter
@Getter
public class Document {
    private DocumentDescription description;
    private String docId;
    private String docStatus;
    private DocumentType docType;
    private Boolean importRequest;
    private String ownerInn;
    private String participantInn;
    private String producerInn;
    private LocalDate productionDate;
    private String productionType;
    private List<Product> products;
    private LocalDate regDate;
    private String regNumber;

}
