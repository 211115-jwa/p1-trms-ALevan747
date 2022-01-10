package com.revature.app;

import io.javalin.Javalin;
import io.javalin.plugin.json.JsonMapper;

import static io.javalin.apibuilder.ApiBuilder.*;
import java.io.IOException;
import com.revature.controllers.RequestsController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TRMSApp {

	public static void main(String[] args) {
		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
			config.jsonMapper(new JacksonMapper());
		}).start();
		
		app.routes(() -> {
			path("/requests", () -> {
				post(RequestsController::submitReimbursementRequest);
				path("/requestor/deny/{id}", () -> {
					put(RequestsController::deny);
				});
				path("/requestor/approve/{id}", () -> {
					put(RequestsController::approve);
				});
				path("/requestor/{id}", () -> {
					get(RequestsController::getRequestsByRequestor);
				});
			});
			path("/requests/manage/{id}", ()->{
				get(RequestsController::getPendingRequestsByRequestor);
			});
			path("/emp/{id}", ()->{
				get(RequestsController::getEmployeeById);
			});
		});
	}

}

class JacksonMapper implements JsonMapper {
    ObjectMapper om = new ObjectMapper();

    {
        om.findAndRegisterModules();
    }

    @Override
    public String toJsonString(Object obj) {
        try {
            return om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public <T> T fromJsonString(String json, Class<T> targetClass) {
        try {
            return om.readValue(json, targetClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
}

/*	
Build a front end to complete the MVP that is provided, allowing users to submit and view requests.
From here, choose at least three features that you want to add to the application based on the
specifications provided. Use BDD with Cucumber to plan the expected behavior of features, 
then implement each feature using Agile methodology. Test each feature that you've added 
with Selenium using your Cucumber feature files.
	
	*/
