package com.vankata.weeski.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String uploadFile(MultipartFile multipartFile, String targetFolder);

    boolean deleteFile(String filePath);
}
