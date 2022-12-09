package es.ohmybooks.www.security.controller;

import java.util.Date;

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
import es.ohmybooks.www.service.UserBookService;

/**
 * Controller de User
 * 
 * @author Group3
 * @version 1.0
 */
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
	UserBookService userBookService;

	@Autowired
	JwtProvider jwtProvider;

	/**
	 * Endpoint que devuelve todos los users de la app
	 * 
	 * @return un json con todos los users y sus atributos
	 */
	@GetMapping("list")
	public ResponseEntity<?> listUsers() {
		return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
	}

	/**
	 * Endpoint que devuelve los users con id igual al valor pasado por parametro.
	 * 
	 * @param userName define el userName del usuario que se quiere filtrar.
	 * @return un mensaje de error o un json con el user filtrado y sus atributos.
	 */
	@GetMapping("userName/{userName}")
	public ResponseEntity<?> findUserByUserName(@PathVariable("userName") String userName) {
		if (!userService.existsByUserName(userName)) {
			return new ResponseEntity<>(new Message("The user doesn't exist"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(userService.getByUserName(userName), HttpStatus.OK);
		}
	}

	/**
	 * Endpoint que modifica los datos del user logueado.
	 * 
   * @param authorization define el token del usuario logueado.
	 * @param userDto define un objeto userDto que contine los atributos necesarios para modificar el usuario.
	 * @param bindingResult validacion del objeto.
	 * @return mensaje de error si el email ya existe o un mensaje de confirmacion si el user ha sido modificado correctamente.
	 */
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

	/**
	 * Endpoint que modifica el password del user logueado con validacion de password antiguo
	 * 
   * @param authorization define el token del usuario logueado.
	 * @param password define un objeto password que contiene los atributos necesarios para 
	 * validar el password antigua y modificarla por la nueva.
	 * @return mensaje de error o confirmacion.
	 */
	@PutMapping("updatePass")
	public ResponseEntity<?> updatePass(@RequestHeader String authorization, @RequestBody Password password) {
		String token = authorization.substring(7);
		String userName = jwtProvider.getUserNameFromToken(token);
		User user = userService.getByUserName(userName).get();
		if (passwordEncoder.matches(password.getOldPassword(), user.getPassword())) {
			user.setPassword(passwordEncoder.encode(password.getNewPassword()));
			userService.save(user);
			return new ResponseEntity<>(new Message("Modified password"), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(new Message("The actual password is not correct"), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Endpoint que cambia el status de user a enable o disable segun estado actual.
	 * 
   * @param authorization define el token del usuario logueado.
	 * @return mensaje de error o confirmacion.
	 */
	@PutMapping("changeStatus")
	public ResponseEntity<?> changeStatusUser(@RequestHeader String authorization) {
		String token = authorization.substring(7);
		String userName = jwtProvider.getUserNameFromToken(token);
		User user = userService.getByUserName(userName).get();
		if (user.isStatus() == true) {
			user.setStatus(false);
			user.setDisableAt(new Date());
			userService.save(user);
			userBookService.changeStatusByUserId(user.getId());
			return new ResponseEntity<>(new Message("Disable user"), HttpStatus.CREATED);
		} else {
			user.setStatus(true);
			user.setDisableAt(null);
			userService.save(user);
			userBookService.changeStatusByUserId(user.getId());
			return new ResponseEntity<>(new Message("Enable user"), HttpStatus.CREATED);
		}
	}

	/**
	 * Endpoint que elimina a un usuario de la app.
	 * Autorizacion solo para rol Admin.
	 * 
	 * @param userName define el userName del usuario que se quiere eliminar.
	 * @return mensaje de error o confirmacion.
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("delete/userName/{userName}")
	public ResponseEntity<?> delete(@PathVariable("userName") String userName) {
		User user = userService.getByUserName(userName).get();
		if (!userService.existsByUserName(userName)) {
			return new ResponseEntity<>(new Message("The user doesn't exist"), HttpStatus.NOT_FOUND);
		} else {
			userBookService.deleteRelationUserBook(user.getId());
			userService.deleteUserById(userService.getByUserName(userName).get().getId());
			return new ResponseEntity<>(new Message("Deleted User"), HttpStatus.OK);
		}
	}

}
