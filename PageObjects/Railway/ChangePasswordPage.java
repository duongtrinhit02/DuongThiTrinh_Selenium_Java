package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;

public class ChangePasswordPage extends GeneralPage{
	private final By txtEmail = By.xpath("//input[@id='email']");
	private final By txtSendInStruction = By.xpath("//input[@type='submit']");
	private final By txtNewPasswordBy = By.xpath("//input[@id='newPassword']");
	private final By txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By btnResetPassword = By.xpath("//input[@type='submit' and @title='Reset password']");
	private final By lbMessageChangePassword = By.xpath("//P[@class='message success']");
	private final By txtTokenResetPassword = By.xpath("//input[@id='resetToken']");
	private final By lbConfirmPasswordError = By.xpath("//span[contains(@class,'field-validation-error')]");
	private final By lbResetPasswordFormError = By.xpath("//p[contains(@class,'message') and contains(@class,'error')]");

	
	public void setEmailForgotPassword(String email) {
		Utilities.clearAndType(this.txtEmail, email);
		Utilities.click(this.txtSendInStruction);
	}
	
	public void setPasswordChangeForm(String password, String confirmPassword) {
		Utilities.clearAndType(this.txtNewPasswordBy, password);
		Utilities.clearAndType(this.txtConfirmPassword, confirmPassword);
		Utilities.click(this.btnResetPassword);
	}
	
	public String getLbMessageChangePassword() {
		return Utilities.getText(this.lbMessageChangePassword);
	}
	
	public String getInputTokenPasswordString() {
		return Utilities.getAttribute(this.txtTokenResetPassword, "value");
	}
	
	public WebElement getInputTokenPasswordElement() {
		return Utilities.getElement(this.txtTokenResetPassword);
	}
	
	public String getErrorConfirmPassword() {
	    return Utilities.getText(lbConfirmPasswordError);
	}
	
	public String getResetPasswordFormError() {
	    return Utilities.getText(lbResetPasswordFormError);
	}
	
}
