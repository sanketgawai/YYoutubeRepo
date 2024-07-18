package qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import qa.utile.ExtentReporter;
import qa.utile.Utiliti;

public class MyListeners implements ITestListener{

	ExtentReports extentReport;
	ExtentTest extentTest;
	WebDriver driver;
	public void onStart(ITestContext context) {
		
	extentReport = ExtentReporter.generateExtentReport();
	
	}
	public void onTestStart(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName()+"started execution");
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName()+"got successfull executed");
	}
	

	public void onTestFailure(ITestResult result) {
		
		WebDriver driver=null;
		try {//*****to get driver from that failer class//***** and make sure in testCass dec public WebDriver driver; to get driver here 
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String destinationScreenshotPath = Utiliti.captureScreendhot(driver,result.getName());
		
		//to attach screenshot to report
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);//addScreenCaptureFromPath(String path)
		extentTest.log(Status.INFO, result.getThrowable());//.log(Status status Markup markup)
		extentTest.log(Status.FAIL, result.getName()+" got failed");
	}

	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+"got skipped");
	}

	
	public void onFinish(ITestContext context) {
		extentReport.flush();//***** if don't flush all details not added to report
		
		//***** below code for auto logic for extent report it will display report after test over 
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);//path of extent report 
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
