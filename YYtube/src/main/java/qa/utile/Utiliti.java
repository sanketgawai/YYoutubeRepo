package qa.utile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utiliti {

	public static Object[][] getTestDataFromExcel(String sheetName)
	{
		XSSFWorkbook workbook=null;
		File excelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\testdata\\youtube.xlsx");
		try {
		FileInputStream fis = new FileInputStream(excelFile);
		workbook = new XSSFWorkbook(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][columns];
		
		for(int i=0;i<rows;i++)
		{
			XSSFRow row  = sheet.getRow(i+1);
			for(int j=0;j<columns;j++)
			{
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				switch(cellType) {
				
				case STRING:
					data[i][j]= cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}
			}
		}
		return data;
	}
	
	public static String captureScreendhot(WebDriver driver,String testName)
	{//***** and make sure in testCass dec public WebDriver driver; to get driver here
			File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			//String destinationScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + result.getName() + ".png";
			String destinationScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + testName+ ".png";
			try {
				FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return destinationScreenshotPath;
	}
}
