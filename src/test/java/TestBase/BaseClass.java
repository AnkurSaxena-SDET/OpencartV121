package TestBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.apache.logging.log4j.LogManager;  //log4j first import
import org.apache.logging.log4j.Logger;      //log4jsecond import

public class BaseClass {


	public WebDriver driver;
	public Logger logger;  //log4j variable declare
	public Properties p;
	

	@BeforeClass(groups = {"Sanity", "Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException
	{
		//loading properties Files
		FileReader file =new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		logger =LogManager.getLogger(this.getClass()); //log4j method 
		
		switch(br.toLowerCase())
		{
		case "chrome" : driver=new ChromeDriver();break;
		case "edge"  :  driver=new EdgeDriver();break;
		case "firefox": driver=new FirefoxDriver();break;
		default : System.out.println("Invalid browser name ..");return;
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL2")); //reading url from properties file. 
		driver.manage().window().maximize();
	}

	@AfterClass(groups = {"Sanity", "Regression","Master"})
	public void tearDown() {
		driver.close();
	}

	public String randomString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNumber()
	{
		String generatedNumber =RandomStringUtils.randomNumeric(10);

		return generatedNumber;
	}

	public String randomAlphaNumberic()
	{
		String generatedString =RandomStringUtils.randomAlphabetic(3);
		String generatedNumber =RandomStringUtils.randomNumeric(3);
		return (generatedString+"@"+generatedNumber);
	}


}
