package Railway;

import static org.testng.Assert.assertEquals;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.testng.Assert;
import org.testng.annotations.Test;

import Account.Account;
import Common.Utilities;
import Common.WindowManager;
import Constant.Constant;
import Mail.RandomMail;
import Menu.MenuRailway;

public class ResetPassword extends BaseTest {
	
	@Test
	public void TC10() {

	    System.out.println("Create Mail account");

	    Account account = Account.generalAccount();

	    RandomMail randomMail = new RandomMail();
        randomMail.open();
        randomMail.setGuerrillaMail(account.getEmail());
        account.setEmail(randomMail.getGmail());

	    HomePage homePage = new HomePage();
	    WindowManager.newTab(Constant.WINDOW_TAB_RAILWAY);
	    Utilities.getUrl(Constant.RAIWAY_URL);

	    RegisterPage registerPage =
	            (RegisterPage) homePage.navigateMenu(MenuRailway.REGISTER);
	    registerPage.register(account);

	    WindowManager.switchToTab(Constant.WINDOW_TAB_MAIL);
	    Utilities.refreshWindow();
	    String activeLink = randomMail.getLinkActive();

	    WindowManager.switchToTab(Constant.WINDOW_TAB_RAILWAY);
	    Utilities.getUrl(activeLink);

	    System.out.println("1. Navigate to QA Railway Login page");
	    LoginPage loginPage =
	            (LoginPage) homePage.navigateMenu(MenuRailway.LOGIN);

	    System.out.println("2. Click on \"Forgot Password page\" link");
	    ChangePasswordPage changePasswordPage =
	            loginPage.clickBtnForgotPasswordPage();

	    System.out.println(
	        "3. Enter the email address of the activated account\n" +
	        "4. Click on \"Send Instructions\" button"
	    );
	    changePasswordPage.setEmailForgotPassword(account.getEmail());

	    System.out.println(
	        "5. Login to the mailbox\n" +
	        "6. Open email with subject containing \"Please reset your password\"\n" +
	        "7. Click on reset link"
	    );

	    WindowManager.switchToTab(Constant.WINDOW_TAB_MAIL);
	    randomMail.getBackToInboxElement().click();
	    String resetLink = randomMail.getLinkResetPassword();

	    WindowManager.switchToTab(Constant.WINDOW_TAB_RAILWAY);

	    System.out.println(
	        "8. Input same password into \"New Password\" and \"Confirm Password\"\n" +
	        "9. Click Reset Password"
	    );

	    Utilities.getUrl(resetLink);

	    String expectedToken = "";
	    String[] tokenParts =
	            resetLink.split(Constant.LINK_RESET_PASSWORD_TOKEN);
	    if (tokenParts.length > 1) {
	        expectedToken = tokenParts[1];
	    }

	    String actualToken =
	            changePasswordPage.getInputTokenPasswordString();
	    actualToken =
	            URLDecoder.decode(actualToken, StandardCharsets.UTF_8);

	    Assert.assertEquals(actualToken, expectedToken);

	    changePasswordPage.setPasswordChangeForm(
	            account.getPassword(),
	            account.getPassword()
	    );

	    System.out.println(
	        "Message \"The new password cannot be the same with the current password\" is shown"
	    );
	    String actualMessage =
	            changePasswordPage.getLbMessageChangePassword();

	    Assert.assertEquals(
	        actualMessage.trim(),
	        Constant.RESET_PASSWORD_SAME_CURRENT_PASSWORD.trim()
	    );
	}


}
