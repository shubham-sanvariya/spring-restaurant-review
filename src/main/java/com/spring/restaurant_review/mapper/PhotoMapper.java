package com.spring.restaurant_review.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.spring.restaurant_review.dtos.PhotoDto;
import com.spring.restaurant_review.entities.Photo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhotoMapper {
    
    PhotoDto toDto(Photo photo);
}
