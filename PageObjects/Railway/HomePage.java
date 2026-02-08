package Railway;

import org.openqa.selenium.By;

import Common.Utilities;
import Common.WindowManager;
import Constant.Constant;
import Menu.MenuRailway;

public class HomePage extends GeneralPage {

    //Locators
    private final By lbTitle = By.xpath("//div[@id='content']/h1");
    private final By btnCreateAccountBy = By.xpath("//a[text()='create an account']");

    //Navigation
    public HomePage open() {
        WindowManager.saveWindow(Constant.WINDOW_TAB_RAILWAY, Constant.WEBDRIVER);
        Constant.WEBDRIVER.navigate().to(Constant.RAIWAY_URL);
        return this;
    }

    //Page content
    public String getTextTitle() {
        Utilities.waitForVisible(this.lbTitle);
        return Utilities.getElement(this.lbTitle).getAttribute("textContent");
    }

    //Actions
    public RegisterPage clickBtnCreateAccount() {
        Utilities.click(this.btnCreateAccountBy);
        return (RegisterPage) navigateMenu(MenuRailway.REGISTER);
    }
}
