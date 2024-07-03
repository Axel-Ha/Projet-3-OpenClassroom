package com.chatop.api.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
public class PictureService {

    @Value("${pictures.path}")
    private String picturePath;

    @Value("${pictures.db.path}")
    private String pictureDbPath;

    public Resource getPicture(String filename) throws MalformedURLException {
        Path file = Paths.get(picturePath).resolve(filename);
        return new UrlResource(file.toUri());

    }

    public String savePicture(MultipartFile imageFile) throws IOException {
        try {
            log.info("PictureService : saveImage()");

            // Convert the multipart file to a byte array
            byte[] bytes = imageFile.getBytes();

            // Define the full path where the image will be saved
            Path path = Paths.get(picturePath + imageFile.getOriginalFilename());

            // Write the bytes to the specified file
            Files.write(path, bytes);

            // Create a File object pointing to the images directory
            File resourcesDirectory = new File(picturePath);

            // Check if the directory does not exist, create it
            if (!resourcesDirectory.exists()) {
                resourcesDirectory.mkdirs();
            }

            // Return the relative path of the file to be saved in the database
            return pictureDbPath + imageFile.getOriginalFilename();
        } catch (IOException e) {
            throw new IOException("Could not store the file, please try again.");
        }
    }

}
