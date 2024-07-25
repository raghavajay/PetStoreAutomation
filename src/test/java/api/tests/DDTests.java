package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	@Test(priority=1, dataProvider="Data",dataProviderClass=DataProviders.class)
public void testPostUser(String userId,String userName,String fName,String lName,String useremail,String password, String phone) {
	User userPayload= new User();
	userPayload.setId(Integer.parseInt(userId));
	userPayload.setUsername(userName);
	userPayload.setFirstName(fName);
	userPayload.setLastName(lName);
	userPayload.setEmail(useremail);
	userPayload.setPassword(password);
	userPayload.setPhone(phone);
	
	Response response=UserEndpoints.createUser(userPayload);
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);
}
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String username) {
		Response response=UserEndpoints.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
}
