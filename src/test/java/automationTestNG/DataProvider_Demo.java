package automationTestNG;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataProvider_Demo {
	public static WebDriver driver;
	
	@BeforeMethod
	public void launchBrowser() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://qa-tekarch.firebaseapp.com/");
	
		driver.manage().window().maximize();
		System.out.println("before method");
		
		Thread.sleep(5000);
	}
	
//	@Test(dataProvider="pCredentials")
//	public void login(String uName,String pwd){
//		System.out.println(uName+"   "+pwd);
//		
//	}
//
//	@DataProvider(name="pCredentials")
//	public Object [][] provide_credentials() {
//		return new Object[][] {{"admin123@gmail.com","admin123"},{"admin@gmail.com","admin"}};
//		
//	}
	@AfterMethod
	public void quitBrowser() throws Exception {
		Thread.sleep(3000);
		driver.close();
	}
	@Test(dataProvider="excelDataInfo")
	public void login(String uName,String pwd,String mail){
		System.out.println(uName+"   "+pwd+"  "+mail);
		
	}

	@DataProvider(name="excelDataInfo")
	public Object [][] readExcelData() throws Exception{
		String sFile=System.getProperty("user.dir")+"/Book1.xlsx";
		return readDatafromExcel(sFile);
	}

	private Object[][] readDatafromExcel(String sFile) throws Exception {
		
			FileInputStream fi=new FileInputStream(sFile);
			XSSFWorkbook mybook=new XSSFWorkbook(fi);
			XSSFSheet sheet=mybook.getSheet("mysheet2");
			XSSFRow row=sheet.getRow(1);
			String s=row.getCell(0).getStringCellValue();
			System.out.println(s);
			XSSFRow row1 = sheet.getRow(0);
			System.out.println(row1.getPhysicalNumberOfCells()); //To take total number of collumns
			System.out.println(sheet.getPhysicalNumberOfRows());//To take total number of Rows
			//System.out.println(row1.getLastCellNum());
			int iCountCol =row1.getLastCellNum();
			int iCountRow = sheet.getPhysicalNumberOfRows();
			Object[][] excelData= new Object[iCountRow][iCountCol]; //Creating multi dimensional array
			
			for(int countRow=0;countRow<iCountRow;countRow++) {
				for(int countCol = 0; countCol<iCountCol;countCol++) {
					XSSFRow tempRow=sheet.getRow(countRow);
					String sTemp;
					try {
					//System.out.println(tempRow.getCell(countCol).getStringCellValue());
					sTemp=tempRow.getCell(countCol).getStringCellValue();
					}catch(Exception e) {
						//System.out.println(tempRow.getCell(countCol).getNumericCellValue());
						sTemp=Double.toString(tempRow.getCell(countCol).getNumericCellValue());
					}
					excelData[countRow][countCol] = sTemp;
				}
			}	
			
			
		return excelData;
	
		}
	@DataProvider(name="asposetrainerInfo")
	Object[][] trainerInfoFromExcel_aspose()throws Exception{
		String sFile = System.getProperty("user.dir")+"/Book1.xlsx";
		 return readDataFromExcelSheet_aspose(sFile);
	}
	//open source free tools can be used anywhere. 
	private Object[][] readDataFromExcelSheet_aspose(String sFile) throws Exception {
		FileInputStream fstream = new FileInputStream(sFile);
		
		//Instantiating a Workbook object
		Workbook workbook = new Workbook(fstream);
		
		//Accessing the first worksheet in the Excel file
		Worksheet worksheet = workbook.getWorksheets().get(0);
		
		//Exporting the contents of 7 rows and 2 columns starting from 1st cell to Array.
		Object[][] dataTable = worksheet.getCells().exportArray(0,0,2,3);
		/*for (int i = 0 ; i < dataTable.length ; i++)
		{
			System.out.println("["+ i +"]: "+ Arrays.toString(dataTable[i]));
		}*/
		//Closing the file stream to free all resources
		fstream.close();
return dataTable;
}
	
	@Test(dataProvider="asposetrainerInfo")
	public void validatingTrainerInfo(String name, String exp, String email) {
		System.out.println(name+"\t"+exp+"\t"+email);
	}
	}
	
