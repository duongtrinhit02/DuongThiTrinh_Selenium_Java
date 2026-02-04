package Railway;

import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends GeneralPage {
	
	private final By _txtUsername = By.xpath("//input[@id='username']");
	private final By _txtPassword = By.xpath("//input[@id='password']");
	private final By _btnLogin = By.xpath("//input[@value='login']");
	
	
	public WebElement getTxtUsername()
	{
		return Constant.WEBDRIVER.findElement(_txtUsername);
	}
	
	public WebElement getTxtPassword()
	{
		return Constant.WEBDRIVER.findElement(_txtPassword);
	}
	
	public WebElement getBtnLogin()
	{
		return Constant.WEBDRIVER.findElement(_btnLogin);
	}

	public void loginFailManyTime(int number)
	{
		  for (int i = 0; i < number; i++) {
		        login(Constant.USERNAME, "123");
		        System.out.println("Login attempt: " + i);
		        Constant.WEBDRIVER.navigate().refresh();
		    }
	    
	}
	
	public HomePage login(String username, String password)
	{
		this.getTxtUsername().clear();
		this.getTxtUsername().sendKeys(username);
	    this.getTxtPassword().clear();
		this.getTxtPassword().sendKeys(password);
		this.getBtnLogin().click();
		
		return new HomePage();
	}
	

}
