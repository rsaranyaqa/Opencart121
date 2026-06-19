package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegisterationTest extends BaseClass {

	@Test(groups = {"Regression", "Master"})
	public void verify_account_registeration() {

		logger.info("*****TC001_AccountRegisterationTest******");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyaccount();
			logger.info("Clicked on MyAccount...");
			hp.clickRegister();
			logger.info("Clicked on Register...");

			AccountRegisterationPage regPage = new AccountRegisterationPage(driver);

			/*
			 * regPage.setFirstName("Saranya"); regPage.setLastName("R");
			 * regPage.setEmail("saranya11@mailinator.com");
			 * regPage.setTelephone("9629750521"); regPage.setpasswprd("Saranya@123");
			 * regPage.setComfirmPasswprd("Saranya@123");
			 */
			// generating randomly
			logger.info("Providing customer details....");
			regPage.setFirstName(randomString().toUpperCase());
			regPage.setLastName(randomString().toUpperCase());
			regPage.setEmail(randomString() + "@mailinator.com");
			regPage.setTelephone(randomNumber());

			String password = randomAphaNumeric();
			regPage.setpasswprd(password);
			regPage.setComfirmPasswprd(password);

			regPage.agreePrivacyPolicy();
			regPage.clickContinueBtn();

			logger.info("Validating the expected message....");
			String confMsg = regPage.getConfirmationMsg();
			if (confMsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("Test Failed...");
				logger.debug("Debug logs....");
				Assert.assertTrue(false);
			}

		//	Assert.assertEquals(confMsg, "Your Account Has Been Created!!!");

		} catch (Exception e) {
			// logger.error("Test Failed...");
			// logger.debug("Debug logs....");
			Assert.fail();
		}

		logger.info("****Finished TC001_AccountRegisterationTest******");
	}

}
