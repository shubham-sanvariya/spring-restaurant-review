package com.spring.restaurant_review.services;

import org.springframework.web.multipart.MultipartFile;

import com.spring.restaurant_review.entities.Photo;

public interface PhotoService {
    Photo uploadPhoto(MultipartFile file);
}