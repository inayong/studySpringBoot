package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import edu.pnu.config.filter.JWTAuthenticationFilter;
import edu.pnu.config.filter.JWTAuthorizationFilter;
import edu.pnu.persistence.MemberRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Autowired
	private MemberRepository memberRepository;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());
		
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers(new AntPathRequestMatcher("/member/**")).authenticated()
				.requestMatchers(new AntPathRequestMatcher("/manager/**")).hasAnyRole("MANAGER", "ADMIN") //manager에는 mamager+admin이 접근가능
				.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN") //admin만 접근가능
				.anyRequest().permitAll());
		
		http.formLogin(frmLogin -> frmLogin.disable());  //Form을 이용한 로그인을 사용하지 않겠다는 설정
		http.httpBasic(basic -> basic.disable());		// http basic인증 방식을 사용하지 않겠다는 설정
		
		//세션을 유지하지 않겠다는 설정 -> url 호출 뒤 응답할때 까지는 유지, 응답후 삭제
		http.sessionManagement(ssmn -> ssmn.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		// spring security가 등록한 filterchain 뒤에 작성한 필터 추가
		http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()));
		
		//spring security가 등록한 필터 중에서 authorizationfilter 앞에 앞에서 작성한 필터를 삽입
		http.addFilterBefore(new JWTAuthorizationFilter(memberRepository), AuthorizationFilter.class);
		
		return http.build();
	}
}
