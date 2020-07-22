package com.cg.bookstore.dao;

import java.util.List;

import com.cg.bookstore.entities.Admin;
import com.cg.bookstore.entities.CustomerInformation;
import com.cg.bookstore.entities.OrderInformation;
import com.cg.bookstore.entities.QueryResponseDTO;

public interface BookStoreDao {

	

	public CustomerInformation getCustomerByEmail(String email);
	public void deleteCustomer(CustomerInformation customer,OrderInformation order);
	boolean saveCustomer(CustomerInformation customer);
	CustomerInformation getCustomer(int customer_id);
	boolean checkCustomerByEmail(String emailAddress);
	public void updateCustomer(CustomerInformation updatedCustomer);
	
	void saveAdmin(Admin admin);
	public Admin getAdmin(int adminId);
	boolean editAdmin(int adminId, Admin admin);
	boolean deleteUser(int adminId);
	boolean checkAdminByEmail(String email);
	Admin getAdminByEmail(String email);
	
	public boolean getCustomerReviewStatus(int customerId);
	public boolean getOrderInformationStatus(int customerId);
	public List<Admin> retreiveList(int adminId);
	QueryResponseDTO getAllCustomers(int pageNumber);
	
	
	long getTotalNoOfPages();
	OrderInformation getOrderByCustomer(int customerId);
}
