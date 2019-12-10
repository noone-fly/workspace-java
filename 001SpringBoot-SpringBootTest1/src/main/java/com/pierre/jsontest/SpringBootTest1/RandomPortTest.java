package com.pierre.jsontest.SpringBootTest1;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RandomPortTest {
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testHello() {
		//测试方法
		String result = restTemplate.getForObject("/hello", String.class);
		
		assertEquals("Hello Spring Boot", result);
	}
}
