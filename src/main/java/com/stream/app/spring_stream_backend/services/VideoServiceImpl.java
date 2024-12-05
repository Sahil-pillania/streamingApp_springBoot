package com.stream.app.spring_stream_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stream.app.spring_stream_backend.entities.Video;

@Service
public class VideoServiceImpl implements VideoService {

	@Override
	public Video save(VideoService video, MultipartFile file) {
		// TODO Auto-generated method stub
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
