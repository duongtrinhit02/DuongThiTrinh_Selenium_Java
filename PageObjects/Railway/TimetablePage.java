package Railway;

import org.openqa.selenium.By;
import Common.Utilities;

public class TimetablePage extends GeneralPage {

    /* ===================== LOCATOR TEMPLATES ===================== */

    private static final String CHECK_PRICE_XPATH =
            "//tr[td[normalize-space()='%s'] and td[normalize-space()='%s']]//a[normalize-space()='check price']";

    private static final String BOOK_TICKET_XPATH =
            "//tr[td[normalize-space()='%s'] and td[normalize-space()='%s']]//a[normalize-space()='book ticket']";


    /* ===================== PRIVATE METHODS ===================== */

    private By getCheckPriceLocator(String depart, String arrive) {
        return By.xpath(String.format(CHECK_PRICE_XPATH, depart, arrive));
    }

    private By getBookTicketLocator(String depart, String arrive) {
        return By.xpath(String.format(BOOK_TICKET_XPATH, depart, arrive));
    }


    /* ===================== BUSINESS METHODS ===================== */

    public TicketPricePage clickCheckPrice(String depart, String arrive) {
        Utilities.click(getCheckPriceLocator(depart, arrive));
        return new TicketPricePage();
    }

    public BookTicketPage clickBookTicket(String depart, String arrive) {
        Utilities.click(getBookTicketLocator(depart, arrive));
        return new BookTicketPage();
    }
}