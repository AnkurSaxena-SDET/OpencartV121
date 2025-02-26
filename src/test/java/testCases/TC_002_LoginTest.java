package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.LoginPage;
import PageObject.HomePage;
import PageObject.MyAccountPage;
import TestBase.BaseClass;

public class TC_002_LoginTest extends BaseClass{
	
	
	@Test(groups={"Sanity","Master"})
	
	public void verify_login()
	{
		logger.info("******Starting TC_002_LoginTest *****");
	
		try
		{
			HomePage hp =new HomePage(driver);
			
			hp.clickMyAccount(); // home page
			
		logger.info("Click on the My Account Link ");
		
		hp.clickMyLogin(); //click on login page link
		
		logger.info("Click on the Login Link ");
		LoginPage lp= new LoginPage(driver);
		
		logger.info("providing Login Details....");
		
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//My Account Page
		MyAccountPage macc =new MyAccountPage(driver);
		
		boolean targetPage=macc.isMyAccountPageExists();
		
		//sol1
		//Assert.assertEquals(targetPage, true, "Login failed");
		
		//sol2
		
		Assert.assertTrue(targetPage);
		
		}
		catch(Exception e)
		{
		  Assert.fail(); 	
		}
		logger.info("******Finished TC_002_LoginTest *****");
		
	
	}

}
