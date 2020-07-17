package com.cg.bookstore.dao;

import java.util.List;

import com.cg.bookstore.entities.Admin;

public interface BookStoreDao {
	public List<Admin> retreiveList(int adminId);
	public Admin getAdmin(int adminId);
}
