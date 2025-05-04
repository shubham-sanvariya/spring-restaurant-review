package com.spring.restaurant_review.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.restaurant_review.entities.Photo;
import com.spring.restaurant_review.services.PhotoService;
import com.spring.restaurant_review.services.StorageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService{
    
    private final StorageService storageService;
    
    @Override
    public Optional<Resource> getPhotoAsResource(String id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Photo uploadPhoto(MultipartFile file) {
        String photoId = UUID.randomUUID().toString();
        String url = storageService.store(file,photoId);

        return Photo.builder()
                    .url(url)
                    .uploadDate(LocalDateTime.now())
                    .build();
    }
    
}
