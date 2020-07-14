package com.example.k8s.springbootk8smysql.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.k8s.springbootk8smysql.entity.User;
import com.example.k8s.springbootk8smysql.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/Users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class UserController {

	private final UserRepository userRepository;

	@PostMapping
	public void create(@RequestBody User user) {
		log.info("Create method called with data : {}", user);
		userRepository.save(user);
	}

	@GetMapping
	public List<User> view() {
		log.info("View method called");
		return userRepository.findAll();
	}

	@GetMapping("/findUser/{id}")
	public Optional<User> getUser(@PathVariable Long id) {
		return userRepository.findById(id);
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
		;
		return "Deleted User Successfully::" + id;
	}

}
