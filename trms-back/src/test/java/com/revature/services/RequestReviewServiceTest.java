package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
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
public class RequestReviewServiceTest {
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
	private RequestReviewService rrServ = new RequestReviewServiceImpl();
	private static Set<Reimbursement> mockRequests;
	private static Set<Employee> mockEmployees;
	private static Set<Comment> mockComments;
	private static List<Reimbursement> mockPendingRequests;

	@BeforeAll
	public static void mockSetup() {
		mockRequests = new HashSet<>();
		mockComments = new HashSet<>();
		mockEmployees = new HashSet<>();
		mockPendingRequests= new ArrayList<>();
		Department dep = new Department();
		dep.setDeptId(1);
		dep.setDeptHeadId(6);
		dep.setName("test");
		for (int i = 1; i < 11; i++) {
			Reimbursement reimb = new Reimbursement();
			reimb.setReqId(i);
			reimb.getRequestor().setEmpId(i);
			reimb.getStatus().setStatusId(i);
			if(i<5)
				reimb.getStatus().setName("denied");
			else
				reimb.getStatus().setName("approved");
			mockPendingRequests.add(reimb);
		}
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
	public void getPendingRequestByRequestorSuccess() {
		Employee req = new Employee();
		
		when(rrServ.getPendingReimbursements(req)).thenReturn(mockRequests);
		Set<Reimbursement> a = rrServ.getPendingReimbursements(req);
		
		assertTrue(mockRequests.size()>a.size());
	}
	
	@Test
	public void approveRequestSuccess() {
		Reimbursement mockReimb= new Reimbursement();
		mockReimb.getStatus().setName("denied");
		doNothing().when(reimDAO).update(mockReimb);
		rrServ.approveRequest(mockReimb);
		assertTrue(mockReimb.getStatus().getName().equals("approved"));
		 
	

		
	}
	
	@Test
	public void denyRequestSuccess() {
		Reimbursement mockReimb= new Reimbursement();
		mockReimb.getStatus().setName("approved");
		doNothing().when(reimDAO).update(mockReimb);
		rrServ.rejectRequest(mockReimb);
		assertTrue(mockReimb.getStatus().getName().equals("denied"));
		 
	}	
}
