package com.ethnicstore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ethnicstore.base.Base;
import com.ethnicstore.util.Util;

public class ES_LoginPage extends Base{
	
	Util util = new Util();

	// Object Respository

		@FindBy(xpath = "//h1[contains(text(), 'Welcome, Please Sign In')]")
		WebElement loginPageTitle;
		
		@FindBy(xpath = "//h1[contains(text(), 'Welcome to The Ethnic Store')]")
		WebElement userTitle;
		
		@FindBy(xpath = "//input[@name='email_address']")
		WebElement emailAddressField;
		
		@FindBy(xpath = "//input[@name='password']")
		WebElement passwordField;
		
		@FindBy(xpath = "//span[contains(text(),'Sign In')]")
		WebElement signinLink;
		
		@FindBy(xpath = "//td[contains(text(),'MODULE_CONTENT_LOGIN_TEXT_LOGIN_ERROR')]")
		WebElement errorMessage;
		
		
		// initializing elements
		public ES_LoginPage() {
			
			PageFactory.initElements(driver, this);
		}

		public String validateESLoginPageTitle() {

			return loginPageTitle.getText();

		}


		public String validateLogin(String emailAddress, String password) throws InterruptedException {

			emailAddressField.sendKeys(emailAddress);
			passwordField.sendKeys(password);
			signinLink.click();
			Thread.sleep(3000);
			if (util.isElementPresent(By.xpath("//td[contains(text(),'MODULE_CONTENT_LOGIN_TEXT_LOGIN_ERROR')]"))) {
				return errorMessage.getText();
			}else {
			
				return userTitle.getText();
			}
			
		}	
}
