package com.programming.techie.repository;

import com.programming.techie.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

//this will extend mongoRepository

public interface VideoRepository extends MongoRepository<Video,String>{
}
