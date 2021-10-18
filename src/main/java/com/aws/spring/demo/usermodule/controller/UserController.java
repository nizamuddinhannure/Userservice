package com.aws.spring.demo.usermodule.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aws.spring.demo.usermodule.model.User;

@RestController
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private static List<User> users = new ArrayList<>();

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String healthcheck() {
		LOGGER.debug("Request to health check");
		String health = "Health Check Status";
		return health;
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String userName(final @PathVariable String name) {
		LOGGER.debug("Request for name[{}]", name);
		String message = "Hi " + name + ", Welcome to my application.";
		return message;
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(final @PathVariable Integer userId) {
		LOGGER.debug("Request to get user by userId [{}]", userId);
		try {
			Optional<User> optionalUser = filterUser(userId);
			if (optionalUser.isEmpty()) {
				LOGGER.error("User[{}] not found.", userId);
			}

			return new ResponseEntity<User>(optionalUser.get(), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.debug("Exception occured in Get User method, Exception is [{}].", e);
		}

		return null;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getUsers() {
		LOGGER.debug("Request to get all users");
		try {
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.debug("Exception occured in Get User method, Exception is [{}].", e);
		}

		return null;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> addUser(@RequestBody User user) {
		LOGGER.debug("Request to add user detail [{}]", user);
		try {
			users.add(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.debug("Exception occured in Post User method, Exception is [{}].", e);
		}

		return null;
	}

	@RequestMapping(value = "user/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> deleteUser(final @PathVariable Integer userId) {
		LOGGER.debug("Request to delete user by userId [{}]", userId);
		try {
			Optional<User> optionalUser = filterUser(userId);

			boolean isUserDeleted = users.removeIf(user -> user.getId().equals(userId));
			if (isUserDeleted) {
				LOGGER.debug("User[{}] successfully deleted.", userId);
			} else {
				LOGGER.error("Faile to delete User[{}].", userId);
			}

			return new ResponseEntity<User>(optionalUser.get(), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.debug("Exception occured in delete User method, Exception is [{}].", e);
		}

		return null;
	}

	public static List<User> getUser() {
		return users;
	}

	public Optional<User> filterUser(Integer userId) {
		return getUser().stream().filter(user -> user.getId().equals(userId)).findFirst();
	}
}
