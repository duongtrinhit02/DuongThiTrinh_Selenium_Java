package Railway;



import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.testng.Assert;
import org.testng.annotations.Test;

import Account.Account;
import Common.AccountHelper;
import Common.RandomString;
import Common.Utilities;
import Common.WindowManager;
import Constant.Constant;
import Mail.RandomMail;
import Menu.MenuRailway;

public class ResetPassword extends BaseTest {
	
	@Test
	public void TC10() {

	    System.out.println("TC10 - Reset password shows error if the new password is same as current");
	    System.out.println("Pre-condition: an activated account is existing");
	    Account account = AccountHelper.createAndActivateAccount();
	    HomePage homePage = new HomePage();

	    System.out.println("Step 1: Navigate to QA Railway Login page");
	    LoginPage loginPage = (LoginPage) homePage.navigateMenu(MenuRailway.LOGIN);

	    System.out.println("Step 2: Click on 'Forgot Password page' link");
	    ChangePasswordPage changePasswordPage = loginPage.clickBtnForgotPasswordPage();

	    System.out.println( "Step 3: Enter the email address of the activated account");
	    System.out.println( "Step 4: Click on 'Send Instructions' button");

	    changePasswordPage.setEmailForgotPassword(account.getEmail());

	    System.out.println( "Step 5: Login to the mailbox (the same mailbox when creating account) ");
	    System.out.println( "Step 6: Open email with subject contaning \"Please reset your password\" and the email of the account at step 3");
	    System.out.println( "Step 7: Click on reset link");
	    WindowManager.switchToTab(Constant.WINDOW_TAB_MAIL);
	    RandomMail randomMail = new RandomMail();
	    randomMail.getBackToInboxElement().click();
	    String resetLink = randomMail.getLinkResetPassword();
	    WindowManager.switchToTab(Constant.WINDOW_TAB_RAILWAY);
	    Utilities.getUrl(resetLink);

	    System.out.println( "Step 8: Input same password into 2 fields  \"new password\" and \"confirm password\"");
	    System.out.println( "Step 9: Click Reset Password");
	    System.out.println("VP: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");

	    String expectedToken = "";
	    String[] tokenParts = resetLink.split(Constant.LINK_RESET_PASSWORD_TOKEN);
	    if (tokenParts.length > 1) {
	        expectedToken = tokenParts[1];
	    }
	    String actualToken = URLDecoder.decode( changePasswordPage.getInputTokenPasswordString(),StandardCharsets.UTF_8);
	    Assert.assertEquals(actualToken, expectedToken);
	    changePasswordPage.setPasswordChangeForm(
	        account.getPassword(),
	        account.getPassword()
	    );

	    System.out.println("VP: Message \"The new password cannot be the same with the current password\" is shown");
	    String actualMessage = changePasswordPage.getLbMessageChangePassword();
	    Assert.assertEquals(actualMessage.trim(), Constant.RESET_PASSWORD_SAME_CURRENT_PASSWORD.trim());
	}

	
	@Test
	public void TC11() {
		System.out.println("TC11 - Reset password shows error if the new password is same as current");
	    System.out.println("Pre-condition: an activated account is existing");

	    Account account = AccountHelper.createAndActivateAccount();
	    HomePage homePage = new HomePage();

	    System.out.println("Step 1: Navigate to QA Railway Login page");
	    LoginPage loginPage =
	        (LoginPage) homePage.navigateMenu(MenuRailway.LOGIN);

	    System.out.println("Step 2: Click on \"Forgot Password page\" link");
	    ChangePasswordPage changePasswordPage =
	            loginPage.clickBtnForgotPasswordPage();

	    System.out.println( "Step 3: Enter the email address of the activated account");
	    System.out.println( "Step 4: Click on 'Send Instructions' button");
	    changePasswordPage.setEmailForgotPassword(account.getEmail());

	    System.out.println( "Step 5: Login to the mailbox (the same mailbox when creating account) ");
	    System.out.println( "Step 6: Open email with subject contaning \"Please reset your password\" and the email of the account at step 3");
	    System.out.println( "Step 7: Click on reset link");
	    WindowManager.switchToTab(Constant.WINDOW_TAB_MAIL);
	    RandomMail randomMail = new RandomMail();
	    randomMail.getBackToInboxElement().click();
	    String resetLink = randomMail.getLinkResetPassword();
	    WindowManager.switchToTab(Constant.WINDOW_TAB_RAILWAY);
	    Utilities.getUrl(resetLink);
	    System.out.println("VP: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");

	    String expectedToken = "";
	    String[] tokenParts = resetLink.split(Constant.LINK_RESET_PASSWORD_TOKEN);
	    if (tokenParts.length > 1) {
	        expectedToken = tokenParts[1];
	    }
	    String actualToken = URLDecoder.decode( changePasswordPage.getInputTokenPasswordString(),StandardCharsets.UTF_8);
	    Assert.assertEquals(actualToken, expectedToken);
	    changePasswordPage.setPasswordChangeForm(
	        account.getPassword(),
	        account.getPassword()
	    );
	    
	    System.out.println( "Step 8: Input different password into 2 fields  \"new password\" and \"confirm password\"");
	    System.out.println( "Step 9: Click Reset Password");
	    String newPassword = RandomString.generateRandomString(Constant.DEFAULT_PASSWORD_LENGTH);
	    String confirmPassword = RandomString.generateRandomString(Constant.DEFAULT_PASSWORD_LENGTH);
	    changePasswordPage.setPasswordChangeForm(newPassword, confirmPassword);

	    System.out.println("VP:Error message \"Could not reset password. Please correct the errors and try again.\" displays above the form.");
	    String actualConfirmError =
	            changePasswordPage.getErrorConfirmPassword();

	    Assert.assertEquals(
	            actualConfirmError.trim(),
	            Constant.RESET_PASSWORD_CONFIRM_NOT_MATCH
	    );
	}


}
