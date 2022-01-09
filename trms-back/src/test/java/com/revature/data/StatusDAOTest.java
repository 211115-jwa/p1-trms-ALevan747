package com.revature.data;

import com.revature.beans.Status;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import org.junit.jupiter.api.*;
import com.revature.utils.DAOFactory;

public class StatusDAOTest {
	
private StatusDAO statDao = DAOFactory.getStatusDAO();
	

	
	@Test
	public void getByIdNotNull()
	{
		Status actual = statDao.getById(1);
		assertNotEquals(null, actual);
	}
	
	@Test
	public void getByIdValidStatus()
	{
		Status one = statDao.getById(1);
		assertEquals("pending approval", one.getName());
	}
	
	@Test
	public void getAllNotNull()
	{
		assertNotNull(statDao.getAll());
	}
	
	@Test
	public void getByNameNotNull()
	{
		assertNotNull(statDao.getByName("pending approval"));
	}
	
	@Test
	public void getByNameNull()
	{
		assertThrows(NullPointerException.class,()->statDao.getByName("pikachu")); //possible change needed
	}
}
