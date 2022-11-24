package es.ohmybooks.www.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import es.ohmybooks.www.dto.Message;
import es.ohmybooks.www.security.dto.Password;
import es.ohmybooks.www.security.dto.UserDto;
import es.ohmybooks.www.security.entity.User;
import es.ohmybooks.www.security.jwt.JwtProvider;
import es.ohmybooks.www.security.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT })
public class UserController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	JwtProvider jwtProvider;

	/**
	 * endpoint that returns all users in the database
	 * 
	 * @return a json with all users and all their fields
	 */
	@GetMapping("list")
	public ResponseEntity<?> listUsers() {
		return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
	}

	/**
	 * endpoint that returns the user with the id equal to the value passed in the
	 * parameter
	 * 
	 * @param id
	 * @return a error message or a json with the searched user and all its fields
	 */
	@GetMapping("userName/{userName}")
	public ResponseEntity<?> findUserByUserName(@PathVariable("userName") String userName) {
		if (!userService.existsByUserName(userName)) {
			return new ResponseEntity<>(new Message("The user doesn't exist"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(userService.getByUserName(userName), HttpStatus.OK);
		}
	}

	@PutMapping("update")
	public ResponseEntity<?> updateUser(@RequestHeader String authorization, @RequestBody UserDto userDto,
			BindingResult bindingResult) {
		String token = authorization.substring(7);
		String userName = jwtProvider.getUserNameFromToken(token);
		User user = userService.getByUserName(userName).get();
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Message("Wrong fields or invalid email"), HttpStatus.BAD_REQUEST);
		}
		if (!userDto.getEmail().equals(user.getEmail())) {
			if (userService.existsByEmail(userDto.getEmail())) {
				return new ResponseEntity<>(new Message("This email already exists"), HttpStatus.BAD_REQUEST);
			}
		}

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPicture(userDto.getPicture());

		userService.save(user);
		return new ResponseEntity<>(new Message("Modified user"), HttpStatus.CREATED);
	}

	@PostMapping("updatePass")
	public ResponseEntity<?> updatePass(@RequestHeader String authorization, @RequestBody Password password) {
		String token = authorization.substring(7);
		String userName = jwtProvider.getUserNameFromToken(token);
		User user = userService.getByUserName(userName).get();
		if (passwordEncoder.matches(password.getOldPassword(), user.getPassword())) {
			user.setPassword(passwordEncoder.encode(password.getNewPassword()));
			userService.save(user);
			return new ResponseEntity<>(new Message("Modified password"), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(new Message("The actual password is not correct"), HttpStatus.CREATED);
		}
	}

	@PutMapping("disable")
	public ResponseEntity<?> disableUser(@RequestHeader String authorization) {
		String token = authorization.substring(7);
		String userName = jwtProvider.getUserNameFromToken(token);
		User user = userService.getByUserName(userName).get();
		user.setStatus(0);
		userService.save(user);
		return new ResponseEntity<>(new Message("Disable user"), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("delete/userName/{userName}")
	public ResponseEntity<?> delete(@PathVariable("userName") String userName) {
		if (!userService.existsByUserName(userName))
			return new ResponseEntity<>(new Message("The user doesn't exist"), HttpStatus.NOT_FOUND);
		userService.deleteUserById(userService.getByUserName(userName).get().getId());
		return new ResponseEntity<>(new Message("Deleted User"), HttpStatus.OK);
	}

}
