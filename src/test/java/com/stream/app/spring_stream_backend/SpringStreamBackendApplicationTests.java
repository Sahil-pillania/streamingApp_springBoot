package com.stream.app.spring_stream_backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stream.app.spring_stream_backend.services.VideoService;

@SpringBootTest
class SpringStreamBackendApplicationTests {

	@Autowired
	VideoService videoService;
	@Test
	void contextLoads() {
		/* videoService.processVideo("3f743f55-7ec0-4f30-8c12-be4ce32124fb"); */
	}

}
