package com.stream.app.spring_stream_backend.controller;

import java.io.ObjectInputFilter.Status;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stream.app.spring_stream_backend.entities.Video;
import com.stream.app.spring_stream_backend.payload.CustomMessage;
import com.stream.app.spring_stream_backend.services.VideoService;


@RestController
@RequestMapping("/api/v1/videos")
@Controller
public class VideoController {
	
	@Autowired
	private VideoService videoService;
	
	public VideoController(VideoService videoService) {
		this.videoService = videoService;
	}
	
	@PostMapping("")
	public ResponseEntity<?> create(
			@RequestParam("file") MultipartFile file,
			@RequestParam("title") String title,
			@RequestParam("description") String Description
			){
		
		Video video = new Video(); 
		video.setTitle(title);
		video.setDescription(Description);
		video.setVideoId(UUID.randomUUID().toString());
		
		Video savedVideo =  videoService.save(video, file);
		
		if(savedVideo !=null) {
			
			return ResponseEntity.status(HttpStatus.OK).body(video);
		}else {
			/*
			 * return
			 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(CustomMessage.
			 * builder().message("Video not uploaded ").success(false).build());
			 */
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);	
		}
		
	
		
	}

}
