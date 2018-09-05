package com.vankata.weeski.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private static final String IMAGES_BASE_PATH =
            "D:\\PROGRAMMING\\JAVA\\SOFTUNI\\JS_WEB\\ANGULAR_FUNDAMENTALS\\wee-ski-project\\images\\";

    @Override
    public String uploadFile(MultipartFile multipartFile, String targetFolder) {
        String fileName = null;
        try {
            String name = multipartFile.getOriginalFilename();
            String extension = name.substring(name.lastIndexOf("."));
            fileName = UUID.randomUUID().toString() + extension;
            String imagePath = IMAGES_BASE_PATH + targetFolder + fileName;
            File targetFile = new File(imagePath);
            Files.copy(multipartFile.getInputStream(),
                    targetFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            System.err.println(ex);
        }

        return fileName;
    }

    @Override
    public boolean deleteFile(String filePath) {
        File file = new File(IMAGES_BASE_PATH + filePath);
        return file.delete();
    }
}