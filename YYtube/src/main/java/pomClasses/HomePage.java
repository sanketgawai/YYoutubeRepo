package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

WebDriver driver;
	
@FindBy(xpath="(//tp-yt-app-drawer[@id='guide']/preceding::a)[6]")
private WebElement signIn;

@FindBy(xpath ="//input[@id='identifierId']")
private WebElement email;

@FindBy(xpath="//span[contains(text(),'Create ac')]")
private WebElement createAccount;

@FindBy(xpath="//span[text()='For my personal use']")
private WebElement presonalUse;




public HomePage(WebDriver driver)
{
	this.driver = driver;
	PageFactory.initElements(driver, this);
}

public void clickOnSignIn()
{
	signIn.click();
}

public void sendEmail(String ema)
{
	email.sendKeys(ema);
}

public void clickOnCreateAccount()
{
	createAccount.click();
}

public Boolean clickOnCreateAccountButtonIsEnable()
{
	Boolean btn = createAccount.isEnabled();
	return btn;
}

public void clickOnPresonalUse()
{
	presonalUse.click();
}

}
