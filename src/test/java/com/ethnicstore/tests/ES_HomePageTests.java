package com.ethnicstore.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ethnicstore.base.Base;
import com.ethnicstore.pages.ES_CreateAccount;
import com.ethnicstore.pages.ES_HomePage;

public class ES_HomePageTests extends Base {

	public ES_HomePage homePage;
	public ES_CreateAccount createAccountPage;

	public ES_HomePageTests() {

		super(); // Super keyword calls Base constructor which has setup

	}

	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) throws InterruptedException {

		initialization(browser);
		homePage = new ES_HomePage();

	}

	@Test
	public void titleTest() {
		String title = homePage.validateESHomePageTitle();
		Assert.assertEquals(title, "The Ethnic Store");
	}

	@Test(dependsOnMethods = "titleTest")
	public void homePageLogoTest() {
		boolean title = homePage.validateESHomePageLogo();
		Assert.assertTrue(title);
		;
	}

	@Test(dependsOnMethods = "homePageLogoTest")
	public void createAccountLinkTest() {
		createAccountPage = homePage.validateCreateAccountLink();
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
