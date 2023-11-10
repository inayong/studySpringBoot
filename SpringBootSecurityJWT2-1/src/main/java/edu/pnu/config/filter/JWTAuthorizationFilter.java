package edu.pnu.config.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter { 
	//onceperrequestfilter를 상속받게 되면 하나의 요청에 대해서 단 한번만 필터를 거치게됨
	//예를들어 forwarding되어 다른 페이지로 이동하게 되더라도 다시 이 필터를 거치지 않게함

	//인가 설정을 위해 사용자의 role 정보를 읽기 위한 객체 설정
	private final MemberRepository memberRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String srcToken = request.getHeader("Authorization");
		if (srcToken == null || !srcToken.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		String jwtToken = srcToken.replace("Bearer ", "");
		
		String username = JWT.require(Algorithm.HMAC256("edu.pnu.jwt")).build().verify(jwtToken).getClaim("username").asString();
		
		Optional<Member> opt = memberRepository.findById(username);
		if (!opt.isPresent()) {
			filterChain.doFilter(request, response);
			return;
		}
		
		Member findmember = opt.get();
		
		//db에서 읽은 사용자정보를 이요해서 userdetails 타입의 객체 생성
		User user = new User(findmember.getUsername(), findmember.getPassword(), AuthorityUtils.createAuthorityList(findmember.getRole().toString()));
		
		// authentication 객체 생성 : 사용자명과 권한 관리를 위한 정보 입력(암호는 x)
		Authentication auth = new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
		
		//security session에 등록
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		filterChain.doFilter(request, response);
	}
	
}
