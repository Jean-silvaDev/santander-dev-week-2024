package me.dio.controller;

import java.net.URI;
import java.util.List;

import me.dio.domain.model.User;
import me.dio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
       var user = userService.findById(id);
       return ResponseEntity.ok(user);
    }
    
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
    	return ResponseEntity.ok(this.userService.findAll());
    }
	
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User userToCreate) {
		var userCreated = this.userService.create(userToCreate);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userToCreate.getId()).toUri();
		return  ResponseEntity.created(location).body(userCreated);
	}
}
