package com.chatop.api.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public String saveImage(MultipartFile imageFile) throws IOException {
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(picturePath + imageFile.getOriginalFilename());
        Files.write(path, bytes);
        log.info("PictureService : saveImage()");

        //Return the path to save in database
        return pictureDbPath + imageFile.getOriginalFilename();

    }
}
