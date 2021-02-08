package lv.rcs.todo.web.controller;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class WebUserDetails extends lv.rcs.todo.dto.UserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String password;

	public WebUserDetails(String email, String password) {
		this(email, password, null, null);
	}

	public WebUserDetails(String email, String password, String firstname, String lastname) {
		super(email, firstname, lastname);
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null; // roles not used
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
