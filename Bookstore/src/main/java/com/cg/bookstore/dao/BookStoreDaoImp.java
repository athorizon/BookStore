 package com.cg.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bookstore.entities.Admin;
import com.cg.bookstore.entities.CustomerInformation;
import com.cg.bookstore.entities.CustomerReview;
import com.cg.bookstore.entities.OrderInformation;
import com.cg.bookstore.entities.QueryResponseDTO;
import com.cg.bookstore.exceptions.BookStoreServiceException;
import com.cg.bookstore.exceptions.ListNotFoundException;
import com.cg.bookstore.exceptions.NoCustomerFoundException;
import com.cg.bookstore.exceptions.UserNotFoundException;

@Repository
@Transactional
public class BookStoreDaoImp implements BookStoreDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public BookStoreDaoImp()
	{}

	
	
	/*********************************************************************************************************************
	 * Method: getAdmin
	 * Description: fetched a object of admin with their adminId
	 * 
	 * @param adminId:
	 *            Admin's userId
     *  Created By - Kunal Maheshwari
	 * 
	 ***********************************************************************************************************************/
	@Override
	public Admin getAdmin(int adminId) {
		// TODO Auto-generated method stub
		Admin admin=entityManager.find(Admin.class, adminId);
		return admin;
	}
	
	@Override
	public Admin getAdminByEmail(String email) {
		String findquery="Select admin FROM Admin admin WHERE admin.email= :email";
		TypedQuery<Admin> query=entityManager.createQuery(findquery,Admin.class).setParameter("email",email);
		return query.getSingleResult();
	}
	
	
	@Override
	public CustomerInformation getCustomerByEmail(String email)
	{   CustomerInformation customer=null;
		try {
		String Qstr="Select customer From CustomerInformation customer Where customer.emailAddress=:email";
		TypedQuery<CustomerInformation> query=entityManager.createQuery(Qstr, CustomerInformation.class).setParameter("email", email);
		customer=query.getSingleResult();
		}
		catch(Exception e)
		{
			throw new UserNotFoundException("No Customer was Found");
		}
		return customer;
	}
	
	@Override
	public CustomerInformation getCustomer(int customer_id) {
		CustomerInformation customer=entityManager.find(CustomerInformation.class, customer_id);
		return customer;
	}

	
	
	
	@Override
	public void deleteCustomer(CustomerInformation customer,OrderInformation order)
	{   entityManager.remove(order);
		entityManager.remove(customer);
    }
	
	/********************************************************************************
	 * Method            deleteUser 
	 * Description       for checking whether the account exists or not and then
	 *                   deleting it
	 * returns boolean   returns true if account exists and gets deleted
	 *                   otherwise returns false if account does not exists 
	 * Created By        Vaishali Tiwari                   
	 * Created on        16-July-2020
	 
	 **********************************************************************************/
	@Override
	public boolean deleteUser(int adminId) throws BookStoreServiceException{
		
		if(entityManager.contains(entityManager.find(Admin.class, adminId)))
		{
		Admin user = entityManager.find(Admin.class, adminId);
		entityManager.remove(user);
		return true;
		}
		else
		{
			throw new BookStoreServiceException("User Not found");
		}
	
	}
	
	
	/**********************************************************************************
	* Method        saveAdmin
	* Description   This method persist data of admin to database 
	* returns       it return nothing
	* Created By    Ashok Sharma 
	* Created on    17-July-2020
	 * @throws BookStoreServiceException
	**********************************************************************************/
	
	@Override
	public void saveAdmin(Admin admin) {

		entityManager.persist(admin);
		
	}
	
	
	@Override
	public boolean editAdmin(int adminId, Admin admin) {
		Admin editAdmin = entityManager.find(Admin.class, adminId);
		if (editAdmin == null)
			return false;
		editAdmin.setEmail(admin.getEmail());
		editAdmin.setFullName(admin.getFullName());
		editAdmin.setPassword(admin.getPassword());
		entityManager.merge(editAdmin);
		return true;
	}
	
	@Override
	public boolean saveCustomer(CustomerInformation customer) {
		 entityManager.persist(customer);
		return true;
		
	}

	/*@Override
	public CustomerInformation FindByCustomerEmail(String emailAddress) {
		
		String findquery="Select customer.emailAddress FROM Customer customer WHERE customer.emailAddress= :emailAddress";
		TypedQuery<CustomerInformation> query=entityManager.createQuery(findquery,CustomerInformation.class).setParameter("emailAddress",emailAddress);
		return query.getSingleResult();
	}*/
	

	
	


	@Override
	public boolean checkCustomerByEmail(String emailAddress) {
		String checkquery="Select customer.emailAddress FROM CustomerInformation customer WHERE customer.emailAddress= :emailAddress";
		TypedQuery<String> query=entityManager.createQuery(checkquery,String.class).setParameter("emailAddress",emailAddress);
		try {
			
			query.getSingleResult();
		} catch(Exception exception) {
			
			return false;
		}
		return true;
	}
	
	
	@Override
	public boolean checkAdminByEmail(String email) {
		String checkquery="Select admin.email FROM Admin admin WHERE admin.email= :email";
		TypedQuery<String> query=entityManager.createQuery(checkquery,String.class).setParameter("email",email);
		try {
			
			query.getSingleResult();
			
		} catch(Exception exception) {
			
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean getCustomerReviewStatus(int customerId)
	{   
		List<CustomerReview> reviewList=null;
		try{
		String Qstr="Select review From CustomerReview review Where review.customerId=:customerId";
	    TypedQuery<CustomerReview> query = entityManager.createQuery(Qstr,CustomerReview.class).setParameter("customerId",customerId);
	    reviewList=query.getResultList();
		}
		catch(Exception e)
		{  
			return false;
		}
		if(reviewList.isEmpty())
		{
			return false;
		}
		return true;
	}
	
	@Override
	public OrderInformation getOrderByCustomer(int customerId)
	{
		String Qstr="Select bookStoreOrder From OrderInformation bookStoreOrder Join bookStoreOrder.customerDetails customer Where customer.customerId=:customerId";
		TypedQuery<OrderInformation> query = entityManager.createQuery(Qstr, OrderInformation.class).setParameter("customerId", customerId);
		return query.getSingleResult();
	}
	@Override
	public boolean getOrderInformationStatus(int customerId){ 
		
		String status;
		try {
			String Qstr="Select bookStoreOrder From OrderInformation bookStoreOrder Join bookStoreOrder.customerDetails customer Where customer.customerId=:customerId";
			TypedQuery<OrderInformation> query = entityManager.createQuery(Qstr, OrderInformation.class).setParameter("customerId", customerId);
			status=query.getSingleResult().getOrderStatus();
		}
		catch(Exception e){
			
			return false;
		}
		if(status.equals("Completed"))
		{
			return false;
		}
		return true;
	}
	
	/*********************************************************************************************************************
	 * Method: retreiveLsit
	 * Description: retreives a list of users from database
	 * 
	 * @param adminId:
	 *            Admin's userId
	 * 
	 * @throws ListNotFoundException:
	 *             if the list is empty or not found
     *  Created By - Kunal Maheshwari
	 * 
	 ***********************************************************************************************************************/
	@Override
	public List<Admin> retreiveList(int adminId) {
		// TODO Auto-generated method stub
		
		String Qstr="Select admin from Admin admin Where Not admin.adminId =: adminId";
		
		try {
		TypedQuery<Admin> query= entityManager.createQuery(Qstr,Admin.class).setParameter("adminId", adminId);
		return query.getResultList();
		}
		catch(Exception e)
		{
			throw new ListNotFoundException("The List you want to access does not exist");
		}
	}



	@Override
	public void updateCustomer(CustomerInformation updatedCustomer) {
		// TODO Auto-generated method stub
		entityManager.merge(updatedCustomer);
	}
	
	
	
	/*************
	 * Method Name:getAllCustomers<br/>
	 * This is used to fetch data of customers if valid pagenumber is provided by 
	 * admin.If pageNumber>availablepages then its going to {@link NoCustomerFoundException}
	 * which represents customer's doesn't exist at that page
	 * @param int pageNumber is the requested pagenumber
	 * @return {@link QueryResponseDTO} result of this query is wrapped in this class<br/> 
	 * which consist list<CustomerInformatio>,totalNoOfPages available,currentRequestedPageNumber
	 **************/

	@Override
	public QueryResponseDTO getAllCustomers(int pageNumber) {
		
			String queryToAllCustomers="SELECT customer FROM CustomerInformation customer WHERE customer.customerId>1 ORDER BY customerId DESC";
			
			TypedQuery<CustomerInformation> typedQueryForFetchingCustomers=entityManager.createQuery(queryToAllCustomers, CustomerInformation.class);
			
			typedQueryForFetchingCustomers.setFirstResult((pageNumber-1)*10); 
			typedQueryForFetchingCustomers.setMaxResults(10);
			
			List<CustomerInformation> resultList=typedQueryForFetchingCustomers.getResultList();
			
			QueryResponseDTO queryResponse=new QueryResponseDTO();
			queryResponse.setCurrentPageNumber(pageNumber);
			queryResponse.setTotalNoOfPages(getTotalNoOfPages());
			queryResponse.setList(resultList);
			return queryResponse;
		
		
	}

	@Override
	public long getTotalNoOfPages()
	{
		long totalNoOfPages=0;
		TypedQuery<Long> typedQueryForTotalNoOfRecords=entityManager.createQuery("SELECT COUNT(customer.customerId) FROM CustomerInformation customer WHERE customer.customerId>1", Long.class);
       
		long totalCount=typedQueryForTotalNoOfRecords.getSingleResult();
		
		if(totalCount%10==0)
			totalNoOfPages=totalCount/10;
		else
			totalNoOfPages=totalCount/10+1;
		
		return totalNoOfPages;
	}
}
