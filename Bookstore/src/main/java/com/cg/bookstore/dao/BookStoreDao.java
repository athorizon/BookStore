package com.cg.bookstore.dao;

import java.util.List;

import com.cg.bookstore.entities.Admin;
import com.cg.bookstore.entities.QueryResponseDTO;

public interface BookStoreDao {
	public List<Admin> retreiveList(int adminId);
	public Admin getAdmin(int adminId);
	QueryResponseDTO getAllCustomers(int pageNumber);
}
