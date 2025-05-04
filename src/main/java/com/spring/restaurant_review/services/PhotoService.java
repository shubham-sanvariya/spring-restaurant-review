package com.spring.restaurant_review.services;

import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.spring.restaurant_review.domain.entities.Photo;

public interface PhotoService {
    Photo uploadPhoto(MultipartFile file);

    Optional<Resource> getPhotoAsResource(String id);
}