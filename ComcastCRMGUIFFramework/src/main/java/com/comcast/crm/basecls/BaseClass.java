package com.comcast.crm.basecls;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.listeners.ListenersPractice;

public class BaseClass
{
	public FileUtility fu=new FileUtility();
	public ExcelUtility eu=new ExcelUtility();
	public JavaUtility ju=new JavaUtility();
	public WebDriverUtility wdu=new WebDriverUtility();
	public WebDriver driver=null;
	public static WebDriver swDriver=null;
	
	
	@BeforeSuite  //(groups= {"smokeTest","regressionTest"})
	public void configBS()
	{
		System.out.println("====connect to DB,Report config====");
	}
	
	@BeforeClass(groups= {"smokeTest","regressionTest"})
	public void configBC() throws IOException
	{
		System.out.println("====Launch the BROWSER====");
		String browser=fu.getDataFromPropertiesFile("Browser");
		
		if(browser.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(browser.equals("firefox")) {
			driver=new FirefoxDriver();
		}else if(browser.equals("edge")){
			driver=new EdgeDriver();
		}else {
			driver=new ChromeDriver();
		}
		swDriver=driver;
	}
	
	@BeforeMethod  //(groups= {"smokeTest","regressionTest"})
	public void configBM() throws IOException
	{
		System.out.println("====login====");
		LoginPage lp=new LoginPage(driver);
		String url=fu.getDataFromPropertiesFile("Url");
		String userName=fu.getDataFromPropertiesFile("UserName");
		String password=fu.getDataFromPropertiesFile("Password");
		lp.loginToApp(url,userName , password);
	}
	
	@AfterMethod  //(groups= {"smokeTest","regressionTest"})
	public void configAm()
	{
		System.out.println("====logout====");
		HomePage hp=new HomePage(driver);
		hp.logout();
	}
	
	@AfterClass  //(groups= {"smokeTest","regressionTest"})
	public void configAC()
	{
		System.out.println("====Close the BROWSER====");
		driver.quit();
	}
	
	@AfterSuite  //(groups= {"smokeTest","regressionTest"})
	public void configAS()
	{
		System.out.println("====Close to DB,Report backup====");
	}
}
