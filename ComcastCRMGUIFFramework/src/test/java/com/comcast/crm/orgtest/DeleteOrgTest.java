package com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest 
{
	public static void main(String[] args) throws IOException 
	{
		//create object
		FileUtility fu=new FileUtility();
		ExcelUtility eu=new ExcelUtility();
		JavaUtility ju=new JavaUtility();
		WebDriverUtility wdu=new WebDriverUtility();
		
//		Reading data from property file
		String browser=fu.getDataFromPropertiesFile("Browser");
		String url=fu.getDataFromPropertiesFile("Url");
		String userName=fu.getDataFromPropertiesFile("UserName");
		String password=fu.getDataFromPropertiesFile("Password");
		
//		CREATE OBJECT TO RANDOM CLASS
		
		
		String orgName=eu.getDataFromExcel("Sheet1", 10, 2)+ju.getRandomNumber();
		
		WebDriver driver=null;
		if(browser.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(browser.equals("firefox")) {
			driver=new FirefoxDriver();
		}else if(browser.equals("edge")){
			driver=new EdgeDriver();
		}else {
			driver=new ChromeDriver();
		}
		//1 LOGIN TO APPLICATION
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
		
		LoginPage lp=new LoginPage(driver);
		
		lp.loginToApp(url,userName, password);
		
		//2		NAVIGATE TO ORGANIZATION MODULE
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		
		//3		CLICK ON CREATE ORGANIZATION BUTTON
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		
//		GO BACK TO ORGANIZATION PAGE
		
//		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		
//		SEARCH FOR ORGANIZATION
		op.getSearchEdt().sendKeys(orgName);
		wdu.select(op.getSearchDD(), "Organization Name");
		op.getSearchBtn().click();
		
		
//		IN DYNAMIC WEBTABLE SELECT AND DELETE ORGANIZATION
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
		wdu.switchToAlertAndAccept(driver);
	
//		LOGOUT
		hp.logout();
		
		driver.quit();
	}
}
