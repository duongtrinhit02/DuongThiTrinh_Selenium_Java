package Railway;

import org.openqa.selenium.By;

import Account.Account;
import Common.Utilities;

public class RegisterPage extends GeneralPage {

    // locators
    private final By txtEmailBy = By.xpath("//input[@id='email' and @name='email']");
    private final By txtPasswordBy = By.xpath("//input[@id='password' and @name='password']");
    private final By txtConfirmPasswordBy = By.xpath("//input[@id='confirmPassword' and @name='confirmPassword']");
    private final By txtPid = By.xpath("//input[@id='pid' and @name='pid']");
    private final By btnRegisterBy = By.xpath("//input[@type='submit' and @title='Register']");
    private final By lbErrorMessageBy = By.xpath("//p[@class='message error']");
    private final By lbErrorMessagePasswordBy = By.xpath("//label[@class='validation-error' and @for='password']");
    private final By lbErrorMessagePidBy = By.xpath("//label[@class='validation-error' and @for='pid']");
    private final By lbErrorMessageConfirmPasswordBy = By.xpath("//label[@class='validation-error' and @for='confirmPassword']");
    private final By lbMessageConfirmActiveBy = By.xpath("//div[@id='content']/p");

    // register action
    public HomePage register(Account userRegisterDTO) {
        Utilities.type(this.txtEmailBy, userRegisterDTO.getEmail());
        Utilities.type(this.txtPasswordBy, userRegisterDTO.getPassword());
        Utilities.type(this.txtConfirmPasswordBy, userRegisterDTO.getPassword());
        Utilities.type(this.txtPid, userRegisterDTO.getPid());
        Utilities.click(this.btnRegisterBy);
        return new HomePage();
    }

    // error messages
    public String getErrorMessage() {
        return Utilities.getText(this.lbErrorMessageBy);
    }

    public String getErrorMessagePassword() {
        return Utilities.getText(this.lbErrorMessagePasswordBy);
    }

    public String getErrorMessagePid() {
        return Utilities.getText(this.lbErrorMessagePidBy);
    }

    public String getErrorMessageConfirmPassword() {
        return Utilities.getText(this.lbErrorMessageConfirmPasswordBy);
    }

    // success message
    public String getMessageConfirmedActive() {
        return Utilities.getText(this.lbMessageConfirmActiveBy);
    }
}
