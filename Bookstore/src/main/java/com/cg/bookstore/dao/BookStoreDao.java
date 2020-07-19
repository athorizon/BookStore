package com.cg.bookstore.dao;

import java.util.List;

import com.cg.bookstore.entities.Admin;
import com.cg.bookstore.entities.CustomerInformation;
import com.cg.bookstore.entities.QueryResponseDTO;

public interface BookStoreDao {
	public List<Admin> retreiveList(int adminId);
	public Admin getAdmin(int adminId);
	QueryResponseDTO getAllCustomers(int pageNumber);
	public CustomerInformation getCustomerByEmail(String email);
	public boolean getCustomerReviewStatus(int customerId);
	public boolean getOrderInformationStatus(int customerId);
	public void deleteCustomer(CustomerInformation customer);
}
