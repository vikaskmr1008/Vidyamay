/**
 * 
 */
package com.vidyamay.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vidyamay.model.User;
import com.vidyamay.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Vidyamay Education Pvt Ltd.
 *
 */
@RestController
@Api(tags = "User Details")
@RequestMapping(path="/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "Fetching all users")
	@GetMapping
	public ResponseEntity<List<User>> allUsers() {
		List<User> users = userService.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@PostMapping
	public User saveStudent(@Valid @RequestBody User user) {
		return userService.save(user);
	}


	@GetMapping("/{id}")
	public User getStudentById(@PathVariable(value = "id") Long id) {
		return userService.findById(id);
	}
}
