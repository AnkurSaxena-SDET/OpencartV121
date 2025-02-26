package testCases;


import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import PageObject.AccountRegistrationPage;
import PageObject.HomePage;
import TestBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups={"Regression","Master"})
	public void verify_acccount_registration()
	{
		
		logger.info("***** Starting TC_001_AccountRegistratationTest *******");
		
		try
		{
	
		HomePage hp =new HomePage(driver);
		
		hp.clickMyAccount();
		
		logger.info("Click on the My Account Link");
		
		hp.clickMyRegister();
		
		logger.info("Click on the Register Link");
		
		AccountRegistrationPage regpage =new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details....");
		
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+ "@gmail.com"); //randomly generated email.id
		regpage.setTelephone(randomNumber());
		
		String password =randomAlphaNumberic();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		
		regpage.setprivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating Expected Message...");
		
		String confmsg=regpage.getConfirmationMsg();
		
		if (confmsg.equals("Your Account Has Been Created!"))
		{
			AssertJUnit.assertTrue(true);
		}else {
			AssertJUnit.assertTrue(false);
			logger.error("Test Failed...");
			logger.debug("Debug logs..");
		}
	    //Assert.assertEquals(confmsg, "Your Account Has Been Created!");
	    
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
		logger.info("***** Finished TC_001_AccountRegistratationTest *******");
	}
	
	
}
