package com.cg.bookstore;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import com.cg.bookstore.entities.Admin;
import com.cg.bookstore.entities.CustomerInformation;

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
		
		/*LocalDate localDate= LocalDate.now();
		CustomerInformation customer1 = new CustomerInformation("customer1@capgemini.com","customer1","cus@123","1234567890","city1",123456,"country1",localDate);
		CustomerInformation customer2 = new CustomerInformation("customer2@capgemini.com","customer2","cus@123","1234567890","city2",123456,"country2",localDate);
		CustomerInformation customer3 = new CustomerInformation("customer3@capgemini.com","customer3","cus@123","1234567890","city3",123456,"country3",localDate);
		entityManager.persist(customer1);
		entityManager.persist(customer2);
		entityManager.persist(customer3);*/
	}

}
