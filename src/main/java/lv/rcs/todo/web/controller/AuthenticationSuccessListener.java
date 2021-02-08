package lv.rcs.todo.web.controller;

import java.util.Date;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		WebUserDetails webUserDetails = (WebUserDetails) event.getAuthentication().getPrincipal();
		webUserDetails.setLastLogin(new Date()); // FIXME works for in memory userService only!
	}

}
