package com.cg.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.cg.bookstore.entities.Admin;
import com.cg.bookstore.entities.QueryResponseDTO;
import com.cg.bookstore.exceptions.UserNotFoundException;
import com.cg.bookstore.service.*;

@RestController
public class BookStoreController {
	
	@Autowired
	BookStoreService bookStoreService;
	

	@RequestMapping("/")
	String check()
	{return "Working";}
	
	@GetMapping("/admin/userlist/{adminId}")
	public ResponseEntity<List<Admin>> getUserList(@PathVariable("adminId") int adminId)
	{   
		if(adminId==0)
		{
			throw new UserNotFoundException("An invalid value is passed and user can't be accessed");
		}
		List<Admin> userList;
		userList=bookStoreService.getUserList(adminId);
		return new ResponseEntity<List<Admin>>(userList,HttpStatus.OK);
	}
	
	@GetMapping(path = "/admin/getallcustomers/{adminEmail}/{adminPassword}/{adminId}/{pageNumber}")
	public ResponseEntity<Object> getAllCustomers(@PathVariable("adminEmail") String adminEmail,@PathVariable("adminPassword") String adminPassword,@PathVariable("adminId") Integer adminId,@PathVariable("pageNumber") Integer pageNumber)
	{
		QueryResponseDTO queryResponse=bookStoreService.getAllCustomers(adminEmail, adminPassword, adminId, pageNumber);
		
		return new ResponseEntity<>(queryResponse,HttpStatus.OK);
	}
	@DeleteMapping("/admin/deleteCustomer/{email}")
	public ResponseEntity<String> deleteCustomer(@PathVariable String email) {
		bookStoreService.deleteCustomer(email);

		return new ResponseEntity<String>("hogaya", HttpStatus.OK);
	}
}
