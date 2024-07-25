package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests {
Faker faker;
User userPayload;
Logger logger;
@BeforeClass
public void setupData() {
	faker=new Faker();
	userPayload=new User();
	userPayload.setId(faker.idNumber().hashCode());
	userPayload.setUsername(faker.name().username());
	userPayload.setFirstName(faker.name().firstName());
	userPayload.setLastName(faker.name().lastName());
	userPayload.setEmail(faker.internet().safeEmailAddress());
	userPayload.setPassword(faker.internet().password(5,10));
	userPayload.setPhone(faker.phoneNumber().cellPhone());
	
	//generating logs
	logger=LogManager.getLogger(this.getClass());
}
@Test (priority=1)
public void testPostUser() {
	logger.info("********Creating user*************");
	Response response=UserEndpoints.createUser(userPayload);
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("******** user created*************");
	}
@Test (priority=2)
public void testGetUserByName() {
	logger.info("********getting user info*************");
	Response response=UserEndpoints.readUser(this.userPayload.getUsername());
	response.then().log().all();
	//response.statusCode(200);
	Assert.assertEquals(response.getStatusCode(),200);
	logger.info("******** user info retreived *************");
	
	}
@Test (priority=3)
public void testUpdateUserByName() {
	// update data using payload
	logger.info("********Updating user*************");

	userPayload.setFirstName(faker.name().firstName());
	userPayload.setLastName(faker.name().lastName());
	userPayload.setEmail(faker.internet().safeEmailAddress());
	
	Response response=UserEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
	response.then().log().body();
	Assert.assertEquals(response.getStatusCode(), 200);
	
	// checking data after update
	Response responseAfterUpdate=UserEndpoints.readUser(this.userPayload.getUsername());
	responseAfterUpdate.then().log().all();
	Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
	logger.info("******** user is updated*************");
	}

@Test(priority=4)
public void deleteUser() {
	logger.info("********Removing user*************");
	Response response=UserEndpoints.deleteUser(this.userPayload.getUsername());
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("******** user removed *************");
	
}
}
