package com.cg.bookstore.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bookstore.dao.*;
import com.cg.bookstore.entities.Admin;
import com.cg.bookstore.entities.CustomerInformation;
import com.cg.bookstore.exceptions.UserNotFoundException;


@Service
@Transactional
public class BookStoreServiceImp implements BookStoreService {
    
	@Autowired
	private BookStoreDao bookStoreDao;
	
    public BookStoreServiceImp()
    {}
    
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
	
//	public void deleteCustomer(String email)
//	{
//		CustomerInformation customer=bookStoreDao.getCustomerByEmail(email);
//		boolean customerReviewStatus = bookStoreDao.getCustomerReviewStatus(customer.getCustomerId());
//		
//		if(customerReviewStatus==true)
//		{
//			//throw exception
//		}
//		
//		boolean orderInformationStatus = bookStoreDao.getOrderInformationStatus(customer.getCustomerId());
//		
//		if(orderInformationStatus==true)
//		{
//			//throw exception
//		}
//		
//		bookStoreDao.deleteCustomer(customer);
//	}

}
