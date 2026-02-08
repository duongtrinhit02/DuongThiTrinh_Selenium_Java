package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;

public class ChangePasswordPage extends GeneralPage{
	private final By txtEmailBy = By.xpath("//input[@id='email']");
	private final By txtSendInStructionBy = By.xpath("//input[@type='submit']");
	
	private final By txtNewPasswordBy = By.xpath("//input[@id='newPassword']");
	private final By txtConfirmPasswordBy = By.xpath("//input[@id='confirmPassword']");
	private final By btnResetPasswordBy = By.xpath("//input[@type='submit' and @title='Reset password']");
	
	private final By lbMessageChangePasswordBy = By.xpath("//P[@class='message success']");
	private final By txtTokenResetPasswordBy = By.xpath("//input[@id='resetToken']");
	
	public void setEmailForgotPassword(String email) {
		Utilities.clearAndType(this.txtEmailBy, email);
		Utilities.click(this.txtSendInStructionBy);
	}
	
	public void setPasswordChangeForm(String password, String confirmPassword) {
		Utilities.clearAndType(this.txtNewPasswordBy, password);
		Utilities.clearAndType(this.txtConfirmPasswordBy, confirmPassword);
		Utilities.click(this.btnResetPasswordBy);
	}
	
	public String getLbMessageChangePassword() {
		return Utilities.getText(this.lbMessageChangePasswordBy);
	}
	
	public String getInputTokenPasswordString() {
		return Utilities.getAttribute(this.txtTokenResetPasswordBy, "value");
	}
	
	public WebElement getInputTokenPasswordElement() {
		return Utilities.getElement(this.txtTokenResetPasswordBy);
	}
}
