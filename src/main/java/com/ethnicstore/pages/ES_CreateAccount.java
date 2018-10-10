package com.ethnicstore.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ethnicstore.base.Base;

public class ES_CreateAccount extends Base {

	// Object Respository

	@FindBy(xpath = "//h1[contains(text(), 'My Account Information')]")
	WebElement createAccountPageTitle;
	
	@FindBy(xpath = "//input[@value= 'm']")
	WebElement maleradiobtn;
	
	@FindBy(xpath = "//input[@value= 'f']")
	WebElement femaleradiobtn;

	@FindBy(xpath = "//input[@name= 'firstname']")
	WebElement firstNamefield;
	
	@FindBy(xpath = "//input[@name= 'lastname']")
	WebElement lastNamefield;
	
	@FindBy(xpath = "//input[@name= 'dob']")
	WebElement dobfield;
		
	@FindBy(xpath = "//input[@name= 'email_address']")
	WebElement emailfield;
	
	@FindBy(xpath = "//input[@name= 'street_address']")
	WebElement addressfield;
	
	@FindBy(xpath = "//input[@name= 'postcode']")
	WebElement postcodefield;
	
	@FindBy(xpath = "//input[@name= 'city']")
	WebElement cityfield;
	
	@FindBy(xpath = "//input[@name= 'state']")
	WebElement statefield;
	
	@FindBy(xpath = "//select[@name= 'country']")
	WebElement countryfield;
	
	@FindBy(xpath = "//input[@name= 'telephone']")
	WebElement telephonefield;
	
	@FindBy(xpath = "//input[@name= 'password']")
	WebElement passwordfield;
	
	@FindBy(xpath = "//input[@name= 'confirmation']")
	WebElement confirmationfield;
	
	@FindBy(xpath = "//span[contains(text(),'Continue')]")
	WebElement continuebtn;
	
	@FindBy(xpath = "//h1[contains(text(),'Your Account Has Been Created!')]")
	WebElement accountsuccesstitle;
	
	// initializing elements
	public ES_CreateAccount() {
		PageFactory.initElements(driver, this);
	}
	
	public String validateCreateAccount(String gender, String fName, String lName, String dob, String email, String address, String postcode, String city, String state, String country, String telephone, String password, String confirmation) throws InterruptedException {
		
		if (gender.equalsIgnoreCase("Male")) {
			
			maleradiobtn.click();
			
		}else if(gender.equalsIgnoreCase("Female")){
			femaleradiobtn.click();
		}
			
		firstNamefield.sendKeys(fName);
		lastNamefield.sendKeys(lName);
		dobfield.sendKeys(dob);
		emailfield.sendKeys(email);
		addressfield.sendKeys(address);
		postcodefield.sendKeys(postcode);
		cityfield.sendKeys(city);
		statefield.sendKeys(state);
		countryfield.sendKeys(country);
		telephonefield.sendKeys(telephone);
		passwordfield.sendKeys(password);
		confirmationfield.sendKeys(confirmation);
		continuebtn.click();
		Thread.sleep(3000);

		if (country == null || country.equals("") || postcode.length() < 4) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println(alertText);
		driver.switchTo().alert().accept();
		System.out.println(alertText);
		return alertText;
		
		}else {
			
		return accountsuccesstitle.getText();
			
		}
	
		
		
		
	}
	
	
	
	
}
