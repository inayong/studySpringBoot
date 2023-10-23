package edu.pnu;

import java.util.Date;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.pnu.domain.Board;

public class JPAClientSearch {

	public static void main(String[] args) {

		//EntityManager 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04"); // => <persistence-unit name="Chapter04">
		EntityManager em = emf.createEntityManager();
		
		try {
			//글 상세 조회
			Board searchBoard = em.find(Board.class, 1L);
			System.out.println("---> " + searchBoard.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();			
		}
		
	}

}
