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
import es.ohmybooks.www.security.dto.UserDto;
import es.ohmybooks.www.security.entity.User;
import es.ohmybooks.www.security.jwt.JwtProvider;
import es.ohmybooks.www.security.service.UserService;


@RestController
@RequestMapping("/user")
@CrossOrigin
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
	public ResponseEntity<?> updateUser(@RequestHeader String authorization, @RequestBody UserDto userDto, BindingResult bindingResult) {
		String token = authorization.substring(7);
		String userName = jwtProvider.getUserNameFromToken(token);
		User user = userService.getByUserName(userName).get();
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Message("Wrong fields or invalid email"), HttpStatus.BAD_REQUEST);
		}
		if (!userDto.getUserName().equals(user.getUserName())){
		 	if (userService.existsByUserName(userDto.getUserName())) {
				return new ResponseEntity<>(new Message("This username already exists"), HttpStatus.BAD_REQUEST);
			}
		}	
		if (!userDto.getEmail().equals(user.getEmail())){
			if (userService.existsByEmail(userDto.getEmail())) {
				return new ResponseEntity<>(new Message("This email already exists"), HttpStatus.BAD_REQUEST);
			}
		}

		user.setName(userDto.getName());
		user.setUserName(userDto.getUserName());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setPicture(userDto.getPicture());
		user.setStatus(user.getStatus());

		userService.save(user);
		return new ResponseEntity<>(new Message("Modified user"), HttpStatus.CREATED);
	}

	@PutMapping("disable")
	public ResponseEntity<?> disableUser(@RequestHeader String authorization) {
		String token = authorization.substring(7);
		String userName = jwtProvider.getUserNameFromToken(token);
		User user = userService.getByUserName(userName).get();
		user.setName(user.getName());
		user.setUserName(user.getUserName());
		user.setEmail(user.getEmail());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setPicture(user.getPicture());
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
