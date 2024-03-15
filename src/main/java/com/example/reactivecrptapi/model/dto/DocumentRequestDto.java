package com.example.reactivecrptapi.model.dto;

import com.example.reactivecrptapi.model.enumeration.DocumentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/*
В теле запроса передается в формате JSON документ: {"description":
{ "participantInn": "string" }, "doc_id": "string", "doc_status": "string",
"doc_type": "LP_INTRODUCE_GOODS", 109 "importRequest": true,
"owner_inn": "string", "participant_inn": "string", "producer_inn":
"string", "production_date": "2020-01-23", "production_type": "string",
"products": [ { "certificate_document": "string",
"certificate_document_date": "2020-01-23",
"certificate_document_number": "string", "owner_inn": "string",
"producer_inn": "string", "production_date": "2020-01-23",
"tnved_code": "string", "uit_code": "string", "uitu_code": "string" } ],
"reg_date": "2020-01-23", "reg_number": "string"}
 */
@NoArgsConstructor
@Getter
@Setter
public class DocumentRequestDto {
    private DocumentDescriptionDto description;
    @JsonProperty("doc_id")
    private String docId;
    @JsonProperty("doc_status")
    private String docStatus;
    @JsonProperty("doc_type")
    private DocumentType docType;
    private Boolean importRequest;
    @JsonProperty("owner_inn")
    private String ownerInn;
    @JsonProperty("participant_inn")
    private String participantInn;
    @JsonProperty("producer_inn")
    private String producerInn;
    @JsonProperty("production_date")
    private LocalDate productionDate;
    @JsonProperty("production_type")
    private String productionType;
    private List<ProductRequestDto> products;
    @JsonProperty("reg_date")
    private LocalDate regDate;
    @JsonProperty("reg_number")
    private String regNumber;
}
