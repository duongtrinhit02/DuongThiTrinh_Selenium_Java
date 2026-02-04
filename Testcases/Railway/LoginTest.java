package Railway;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import Common.Utilities;
import Constant.Constant;

public class LoginTest {
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Pre-condition");
		
		
				
		Constant.WEBDRIVER = new ChromeDriver();
		Constant.WEBDRIVER.manage().window().maximize();
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("Post-condition");
		
		Constant.WEBDRIVER.quit();
	}
	
	@Test
	public void TC01() {
		System.out.println("TC01 - User can log into Railway with valid username and password");
		HomePage homePage = new HomePage();
		homePage.open();
		
		LoginPage loginPage = homePage.gotoLoginPage();
		
		String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();
		String expectedMsg = "Welcome" + Constant.USERNAME;
		
		Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
	}
	@Test
	public void TC02() {
	    System.out.println("TC02 - User cannot login with blank Username");

	    HomePage homePage = new HomePage();
	    homePage.open();

	    LoginPage loginPage = homePage.gotoLoginPage();

	    loginPage.login("", Constant.PASSWORD);

	    String actualMsg = loginPage.getErrorMessage();

	    String expectedMsg =
	        "There was a problem with your login and/or errors exist in your form.";
	    System.out.println("Login fail with lank username");
	    Assert.assertEquals(actualMsg, expectedMsg,
	        "Error message is not displayed as expected");
	    
	}
	
	@Test
	public void TC03() {
	    System.out.println("TC03 - User cannot log into Railway with invalid password");

	    HomePage homePage = new HomePage();
	    homePage.open();

	    LoginPage loginPage = homePage.gotoLoginPage();

	    loginPage.login(Constant.USERNAME, "123456");

	    String actualMsg = loginPage.getErrorMessage();

	    String expectedMsg =
	        "There was a problem with your login and/or errors exist in your form.";

	    Assert.assertEquals(actualMsg, expectedMsg,
	        "Error message is not displayed as expected");
	}
	
	@Test
	public void TC04() {
	    System.out.println("TC04 - Login with wrong password many times");

	    HomePage homePage = new HomePage();
	    homePage.open();
	    LoginPage loginPage = homePage.gotoLoginPage();
	    loginPage.loginFailManyTime(5);
	    String actualMsg = loginPage.getErrorMessage();
	    
	    String expectedMsg = "Invalid username or password. Please try again.";
	    Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");

	  
	    String actualMsg2 = loginPage.login(Constant.USERNAME, "123").getErrorMessage();
	    String expectedMsg2 = "4 out of 5 login attempts, Warning message is not displayed";
	    Assert.assertEquals(actualMsg2, expectedMsg2, "Error message is not displayed as expected");
	}
	@Test
	public void TC05() {
	    System.out.println("TC05 - User can't login with an account hasn't been activated");

	    
	    HomePage homePage = new HomePage();
	    homePage.open();

	    LoginPage loginPage = homePage.gotoLoginPage();

	    loginPage.login(Constant.INACTIVE_USERNAME, Constant.INACTIVE_PASSWORD);

	    String actualMsg = loginPage.getErrorMessage();
	    String expectedMsg = "Invalid username or password. Please try again.";

	    Assert.assertEquals(actualMsg, expectedMsg,
	            "Error message is not displayed as expected");
	}


}
