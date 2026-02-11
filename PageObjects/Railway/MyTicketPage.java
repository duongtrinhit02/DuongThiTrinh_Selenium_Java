package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Account.BookTicketData;

public class MyTicketPage extends GeneralPage {

    private By tabMyTicket = By.xpath("//a[span[normalize-space()='My ticket']]");

    public MyTicketPage clickTabMyTicket() 
    {
        Utilities.click(tabMyTicket);
        return new MyTicketPage();
    }

    private By getTicketRowLocator(BookTicketData data) 
    {
        String xpath = String.format(
                "//table[@class='MyTable']//tr" +
                "[td[normalize-space()='%s']]" +
                "[td[normalize-space()='%s']]" +
                "[td[normalize-space()='%s']]" +
                "[td[normalize-space()='%s']]" +
                "[td[normalize-space()='%s']]",
                data.getDepartFrom().getValue(),
                data.getArriveAt().getValue(),
                data.getSeatType().getValue(),
                data.getDepartDate(),
                data.getTicketAmount().getValue()
        );

        return By.xpath(xpath);
    }

    public void clickCancelButton(BookTicketData data)
    {
        WebElement row = Utilities.getElement(getTicketRowLocator(data));
        row.findElement(By.xpath(".//input[@value='Cancel']")).click();
    }

    public boolean isTicketDisplayed(BookTicketData data) 
    {

        String xpath = String.format(
                "//table[@class='MyTable']//tr" +
                "[td[normalize-space()='%s']]" +
                "[td[normalize-space()='%s']]" +
                "[td[normalize-space()='%s']]" +
                "[td[normalize-space()='%s']]" +
                "[td[normalize-space()='%s']]",
                data.getDepartFrom().getValue(),
                data.getArriveAt().getValue(),
                data.getSeatType().getValue(),
                data.getDepartDate(),
                data.getTicketAmount().getValue()
        );

        return Utilities.findElements(By.xpath(xpath)).size() > 0;
    }
    
}
