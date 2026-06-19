package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage {

//	WebDriver driver;

	// Constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}

	// Locating elements

	@FindBy(how=How.XPATH, using = "//span[normalize-space()='My Account']") WebElement lnkMyaccount;
//	@FindBy(css = "div[class='nav float-end'] div[class='dropdown'] span[class='d-none d-md-inline']")
	//WebElement lnkMyaccount;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	
	@FindBy(xpath = "//a[normalize-space()='Login']") WebElement linklogin;
	

	// actions
	public void clickMyaccount() {
		lnkMyaccount.click();

	}

	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin()
	{
		linklogin.click();
	}

}
