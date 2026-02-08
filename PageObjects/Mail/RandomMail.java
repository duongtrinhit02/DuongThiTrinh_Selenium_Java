package Mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Common.WindowManager;
import Constant.Constant;

public class RandomMail {

    // ===== LOCATORS =====
    private final By lbIdEmailBy = By.xpath("//span[@id='inbox-id']");
    private final By txtNameEmailBy = By.xpath("//span[@id='inbox-id']/input");
    private final By btnSetNameEmailBy = By.xpath("//button[text()='Set']");
    private final By lbEmailBy = By.xpath("//span[@id='email-widget']");
    private final By checkAliasBy = By.xpath("//input[@id='use-alias']");
    private final By boxEmailConfirmAccountBy = By.xpath("//td[contains(text(), 'Please confirm your account')]");
    private final By linkActiveAccountBy = By.xpath("//div[@class='email_body']/a");
    private final By boxEmailResetPasswordBy = By.xpath("//td[contains(text(),'Please reset your password')]");
    private final By linkActiveResetPasswordBy = By.xpath("//div[@class='email_body']/a");
    private final By btnBackToInboxBy = By.xpath("//a[@id='back_to_inbox_link']");

    // ===== EMAIL SETUP =====
    public void setGuerrillaMail(String emailName) {
        Utilities.clickByJS(this.lbIdEmailBy);
        Utilities.clearAndType(this.txtNameEmailBy, emailName);
        Utilities.clickByJS(this.btnSetNameEmailBy);
    }

    // ===== GET EMAIL INFO =====
    public String getGmail() {
        Utilities.click(this.checkAliasBy);
        return Utilities.getText(this.lbEmailBy);
    }

    // ===== EMAIL ACTIONS =====
    public String getLinkActive() {
        Utilities.clickByJS(this.boxEmailConfirmAccountBy);
        return Utilities.getAttribute(this.linkActiveAccountBy, "href");
    }

    public String getLinkResetPassword() {
        Utilities.clickByJS(this.boxEmailResetPasswordBy);
        return Utilities.getAttribute(this.linkActiveResetPasswordBy, "href");
    }

    public WebElement getBackToInboxElement() {
        return Utilities.getElement(this.btnBackToInboxBy);
    }

    // ===== NAVIGATION =====
    public RandomMail open() {
        Constant.WEBDRIVER.navigate().to(Constant.RANDOMMAIL_URL);
        WindowManager.saveWindow(Constant.WINDOW_TAB_MAIL, Constant.WEBDRIVER);
        return this;
    }
}
