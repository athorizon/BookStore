package com.cg.bookstore.service;
import java.util.List;
import com.cg.bookstore.entities.Admin;


public interface BookStoreService {
	public List<Admin> getUserList(int adminId);

}
