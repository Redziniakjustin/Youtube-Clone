package com.programming.techie.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadFile(MultipartFile file);
    //best practice - if we want to implement more we can make this polymorphic!



}
