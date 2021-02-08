package lv.rcs.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lv.rcs.todo.controller.service.UserService;
import lv.rcs.todo.web.controller.WebUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		WebUserDetails userDetails = (WebUserDetails) userService.get(username);
		return userDetails;
	}

}
