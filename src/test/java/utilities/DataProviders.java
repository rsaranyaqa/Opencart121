package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	// DataProvider 1

	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {
		String path = ".\\testData\\Opencart_LoginData.xlsx"; // taking xl file from testData

		ExcelUtility xlutil = new ExcelUtility(path); // creating an object for XLUtility

	//	int totalrows = xlutil.getRowCount("Sheet1", path);
	//	int totalcols = xlutil.getCellCount("Sheet1", path, 1);
		
		int totalrows = ExcelUtility.getRowCount(path, "Sheet1");
		int totalcols = ExcelUtility.getCellCount( path, "Sheet1", 1);

		String logindata[][] = new String[totalrows][totalcols]; // created for two dimension array

		for (int i = 1; i <= totalrows; i++) // read the data from xl storing in two dimension array
		{
			for (int j = 0; j < totalcols; j++) // i is row j is col
			{
				logindata[i - 1][j] = ExcelUtility.getCellData( path, "Sheet1", i, j); // 1,0
			}
		}

		return logindata; // returning two dimension array
	}
	

	// DataProvider 2

	// DataProvider 3
	
	// DataProvider 4

}
