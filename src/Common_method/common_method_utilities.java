package Common_method;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class common_method_utilities {

	public static void evidencefilecreator(String filename, String request, String response) throws IOException 
	{
		File newtextfile = new File("D:\\restassuredevidence\\" + filename + ".txt");
		if (newtextfile.createNewFile())
		{
			FileWriter dataWriter = new FileWriter(newtextfile);
			dataWriter.write("Requestbody is :\n" + request + "\n\n");
			dataWriter.write("Responsebody is :\n" + response);
			dataWriter.close();
			System.out.println("request and responsebody data saved in :" + newtextfile.getName());
		} 
		else
		{
			System.out.println(newtextfile.getName() + " Already exist take a backup of it ! ");

		}
	}
}
