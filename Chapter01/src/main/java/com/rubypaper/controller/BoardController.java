package com.rubypaper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

class Person {
	String name;
	int age;
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
@RestController
public class BoardController {
	

	public BoardController() {
		System.out.println("===> BoardController 생성");
	}
	
	@GetMapping("/hello")
	public String hello(String name) {
		return "Hello : " + name;
	}
	
	@GetMapping("/hello1")
	public String hello(String name, Integer age) {
		if (age == null) age = 0;
		return "Hello : " + name + "(" + age + "세)";
	}
	
	@GetMapping("/person")
	public String person(Person person) {
		return person.toString();
	}
	
	@GetMapping("/jsonperson")
	public String jsonperson(@RequestBody Person person) {
		
		return person.toString() + " with json";
	}
	@PostMapping("/login")
	public String login(String id, String pwd) {
		return id + "," + pwd;
	}
}
