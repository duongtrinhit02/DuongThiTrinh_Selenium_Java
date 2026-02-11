package Railway;

import org.openqa.selenium.By;

import Common.Utilities;
import Constant.Constant;
import Menu.MenuRailway;

public class GeneralPage {

    //Locators
    private final By tabLoginBy = By.xpath("//div[@id='menu']//a/span[text()='Login']");
    private final By tabLogOutBy = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    private final By tabWelcomMessage = By.xpath("//div[@class='account']/strong");
    private final String tabMenuString = "//div[@id='menu']//a[normalize-space()='%s']";
    private final By lbTitleBy = By.xpath("//h1[@align='center']");

    //Protected locators for child pages
    protected By getTabWelcomMessageBy() {
        return tabWelcomMessage;
    }

    protected By getTabLogOutBy() {
        return tabLogOutBy;
    }

    protected By getTabLoginBy() {
        return tabLoginBy;
    }

    //Login status
    public boolean isUserLoggedIn() {
        return Utilities.isElementDisplayed(tabLogOutBy);
    }

    public void logoutIfLoggedIn() {
        if (isUserLoggedIn()) {
            Utilities.waitForClickable(tabLogOutBy);
            Utilities.getElement(tabLogOutBy).click();
        }
    }

    //User info
    public String getWelcomMessage() {
        Utilities.waitForVisible(this.tabWelcomMessage);
        return Utilities.getElement(this.tabWelcomMessage).getAttribute("textContent");
    }

    //Menu navigation
    public GeneralPage navigateMenu(MenuRailway menu) {
        By menuBy = By.xpath(String.format(this.tabMenuString, menu.getText()));
        Utilities.waitForClickable(menuBy);
        Utilities.getElement(menuBy).click();
        return PageFactoryManager.getPage(menu.getPageClass());
    }

    //Page title
    public String getTitle() {
        return Utilities.getText(this.lbTitleBy);
    }
    
    public void openRailway() {
    	Utilities.getUrl(Constant.RAIWAY_URL);
    }
    
}
