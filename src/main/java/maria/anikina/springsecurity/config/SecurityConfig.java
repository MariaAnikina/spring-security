package maria.anikina.springsecurity.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/auth/login", "/error", "/auth/registration").permitAll()
				.antMatchers("/person").hasAnyRole("SUPERVISOR", "ADMIN")
				.antMatchers("/news").hasAnyRole("USER", "SUPERVISOR", "ADMIN")
				.anyRequest().hasRole("ADMIN")
				.and()
				.formLogin().loginPage("/auth/login")
				.loginProcessingUrl("/process_login")
				.defaultSuccessUrl("/news", true)
				.failureUrl("/auth/login?error")
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/auth/login");
		return http.build();
	}


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
