package com.revature.data;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.*;
import com.revature.beans.GradingFormat;
import com.revature.utils.DAOFactory;

public class GradingFormatDAOTest {
	
private GradingFormatDAO gfDao = DAOFactory.getGradingFormatDAO();
	
	@Test
	public void getByIdNotNull()
	{
		GradingFormat a = gfDao.getById(1);
		assertNotNull(a);
	}
	
	@Test
	public void getByIdNull()
	{
		GradingFormat a = gfDao.getById(54545);
		assertNull(a);
	}
	
	@Test
	public void getAllNotNull()
	{
		assertNotNull(gfDao.getAll());
	}
	
	@Test
	public void getByNameNotNull()
	{
		assertNotNull(gfDao.getByName("pass/fail"));
	}
	
	@Test
	public void getByNameNull()
	{
		assertTrue(gfDao.getByName("pdsadsaasasdsad").isEmpty());
	}
}