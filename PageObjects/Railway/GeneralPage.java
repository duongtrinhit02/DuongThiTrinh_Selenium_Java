package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class GeneralPage {

	private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
	private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
	private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");
	private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
	
	protected WebElement getTabLogin() {
		return Constant.WEBDRIVER.findElement(tabLogin);
	}
	
	protected WebElement getTabLogout() {
		return Constant.WEBDRIVER.findElement(tabLogout);
	}

	protected WebElement getLbWelcomeMessage() {
		return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
	}
	protected WebElement getLblErrorMessage() {
		return Constant.WEBDRIVER.findElement(lblLoginErrorMsg);
	}
	
	
	public String getWelcomeMessage()
	{
		return this.getLbWelcomeMessage().getText();
		
	}
	
	public String getErrorMessage() {
		return this.getLblErrorMessage().getText();
	}
	
	public LoginPage gotoLoginPage()
	{
		this.getTabLogin().click();
		return new LoginPage();
	}
}
