package com.revature.services;


import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.ReimbursementDAO;
import com.revature.data.postgres.ReimbursementPostgres;

public class RequestReviewServiceImpl implements RequestReviewService{
	private ReimbursementDAO reimbDao= new ReimbursementPostgres();
	@Override
	public Set<Reimbursement> getPendingReimbursements(Employee approver) {
		Set<Reimbursement> all = reimbDao.getByRequestor(approver);
		Set<Reimbursement> pending= new HashSet<Reimbursement>();
		for(Reimbursement r: all) {
			if(r.getStatus().getName().contains("pending")) {
				pending.add(r);
			}	
		}
		
		return pending;
	}
/**
 * Approving or rejecting requests requires us to create a Status object. 
 * so we can update the status within the reimbursement setstatus method.
 * */
	@Override
	public void approveRequest(Reimbursement request) {
		Status stat =request.getStatus();
		if(!stat.getName().equals("approved"))
		stat.setName("approved");
		request.setStatus(stat);
		reimbDao.update(request);
	}

	@Override
	public void rejectRequest(Reimbursement request) {
		Status stat =request.getStatus();
		if(!stat.getName().equals("denied"))
		stat.setName("denied");
		request.setStatus(stat);
		reimbDao.update(request);
		
	}
	@Override
	public Reimbursement getById(int Id) {
	return reimbDao.getById(Id);
		
	}
	
	@Override
	public void rejectRequest(Reimbursement request, Comment comment) {
		// TODO Auto-generated method stub
		
	}

}

