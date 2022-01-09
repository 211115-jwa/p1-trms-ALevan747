package com.revature.data;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.*;
import com.revature.utils.DAOFactory;



public class EventTypeDAOTest {

private EventTypeDAO eDao = DAOFactory.getEventTypeDAO();
	

	@Test
	public void getByIdNotNull()
	{
		assertNotNull(eDao.getById(1));
	}
	
	@Test
	public void getByIdNull()
	{
		assertNull(eDao.getById(85));
	}
	
	@Test
	public void getAllNotNull()
	{
		assertNotNull(eDao.getAll());
	}
	
	@Test
	public void getByNameNotNull()
	{
		assertFalse(eDao.getByName("other").isEmpty());
	}
	
	@Test
	public void getByNameNull()
	{
		assertTrue(eDao.getByName("party").isEmpty());
	}
}
