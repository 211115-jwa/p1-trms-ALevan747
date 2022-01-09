package com.revature.data;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import org.junit.jupiter.api.*;
import com.revature.beans.EventType;
import com.revature.beans.GradingFormat;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.utils.DAOFactory;

public class ReimbursementDAOTest {

	private ReimbursementDAO redao = DAOFactory.getReimbursementDAO();
	private EmployeeDAO empdao = DAOFactory.getEmployeeDAO();
	private StatusDAO statdao = DAOFactory.getStatusDAO();
	
	@Test
	public void getByIdNotNull() {
		Reimbursement a = redao.getById(1);
		assertNotNull(a);
	} 
	
	@Test
	public void getByIdNull() {
		Reimbursement a = redao.getById(21231212);
		assertNull(a);
	} 
	
	@Test
	public void createTest() {
		Reimbursement t = new Reimbursement();
		t.getRequestor().setEmpId(4);
		t.setEventType(new EventType());
		t.setGradingFormat(new GradingFormat());
		t.setStatus(new Status());
		DateTimeFormatter dformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		t.setEventDate(LocalDate.parse("2020-12-20",dformat));
		DateTimeFormatter tformat = DateTimeFormatter.ofPattern("HH:mm:ss");
		t.setEventTime(LocalTime.parse("12:11:55",tformat));
		assertNotEquals(0,redao.create(t));
	}
	
	@Test
	public void getAll() {
		Set<Reimbursement> all =redao.getAll();
		assertNotNull(all);
	}
	
	@Test
	public void updateTest()
	{
		Reimbursement r = redao.getById(4);
		r.setCost(100.0);
		//DateTimeFormatter dformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		//r.setSubmittedAt(LocalDateTime.parse("2020-12-20 12:45:12",dformat));
	//	r.setReqId(1);
		redao.update(r);
	//	System.out.println(r.getCost());
		assertEquals(r.getCost(),redao.getById(4).getCost());
	}
	
	@Test
	public void deleteTest()
	{
		Reimbursement t = new Reimbursement();
		t.getRequestor().setEmpId(1);
		t.setEventType(new EventType());
		t.setGradingFormat(new GradingFormat());
		t.setStatus(new Status());
		DateTimeFormatter dformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		t.setEventDate(LocalDate.parse("2020-12-20",dformat));
		DateTimeFormatter tformat = DateTimeFormatter.ofPattern("HH:mm:ss");
		t.setEventTime(LocalTime.parse("12:11:55",tformat));
		int id =redao.create(t);
		redao.delete(redao.getById(id));
		assertNull(redao.getById(id));
	}
	
	@Test
	public void getByRequestorNotNull() {
		
		//Set<Reimbursement> r = redao.getByRequestor(empdao.getById(4));
		assertNotNull(redao.getByRequestor(empdao.getById(4)));
		
	}
	
	@Test
	public void getByRequestorNull() {
		assertThrows(NullPointerException.class,()->redao.getByRequestor(empdao.getById(545645645)));
		
	}
	
	@Test
	public void getByStatusNotNull() {
		assertNotNull(redao.getByStatus(statdao.getById(1)));
	}
	
	@Test
	public void getByStatusNull() {
		assertTrue(redao.getByStatus(statdao.getById(9)).isEmpty());
	}
}
