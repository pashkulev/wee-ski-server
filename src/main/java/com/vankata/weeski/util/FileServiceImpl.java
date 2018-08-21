package com.vankata.weeski.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private static final String IMAGE_BASE_PATH =
            "D:\\PROGRAMMING\\JAVA\\SOFTUNI\\JS_WEB\\ANGULAR_FUNDAMENTALS\\wee-ski-project\\images\\profilePictures\\";

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        //todo: try to move this logic to another service or even microservice
        String fileName = null;
        try {

            String name = multipartFile.getOriginalFilename();
            String extension = name.substring(name.lastIndexOf("."));
            fileName = UUID.randomUUID().toString() + extension;
            String imagePath = IMAGE_BASE_PATH + fileName;
            File targetFile = new File(imagePath);
            Files.copy(multipartFile.getInputStream(),
                    targetFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return fileName;
    }

    @Override
    public boolean deleteFile(String filePath) {
        File file = new File(IMAGE_BASE_PATH + filePath);
        return file.delete();
    }
}