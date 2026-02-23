package com.comcast.crm.contacttest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass1;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;

public class CreateContactTest_WithConfigDat extends BaseClass1
{
	
	@Test(groups="smokeTest")
	public void createContactTest() throws EncryptedDocumentException, IOException
	{
		//read testscript data from excel file
		String lastName=eu.getDataFromExcel("Sheet3", 1, 2)+ju.getRandomNumber();
		
//		NAVIGATE TO ORGANIZATION MODULE
		HomePage hp=new HomePage(driver);
		hp.getContactLink();
		
//		CLICK ON CREATE ORGANIZATION BUTTON
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
//		ENTER ALL ORGANIZATION DETAILS AND CREATE NEW ORGANIZATION
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
	
		
//		VERIFY HEADER ORGNAME INFO EXCEPTED RESULT
		String actlastname=driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actlastname.contains(lastName)) {
			System.out.println(lastName +" is created === PASS");
		}else {
			System.out.println(lastName + "is not created === FAIL");
		}
	}
	
	@Test(groups="regressionTest")
	public void createContactWithSupportDateTest() throws EncryptedDocumentException, IOException
	{

		String lastName=eu.getDataFromExcel("Sheet3", 4, 2)+ju.getRandomNumber();
	
//		NAVIGATE TO ORGANIZATION MODULE
		HomePage hp=new HomePage(driver);
		hp.getContactLink();
		
//		CLICK ON CREATE ORGANIZATION BUTTON
		driver.findElement(By.linkText("Contacts")).click();
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
	}
	
	@Test(groups="regressionTest")
	public void createContactWithOrgTest() throws EncryptedDocumentException, IOException
	{
//		CREATE OBJECT TO RANDOM CLASS
		String contactLastName=eu.getDataFromExcel("Sheet3", 7, 3 )+ju.getRandomNumber();
		String orgName=eu.getDataFromExcel("Sheet3", 7,3)+ju.getRandomNumber();
		
		
//		NAVIGATE TO ORGANIZATION MODULE
		HomePage hp=new HomePage(driver);
		hp.getOrgLink();
		
//		CLICK ON CREATE ORGANIZATION BUTTON
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
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

	}	
}

