package edu.pnu.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

public class BoardUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//memRepo에서 사용자 정보를 검색해서
		Optional<Member> option = memRepo.findById(username);
		if (!option.isPresent()) {
			throw new UsernameNotFoundException("사용자가 없습니다.");
		}
		Member member = option.get();
		System.out.println(member); //검색된 사용자 정보를 console에 출력
		
		// UserDetails 타입의 객체를 생성해서 리턴(o.s.s.core.userdetails.User)
		return new User(member.getUsername(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
	}

}
