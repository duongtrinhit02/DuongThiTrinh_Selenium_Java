package Railway;

import org.openqa.selenium.By;
import Account.BookTicketData.Station;
import Common.Utilities;

public class TimetablePage extends GeneralPage {

    private By getCheckPriceLocator(Station depart, Station arrive) 
    {
        String xpath = String.format(
            "//tr[td[normalize-space()='%s'] and td[normalize-space()='%s']]//a[normalize-space()='check price']",
            depart.getValue(),
            arrive.getValue()
        );

        return By.xpath(xpath);
    }

    private By getBookTicketLocator(Station depart, Station arrive) 
    {

        String xpath = String.format(
            "//tr[td[normalize-space()='%s'] and td[normalize-space()='%s']]//a[normalize-space()='book ticket']",
            depart.getValue(),
            arrive.getValue()
        );

        return By.xpath(xpath);
    }

    public TicketPricePage clickCheckPrice(Station depart, Station arrive) {
        Utilities.click(getCheckPriceLocator(depart, arrive));
        return new TicketPricePage();
    }

    public BookTicketPage clickBookTicket(Station depart, Station arrive) {
        Utilities.click(getBookTicketLocator(depart, arrive));
        return new BookTicketPage();
    }
}
