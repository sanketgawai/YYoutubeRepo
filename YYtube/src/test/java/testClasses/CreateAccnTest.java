package testClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pomClasses.BirthDateSelect;
import pomClasses.CreateGoogleAccount;
import pomClasses.HomePage;
import qa.base.Bas;
import qa.utile.Utiliti;

public class CreateAccnTest extends Bas {

	WebDriver driver;
	HomePage homePage;
	CreateGoogleAccount createGoogleAccount;
	BirthDateSelect birthDateSelect;
//	@Parameters("browserName")
	@BeforeTest
	public void openBrowser()
	{
		getData();
		driver = initializeBrowser(prop.getProperty("browserName"));
	}
	
	@BeforeClass
	public void pomClassObject()
	{
		homePage = new HomePage(driver);
		createGoogleAccount = new CreateGoogleAccount(driver);
		birthDateSelect = new BirthDateSelect(driver);
	}
	@BeforeMethod
	public void openApplication()
	{
		driver.get(prop.getProperty("url"));
	}
	@Test(enabled=false)
	public void checkbuttonIsEnable()
	{
		
		homePage.clickOnSignIn();
		homePage.sendEmail(prop.getProperty("email"));
		Boolean actualButton = homePage.clickOnCreateAccountButtonIsEnable();
		Assert.assertTrue(actualButton);
	}
	@Test(dataProvider="validateCredential")
	public void sendNameAndCheckTitle(String name, String surname)
	{
		homePage.clickOnSignIn();
		homePage.clickOnCreateAccount();
		homePage.clickOnPresonalUse();
		//createGoogleAccount.sendFirstAndLastNameAndClickNext("luffy", "Emprorer");
		createGoogleAccount.sendFirstName(name);
		createGoogleAccount.sendLastName(surname);
	}
	
	@DataProvider(name="validateCredential")
	public Object[][] sendData()
	{
		//Object [] data = {"luffy","Emprorer"};
		//Object[][] data = {{"luffy","Emprorer"},{"zoro","dark king"}};
		Object[][] data = Utiliti.getTestDataFromExcel("ytube");
		return data;
	}
		
	
	@AfterMethod()
	public void afterMethod()
	{
		System.out.println("after method");
	}
	@AfterClass()
	public void afterClass()
	{
		homePage=null;
	}
	@AfterTest()
	public void quitBrowser()
	{
		System.gc();
		driver.quit();
	}
	
}
