package lv.rcs.todo.web.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lv.rcs.todo.SignUpException;
import lv.rcs.todo.controller.service.UserService;
import lv.rcs.todo.dto.UserDetails;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Could be in it's own controller
	@PostMapping("/signup")
	public UserDetails signup(@RequestBody SignupInfo info) throws SignUpException {
		WebUserDetails webUserDetails = toWebUserDetails(info);
		userService.save(webUserDetails);
		return userService.get(webUserDetails.getEmail()).strip(); // readback & strip down to UserDetails
	}

	@GetMapping("/profile")
	public UserDetails getProfile(@AuthenticationPrincipal UserDetails user) {
		return userService.get(user.getEmail()).strip(); // strip down to UserDetails
	}

	@PostMapping("/profile")
	public void updateProfile(@RequestBody SignupInfo info, @AuthenticationPrincipal UserDetails user) {

		String firstname = info.getFirstname();
		if (firstname != null) { // null not allowed
			user.setFirstname(firstname);
		}

		String lastname = info.getLastname();
		if (lastname != null) { // null not allowed
			user.setLastname(lastname);
		}

		userService.update(user); // note that security context might need reload in case of persistent storage
	}

	@DeleteMapping("/profile")
	public void deleteUser(@AuthenticationPrincipal UserDetails user) {
		userService.remove(user.getEmail());
		SecurityContextHolder.getContext().setAuthentication(null); // logout
	}

	@ExceptionHandler({ SignUpException.class })
	public ResponseEntity<Object> handleCustomException(SignUpException e) {

		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		Map<String, Object> body = new LinkedHashMap<>(); // TODO Use @ErrorAttributes
		body.put("timestamp", new Date());
		body.put("status", status.value());
		body.put("message", e.getMessage());

		return new ResponseEntity<Object>(body, status);
	}

	private WebUserDetails toWebUserDetails(SignupInfo signupInfo) throws SignUpException {
		if (signupInfo == null) {
			throw new SignUpException("empty");
		}

		String password = signupInfo.getPassword();
		String email = signupInfo.getEmail();

		// check if email and password are not empty and if username(email) is not taken
		if (email == null || email.isBlank() || password == null || password.isBlank()
				|| userService.get(email) != null) {
			throw new SignUpException("invalid email or password");
		}

		// create web user with encoded password
		String firestname = signupInfo.getFirstname(); // TODO validate
		String lastname = signupInfo.getLastname();// TODO validate
		String passwordEncoded = passwordEncoder.encode(password);
		return new WebUserDetails(email, passwordEncoded, firestname, lastname);
	}

}
