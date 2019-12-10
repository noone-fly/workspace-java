package com.pierre.restclient.SpringBoot_RestClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pierre.restclient.SpringBoot_RestClient.RestTemplateMain.Person;

@Service
public class MyService {
	
	@Autowired
	private RestTemplateBuilder builder;
	
	@Bean
	public RestTemplate restTemplate() {
		return builder.rootUri("http://localhost:8091").build();
	}
	
	public Person useBuilder() {
		Person person = restTemplate().getForObject("/person/angus", Person.class);
		return person;
	}
}
