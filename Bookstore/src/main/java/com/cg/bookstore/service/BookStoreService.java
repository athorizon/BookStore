package com.cg.bookstore.service;
import java.util.List;
import com.cg.bookstore.entities.Admin;
import com.cg.bookstore.entities.QueryResponseDTO;


public interface BookStoreService {
	public List<Admin> getUserList(int adminId);

	QueryResponseDTO getAllCustomers(String adminEmail, String adminPassword, int adminId, int pageNumber);

	public void deleteCustomer(String email);

}
