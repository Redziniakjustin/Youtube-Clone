package com.programming.techie.service;

import com.programming.techie.model.Video;
import com.programming.techie.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;

@Service
@RequiredArgsConstructor
public class VideoService {

    //autowire
    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    public void uploadVideo (MultipartFile multipartFile){
        //upload video file onto AWS S3
        // Save video Data to DB
        // Minimal functionality to save the video
        String videoUrl = s3Service.uploadFile(multipartFile);
        var video = new Video();
        video.setVideoUrl(videoUrl);
        //saving metadata to mongo DB database
        videoRepository.save(video);

    }

}
