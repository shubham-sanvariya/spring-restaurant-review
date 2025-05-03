package com.spring.restaurant_review.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.spring.restaurant_review.exceptions.StorageException;
import com.spring.restaurant_review.services.StorageService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileSystemStorageService implements StorageService {

    @Value("${app.storage.location:uploads}")
    private String storageLocation;

    private Path rootLocation;

    @PostConstruct
    public void init() {
        rootLocation = Paths.get(storageLocation);
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not intialize storage location", e);
        }
    }

    @Override
    public String store(MultipartFile file, String filename) {
        if (file.isEmpty()) {
            throw new StorageException("Cannot save an empty file");
        }

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename != null ? StringUtils.getFilenameExtension(originalFilename) : "";

        String finalFileName = StringUtils.hasText(extension) ? filename + "." + extension : filename;

        Path destinationFile = rootLocation.resolve(Paths.get(finalFileName))
                .normalize()
                .toAbsolutePath();

        if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
            throw new StorageException("Cannot store file outside specified directory");
        }

        // auto closable no need for final
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to store file", e);
        }

        return finalFileName;
    }

    @Override
    public Optional<Resource> loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);

            Resource resource;
            resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return Optional.of(resource);
            } else {
                return Optional.empty();
            }
        } catch (MalformedURLException e) {
            log.warn("Could not read file: %s".formatted(filename),e);
            return Optional.empty();
        }
    };

}
