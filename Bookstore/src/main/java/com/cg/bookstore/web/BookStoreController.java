package com.cg.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.cg.bookstore.entities.Admin;
import com.cg.bookstore.entities.CustomerInformation;
import com.cg.bookstore.entities.QueryResponseDTO;
import com.cg.bookstore.exceptions.BookStoreServiceException;
import com.cg.bookstore.service.*;

@RestController
public class BookStoreController {
	
	@Autowired
	BookStoreService bookStoreService;
	

	@RequestMapping("/")
	String check()
	{return "Working";}
	
	/*********************************************************************************************************************
	 * Method: getUserList
	 * Description: handler mapped with get function to get request from the ui. fetches the user list
	 * 
	 * @param adminId:  Admin's userId
	 * @return userList: list containing the objects of admins from the database            
     *  Created By - Kunal Maheshwari
	 * 
	 ***********************************************************************************************************************/
	
	@GetMapping("/admin/getallusers/{adminId}")
	public ResponseEntity<List<Admin>> getUserList(@PathVariable("adminId") int adminId)
	{ 
		List<Admin> userList;
		userList=bookStoreService.getUserList(adminId);
		return new ResponseEntity<List<Admin>>(userList,HttpStatus.OK);
	}
	
	/**
	 * getAllCustomers is for retrieving all customers.data is retrieved only by admin
	 * so that valid admin credentials should be provided to fetch also you cannot fetch all data
	 * at once you need to specify page number to retrieve.
	 * @param adminEmail is emailaddress of admin
	 * @param adminPassword is password of admin account
	 * @param adminId is unique of admin 
	 * @param pageNumber is the page which you want to fetch at a time you can fetch five records
	 * @author Aravinda Reddy
	 */
	//todo ask peers about password encryption 
	@GetMapping(path = "/admin/customers/{adminEmail}/{adminPassword}/{adminId}/{pageNumber}")
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
	
	@DeleteMapping("/admin/deleteUser/{adminId}")
	public ResponseEntity<String> deleteUser(@PathVariable int adminId) throws BookStoreServiceException {
		String response="";
		boolean result = bookStoreService.deleteUser(adminId);
		if (result) {
			response = "User Account deleted Sucessfully";
		} 
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	/**********************************************************************************
	* Method        addAdmin
	* Description   To add another admin 
	* returns       This method return string to tell if new admin is created or not.
	* Created By    Ashok Sharma 
	* Created on    17-July-2020
	 * @throws BookStoreServiceException
	**********************************************************************************/
	
	
	@PostMapping(value="/admin/addAdmin",consumes= {"application/json"})
	public String addAdmin(@RequestBody Admin admin) throws BookStoreServiceException
	{
		try 
		{	
			return bookStoreService.addAdmin(admin);
		}
		catch(NullPointerException bookStoreException)
		{
			throw new BookStoreServiceException("Please Enter Valid Input");
		}
	}
	
	@PutMapping("/admin/editAdmin/{adminId}")
	public String editAdmin(@PathVariable int adminId, @RequestBody Admin admin) throws BookStoreServiceException {
		try{
			
			return bookStoreService.editAdmin(adminId, admin);
			} catch(Exception exception) {
				throw new BookStoreServiceException(exception.getMessage());
			}
	}
	
	@PostMapping("/admin/addcustomers")
	public String addCustomer(@RequestBody CustomerInformation customerInformation) throws BookStoreServiceException
	{
		try {
			boolean status=bookStoreService.saveCustomer(customerInformation);
			if(!status) {
			
			throw new BookStoreServiceException("Can't Perform Signup Process! Check your Entered data is correct");
	        	}
		
		}
		catch(Exception e) {
			return "EmailId already exist";
		}
		return "Customer Profile Created Successfully";
		
	}
	
	@GetMapping(value="/customerlogin/{email}/{password}")
	public ResponseEntity<Integer> customerlogin(@PathVariable("email") String email,@PathVariable("password") String password) throws BookStoreServiceException {
		Integer customerid=bookStoreService.loginCustomer(email, password);
		return new ResponseEntity<Integer>(customerid, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/adminlogin/{email}/{password}")
	public ResponseEntity<Integer> adminlogin(@PathVariable("email") String email,@PathVariable("password") String password) throws BookStoreServiceException {
		
		Integer adminid=bookStoreService.loginAdmin(email, password);
		return new ResponseEntity<Integer>(adminid, HttpStatus.OK);
	}
	
	@PutMapping("admin/updatecustomer")
	public ResponseEntity<String> updateCustomer(@RequestBody CustomerInformation customer) throws BookStoreServiceException {
		bookStoreService.editCustomer(customer);
		return new ResponseEntity<String>("updated the customer",HttpStatus.OK);
	}
}
