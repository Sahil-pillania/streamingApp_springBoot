package com.stream.app.spring_stream_backend.services;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stream.app.spring_stream_backend.entities.Video;

import org.springframework.util.StringUtils;


@Service
public class VideoServiceImpl implements VideoService {
	
	@Value("${files.video")
	String DIR;

	@Override
	public Video save(Video video, MultipartFile file) {
		
		// original filename
		
		try {
			String filename = file.getOriginalFilename();
			String contentType = file.getContentType();
			InputStream inputStream = file.getInputStream();
		
		
		
		
		
		
 		// folder path: create
			
			String cleanFileName =  StringUtils.cleanPath(filename);
			String cleanFolder =  StringUtils.cleanPath(DIR);
			
			Path path = Paths.get(cleanFolder, cleanFileName);
			
			System.out.println(path);
		
		// folder path with filename
		
		// copy file to the folder
		
		// video meta data
		
		// metadata save
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return null;
	}

	@Override
	public Video get(String videoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Video getByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Video> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
