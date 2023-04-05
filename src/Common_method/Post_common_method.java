package Common_method;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class Post_common_method {
	
	public static int responsestatuscode_extractor(String baseuri, String resource, String req_body) 
	{
	  RestAssured.baseURI = baseuri;
	  int response_statuscode = given().header("Content-Type", "application/json").body(req_body).when().post(resource)
			                    .then().extract().statusCode();
	  return response_statuscode;
	}
	
	public static String responsebody_extractor(String baseuri, String resource, String req_body) 
	{
	  RestAssured.baseURI = baseuri;
	  String response_body = given().header("Content-Type", "application/json").body(req_body).when().post(resource)
			                    .then().extract().response().asString();
	  return response_body;
	}
	
}
