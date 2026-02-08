package Constant;

import org.openqa.selenium.WebDriver;

/**
 * Constant
 * Global constants for automation framework
 */
public class Constant {

    // ===== WEBDRIVER =====
    public static WebDriver WEBDRIVER;


    // ===== URL =====
    public static final String RAIWAY_URL = "http://saferailway.somee.com/Page/HomePage.cshtml";
    public static final String RANDOMMAIL_URL = "https://www.guerrillamail.com/";


    // ===== ACCOUNT DATA =====
    public static final String USERNAME = "1202phamnam@gmail.com";
    public static final String PASSWORD = "11111111";

    public static final String INACTIVE_USERNAME = "duongtrinhit02@gmail.com";
    public static final String INACTIVE_PASSWORD = "11111111";


    // ===== LOGIN / REGISTER MESSAGES =====
    public static final String LOGIN_MESSAGE_ERROR =
            "There was a problem with your login and/or errors exist in your form.";

    public static final String LOGIN_MESSAGE_INVALID_PASSWORD =
            "Invalid username or password. Please try again.";

    public static final String LOGIN_MESSAGE_FAIL_4 =
            "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

    public static final String HOME_MESSAGE_WELLCOME =
            "Welcome to Safe Railway";

    public static final String REGISTER_MESSAGE_ERROR_DUPLICAP_MAIL =
            "This email address is already in use.";

    public static final String REGISTER_MESSAGE_ERROR_FORM =
            "There're errors in the form. Please correct the errors and try again.";

    public static final String REGISTER_MESSAGE_ERROR_PASSWORD_FIELD =
            "Invalid password length.";

    public static final String REGISTER_MESSAGE_ERROR_PID_FIELD =
            "Invalid ID length.";

    public static final String REGISTER_MESSAGE_SUCCESSFUL_ACTIVE =
            "Registration Confirmed! You can now log in to the site.";

    public static final String REGISTER_MESSAGE_SUCCESSFUL =
            "Thank you for registering your account";

    public static final String REGISTER_TITLE =
            "Create account";


    // ===== RESET PASSWORD =====
    public static final String RESET_PASSWORD_SAME_CURRENT_PASSWORD =
            "The new password cannot be the same with the current password";

    public static final String LINK_RESET_PASSWORD_TOKEN =
            "resetToken=";


    // ===== DEFAULT CONFIG =====
    public static final int DEFAULT_TIMEOUT = 30;
    public static final int DEFAULT_EMAIL_LENGTH = 12;
    public static final int DEFAULT_PASSWORD_LENGTH = 12;
    public static final int DEFAULT_PID_LENGTH = 12;


    // ===== WINDOW / TAB NAME =====
    public static final String WINDOW_TAB_MAIL = "mail";
    public static final String WINDOW_TAB_RAILWAY = "railway";
}
