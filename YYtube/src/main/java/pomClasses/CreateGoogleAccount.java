package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateGoogleAccount {

	WebDriver driver;
	
	@FindBy(id="firstName")
	private WebElement firstName;
	
	@FindBy(xpath="//input[@id='lastName']")
	private WebElement lastName;
	
	@FindBy(xpath="//div[@id='yDmH0d']/descendant::button/descendant::span")
	private WebElement next;
	
	public CreateGoogleAccount(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void sendFirstName(String f)
	{
		firstName.sendKeys(f);
	}
	
	public void sendLastName(String k)
	{
		lastName.sendKeys(k);
	}

	public void sendFirstAndLastNameAndClickNext(String frsName, String lasName)
	{
		firstName.sendKeys("kaido");
		lastName.sendKeys(lasName);
		next.click();
	}
	
	
}
