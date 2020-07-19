package com.cg.bookstore;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.bookstore.entities.*;
import com.cg.bookstore.dao.BookStoreDao;
import com.cg.bookstore.exceptions.UserNotFoundException;
import com.cg.bookstore.service.BookStoreServiceImp;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookstoreApplicationTests {

	
	@Mock
	BookStoreDao bookStoredao;
	
	@InjectMocks
	private BookStoreServiceImp bookStoreService;
	
	
	@Test
	void TC_1() {
		assertThrows(UserNotFoundException.class,()->{bookStoreService.getUserList(1000);});
	}
	
	@Test
	void TC_2()
	{   
	    
	}
	

}
