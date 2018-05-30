/**
 * 
 */
package com.vidyamay.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vidyamay.common.AbstractServiceHandler;
import com.vidyamay.model.User;
import com.vidyamay.service.UserService;
import com.vidyamay.utils.PlatformErrorCodes;
import com.vidyamay.utils.PlatformException;
import com.vidyamay.utils.PlatformExceptionTranslator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Vidyamay Education Pvt Ltd.
 *
 */
@RestController
@Api(tags = "User Details")
@RequestMapping(path="/v1/users")
public class UserController extends AbstractServiceHandler {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "Fetching all users")
	@GetMapping
	public final Flux<User> allUsers() throws PlatformException {
		Flux<User> users = userService.findAll();
		if (ObjectUtils.isEmpty(users)) {
			throw PlatformExceptionTranslator.wrapException(PlatformErrorCodes.NOT_FOUND.value(),
		              PlatformErrorCodes.NOT_FOUND.getErrorCode(), "Not Found");
		}
		return users;
	}
	
	@ApiOperation(value = "save a user")
	@PostMapping
	public Mono<User> saveUser(@Valid @RequestBody User user) throws PlatformException {
		return userService.save(user);
	}
	
	@ApiOperation(value = "update a user")
	@PutMapping
	public Mono<User> update(@Valid @RequestBody User user) throws PlatformException {
		return userService.updateResource(user);
	}

	@ApiOperation(value = "Fetching a user by Id")
	@GetMapping("/{id}")
	public Mono<User> findUserById(@PathVariable(value = "id") Long id) throws PlatformException {
		Mono<User> user = userService.findById(id);
		if (ObjectUtils.isEmpty(user)) {
			throw PlatformExceptionTranslator.wrapException(PlatformErrorCodes.NOT_FOUND.value(),
		              PlatformErrorCodes.NOT_FOUND.getErrorCode(), "Not Found");
		}
		return user;
	}
	
	@ApiOperation(value = "delete a user by Id")
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable(value = "id") Long id) throws PlatformException {
		userService.deleteResource(id);
	}
}
