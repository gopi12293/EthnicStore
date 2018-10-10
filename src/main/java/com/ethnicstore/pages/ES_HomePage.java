package com.ethnicstore.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ethnicstore.base.Base;

public class ES_HomePage extends Base {

	// Object Respository

	@FindBy(xpath = "//img[@title='The Ethnic Store']")
	WebElement homePageLogo;

	@FindBy(xpath = "//u[contains(text(), 'create an account')]")
	WebElement createAccountLink;
	
	@FindBy(xpath = "//u[contains(text(), 'log yourself in')]")
	WebElement loginLink;

	// initializing elements
	public ES_HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String validateESHomePageTitle() {

		return driver.getTitle();

	}

	public boolean validateESHomePageLogo() {

		return homePageLogo.isDisplayed();

	}

	public ES_CreateAccount validateCreateAccountLink() {

		createAccountLink.click();
		return new ES_CreateAccount();

	}
	
	public ES_LoginPage validateLoginLink() {

		loginLink.click();
		return new ES_LoginPage();

	}

}
