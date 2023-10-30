package edu.pnu;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
public class TestRepository {

	@Autowired
	private MemberRepository memberRepo;
	
	@Test
	public void TestInsert() {
		for (int i = 0; i <10; i++) {
			Member member = new Member();
			member.setName("mem" + i);
			member.setPass("abcd");
			member.setRegedate(new Date());
			memberRepo.save(member);
		
		}
	}
}
