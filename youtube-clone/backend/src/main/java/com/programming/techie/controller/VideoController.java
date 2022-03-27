package com.programming.techie.controller;


import com.programming.techie.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//Rest Controller - we will tell spring boot where to find the REST API
// This endpoint will also listen for the POST request
//Creating Request at Server Side

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {

    //autowire Video Service
    private final VideoService videoService;

    // Created the endpoint method to upload the video, returning the response created.
    // in upload video, calling s3 to upload the video and creating the unique key and uploading the key
    // to the bucket and setting access control as public key, so that it can be video
    // saving metadata to mongoDB database

    //passing file to service  - which is in turn upadating to SW3
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadVideo (@RequestParam("file") MultipartFile file){
        videoService.uploadVideo(file);
    }

}
