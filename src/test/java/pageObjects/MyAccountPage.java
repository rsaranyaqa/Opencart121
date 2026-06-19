package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	// my account heading 
	@FindBy(xpath = "//h2[text()='My Account']") WebElement msgHeading;
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']") WebElement lnklogout;	// added in step number 6
	
	public boolean isMyAccountPageExist()
	{
		try
		{
			return (msgHeading.isDisplayed());
		}
		catch(Exception e)
		{
			return(false);
		}
	}
	
	public void clickLogout()
	{
	//	lnklogout.click();
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].click();", lnklogout);
	}
}
