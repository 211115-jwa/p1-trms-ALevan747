package com.revature.data;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.*;
import com.revature.beans.Comment;
import com.revature.utils.DAOFactory;

public class CommentDAOTest {
	private CommentDAO cDao = DAOFactory.getCommentDAO();
	private ReimbursementDAO redao = DAOFactory.getReimbursementDAO();
	private EmployeeDAO empdao = DAOFactory.getEmployeeDAO();
	
	
	@Test
	public void createTest() {
		Comment c = new Comment();
		c.setRequest(redao.getById(2));
		c.setApprover(empdao.getById(4));
		c.setCommentText("daotest");
		int id= cDao.create(c);
		//System.out.println(id);
		assertNotEquals(0, id);
	}
	
	@Test
	public void getByIdNull()
	{
		assertNull(cDao.getById(1212));
	}
	
	@Test
	public void getByIdNotNull()
	{
		Comment a= cDao.getById(6);
		System.out.println(a);
		assertNotNull(a);
	}
	
	@Test
	public void getAllNotNull()
	{
		assertNotNull(cDao.getAll());
	}
	
	@Test
	public void getByReqINull()
	{
		assertTrue(cDao.getByRequestId(54654).isEmpty());
	}
	
	@Test
	public void getByReqIdNotNull()
	{
		assertNotNull(cDao.getByRequestId(1));
	}
	
}
