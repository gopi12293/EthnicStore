package com.ethnicstore.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ethnicstore.base.Base;

public class ES_UserProfilePage extends Base{

	
	// Object Respository

			@FindBy(xpath = "//a[contains(text(),'Boys White Linen Kurta')]")
			WebElement productLink;
			
			@FindBy(xpath = "//span[contains(text(),'Log Off')]")
			WebElement logoffBtn;
			
			@FindBy(xpath = "//h1[contains(text(),'Boys White Linen Kurta')]")
			WebElement productTitle;
			
			@FindBy(xpath = "//span[contains(text(),'Add to Cart')]")
			WebElement addtocartBtn;
			
			@FindBy(xpath = "//strong[contains(text(),'Boys White Linen Kurta')]")
			WebElement cartitemTitle;
			
			@FindBy(xpath = "//span[contains(text(),'Checkout')]")
			WebElement checkoutBtn;
			
			@FindBy(xpath = "//textarea[@name='comments']")
			WebElement textareaField;
			
			@FindBy(xpath = "//span[contains(text(),'Continue')]")
			WebElement continueBtn;
			
			@FindBy(xpath = "//h1[contains(text(),'Payment Information')]")
			WebElement paymentinfoTitle;
			
			@FindBy(xpath = "//span[contains(text(),'Cart Contents')]")
			WebElement cartContentBtn;
			
			@FindBy(xpath = "//input[@type='text']")
			WebElement quantityField;
			
			@FindBy(xpath = "//span[contains(text(),'Update')]")
			WebElement updateBtn;
			
			@FindBy(xpath = "//a[contains(text(),'remove')]")
			WebElement removeBtn;
			
			@FindBy(xpath = "//div[contains(text(),'Your Shopping Cart is empty!')]")
			WebElement removedCartTitle;
			
			@FindBy(xpath = "//h1[contains(text(),'Log Off')]")
			WebElement logoffTitle;
			
			@FindBy(xpath = "//p[@class='stockWarning']")
			WebElement cartOverloadError;
			
			// initializing elements
			public  ES_UserProfilePage(){
				
				PageFactory.initElements(driver, this);
			}

			public String validateAddingItemtoCart() {
				
				productLink.click();
				if (productTitle.isDisplayed()) {
					addtocartBtn.click();
					if (cartitemTitle.isDisplayed()) {
						return cartitemTitle.getText();
						
					}
				}
				return null;	
			}
			
			public String validateOverloadCart(String quantity) {
				
				productLink.click();
				addtocartBtn.click();
				quantityField.sendKeys(quantity);
				updateBtn.click();
				return cartOverloadError.getText();
				
			}
			
			public String validateRemoveAddediteminCart() {
				productLink.click();
				addtocartBtn.click();
				cartContentBtn.click();
				removeBtn.click();
				return removedCartTitle.getText();		
			}
			
			public void clearCart() {
				cartContentBtn.click();
				removeBtn.click();
			}
			
			public String validatePlaceOrder() {
			
				productLink.click();
				addtocartBtn.click();
				checkoutBtn.click();
				textareaField.sendKeys("Added comments");
				continueBtn.click();
				return paymentinfoTitle.getText();
			}
			
			public String validateLogoff() {
				logoffBtn.click();
				return logoffTitle.getText();
			}
}
