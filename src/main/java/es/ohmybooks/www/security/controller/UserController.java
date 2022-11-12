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

  /**
   * endpoint that returns all users in the database
   * 
   * @return a json with all users and all their fields
   */
  @PostMapping("list")
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
  @PostMapping("id/{id}")
  public ResponseEntity<?> findUserByUserName(@PathVariable("id") int id) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<>(new Message("The user doesn't exist"), HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }
  }

	/**
	 * TODO
	 * 
	 * @param userDto
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("update/{idUser}")
	public ResponseEntity<?> updateUser(@PathVariable("idUser") int idUser, @RequestBody UserDto userDto, BindingResult bindingResult) {
		User user = userService.findById(idUser).get();
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

	/**
	 * TODO error al cambiar estado a disable
	 * 
	 * @param idUser
	 * @param status
	 * @return
	 */
	/*
	@PostMapping("disable/{idUser}")
	public ResponseEntity<?> disableUser(@PathVariable("idUser") int idUser, @RequestParam String status) {
		User user = userService.findById(idUser).get();
		user.setName(user.getName());
		user.setUserName(user.getUserName());
		user.setEmail(user.getEmail());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setPicture(user.getPicture());
		user.setStatus(status);

		userService.save(user);
		return new ResponseEntity<>(new Message("Disable user"), HttpStatus.CREATED);
	}
	*/

	@PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("delete/{idUser}")
  public ResponseEntity<?> deleteBook(@PathVariable("idUser") int idUser) {
    if (!userService.existsById(idUser))
      return new ResponseEntity<>(new Message("The user doesn't exist"), HttpStatus.NOT_FOUND);
    userService.deleteUserById(idUser);
    return new ResponseEntity<>(new Message("Deleted User"), HttpStatus.OK);
  }

}
