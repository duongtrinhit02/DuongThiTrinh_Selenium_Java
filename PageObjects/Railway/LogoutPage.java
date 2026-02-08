package Railway;

import org.openqa.selenium.By;

import Common.Utilities;

public class LogoutPage extends GeneralPage{
	private By btnLogoutBy = By.xpath("//a[@href='/Account/Logout']");
	
	public HomePage logout() {
		Utilities.getElement(this.btnLogoutBy).click();
		return new HomePage();
	}

}
