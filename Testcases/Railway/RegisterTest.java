package Railway;

import Account.Account;
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

        System.out.println(
            "TC07 - User can't create account with an already in-use email\n" +
            "Pre-condition: an activated account is existing"
        );
        Account account = Account.generalAccount();
        RandomMail randomMail = new RandomMail();
        randomMail.open();
        randomMail.setGuerrillaMail(account.getEmail());
        account.setEmail(randomMail.getGmail());
        
        System.out.println("1. Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        WindowManager.newTab(Constant.WINDOW_TAB_RAILWAY);
        Utilities.getUrl(Constant.RAIWAY_URL);

        System.out.println(
            "2. Click on \"Register\" tab\n" +
            "Enter information of the created account\n" +
            "Click on \"Register\" button"
        );
        RegisterPage registerPage =
                (RegisterPage) homePage.navigateMenu(MenuRailway.REGISTER);
        registerPage.register(account);

        System.out.println("3. Activate account via email");
        WindowManager.switchToTab(Constant.WINDOW_TAB_MAIL);
        Utilities.refreshWindow();
        String linkActive = randomMail.getLinkActive();

        WindowManager.switchToTab(Constant.WINDOW_TAB_RAILWAY);
        Constant.WEBDRIVER.get(linkActive);

        System.out.println("4. Register again with the same email");
        registerPage =
                (RegisterPage) homePage.navigateMenu(MenuRailway.REGISTER);
        registerPage.register(account);

        System.out.println(
            "Verify error message \"This email address is already in use.\" displays"
        );
        String actualMessage = registerPage.getErrorMessage();
        String expectedMessage =
                Constant.REGISTER_MESSAGE_ERROR_DUPLICAP_MAIL;

        Assert.assertEquals(actualMessage.trim(), expectedMessage.trim());
    }

    @Test
    public void TC08() {

        System.out.println(
            "TC08 - User can't create account while password and PID fields are empty"
        );

        String email = RandomString.generateRandomString(
                Constant.DEFAULT_EMAIL_LENGTH);
        Account account = new Account(email, "", "");

        RandomMail randomMail = new RandomMail();
        randomMail.open();
        randomMail.setGuerrillaMail(account.getEmail());
        account.setEmail(randomMail.getGmail());

        System.out.println("1. Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        WindowManager.newTab(Constant.WINDOW_TAB_RAILWAY);
        Utilities.getUrl(Constant.RAIWAY_URL);

        System.out.println(
            "2. Click on \"Register\" tab\n" +
            "Enter information with empty Password and PID\n" +
            "Click on \"Register\" button"
        );
        RegisterPage registerPage =
                (RegisterPage) homePage.navigateMenu(MenuRailway.REGISTER);
        registerPage.register(account);

        System.out.println(
            "Verify error message \"There're errors in the form. Please correct the errors and try again.\" displays"
        );
        Assert.assertEquals(
            registerPage.getErrorMessage().trim(),
            Constant.REGISTER_MESSAGE_ERROR_FORM.trim()
        );

        System.out.println(
            "Verify error message \"Invalid password length.\" displays next to Password field"
        );
        Assert.assertEquals(
            registerPage.getErrorMessagePassword().trim(),
            Constant.REGISTER_MESSAGE_ERROR_PASSWORD_FIELD.trim()
        );

        System.out.println(
            "Verify error message \"Invalid ID length.\" displays next to PID field"
        );
        Assert.assertEquals(
            registerPage.getErrorMessagePid().trim(),
            Constant.REGISTER_MESSAGE_ERROR_PID_FIELD.trim()
        );
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

        System.out.println("1. Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        WindowManager.newTab(Constant.WINDOW_TAB_RAILWAY);
        Utilities.getUrl(Constant.RAIWAY_URL);

        System.out.println("2. Click on \"Create an account\"");
        RegisterPage registerPage = homePage.clickBtnCreateAccount();

        System.out.println(
            "Home page is shown with guide containing href \"create an account\" to \"Register\" page\n" +
            "Register page is shown"
        );
        Assert.assertEquals(registerPage.getTitle(), Constant.REGISTER_TITLE);

        System.out.println(
            "3. Enter valid information into all fields\n" +
            "4. Click on \"Register\" button"
        );
        registerPage.register(account);

        System.out.println("\"Thank you for registering your account\" is shown");
        Assert.assertEquals(
            registerPage.getTitle(),
            Constant.REGISTER_MESSAGE_SUCCESSFUL
        );

        System.out.println(
            "5. Get activation email\n" +
            "6. Open email with subject containing \"Please confirm your account\""
        );
        WindowManager.switchToTab(Constant.WINDOW_TAB_MAIL);
        String activeLink = randomMail.getLinkActive();

        WindowManager.switchToTab(Constant.WINDOW_TAB_RAILWAY);
        Utilities.getUrl(activeLink);

        System.out.println(
            "Redirect to Railway page and message " +
            "\"Registration Confirmed! You can now log in to the site\" is shown"
        );
        String actualMessage =
                registerPage.getMessageConfirmedActive();

        Assert.assertEquals(
            actualMessage.trim(),
            Constant.REGISTER_MESSAGE_SUCCESSFUL_ACTIVE.trim()
        );
    }

}
