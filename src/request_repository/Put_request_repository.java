package request_repository;

import java.io.IOException;
import java.util.ArrayList;

import Common_method.getData;

public class Put_request_repository {
	
	public static String baseuri()
	{
		String baseuri = "https://reqres.in";
		return baseuri;
	}
	
	public static String resource()
	{
		String resource = "/api/users/2";
		return resource;
	}
	
	public static String Put_request_tc1() throws IOException
	{
		ArrayList<String> data = getData.getDataExcel("Put_data","tc1");
		String Name = data.get(2);
		String Job = data.get(3);
		String requestBody = "{\r\n"
				+ "    \"name\": \""+Name+"\",\r\n"
				+ "    \"job\": \""+Job +"\"\r\n"
				+ "}";
		return requestBody;
		
	}
	/*public static String Put_request_tc2()
	{
		String requestBody = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";
		return requestBody;
	}*/

}