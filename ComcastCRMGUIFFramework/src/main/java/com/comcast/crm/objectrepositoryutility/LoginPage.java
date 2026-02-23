package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

/**
 * 
 * @author vinitha
 * 
 * Contains login page elements & business library like login()
 */


public class LoginPage extends WebDriverUtility
{
	//Rule-1: Create a separate java class
	//Rule-2: Object creation
	
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="user_name")
	public WebElement usernameEdit;
	
	@FindBy(name="user_password")
	public WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	public WebElement loginBtn;

	//Rule-3:Object Initialization
	//Rule-4:Object Encapsulation
	
	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//Rule-5:provide actions
	
	/**
	 * login to application based on username,password,url arguments
	 * @param url
	 * @param username
	 * @param password
	 */
	
	public void loginToApp(String url,String username,String password)
	{
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		loginBtn.click();
	}
}
