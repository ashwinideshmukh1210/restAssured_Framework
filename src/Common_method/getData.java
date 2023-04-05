package Common_method;

import java.io.FileInputStream;
import java.lang.Exception;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class getData {
	public static ArrayList<String> getDataExcel(String testSheetName,String testCaseName) throws IOException
	{
		ArrayList<String> arrayData = new ArrayList<String>();
		//step 1 locate excel data file, by creating the object of fileinputstream
		FileInputStream fis = new FileInputStream("D:\\test_data.xlsx");
		
		//step 2 create the object of XSSFWorkbook to open the excel file
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		//step 3 access the desired sheet 
	   //step 3.1 fetch the count of sheets available in the excel file 
		int countOfSheet = workbook.getNumberOfSheets();
		
		//step 3.2 fetch the name of sheet and compare against desired sheetname
		for(int i=0;i<countOfSheet;i++)
		{
			String sheetname = workbook.getSheetName(i);
			if (sheetname.equalsIgnoreCase(testSheetName))
			{
			  //step 4 access the sheet and iterate through rows to fetch the column in which testcasename column is found
				XSSFSheet sheet = workbook.getSheetAt(i);
			  // step 4.1 create itrator for rows
				Iterator<Row> Rows = sheet.iterator();
				Row firstRow = Rows.next();
			  // step 4.2 create iterator for cells
				Iterator<Cell> Cells = firstRow.cellIterator();
				int j=0;
				int tc_column = 0;
				
				//step 4.3 read the cell values of row no.1 to comppare against the testcase name
				while(Cells.hasNext())
				
				{
					Cell cellvalue = Cells.next();
					if (cellvalue.getStringCellValue().equalsIgnoreCase("tc_name"))
					{
						tc_column = j;
						//System.out.println(tc_column);
					}
					j++;
				}
				 // step 5 fetch the data for designated testcase
				   while(Rows.hasNext())
				   {
					   Row dataRow = Rows.next();
					   if(dataRow.getCell(tc_column).getStringCellValue().equalsIgnoreCase(testCaseName))
					   {
						  Iterator<Cell> dataCellvalue = dataRow.cellIterator();
						  while(dataCellvalue.hasNext())
						     {
							  Cell dataOfCell = dataCellvalue.next();
							//Method 1 -- to extract cell value by using try and catch
							  try
							  {
							  String testdata = dataOfCell.getStringCellValue();
							  arrayData.add(testdata);
							  }
							  catch (IllegalStateException e)
							  {
								int inttestdata = (int) dataOfCell.getNumericCellValue();
								String stringtestdata = Integer.toString(inttestdata);
								arrayData.add(stringtestdata);
							  }
	/*				       	//Method 2-- To Extract the cell value by using if and else
							    CellType datatype =  dataOfCell.getCellType();
							    if (datatype.toString() == "NUMERIC")
							    {
							    	int inttestdata = (int) dataOfCell.getNumericCellValue();
							    	String stringtestdata = Integer.toString(inttestdata);
							    	arrayData.add(stringtestdata);
							    }
								  
							    else if (datatype.toString() == "STRING")
							    {
							        String testdata = dataOfCell.getStringCellValue();
									arrayData.add(testdata);
							    }
							    
							  //Method 3 -- Extract the data by converting it into String
							    String testData = dataOfCell.toString().replaceAll("\\.\\d+$", "");
							    arrayData.add(testData);
							    System.out.println(testData);
							    
							  //Method 4-- Extract the data by using Dataformatter
							    DataFormatter format = new DataFormatter();
							    String testdata = format.formatCellValue(dataOfCell);
							    arrayData.add(testdata);
							    System.out.println(testdata);                                             */
							  }
						   }
					    }
				    }
			    }
		    return arrayData;
	       }
	    }

