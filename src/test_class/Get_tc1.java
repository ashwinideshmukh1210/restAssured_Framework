package test_class;

import java.io.IOException;
import org.testng.Assert;

import org.testng.annotations.Test;

import Common_method.Get_common_method;
import Common_method.common_method_utilities;
import io.restassured.path.json.JsonPath;
import request_repository.Get_request_repository;

public class Get_tc1 {
	@Test
	public static void orchestrator() throws IOException 
	{
		String responseBody = "" ;
		int responseStatuscode = 0;
		String baseuri = Get_request_repository.baseuri();
		String resource = Get_request_repository.resource();
		String requestBody = Get_request_repository.Get_request_tc1();
		for(int i=0 ; i<5 ; i++)
		{
			responseStatuscode = Get_common_method.responsestatuscode_extractor(baseuri, resource, requestBody);
			if(responseStatuscode == 200)
			{
		     responseBody = Get_common_method.responsebody_extractor(baseuri, resource, requestBody);
				            responseBodyValidator(responseBody);
				        
				        break;
			        }
			else
			{
				System.out.println("correct status code is not found in the iteration" + i);
			}
		}
		     common_method_utilities.evidencefilecreator("Get_tc1",requestBody,responseBody);
		     Assert.assertEquals(responseStatuscode, 200);
	}
	 
	
	public static void responseBodyValidator(String responseBody) 
	{
		//create json path jsp
				JsonPath jsp = new JsonPath (responseBody);
				
				//find length of array
				int count=jsp.getInt("data.size()");
				//System.out.println("length of array:" +count);
				
				//extract responsebody parameter
				int id[]= {7,8,9,10,11,12};
				String email[]= {"michael.lawson@reqres.in","lindsay.ferguson@reqres.in","tobias.funke@reqres.in","byron.fields@reqres.in","george.edwards@reqres.in","rachel.howell@reqres.in"};
				String first_name[]={"Michael","Lindsay","Tobias","Byron","George","Rachel"};
				String last_name[]= {"Lawson","Ferguson","Funke", "Fields","Edwards","Howell"};
				String avatar[]= {"https://reqres.in/img/faces/7-image.jpg","https://reqres.in/img/faces/8-image.jpg","https://reqres.in/img/faces/9-image.jpg","https://reqres.in/img/faces/10-image.jpg","https://reqres.in/img/faces/11-image.jpg", "https://reqres.in/img/faces/12-image.jpg"};
				
				for(int i = 0; i<count; i++) {
				
				//extract responsebody parameter
				int req_id=id[i];
				String req_email=email[i];
				String req_fname=first_name[i];
				String req_lname=last_name[i];
				String req_avatar=avatar[i];
				
				int res_id=jsp.getInt("data["+i+"].id");
				//System.out.println(res_id);
				String res_email=jsp.getString("data["+i+"].email");
				//System.out.println(res_email);
				String res_Fname=jsp.getString("data["+i+"].first_name");
				//System.out.println(res_Fname);
				String res_Lname=jsp.getString("data["+i+"].last_name");
				//System.out.println(res_Lname);
				String res_Avatar=jsp.getString("data["+i+"].avatar");
			     //System.out.println(res_Avatar);
				
				//validate responsebody
				Assert.assertEquals(res_id,req_id);
				Assert.assertEquals(res_email,req_email);
				Assert.assertEquals(res_Fname,req_fname);
				Assert.assertEquals(res_Lname, req_lname);
				Assert.assertEquals(res_Avatar, req_avatar);
				
		}
	}
}
			
			