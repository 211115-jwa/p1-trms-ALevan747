package com.revature.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.Employee;
import com.revature.data.EmployeeDAO;
import com.revature.data.postgres.EmployeePostgres;

import io.javalin.http.Context;

public class EmployeesController {
	private static EmployeeDAO empDAO = new EmployeePostgres();
	private static Employee emp = new Employee();
	
	private static Logger log = LogManager.getLogger(EmployeesController.class);
	
	
	public static void viewEmployeeById(Context ctx) {
		
		int id = Integer.parseInt(ctx.pathParam("empId"));
		log.info("getting employee by id: "+id);
		try {
			
		emp = empDAO.getById(id);
		if (id != 0) {
			ctx.json(emp);
		} else {
			ctx.status(404);
			ctx.result("The employee does not exist.");
		}
	} catch (NumberFormatException e) {
		ctx.status(400);
		ctx.result("Employee ID must be an integer. Please try again.");
	}
		
		}
}
