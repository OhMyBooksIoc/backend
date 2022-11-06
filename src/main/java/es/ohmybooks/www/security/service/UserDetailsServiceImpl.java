package es.ohmybooks.www.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.ohmybooks.www.security.entity.User;
import es.ohmybooks.www.security.entity.UserMain;

import javax.transaction.Transactional;

/**
 * Convert the User class to a UserMain
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userService.getByUserName(userName).get();
		return UserMain.build(user);
	}

}
