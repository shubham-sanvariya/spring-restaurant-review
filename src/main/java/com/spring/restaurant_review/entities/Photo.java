package com.spring.restaurant_review.entities;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Photo {
    
    @Field(type = FieldType.Keyword)
    private String url;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private String uploadDate;
}
