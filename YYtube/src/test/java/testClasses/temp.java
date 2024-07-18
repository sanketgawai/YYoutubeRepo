package testClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class temp {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://www.youtube.com/");
		
		WebElement signIn = driver.findElement(By.xpath("(//tp-yt-app-drawer[@id='guide']/preceding::a)[6]"));
		signIn.click();
		
		WebElement email = driver.findElement(By.xpath("//input[@id='identifierId']"));
		email.sendKeys("kaido@gmail.com");
		
		WebElement createAccount = driver.findElement(By.xpath("//span[contains(text(),'Create ac')]"));
		System.out.println("createAccount : "+createAccount.isEnabled());
		createAccount.click();
	}
}
