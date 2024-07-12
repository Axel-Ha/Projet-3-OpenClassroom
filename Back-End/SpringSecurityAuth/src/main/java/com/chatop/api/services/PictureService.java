package com.chatop.api.services;

import com.chatop.api.exceptions.PictureErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional(readOnly = true)
    public Resource getPicture(String filename) {
        try {
            // Construct the full file path using the given filename and base picture path
            Path filePath = Paths.get(picturePath).resolve(filename).normalize();
            // Create a resource object from the file path
            Resource resource = new UrlResource(filePath.toUri());
            // Check if the resource exists and is readable
            if (resource.exists() && resource.isReadable()) {
                return resource; // Return the resource if it is found and readable
            } else {
            throw new PictureErrorException("File not found or not readable");
        }
    } catch (Exception e) {
        throw new PictureErrorException("Error retrieving file", e);
    }
}

    public String savePicture(MultipartFile imageFile) throws PictureErrorException {
        try {
            // Create a File object pointing to the images directory
            File resourcesDirectory = new File(picturePath);

            // if the directory does not exist, create it
            if (!resourcesDirectory.exists()) {
                resourcesDirectory.mkdirs();
            }

            // Convert the multipart file to a byte array
            byte[] bytes = imageFile.getBytes();

            // Define the full path where the image will be saved
            Path path = Paths.get(picturePath + imageFile.getOriginalFilename());

            // Write the bytes to the specified file
            Files.write(path, bytes);

            // Return the relative path of the file to be saved in the database
            return imageFile.getOriginalFilename();

        } catch (IOException e) {
            throw new PictureErrorException("Could not store the file, please try again.", e);
        }
    }

}
