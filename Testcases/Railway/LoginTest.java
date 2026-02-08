package Railway;

import org.testng.annotations.Test;
import Account.Account;
import org.testng.Assert;
import Common.RandomString;
import Constant.Constant;
import Menu.MenuRailway;

public class LoginTest extends BaseTest {
	
	@Test
	public void TC01() {

	    System.out.println("TC01 - User can log into Railway with valid username and password");

	    HomePage homePage = new HomePage();
	    System.out.println("1. Navigate to QA Railway Website");
	    homePage.open();
	    homePage.logoutIfLoggedIn();

	    System.out.println("2. Click on \"Login\" tab");
	    LoginPage loginPage =
	            (LoginPage) homePage.navigateMenu(MenuRailway.LOGIN);

	    System.out.println("3. Enter valid Email and Password then click Login");
	    Account account =
	            new Account(Constant.USERNAME, Constant.PASSWORD.toUpperCase());

	    String actualMessage =
	            loginPage.login(account).getWelcomMessage();

	    String expectedMessage =
	            "Welcome " + Constant.USERNAME;

	    System.out.println("User is logged in successfully");
	    Assert.assertEquals(actualMessage.trim(), expectedMessage.trim());

	    new LogoutPage().logout();
	}

	
	@Test
	public void TC02() {
	    System.out.println("TC02: User cannot login with blank \"Username\" textbox");
	    System.out.println("1. Navigate to QA Railway Website");

	    HomePage homePage = new HomePage();
	    homePage.open();
	    homePage.logoutIfLoggedIn();

	    System.out.println("2. Click on \"Login\" tab");
	    LoginPage loginPage = (LoginPage) homePage.navigateMenu(MenuRailway.LOGIN);

	    System.out.println(
	        "3. User doesn't type any words into \"Username\" textbox but enter valid information into \"Password\" textbox\n" +
	        "4. Click on \"Login\" button"
	    );
	    Account loginDTO = new Account("", Constant.PASSWORD.toUpperCase());
	    loginPage.login(loginDTO);

	    System.out.println(
	        "User can't login and message \"There was a problem with your login and/or errors exist in your form.\" appears."
	    );
	    String actualMessage = loginPage.getErrorMessageLoginString();
	    String expectedMessage = Constant.LOGIN_MESSAGE_ERROR;

	    Assert.assertEquals(actualMessage.trim(), expectedMessage.trim());
	}

	@Test
	public void TC03() {
	    System.out.println("TC03: User cannot log into Railway with invalid password");
	    System.out.println("1. Navigate to QA Railway Website");

	    HomePage homePage = new HomePage();
	    homePage.open();
	    homePage.logoutIfLoggedIn();

	    System.out.println("2. Click on \"Login\" tab");
	    LoginPage loginPage = (LoginPage) homePage.navigateMenu(MenuRailway.LOGIN);

	    System.out.println("3. Enter valid Email and invalid Password\n4. Click on \"Login\" button");
	    Account loginDTO = new Account(Constant.USERNAME, Constant.PASSWORD.toUpperCase());
	    loginPage.login(loginDTO);

	    System.out.println("User can't login and error message is displayed");
	    String actualMessage = loginPage.getErrorMessageLoginString();
	    String expectedMessage = Constant.LOGIN_MESSAGE_ERROR;

	    Assert.assertEquals(actualMessage.trim(), expectedMessage.trim());
	}

	@Test
	public void TC04() {
	    System.out.println("TC04: User cannot log into Railway with invalid password");
	    System.out.println("1. Navigate to QA Railway Website");

	    int retryTimes = 3;
	    HomePage homePage = new HomePage();
	    homePage.open();
	    homePage.logoutIfLoggedIn();

	    System.out.println("2. Click on \"Login\" tab");
	    LoginPage loginPage = (LoginPage) homePage.navigateMenu(MenuRailway.LOGIN);

	    System.out.println("3. Enter valid Username and invalid Password");
	    Account loginDTO = new Account(Constant.USERNAME, RandomString.generateRandomString(12));
	    loginPage.login(loginDTO);

	    System.out.println("Verify \"Invalid username or password. Please try again\" is shown");
	    String actualMessage = loginPage.getErrorMessageLoginString();
	    String expectedMessage = Constant.LOGIN_MESSAGE_INVALID_PASSWORD;
	    Assert.assertEquals(actualMessage.trim(), expectedMessage.trim());

	    for (int i = 1; i <= retryTimes; i++) {
	        System.out.println("Retry login with invalid password. Time = " + i);
	        loginDTO.setPassword(RandomString.generateRandomString(12));
	        loginPage.login(loginDTO);
	    }

	    System.out.println(
	        "User can't login and message \"You have used 4 out of 5 login attempts...\" appears."
	    );
	    String actualFailMessage = loginPage.getErrorMessageLoginString();
	    String expectedFailMessage = Constant.LOGIN_MESSAGE_FAIL_4;

	    Assert.assertEquals(actualFailMessage.trim(), expectedFailMessage.trim());
	}

	@Test
	public void TC05() {
	    System.out.println("TC05: User can't login with an account hasn't been activated");
	    System.out.println("Pre-condition: a not-active account is existing");
	    System.out.println("1. Navigate to QA Railway Website");

	    String password = RandomString.generateRandomString(12);
	    String pid = RandomString.generateRandomString(12);
	    String email = RandomString.generateRandomEmail();

	    Account registerDTO = new Account(email, password, pid);

	    HomePage homePage = new HomePage();
	    homePage.open();

	    RegisterPage registerPage = (RegisterPage) homePage.navigateMenu(MenuRailway.REGISTER);
	    registerPage.register(registerDTO);

	    homePage.navigateMenu(MenuRailway.HOME);
	    homePage.logoutIfLoggedIn();

	    System.out.println("2. Click on \"Login\" tab");
	    LoginPage loginPage = (LoginPage) homePage.navigateMenu(MenuRailway.LOGIN);

	    System.out.println("3. Login with non-activated account");
	    Account loginDTO = new Account(email, password);
	    loginPage.login(loginDTO);

	    System.out.println("User can't login and message \"Invalid username or password. Please try again.\" appears.");
	    String actualMessage = loginPage.getErrorMessageLoginString();
	    String expectedMessage = Constant.LOGIN_MESSAGE_INVALID_PASSWORD;

	    Assert.assertEquals(actualMessage.trim(), expectedMessage.trim());
	}

}
