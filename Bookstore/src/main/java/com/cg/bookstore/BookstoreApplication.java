package com.cg.bookstore;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bookstore.entities.Admin;

@SpringBootApplication
@Transactional
public class BookstoreApplication implements CommandLineRunner{

	@Autowired
	EntityManager entityManager;
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*Admin admin1= new Admin("Admin1@capgemini.com"," Admin1","Admin@123");
		Admin admin2=new Admin("Admin2@capgemini.com","Admin2","Admin@123");
		Admin admin3=new Admin("Admin3@capgemini.com","Admin3","Admin@123");
		entityManager.persist(admin1);
		entityManager.persist(admin2);
		entityManager.persist(admin3);*/
		
	}

}
