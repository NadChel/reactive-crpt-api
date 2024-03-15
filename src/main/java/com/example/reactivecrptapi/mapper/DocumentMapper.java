package com.example.reactivecrptapi.mapper;

import com.example.reactivecrptapi.model.dto.DocumentRequestDto;
import com.example.reactivecrptapi.model.entity.Document;
import org.mapstruct.Mapper;

@Mapper
public interface DocumentMapper {
    Document toDocument(DocumentRequestDto documentDto);
}
