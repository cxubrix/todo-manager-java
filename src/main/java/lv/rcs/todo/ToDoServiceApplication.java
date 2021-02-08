package lv.rcs.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lv.rcs.todo.controller.service.SimpleUserService;
import lv.rcs.todo.controller.service.ToDoManager;
import lv.rcs.todo.controller.service.UserService;
import lv.rcs.todo.dto.ToDo;
import lv.rcs.todo.web.controller.WebUserDetails;

@SpringBootApplication
@Configuration
@EnableWebSecurity
public class ToDoServiceApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(ToDoServiceApplication.class, args);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());
	}

	@Bean
	public UserService userService(ToDoManager todoManager) {
		UserService userService = new SimpleUserService();

		userService.save(new WebUserDetails("user", passwordEncoder().encode("password")));
		WebUserDetails user = (WebUserDetails) userService.get("user");
		todoManager.add(user, new ToDo("make coffee!"));
		todoManager.add(user, new ToDo("do stuff!"));

		userService.save(new WebUserDetails("test", passwordEncoder().encode("password")));
		WebUserDetails test = (WebUserDetails) userService.get("test");
		todoManager.add(test, new ToDo("test stuff!"));
		todoManager.add(test, new ToDo("mock stuff!"));
		todoManager.add(test, new ToDo("fix stuff!"));

		return userService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors() // not supporting csrf, cors can be done ambiguous here 
				// allow signup endpoint
				.and().authorizeRequests().antMatchers("/signup").permitAll()								
				// allow swagger endpoint
				.and().authorizeRequests().antMatchers("/documentation/**").permitAll()
				.and().authorizeRequests().antMatchers("/v2/api-docs").permitAll()
				// authorize all requests
				.and().authorizeRequests().anyRequest().authenticated()
				// use basic auth
				.and().httpBasic();
	}

}
