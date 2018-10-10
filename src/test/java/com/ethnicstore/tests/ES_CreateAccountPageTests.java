package com.ethnicstore.tests;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.DateFormatter;

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
import com.ethnicstore.util.Util;

public class ES_CreateAccountPageTests extends Base {

	public ES_HomePage homePage;
	public ES_CreateAccount createAccountPage;
	public Util util = new Util();

	public ES_CreateAccountPageTests() {

		super(); // Super keyword calls Base constructor which has setup

	}

	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) throws InterruptedException {

		initialization(browser);
		homePage = new ES_HomePage();
		createAccountPage = homePage.validateCreateAccountLink();

	}

	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException {
		Object data[][] = util.getExceldata("AccountDetails");
		return data;
	}

	@Test(dataProvider = "getData")
	public void validateCreateAccountTest(String gender, String fName, String lName, String dob, String email,
			String address, String postcode, String city, String state, String country, String telephone,
			String password, String confirmation) throws InterruptedException {

		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("ddMMMyyyyHHmmss");
		email = df.format(date) + "@gmail.com";
		String title = createAccountPage.validateCreateAccount(gender, fName, lName, dob, email, address, postcode,

				city, state, country, telephone, password, confirmation);

		if (country == null || country.equals("")) {
			Assert.assertTrue(title.contains("You must select a country"));
		} else if (postcode.length() < 4) {
			Assert.assertTrue(title.contains("Your Post Code must contain a minimum of 4 characters"));
		} else {
			Assert.assertEquals(title, "Your Account Has Been Created!");
		}
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
