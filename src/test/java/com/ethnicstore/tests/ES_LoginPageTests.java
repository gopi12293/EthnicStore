package com.ethnicstore.tests;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ethnicstore.base.Base;
import com.ethnicstore.pages.ES_CreateAccount;
import com.ethnicstore.pages.ES_HomePage;
import com.ethnicstore.pages.ES_LoginPage;
import com.ethnicstore.util.Util;

public class ES_LoginPageTests extends Base {

	public ES_HomePage homePage;
	public ES_CreateAccount createAccountPage;
	public Util util = new Util();
	public ES_LoginPage loginPage;

	public ES_LoginPageTests() {

		super(); // Super keyword calls Base constructor which has setup

	}

	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) throws InterruptedException {

		initialization(browser);
		homePage = new ES_HomePage();
		createAccountPage = new ES_CreateAccount();
		loginPage = homePage.validateLoginLink();
	}

	@Test
	public void validateLoginpageTitleTest() {
		String title = loginPage.validateESLoginPageTitle();
		Assert.assertEquals(title, "Welcome, Please Sign In");
	}

	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException {
		Object data[][] = util.getExceldata("Credentials");
		return data;
	}

	@Test(dependsOnMethods = "validateLoginpageTitleTest", dataProvider = "getData")
	public void validateLoginTest(String emailAddress, String password) throws InterruptedException {

		String message = loginPage.validateLogin(emailAddress, password);
		if (message.contains("LOGIN_ERROR")) {
			Assert.assertTrue(true, "Invalid Credentials entered");
		} else if (message.contains("Welcome to The Ethnic Store")) {
			Assert.assertTrue(true, "Valid Credentials entered");
		}
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
