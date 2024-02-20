package maria.anikina.springsecurity.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import maria.anikina.springsecurity.model.PersonEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@Getter
@Component
public class PersonDetails implements UserDetails {
	private final PersonEntity personEntity;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(personEntity.getRole()));
	}

	@Override
	public String getPassword() {
		return this.personEntity.getPassword();
	}

	@Override
	public String getUsername() {
		return this.personEntity.getUsername();
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
