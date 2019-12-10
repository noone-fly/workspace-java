package com.pierre.restclient.SpringBoot_RestClient;

import org.springframework.web.client.RestTemplate;


public class RestTemplateMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		Person person = restTemplate.getForObject("http://localhost:8091/person/angus", Person.class);
		System.out.println(person.getName() + "----" + person.getAge());
	}
	
	static class Person{
		String name;
		Integer age;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
	}
}
