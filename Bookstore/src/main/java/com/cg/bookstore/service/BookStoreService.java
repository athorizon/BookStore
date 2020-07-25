package com.cg.bookstore.service;
import java.util.List;
import com.cg.bookstore.entities.Admin;
import com.cg.bookstore.entities.CustomerInformation;
import com.cg.bookstore.entities.QueryResponseDTO;
import com.cg.bookstore.exceptions.BookStoreServiceException;


public interface BookStoreService {
	public List<Admin> getUserList(int adminId);

	QueryResponseDTO getAllCustomers(String adminEmail, String adminPassword, int adminId, int pageNumber);

	public void deleteCustomer(String email);

	String editAdmin(int adminId, Admin admin) throws BookStoreServiceException;

	Admin loginAdmin(String email, String password) throws BookStoreServiceException;

	String addAdmin(Admin admin) throws BookStoreServiceException;

	boolean saveCustomer(CustomerInformation customerInfromation);

	CustomerInformation loginCustomer(String email, String password) throws BookStoreServiceException;

	boolean deleteUser(int adminId) throws BookStoreServiceException;

	void editCustomer(String email,CustomerInformation customer);

}
