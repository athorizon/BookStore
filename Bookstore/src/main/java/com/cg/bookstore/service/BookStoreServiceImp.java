package com.cg.bookstore.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bookstore.dao.*;
import com.cg.bookstore.entities.Admin;
import com.cg.bookstore.entities.CustomerInformation;
import com.cg.bookstore.entities.OrderInformation;
import com.cg.bookstore.entities.QueryResponseDTO;
import com.cg.bookstore.exceptions.BookStoreServiceException;
import com.cg.bookstore.exceptions.InvalidCredentialsException;
import com.cg.bookstore.exceptions.ListNotFoundException;
import com.cg.bookstore.exceptions.NoCustomerFoundException;
import com.cg.bookstore.exceptions.UserNotFoundException;


@Service
@Transactional
public class BookStoreServiceImp implements BookStoreService {
    
	@Autowired
	private BookStoreDao bookStoreDao;
	
    public BookStoreServiceImp()
    {}
    

	/*********************************************************************************************************************
	 * Method: getUserList
	 * Description: checks wheater the loggedin Admin is valid. and then ask for list of other admins
	 * 
	 * @param adminId:  Admin's userId
	 * @throws UserNotFoundException: if the admin is an invalid or not present in the database.
	 * @return userList: list containing the objects of admins from the database            
     *  Created By - Kunal Maheshwari
	 ***********************************************************************************************************************/
	@Override
	public List<Admin> getUserList(int adminId) {
		// TODO Auto-generated method stub
		Admin admin=bookStoreDao.getAdmin(adminId);
		if(admin==null)
			throw new UserNotFoundException("User might be removed or not available");
		List<Admin> userList;
		userList=bookStoreDao.retreiveList(adminId);
		return userList;
	}
	
	@Override
	public void deleteCustomer(String email)
	{
		CustomerInformation customer=bookStoreDao.getCustomerByEmail(email);
		boolean customerReviewStatus = bookStoreDao.getCustomerReviewStatus(customer.getCustomerId());
		
		if(customerReviewStatus==true)
		{   
			throw new UserNotFoundException("cannot delete as review is found");
		}
		
		boolean orderInformationStatus = bookStoreDao.getOrderInformationStatus(customer.getCustomerId());
		
		if(orderInformationStatus==true)
		{
			throw new UserNotFoundException("cannot delete as order is found");
		}
		OrderInformation orderToDelete=bookStoreDao.getOrderByCustomer(customer.getCustomerId());
		
		bookStoreDao.deleteCustomer(customer,orderToDelete);
	}
	/********************************************************************************
	 * Method            deleteUser 
	 * Description       for deleting User account
	 * Created By        Vaishali Tiwari                   
	 * Created on        16-July-2020
	 
	 **********************************************************************************/
	
	
	@Override
	public boolean deleteUser(int adminId) throws BookStoreServiceException
	{
		return bookStoreDao.deleteUser(adminId);
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
	@Override
	public QueryResponseDTO getAllCustomers(String adminEmail, String adminPassword, int adminId,
			int pageNumber) {
		if(pageNumber>0)
		{
			if(adminId>0)
			{
					Admin admin=bookStoreDao.getAdmin(adminId);
					if(admin==null)
					{
						throw new InvalidCredentialsException("Invalid credentials!");
					}
					else if(admin.getEmail().equals(adminEmail) && admin.getPassword().equals(adminPassword))
					{
						if(pageNumber<=bookStoreDao.getTotalNoOfPages())
						{
							//todo ask if we can do this as it is io operation
							return bookStoreDao.getAllCustomers(pageNumber);
						}
						else
						{
							throw new NoCustomerFoundException("Customer's Data is Not Found in requested pageNumber");
						}
					}
					else
					{
						throw new InvalidCredentialsException("Invalid Credentials!");
					}
			}
			else
			{
				throw new InvalidCredentialsException("Credentials are invalid");
			}
		}
		else
		{
			throw new NoCustomerFoundException("Invalid page numnber");
		}
	}
	
	
	
	@Override
	public void editCustomer(CustomerInformation customer)
	{   String emailAddress=customer.getEmailAddress();
		CustomerInformation updatedCustomer=bookStoreDao.getCustomerByEmail(emailAddress);
		if(updatedCustomer==null)
			throw new UserNotFoundException("Provided details can be updated as an user is not found");
		updatedCustomer.setCity(customer.getCity());
		updatedCustomer.setCountry(customer.getCountry());
		updatedCustomer.setFullName(customer.getFullName());
		updatedCustomer.setPhoneNumber(customer.getPhoneNumber());
		updatedCustomer.setZipCode(customer.getZipCode());
		
		bookStoreDao.updateCustomer(updatedCustomer);
	}
	
	String regexForPassword = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,16}$";
	
	
	Pattern patternForPassword = Pattern.compile(regexForPassword);
	
	/*//**********************************************************************************
	* Method        addAdmin
	* Description   This method will check all the validation and Exception if entered
	*                 details are correct then only it will send data to dao layer
	* returns       This method return string to tell the admn if another admin is created or not.
	* Created By    Ashok Sharma 
	* Created on    17-July-2020
	 * @throws BookStoreServiceException
	**********************************************************************************/
	
	@Override
	public String addAdmin(Admin admin) throws BookStoreServiceException {
		
		boolean check=bookStoreDao.checkAdminByEmail(admin.getEmail());
		if(check==true)
		{
			throw new BookStoreServiceException("Entered email id is already exists");
		}
		
		if(admin.getEmail().equals("") || (admin.getEmail().length()<11 || admin.getEmail().length()>64))
		{
            throw new BookStoreServiceException("Please Enter Valid Email Id");
		}
		
		else if(admin.getFullName().equals("") || (admin.getFullName().length()<8 || admin.getFullName().length()>30) )
		{
			throw new BookStoreServiceException("Name Can't Be Empty having length between 8 and 30");
		}
		
		
		else if(admin.getPassword().equals("") || (admin.getPassword().length()<8 || admin.getPassword().length()>16))
		{
			throw new BookStoreServiceException("Password shouldn't be empty having length between 8 to 16 ");

		}
		
		Matcher matcher = patternForPassword.matcher(admin.getPassword());
		
		System.out.println(matcher.matches());
	    if(matcher.matches()==false)
		{
		throw new BookStoreServiceException("Password Must have  alteast one special ,one numeric, one capital character");
		}
	    bookStoreDao.saveAdmin(admin);
	    return "New Admin Created Successfully";
		
	}

	
	
	
	@Override
	public String editAdmin(int adminId, Admin admin) throws BookStoreServiceException{
		if(bookStoreDao.editAdmin(adminId, admin))
			return "Admin updated";
		else
			throw new BookStoreServiceException("Admin not found.");
	}

	
	@Override
	public boolean saveCustomer(CustomerInformation customer) {
		Date dateNow = new Date();
		SimpleDateFormat objectofSimpleDateFormat=new SimpleDateFormat ("hh:mm a',' E dd-MM-yyyy");
		if(customer.getEmailAddress()==null || customer.getCity()==null || customer.getCountry()==null || customer.getEmailAddress()==null || customer.getFullName()==null || 
				customer.getPassword()==null || customer.getPhoneNumber()==null || customer.getZipCode()==0  )
			throw new BookStoreServiceException("A data filed is found to be null");
		customer.setRegisterDate(objectofSimpleDateFormat.format(dateNow));
		boolean check=bookStoreDao.saveCustomer(customer);
		return check;
	}
	
	@Override
	public Integer loginCustomer(String email, String password) throws BookStoreServiceException {
		
		if(!bookStoreDao.checkCustomerByEmail(email))
				throw new BookStoreServiceException("Customer is not registered with this email");
		
		
		CustomerInformation customer=bookStoreDao.getCustomerByEmail(email);
		
		if(customer.getPassword().equals(password)==false)
				throw new BookStoreServiceException("The password does not match the Email provided");
			
		return customer.getCustomerId();
	}
	
	@Override
	public Integer loginAdmin(String email, String password) throws BookStoreServiceException {
		if(!bookStoreDao.checkAdminByEmail(email))
				throw new BookStoreServiceException("Admin is not registered with this email");
		
		Admin admin=bookStoreDao.getAdminByEmail(email);
		
		if(admin.getPassword().equals(password)==false)
				throw new BookStoreServiceException("Admin is not registered with this email");
		return admin.getAdminId();
	}
}
