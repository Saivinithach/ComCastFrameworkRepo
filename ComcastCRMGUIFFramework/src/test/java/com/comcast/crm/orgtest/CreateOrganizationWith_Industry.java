package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrganizationWith_Industry {

	public static void main(String[] args) throws InterruptedException, IOException 
	{
		//create object
		FileUtility fu=new FileUtility();
		ExcelUtility eu=new ExcelUtility();
		JavaUtility ju=new JavaUtility();
		
//		Reading data from property file
		String browser=fu.getDataFromPropertiesFile("Browser");
		String url=fu.getDataFromPropertiesFile("Url");
		String userName=fu.getDataFromPropertiesFile("UserName");
		String password=fu.getDataFromPropertiesFile("Password");
		
//		CREATE OBJECT TO RANDOM CLASS
		Random random=new Random();
//		SET UPPERLIMIT
		int randomInt=random.nextInt(1000);
		String orgName=eu.getDataFromExcel("Sheet1", 4, 2)+ju.getRandomNumber();
		String industry=eu.getDataFromExcel("Sheet1", 4, 3);
		String type=eu.getDataFromExcel("Sheet1", 4, 4);
		

		WebDriver driver=null;
		
		if(browser.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(browser.equals("firefox")) {
			driver=new FirefoxDriver();
		}else if(browser.equals("edge")) {
			driver=new EdgeDriver();
	 	}else {
	 		driver=new ChromeDriver();
	 	}
		
//		LOGIN TO APPLICATION
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
//		NAVIGATE TO ORGANIZATION MODULE
		driver.findElement(By.linkText("Organizations")).click();
		
//		CLICK ON CREATE ORGANIZATION BUTTON
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
//		ENTER ALL ORGANIZATION DETAILS AND CREATE NEW ORGANIZATION
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys("hyderabad");
		
		WebElement ind = driver.findElement(By.name("industry"));
		Select sel=new Select(ind);
		sel.selectByVisibleText(industry);
		
		WebElement ty = driver.findElement(By.name("accounttype"));
		Select sel1=new Select(ty);
		sel1.selectByVisibleText(type);		
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
		
//		VERIFY THE INDUSTRY AND TYPE INFO
		String actIndustry=driver.findElement(By.id("dtlview_Industry")).getText();
		if(actIndustry.contains(industry)) {
			System.out.println(industry +" is created === PASS");
		}else {
			System.out.println(industry + "is not created === FAIL");
		}
		String actType=driver.findElement(By.id("dtlview_Type")).getText();
		if(actType.contains(type)) {
			System.out.println(type +" is created === PASS");
		}else {
			System.out.println(type + "is not created === FAIL");
		}
		
//		LOGOUT
		driver.quit();
	}

}
