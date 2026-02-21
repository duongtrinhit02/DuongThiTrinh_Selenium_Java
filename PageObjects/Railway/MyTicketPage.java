package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Account.Ticket;
import Common.Utilities;

public class MyTicketPage extends GeneralPage {

    /* ===================== LOCATORS ===================== */

    private final By tabMyTicket = By.xpath("//a[span[normalize-space()='My ticket']]");
    private final By btnCancelTicket = By.xpath("//tr[@class='OddRow']/descendant::input[@type='button']");


    /* ===================== PRIVATE METHODS ===================== */

    private By getTicketRowLocator(Ticket data) {

        String departDate = Utilities.getFutureDate(data.getDepartDate());

        String xpath = String.format(
                "//table[@class='MyTable']//tr" +
                        "[td[normalize-space()='%s']]" +
                        "[td[normalize-space()='%s']]" +
                        "[td[normalize-space()='%s']]" +
                        "[td[normalize-space()='%s']]" +
                        "[td[normalize-space()='%s']]",
                data.getDepartStation(),
                data.getArriveStation(),
                data.getSeatType(),
                departDate,
                String.valueOf(data.getAmount())
        );

        return By.xpath(xpath);
    }


    /* ===================== BUSINESS METHODS ===================== */

    public MyTicketPage clickTabMyTicket() {
        Utilities.click(tabMyTicket);
        return new MyTicketPage();
    }

    public void clickCancelButton() {
        WebElement cancelElement = Utilities.waitForElementVisible(btnCancelTicket);
        cancelElement.click();
    }

    public boolean isTicketDisplayed(Ticket data) {
        return Utilities.findElements(getTicketRowLocator(data)).size() > 0;
    }
}