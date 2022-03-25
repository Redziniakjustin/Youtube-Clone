package com.programming.techie.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service implements FileService{
    private final AmazonS3Client awsS3Client;

    //Constructor removed for Lombok
//    public S3Service(AmazonS3Client awsS3Client) {
//        this.awsS3Client = awsS3Client;
//    }

    @Override
    public String uploadFile(MultipartFile file) {
        //Upload file to AWS S3

        //Prepare my key
        var filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        var key = UUID.randomUUID().toString() + filenameExtension;

        //from S3 module
        var metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        //amazon bucket connection! - just need to provide the name of the bucket
        try {
            awsS3Client.putObject("myytclone", key, file.getInputStream(), metadata);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception occurred while the file was being uploaded!");
        }

        // So that the video can be visible to the client
        awsS3Client.setObjectAcl("myytclone", key, CannedAccessControlList.PublicRead);

        // getting the required key
        return awsS3Client.getResourceUrl("myytclone", key);
    }
}
