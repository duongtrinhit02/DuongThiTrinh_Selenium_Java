package Railway;

import Account.Account;
import Common.AccountHelper;
import Common.RandomString;
import Common.Utilities;
import Common.WindowManager;
import Constant.Constant;
import Mail.RandomMail;
import Menu.MenuRailway;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    @Test
    public void TC07() {

        System.out.println("TC07 - User can't create account with an already in-use email\n");
        System.out.println("Pre-condition: an activated account is existing");
        Account account = AccountHelper.createAndActivateAccount();
        
        System.out.println("Step 1: Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        WindowManager.newTab(Constant.WINDOW_TAB_RAILWAY);
        Utilities.getUrl(Constant.RAIWAY_URL);

        System.out.println("Step 2: Click on 'Register' tab");
        System.out.println("Step 3: Enter information of the created account in Pre-condition.");
        System.out.println("Step 4: Click on \"Login\" button");
        RegisterPage registerPage = (RegisterPage) homePage.navigateMenu(MenuRailway.REGISTER);
        registerPage.register(account);
        
        System.out.println("VP: Error message \"This email address is already in use.\" displays above the form.");
        String actualMessage = registerPage.getErrorMessage();
        String expectedMessage = Constant.REGISTER_MESSAGE_ERROR_DUPLICAP_MAIL;
        Assert.assertEquals(actualMessage.trim(), expectedMessage.trim());
    }

    @Test
    public void TC08() {

        System.out.println("TC08 - User can't create account while password and PID fields are empty");
        String email = RandomString.generateRandomString(Constant.DEFAULT_EMAIL_LENGTH);
        Account account = new Account(email, "", "");
        RandomMail randomMail = new RandomMail();
        randomMail.open();
        randomMail.setGuerrillaMail(account.getEmail());
        account.setEmail(randomMail.getGmail());

        System.out.println("Step 1: Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        WindowManager.newTab(Constant.WINDOW_TAB_RAILWAY);
        Utilities.getUrl(Constant.RAIWAY_URL);

        System.out.println("Step 2: Click on 'Register' tab");
        System.out.println("Step 3: Enter valid email address and leave other fields empty");
        System.out.println("Step 4: Click on \"Login\" button");
        RegisterPage registerPage = (RegisterPage) homePage.navigateMenu(MenuRailway.REGISTER);
        registerPage.register(account);

        System.out.println("VP: Message \"There're errors in the form. Please correct the errors and try again.\" appears above the form.");
        Assert.assertEquals(registerPage.getErrorMessage().trim(), Constant.REGISTER_MESSAGE_ERROR_FORM.trim());

        System.out.println("VP: error message 'Invalid password length.' displays next to Password field");
        Assert.assertEquals(registerPage.getErrorMessagePassword().trim(), Constant.REGISTER_MESSAGE_ERROR_PASSWORD_FIELD.trim());

        System.out.println("VP: error message 'Invalid ID length.' displays next to PID field");
        Assert.assertEquals(registerPage.getErrorMessagePid().trim(), Constant.REGISTER_MESSAGE_ERROR_PID_FIELD.trim());
    }
    
    @Test
    public void TC09() {

        System.out.println("TC09 - User create and activate account");
        System.out.println("Create mail account");
        Account account = Account.generalAccount();
        RandomMail randomMail = new RandomMail();
        randomMail.open();
        randomMail.setGuerrillaMail(account.getEmail());
        account.setEmail(randomMail.getGmail());

        System.out.println("Step 1: Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        WindowManager.newTab(Constant.WINDOW_TAB_RAILWAY);
        Utilities.getUrl(Constant.RAIWAY_URL);

        System.out.println("Step 2: Click on \"Create an account\"");
        RegisterPage registerPage = homePage.clickBtnCreateAccount();

        System.out.println("VP: Register page is shown");
        Assert.assertEquals(registerPage.getTitle(), Constant.REGISTER_TITLE);

        System.out.println("Step 3: Enter valid information into all fields"); 
        System.out.println("Step 4: Click on \"Register\" button"); 
        registerPage.register(account);

        System.out.println("VP: \"Thank you for registering your account\" is shown");
        Assert.assertEquals(registerPage.getTitle(), Constant.REGISTER_MESSAGE_SUCCESSFUL);

        System.out.println("Step 5: Get email information (webmail address, mailbox and password) and navigate to that webmail");
        System.out.println("Step 6:  Login to the mailbox");
        System.out.println("Step 7: Open email with subject containing \"Please confirm your account\"");
        System.out.println("Step 8: Click on the activate link");
        WindowManager.switchToTab(Constant.WINDOW_TAB_MAIL);
        String activeLink = randomMail.getLinkActive();

        WindowManager.switchToTab(Constant.WINDOW_TAB_RAILWAY);
        Utilities.getUrl(activeLink);

        System.out.println("VP: Redirect to Railways page and message \"Registration Confirmed! You can now log in to the site\" is shown");
        String actualMessage = registerPage.getMessageConfirmedActive();

        Assert.assertEquals(actualMessage.trim(),
            Constant.REGISTER_MESSAGE_SUCCESSFUL_ACTIVE.trim());
    }

}
