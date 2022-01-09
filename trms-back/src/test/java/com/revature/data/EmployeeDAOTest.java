package com.revature.data;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.jupiter.api.*;

import com.revature.beans.Employee;
import com.revature.utils.DAOFactory;

import io.cucumber.java.BeforeAll;

public class EmployeeDAOTest {
	private EmployeeDAO empDao = DAOFactory.getEmployeeDAO();
	private DepartmentDAO dDao=DAOFactory.getDepartmentDAO();
	
	@Test
	public void createTest() {
		Employee n = new Employee();
		n.setDepartment(dDao.getById(1));
		n.setSupervisor(empDao.getById(4));
		assertNotEquals(0, empDao.create(n));
	}
	
	
	@BeforeAll
	public void getByIdNotNull()
	{
		assertNotNull(empDao.getById(1));
	}
	
	
	@Test
	public void getByIdNull()
	{
		assertNull(empDao.getById(546512));
	}
	
	@Test
	public void getAllNotNull()
	{
		assertFalse(empDao.getAll().isEmpty());
	}
	
	public void getByUsernameNotNull()
	{
		assertNotNull(empDao.getByUsername("ricky23i"));
	}
	
	
	@Test
	public void getByUsernameNull()
	{
		assertNull(empDao.getByUsername("jdskfsjdklfsd"));
	}
	

}