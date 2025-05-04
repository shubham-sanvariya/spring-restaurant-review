package com.spring.restaurant_review.controller;

import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.restaurant_review.dtos.PhotoDto;
import com.spring.restaurant_review.entities.Photo;
import com.spring.restaurant_review.mapper.PhotoMapper;
import com.spring.restaurant_review.services.PhotoService;

import java.net.http.HttpHeaders;

import org.springframework.core.io.Resource;
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

    @GetMapping(path = "/{id:.+}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String id){
       return photoService.getPhotoAsResource(id)
            .map(photo -> ResponseEntity.ok().contentType(
                MediaTypeFactory.getMediaType(photo)
                    .orElse(MediaType.APPLICATION_OCTET_STREAM)
            )
            .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "inline")
            .body(photo)
            )
            .orElse(ResponseEntity.notFound().build());
            
    }
}
