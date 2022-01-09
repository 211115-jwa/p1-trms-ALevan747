package com.revature.data;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.*;

import com.revature.utils.DAOFactory;

public class DepartmentDAOTest {
	private DepartmentDAO depDao = DAOFactory.getDepartmentDAO();
	
	@Test
	public void getByIdNotNull()
	{
		assertNotNull(depDao.getById(1));
	}
	
	@Test
	public void getByIdNull()
	{
		assertNull(depDao.getById(2454));
	}
	
	@Test
	public void getAllNotNull()
	{
		assertNotNull(depDao.getAll());
	}
	
	@Test
	public void getByNameNotNull()
	{
		assertNotNull(depDao.getByName("Engineers"));
	}
	
	
	@Test
	public void getByNameNull()
	{
		assertTrue(depDao.getByName("pokemon").isEmpty());
	}
	

}
