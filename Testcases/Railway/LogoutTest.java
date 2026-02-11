package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Account.Account;
import Constant.Constant;
import Menu.MenuRailway;

public class LogoutTest extends BaseTest {
	
	@Test
	public void TC06() {
	    System.out.println("TC06 - User is redirected to Home page after logging out");
	    System.out.println("Step 1: Navigate to QA Railway Website");
	    HomePage homePage = new HomePage();
		homePage.openRailway();
		
	    System.out.println("Step 2: Login with valid Email and Password");
	    LoginPage loginPage = (LoginPage) homePage.navigateMenu(MenuRailway.LOGIN);
	    Account loginDTO = new Account(Constant.USERNAME, Constant.PASSWORD);
	    loginPage.login(loginDTO);

	    System.out.println("Step 3: Click on \"FAQ\" tab");
	    homePage.navigateMenu(MenuRailway.FAQ);

	    System.out.println("Step 4: Click on \"Log out\" tab");
	    LogoutPage logoutPage = new LogoutPage();
	    logoutPage.logout();

	    System.out.println("VP: Home page displays.\r\n" + "\"Log out\" tab is disappeared.");
	    String actualMessage = homePage.getTextTitle();
	    String expectedMessage = Constant.HOME_MESSAGE_WELLCOME;
	    Assert.assertEquals(actualMessage.trim(), expectedMessage.trim());
	}

}
