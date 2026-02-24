package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Products 
{
	@FindBy(xpath="//input[@alt='Create Product...']")
	private WebElement createProductImg;
	
	@FindBy(name="search")
	private WebElement ele2;
	
	@FindBy(name="searchBtn")
	private WebElement ele3;
}
