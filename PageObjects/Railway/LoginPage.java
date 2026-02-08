package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Account.Account;
import Common.Utilities;
import Constant.Constant;

public class LoginPage extends GeneralPage {

    // locators
    private final By txtUsername = By.xpath("//input[@id='username']");
    private final By txtPassword = By.xpath("//input[@id='password']");
    private final By btnLogin = By.xpath("//input[@value='login']");
    private final By lbMessageError = By.xpath("//p[]@class='message error LoginForm'");
    private final By lbMessageErrorLoginBy = By.xpath("//p[@class='message error LoginForm']");
    private final By btnForgotPasswordPageBy = By.xpath("//a[text()='Forgot Password page']");

    // element getters
    public WebElement getTxtUsername() {
        return Constant.WEBDRIVER.findElement(txtUsername);
    }

    public WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(txtPassword);
    }

    public WebElement getBtnLogin() {
        return Constant.WEBDRIVER.findElement(btnLogin);
    }

    public WebElement getLbMessageError() {
        return Utilities.getElement(this.lbMessageError);
    }

    // validation messages
    public String getErrorMessageLoginString() {
        Utilities.waitForVisible(this.lbMessageErrorLoginBy);
        return Utilities.getElement(this.lbMessageErrorLoginBy).getAttribute("textContent");
    }

    // login action
    public HomePage login(Account userDto) {
        Utilities.clearAndType(this.getTxtUsername(), userDto.getEmail());
        Utilities.clearAndType(this.getTxtPassword(), userDto.getPassword());

        Utilities.scrollToElement(this.getBtnLogin());
        Utilities.waitForClickable(this.btnLogin);
        getBtnLogin().click();

        return new HomePage();
    }
    
    public ChangePasswordPage clickBtnForgotPasswordPage() {
		Utilities.click(this.btnForgotPasswordPageBy);
		return new ChangePasswordPage();
		 
	}
}
