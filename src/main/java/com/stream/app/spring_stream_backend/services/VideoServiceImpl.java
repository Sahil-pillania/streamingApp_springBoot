package com.stream.app.spring_stream_backend.services;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stream.app.spring_stream_backend.entities.Video;
import com.stream.app.spring_stream_backend.repositories.VideoRepository;

import jakarta.annotation.PostConstruct;

import org.springframework.util.StringUtils;


@Service
public class VideoServiceImpl implements VideoService {
	
	@Autowired
	private  VideoRepository videoRepository;
	
    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }
	
	@Value("${files.video}")
	String DIR;
	
	@PostConstruct
	public void init() {
		File file = new File(DIR);
		if(!file.exists()) {
			file.mkdir();
			System.out.println("Folder created");
		}else {
			System.out.println("folder already created");
		}
	}

	@Override
	public Video save(Video video, MultipartFile file) {
		
		// original filename
		
		try {
			String filename = file.getOriginalFilename();
			String contentType = file.getContentType();
			InputStream inputStream = file.getInputStream();
		
		
		
		
			// file path 
			String cleanFileName =  StringUtils.cleanPath(filename);
		
 		   // folder path: create
			
			String cleanFolder =  StringUtils.cleanPath(DIR);
			
			// folder path with filename
			Path path = Paths.get(cleanFolder, cleanFileName);
			
			/* System.out.println(path); */
		
		
		
		// copy file to the folder
			Files.copy(inputStream,path, StandardCopyOption.REPLACE_EXISTING);
		
		// video meta data
			
			video.setContentType(contentType);
			video.setFilePath(path.toString());
		
		// metadata save
			
			return videoRepository.save(video);
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			return null;
		}
		
		
		
		
	}

	@Override
	public Video get(String videoId) {
		
		Video video =  videoRepository.findById(videoId).orElseThrow(()-> new RuntimeException("Video not found"));
		
		return video;
	}

	@Override
	public Video getByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Video> getAll() {
		
		return videoRepository.findAll();
	}
	
	

}
