package com.cg.bookstore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import com.cg.bookstore.entities.*;
import com.cg.bookstore.dao.BookStoreDao;
import com.cg.bookstore.exceptions.UserNotFoundException;
import com.cg.bookstore.service.BookStoreService;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookstoreApplicationTests {

	
	@Autowired
	BookStoreDao bookStoreDao;
	
	@Autowired
	private BookStoreService bookStoreService;
	
	
	@Test
	void TC_1() {
		assertThrows(UserNotFoundException.class,()->{bookStoreService.getUserList(1000);});
	}
	
	@Test
	void TC_2()
	{   
	    List<Admin> expectedUserList=new ArrayList<Admin>();
	    Admin admin1=new Admin("Kunal@gmail.com","Kunal Kmar","asas2432Q@");
	    Admin admin2=new Admin("Admin2@capgemini.com","Admin2","Admin@123");
	    expectedUserList.add(admin1);
	    expectedUserList.add(admin2);
	    List<Admin> returnList=bookStoreService.getUserList(100);
	}
}
