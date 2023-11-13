package edu.pnu.board.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers(new AntPathRequestMatcher("/board/**")).authenticated()
				.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
				.anyRequest().permitAll());
		
		http.csrf(csrf -> csrf.disable());
		
		http.formLogin(form -> form
				.loginPage("/system/login")
				.defaultSuccessUrl("/board/getBoardList", true));
		
		http.exceptionHandling(ex -> ex.accessDeniedPage("/system/accessDenied"));
		
		http.logout(logout -> logout.logoutUrl("/system/logout").invalidateHttpSession(true).logoutSuccessUrl("/"));
		
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
