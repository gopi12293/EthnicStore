package com.ethnicstore.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ethnicstore.base.Base;
import com.ethnicstore.pages.ES_CreateAccount;
import com.ethnicstore.pages.ES_HomePage;
import com.ethnicstore.pages.ES_LoginPage;
import com.ethnicstore.pages.ES_UserProfilePage;
import com.ethnicstore.util.Util;

public class ES_UserProfilePageTests extends Base{

	public ES_HomePage homePage;
	public ES_CreateAccount createAccountPage;
	public ES_LoginPage loginPage;
	public ES_UserProfilePage userProfilePage;
	public Util util = new Util();

	public ES_UserProfilePageTests() {

		super(); // Super keyword calls Base constructor which has setup

	}

	
	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) throws InterruptedException {

		initialization(browser);
		Thread.sleep(5000);
		homePage = new ES_HomePage();
		createAccountPage = new ES_CreateAccount();
		loginPage = homePage.validateLoginLink();
		Thread.sleep(3000);
		userProfilePage = new ES_UserProfilePage();
		loginPage.validateLogin(properties.getProperty("emailAddress"), properties.getProperty("password"));
		Thread.sleep(3000);
	}
	
	@Test
	public void validateAddingItemtoCartTest() {
		
		String status = userProfilePage.validateAddingItemtoCart();
		if (status.contains("Boys White Linen Kurta")) {
			Assert.assertTrue(true, "Item added to cart");
		}else {
			Assert.assertTrue(false, "Item not added");
		}
	}
	
	@Test
	public void validateUpdateitemcartTest() {
		String error = userProfilePage.validateOverloadCart(properties.getProperty("quantity"));
		userProfilePage.clearCart();
		if (error.contains("dont exist in desired quantity in our stock")) {
			Assert.assertTrue(true, "CartOverload");
		}else {
			Assert.assertTrue(false, "Cart not overloaded");
		}
	}
	
	@Test
	public void validateRemovecartTest() {
		String cartValue = userProfilePage.validateRemoveAddediteminCart();
		if (cartValue.contains("Cart is empty")) {
			Assert.assertTrue(true, "Cart is empty");
		}else {
			Assert.assertTrue(false, "Cart is not empty");
		}
	}
	
	@Test
	public void validatePlaceorderTest() {
		String paymentInfo = userProfilePage.validatePlaceOrder();
		if (paymentInfo.contains("Payment Information")) {
			Assert.assertTrue(true, "Item is ready to order");
		}else {
			Assert.assertTrue(false, "Item is not ready to order");
		}		
	}
	
	@Test
	public void validateLogoffTest() {
		String logoffStatus = userProfilePage.validateLogoff();
		if (logoffStatus.contains("Log Off")) {
			Assert.assertTrue(true, "Loggedoff successfully");
		}else {
			Assert.assertTrue(false, "Not loggedoff");
		}	
	}
	
	
	@AfterMethod
	public void tearDown() {

		driver.quit();
	}
	

}
