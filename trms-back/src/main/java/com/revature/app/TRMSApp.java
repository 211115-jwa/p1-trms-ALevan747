package com.revature.app;

import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.revature.controllers.RequestsController;

public class TRMSApp {

	public static void main(String[] args) {
		Javalin app = Javalin.create().start();
		
		app.routes(() -> {
			path("/requests", () -> {
				post(RequestsController::submitReimbursementRequest);
				path("/requestor/{id}", () -> {
					get(RequestsController::getRequestsByRequestor);
				});
			});
		});
	}

}

/*	
Build a front end to complete the MVP that is provided, allowing users to submit and view requests.
From here, choose at least three features that you want to add to the application based on the
specifications provided. Use BDD with Cucumber to plan the expected behavior of features, 
then implement each feature using Agile methodology. Test each feature that you've added 
with Selenium using your Cucumber feature files.
	
	*/
