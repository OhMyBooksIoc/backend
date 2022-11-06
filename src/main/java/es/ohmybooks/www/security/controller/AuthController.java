package es.ohmybooks.www.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import es.ohmybooks.www.dto.Message;
import es.ohmybooks.www.security.dto.JwtDto;
import es.ohmybooks.www.security.dto.Login;
import es.ohmybooks.www.security.dto.NewUser;
import es.ohmybooks.www.security.entity.Role;
import es.ohmybooks.www.security.entity.User;
import es.ohmybooks.www.security.enums.RoleName;
import es.ohmybooks.www.security.jwt.JwtProvider;
import es.ohmybooks.www.security.service.RoleService;
import es.ohmybooks.www.security.service.UserService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	JwtProvider jwtProvider;

	/**
	 * TODO
	 * 
	 * @param newUser
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/newUser")
	public ResponseEntity<?> newUser(@RequestBody NewUser newUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Message("Wrong fields or invalid email"), HttpStatus.BAD_REQUEST);
		}
		if (userService.existsByUserName(newUser.getUserName())) {
			return new ResponseEntity<>(new Message("This username already exists"), HttpStatus.BAD_REQUEST);
		}
		if (userService.existsByEmail(newUser.getEmail())) {
			return new ResponseEntity<>(new Message("This email already exists"), HttpStatus.BAD_REQUEST);
		}

		User user = new User(newUser.getName(), newUser.getUserName(),
				newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));

		Set<Role> roles = new HashSet<>();
		roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
		if (newUser.getRoles().contains("admin")) {
			roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
			roles.add(roleService.getByRoleName(RoleName.ROLE_AUTHOR).get());
		}
		if (newUser.getRoles().contains("author")) {
			roles.add(roleService.getByRoleName(RoleName.ROLE_AUTHOR).get());
		}
		user.setRoles(roles);

		userService.save(user);

		return new ResponseEntity<>(new Message("Created user"), HttpStatus.CREATED);
	}

	/**
	 * TODO
	 * 
	 * @param loginUsuario
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Login login, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Message("Bad fields"), HttpStatus.BAD_REQUEST);
		}
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(login.getUserName(),
						login.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity<>(jwtDto, HttpStatus.OK);
	}
}
