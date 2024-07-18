package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class BirthDateSelect {

WebDriver driver;	
	
@FindBy(xpath="//input[@id='day']")
private WebElement day;

@FindBy(xpath="//select[@id='month']")
private WebElement month;

@FindBy(id="year")
private WebElement year;

@FindBy(id="gender")
private WebElement gender;

public BirthDateSelect(WebDriver driver)
{
	this.driver = driver;
}

public void sendDay()
{
	day.sendKeys("12");
}

public void selectMonth()
{
	Select s = new Select(month);
	s.selectByVisibleText("December");
}

public void sendYear()
{
	year.sendKeys("2000");
}

public void selectGender()
{
	Select s = new Select(gender);
	s.selectByVisibleText("Male");
}

}
