package edu.pnu;

public class BuilderTest {

	public static void main(String[] args) {
		Member m = Member.builder().username("user").password("1234").build();
		
		System.out.println(m);
	}
}
