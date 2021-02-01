package lv.rcs.todo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lv.rcs.todo.controller.service.UserService;
import lv.rcs.todo.web.controller.WebUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	@PostConstruct
	public void addUser() {
		WebUserDetails user = new WebUserDetails("admin@rcs.lv", passwordEncoder.encode("password"), "Developer",
				"Test");
		userService.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("load user: " + username);
		return (UserDetails) userService.get(username);
	}

}
