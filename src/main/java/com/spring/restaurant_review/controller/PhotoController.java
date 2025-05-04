package com.spring.restaurant_review.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.restaurant_review.dtos.PhotoDto;
import com.spring.restaurant_review.entities.Photo;
import com.spring.restaurant_review.mapper.PhotoMapper;
import com.spring.restaurant_review.services.PhotoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/photos")
public class PhotoController {
    
    private final PhotoService photoService;
    private final PhotoMapper photoMapper;

    @PostMapping
    public PhotoDto uploadPhoto(@RequestParam("file") MultipartFile file) {
        Photo savedPhoto = photoService.uploadPhoto(file);
        return photoMapper.toDto(savedPhoto);
    }
}
