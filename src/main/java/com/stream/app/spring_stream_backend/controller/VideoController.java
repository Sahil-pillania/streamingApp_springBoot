package com.stream.app.spring_stream_backend.controller;

import java.io.ObjectInputFilter.Status;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stream.app.spring_stream_backend.entities.Video;
import com.stream.app.spring_stream_backend.payload.CustomMessage;
import com.stream.app.spring_stream_backend.services.VideoService;

import jakarta.annotation.Resource;


@RestController
@RequestMapping("/api/v1/videos")
@CrossOrigin("*")
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
	
	
    //get all videos

    @GetMapping("")
    public List<Video> getAll() {
        return videoService.getAll();
    }
	
	
	
	
	// stream API
	
	@GetMapping("/stream/{videoId}")
	public ResponseEntity<Resource> stream(
			@PathVariable String videoId
			){
		Video video = videoService.get(videoId);
		
		String contentType = video.getContentType();
		String filePath =  video.getFilePath();
		
		
		Resource resource = (Resource) new FileSystemResource(filePath);
		
		if(contentType == null) {
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity
				.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.body(resource);
		
	}

}




