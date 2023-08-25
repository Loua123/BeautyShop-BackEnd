package com.example.ecommercelouabackend.services;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadService {

    private static final String UPLOAD_PATH = "src/main/resources/static/images";

    public String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty. Please select a file to upload.");
        }

        // Get the filename and extension
        String fileName = file.getOriginalFilename();
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));

        // Generate a unique filename to avoid conflicts
        String uniqueFileName = generateUniqueFileName(fileExtension);

        // Save the file to the static folder
        Path targetPath = Path.of(UPLOAD_PATH, uniqueFileName);
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }

    private String generateUniqueFileName(String fileExtension) {
        // Implement your own logic to generate a unique filename here
        // For example, you can use a combination of timestamp and a random value
        String timestamp = String.valueOf(System.currentTimeMillis());
        String random = String.valueOf((int) (Math.random() * 10000));
        return timestamp + "_" + random + fileExtension;
    }
}
