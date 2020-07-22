package com.cg.bookstore;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bookstore.dao.BookStoreDao;
import com.cg.bookstore.dao.BookStoreDaoImp;
import com.cg.bookstore.entities.Admin;
import com.cg.bookstore.entities.CustomerInformation;
import com.cg.bookstore.entities.CustomerReview;
import com.cg.bookstore.entities.OrderInformation;

@SpringBootApplication
@Transactional
public class BookstoreApplication implements CommandLineRunner{

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	BookStoreDao dao;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Admin admin1= new Admin("Admin1@capgemini.com"," Admin1","Admin@123");
		Admin admin2=new Admin("Admin2@capgemini.com","Admin2","Admin@123");
		Admin admin3=new Admin("Admin3@capgemini.com","Admin3","Admin@123");
		entityManager.persist(admin1);
		entityManager.persist(admin2);
		entityManager.persist(admin3);
		
		LocalDate localDate= LocalDate.now();
		CustomerInformation customer1 = new CustomerInformation("customer1@capgemini.com","customer1","cus@123","1234567890","city1",123456,"country1","10/07/2020");
		CustomerInformation customer2 = new CustomerInformation("customer2@capgemini.com","customer2","cus@123","1234567890","city2",123456,"country2","10/07/2020");
		CustomerInformation customer3 = new CustomerInformation("customer3@capgemini.com","customer3","cus@123","1234567890","city3",123456,"country3","10/07/2020");
		entityManager.persist(customer1);
		entityManager.persist(customer2);
		entityManager.persist(customer3);
		
		
		CustomerReview review1=new CustomerReview(4,"A very Nice book","Awesome book,one should must read",84);
		entityManager.persist(review1);
		
		CustomerInformation customer=dao.getCustomer(102);
		OrderInformation order=new OrderInformation(customer,"mera ghar",5,new Float(500.55),new Float(500.55),"Completed","pesa");
		entityManager.persist(order);
	}

}
