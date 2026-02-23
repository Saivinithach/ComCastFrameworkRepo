package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewOrganizationPage 
{
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(name="accountname")
	private WebElement orgNameInput;
	
	@FindBy(xpath="(//textarea[@class='detailedViewTextBox'])[2]")
	private WebElement shipAd;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	
	public WebElement getOrgName() {
		return orgNameInput;
	}
	public WebElement getShipAd() {
		return shipAd;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	
	public void createOrg(String orgName)
	{
		orgNameInput.sendKeys(orgName);
		shipAd.sendKeys("Kolluru");
		saveBtn.click();
	}
}
