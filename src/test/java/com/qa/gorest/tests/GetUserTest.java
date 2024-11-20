package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

import io.qameta.allure.Description;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;


public class GetUserTest extends BaseTest{
	
	int firstId;
	String firstName;
	String email;
	String gender;
	String userStatus;
	
	
	@BeforeMethod
	public void getUserSetup() {
		
		restClient = new RestClient(prop,baseURI);
		
	}

	@Description("Verify the getAllUser api to fetch all user data")
	@Test(enabled=true)
	public void getAllUsersTest() {
		
		Response response = restClient.get(GOREST_ENDPOINT, true, true)
						.then().log().all()
						.assertThat()
						.statusCode(APIHttpStatus.OK_200.getCode())
						.and()
						.extract().response();
		
		JsonPath js = response.jsonPath();
					
		//get the id of the first product:
		firstId = js.getInt("[0].id");
		System.out.println("firstId = " + firstId);
				
		firstName = js.getString("[0].name");
		System.out.println("firstName = " + firstName );
				
		email = js.getString("[0].email");
		System.out.println("email = " + email);
				
		gender = js.getString("[0].gender");
		System.out.println("gender = " + gender);
					
		userStatus = js.getString("[0].status");
		System.out.println("userStatus = " + userStatus);
			
	}
	
	@Test
	public void getUserTest() {
		
		restClient.get(GOREST_ENDPOINT+"/"+firstId, true, true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode())
		.and()
		.body("id", equalTo(firstId));
			
	}
	
	@Test()
	public void getUserWithQueryParamsTest() {
		Map<String,Object> queryParams = new HashMap<String,Object>();
		queryParams.put("name", firstName);
		queryParams.put("status", userStatus);

		restClient.get(GOREST_ENDPOINT, queryParams, null,true, true)
					.then().log().all()
						.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
						
	}
}
