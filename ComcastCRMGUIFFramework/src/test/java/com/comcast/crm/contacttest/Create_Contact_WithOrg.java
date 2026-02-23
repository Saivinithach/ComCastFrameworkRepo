package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.swing.plaf.basic.BasicArrowButton;

import org.apache.poi.ss.usermodel.Cell;
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
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class Create_Contact_WithOrg {

	public static void main(String[] args) throws IOException, InterruptedException {
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
		String contactLastName=eu.getDataFromExcel("Sheet3", 7, 3 )+ju.getRandomNumber();
		String orgName=eu.getDataFromExcel("Sheet3", 7,3)+ju.getRandomNumber();
		
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
		wdu.waitForPageToLoad(driver);
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
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
		
//		VERIFY THAT HEADER MSG EXCEPTED RESULT
		String headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerinfo.contains(orgName)) {
			System.out.println(orgName +" is created === PASS");
		}else {
			System.out.println(orgName + "is not created === FAIL");
		}
		
//		VERIFY HEADER ORGNAME INFO EXCEPTED RESULT
		String actOrgname=driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actOrgname.contains(orgName)) {
			System.out.println(orgName +" is created === PASS");
		}else {
			System.out.println(orgName + "is not created === FAIL");
		}
		
		
//		NAVIGATE TO ORGANIZATION MODULE
	    driver.findElement(By.linkText("Contacts")).click();
		
//		CLICK ON CREATE ORGANIZATION BUTTON
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
//		ENTER ALL ORGANIZATION DETAILS AND CREATE NEW ORGANIZATION
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(contactLastName);
		driver.findElement(By.xpath("//img[@title='Select']")).click();
		
//		SWITCH TO CHILD WINDOW
		wdu.switchToTabOnUrl(driver, "module=Accounts");
		
		
		driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@name='search']")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
//		SWITCH TO PARENT WINDOW
		wdu.switchToTabOnUrl(driver, "Contacts&action");
		
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
	
		
//		VERIFY HEADER PHONENUMBER INFO EXCEPTED RESULT
//		String actphone=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//		if(actphone.trim().equals(orgnames)) {
//			System.out.println(orgnames +" is created === PASS");
//		}else {
//			System.out.println(orgnames + "is not created === FAIL");
//		}
		
//		VERIFY HEADER ORGANIZATION INFO EXCEPTED RESULT
		String actlastname=driver.findElement(By.id("mouseArea_Organization Name")).getText();
//		String actlastname=driver.findElement(By.xpath("//input[@name='lastname']")).getText();
		System.out.println(actlastname);
//		System.out.println(contactLastName);
		if(actlastname.contains(contactLastName)) {
			System.out.println(contactLastName +" is created === PASS");
		}else {
			System.out.println(contactLastName + "is not created === FAIL");
		}
		
		
//		LOGOUT
		driver.quit();
		
	}

}