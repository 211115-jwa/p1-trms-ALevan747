package com.revature.services;

import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.revature.beans.Comment;
import com.revature.beans.Department;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.data.CommentDAO;
import com.revature.data.DepartmentDAO;
import com.revature.data.EmployeeDAO;
import com.revature.data.EventTypeDAO;
import com.revature.data.GradingFormatDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.data.StatusDAO;
import com.revature.utils.DAOFactory;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
	@Mock
	private CommentDAO cDAO = DAOFactory.getCommentDAO();

	@Mock
	private DepartmentDAO dDao = DAOFactory.getDepartmentDAO();

	@Mock
	private EmployeeDAO empDAO = DAOFactory.getEmployeeDAO();

	@Mock
	private EventTypeDAO eventDAO = DAOFactory.getEventTypeDAO();

	@Mock
	private GradingFormatDAO gfDAO = DAOFactory.getGradingFormatDAO();

	@Mock
	private ReimbursementDAO reimDAO = DAOFactory.getReimbursementDAO();

	@Mock
	private StatusDAO statDAO = DAOFactory.getStatusDAO();

	@InjectMocks
	private EmployeeService empServ = new EmployeeServiceImpl();
	private static Set<Reimbursement> mockRequests;
	private static Set<Employee> mockEmployees;
	private static Set<Comment> mockComments;


	
	@BeforeAll
	public static void mockSetup() {
		mockRequests = new HashSet<>();
		mockComments = new HashSet<>();
		mockEmployees = new HashSet<>();
		Department dep = new Department();
		dep.setDeptId(1);
		dep.setDeptHeadId(6);
		dep.setName("test");
		for (int i = 1; i < 11; i++) {
			Reimbursement reimb = new Reimbursement();
			reimb.setReqId(i);
			reimb.getRequestor().setEmpId(i);
			reimb.getStatus().setStatusId(i);
			mockRequests.add(reimb);
		}
		
		for (int i = 1; i < 30; i++) {
			Comment c = new Comment();
			c.setCommentId(i);
			for (int j = 1; i < 11; i++) {
				c.setRequest(new Reimbursement());
				c.getRequest().setReqId(j);
				c.setApprover(new Employee());
				if (i < 3)
					c.getApprover().setEmpId(1);
				else if (i < 5)
					c.getApprover().setEmpId(6);
				else
					c.getApprover().setEmpId(10);
			}
			mockComments.add(c);
		}
		
		for (int i = 1; i < 30; i++) {
			Employee emp = new Employee();
			emp.setEmpId(i);
			emp.setFirstName("Mock" + i);
			emp.setLastName("Test" + i);
			emp.setUsername("mocktest" + i);
			emp.setPassword("123456");
			emp.setFunds(1000.00);
			emp.setDepartment(dep);
			emp.getSupervisor().setEmpId(10);
			if (i == 6)
				emp.getRole().setRoleId(3);
			else if (i == 10)
				emp.getRole().setRoleId(2);
			else
				emp.getRole().setRoleId(1);
			mockEmployees.add(emp);
		}
		
	}
	
	@Test
	public void getRequestOptionsSuccess() {
	}
	
	@Test
	public void getReimbursementRequestSuccess() {
		Employee req = new Employee();
		
		when(reimDAO.getByRequestor(req)).thenReturn(mockRequests);
		Set<Reimbursement> a = empServ.getReimbursementRequests(req);
		
		assertEquals(mockRequests, a);
	}
	
	@Test
	public void submitReimbursementRequestSuccess() {
		Reimbursement req = new Reimbursement();
		//req.setReqId(123);
		//System.out.println(mockRequests.size()+"before");
		mockRequests.add(req);
		when(reimDAO.create(req)).thenReturn(mockRequests.size());
		int r= reimDAO.create(req);
		//System.out.println(mockRequests.size());
		//System.out.println("size: "+r);
		assertEquals(r,mockRequests.size());
	}
}