package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactWithSupportDate {

	public static void main(String[] args) throws IOException 
	{
//		/create object
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
		
		String lastName=eu.getDataFromExcel("Sheet3", 4, 2)+ju.getRandomNumber();
		
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
//LOGIN TO APPLICATION
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
//		NAVIGATE TO ORGANIZATION MODULE
		driver.findElement(By.linkText("Contacts")).click();
		
//		CLICK ON CREATE ORGANIZATION BUTTON
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
//		ENTER ALL ORGANIZATION DETAILS AND CREATE NEW ORGANIZATION
		
		String startDate=ju.getSystemDateYYYYDDMM();	
		String endDate=ju.getRequiredDateYYYYDDMM(30);
		
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		
		
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
	
		
//		VERIFY AND VALIDATE
		String actStartDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actStartDate.contains(startDate)) {
			System.out.println(startDate +" information is verified === PASS");
		}else {
			System.out.println(startDate + "information is not verified === FAIL");
		}
		
//		Verify Date
		String actEndDate=driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actEndDate.contains(endDate)) {
			System.out.println(endDate +" information is verified === PASS");
		}else {
			System.out.println(endDate + "information is not verified === FAIL");
		}
		
		
//		LOGOUT
		driver.quit();
	}

}
