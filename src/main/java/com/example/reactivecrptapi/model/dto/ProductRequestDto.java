package com.example.reactivecrptapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
public class ProductRequestDto {
    @JsonProperty("certificate_document")
    private String certificateDocument;
    @JsonProperty("certificate_document_date")
    private LocalDate certificateDocumentDate;
    @JsonProperty("certificate_document_number")
    private String certificateDocumentNumber;
    @JsonProperty("owner_inn")
    private String ownerInn;
    @JsonProperty("producer_inn")
    private String producerInn;
    @JsonProperty("production_date")
    private LocalDate productionDate;
    @JsonProperty("tnved_code")
    private String tnvedCode;
    @JsonProperty("uit_code")
    private String uitCode;
    @JsonProperty("uitu_code")
    private String uituCode;
}
