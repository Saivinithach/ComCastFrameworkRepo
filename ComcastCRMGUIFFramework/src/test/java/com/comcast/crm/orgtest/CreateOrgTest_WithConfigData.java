package com.comcast.crm.orgtest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass1;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

@Listeners
public class CreateOrgTest_WithConfigData extends BaseClass1
{
	@Test(groups="smokeTest")
	public void createOrgTest() throws EncryptedDocumentException, IOException
	{
		String orgName=eu.getDataFromExcel("Sheet1", 1, 2)+ju.getRandomNumber();
		
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		
		CreatingNewOrganizationPage cop=new CreatingNewOrganizationPage(driver);
		cop.createOrg(orgName);
		
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actOrgName=oip.getHeaderMsg().getText();
		if(actOrgName.contains(orgName)) {
			System.out.println(orgName+" is verified ==PASS");
		}
		else{
			System.out.println(orgName+" is verified ==FAIL");
		}
	}
	
	@Test(groups="regressionTest")
	public void createOrgWithPhnNum() throws EncryptedDocumentException, IOException
	{
		String orgName=eu.getDataFromExcel("Sheet1", 7, 2)+ju.getRandomNumber();
		String phonenumber=eu.getDataFromExcel("Sheet1",7 ,3 );
		
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		
//		NAVIGATE TO ORGANIZATION MODULE
		driver.findElement(By.linkText("Organizations")).click();
		
//		CLICK ON CREATE ORGANIZATION BUTTON
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
//		ENTER ALL ORGANIZATION DETAILS AND CREATE NEW ORGANIZATION
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		
		driver.findElement(By.id("phone")).sendKeys(phonenumber);
		driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys("hyderabad");
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
	
		
//		VERIFY HEADER ORGNAME INFO EXCEPTED RESULT
		String actphone=driver.findElement(By.id("dtlview_Phone")).getText();
		if(actphone.contains(phonenumber)) {
			System.out.println(phonenumber +" is created === PASS");
		}else {
			System.out.println(phonenumber + "is not created === FAIL");
		}
		
	}
	
	@Test(groups="regressionTest")
	public void createOrgWithIndustry() throws EncryptedDocumentException, IOException
	{
		String orgName=eu.getDataFromExcel("Sheet1", 4, 2)+ju.getRandomNumber();
		String industry=eu.getDataFromExcel("Sheet1", 4, 3);
		String type=eu.getDataFromExcel("Sheet1", 4, 4);
		
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		
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
	}
}
