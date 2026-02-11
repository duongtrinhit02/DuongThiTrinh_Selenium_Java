package Railway;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import Account.BookTicketData;
import Common.Utilities;
import Constant.Constant;

public class BookTicketPage extends GeneralPage {

    private By ddlDepartFrom   = By.name("DepartStation");
    private By ddlArriveAt     = By.name("ArriveStation");
    private By ddlSeatType     = By.name("SeatType");
    private By ddlDepartDate   = By.name("Date");
    private By ddlTicketAmount = By.name("TicketAmount");
    private By btnBookTicket   = By.cssSelector("input[value='Book ticket']");
    private By lblSuccessMsg   = By.xpath("//h1[contains(text(),'Ticket booked successfully')]");

    private Select getDateSelect()
    {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.DEFAULT_TIMEOUT));
        wait.until(driver -> {
            Select s = new Select(driver.findElement(ddlDepartDate));
            return s.getOptions().size() > 1;
        });

        return new Select(Utilities.getElement(ddlDepartDate));
    }

    private String getDepartDatePlusDays(int plusDays)
    {
        Select select = getDateSelect();
        List<WebElement> options = select.getOptions();
        int currentIndex = select.getOptions()
                                 .indexOf(select.getFirstSelectedOption());
        int targetIndex = currentIndex + plusDays;
        if (targetIndex >= options.size()) {
            throw new RuntimeException("Not enough future dates");
        }
        return options.get(targetIndex).getText().trim();
    }

    public String selectTomorrowDepartDate() {

        String tomorrow = Utilities.getTomorrowDate("M/d/yyyy");
        Utilities.selectDropdownByVisibleText( ddlDepartDate, tomorrow ); 
        return tomorrow;
    }

    public String bookTicket(BookTicketData data, int plusDays) 
    {

        // Select Depart
        Utilities.selectDropdownByVisibleText(ddlDepartFrom,data.getDepartFrom().getValue());
        // Wait Arrive refresh
        Utilities.waitUntilDropdownHasOption( ddlArriveAt,data.getArriveAt().getValue());
        // Select Arrive
        Utilities.selectDropdownByVisibleText(ddlArriveAt, data.getArriveAt().getValue());
        // Select Date
        String departDate = getDepartDatePlusDays(plusDays);
        Utilities.selectDropdownByVisibleText(ddlDepartDate, departDate);
        data.setDepartDate(departDate);
        // Select Seat
        Utilities.selectDropdownByVisibleText(ddlSeatType,data.getSeatType().getValue());
        // Select Amount
        Utilities.selectDropdownByVisibleText(ddlTicketAmount,data.getTicketAmount().getValue());
        Utilities.click(btnBookTicket);

        return departDate;
    }
    
    public String getSelectedDepartFrom()
    {
        return new Select(Utilities.getElement(ddlDepartFrom))
                .getFirstSelectedOption()
                .getText();
    }

    public String getSelectedArriveAt()
    {
        return new Select(Utilities.getElement(ddlArriveAt))
                .getFirstSelectedOption()
                .getText();
    }


    public boolean isBookTicketSuccess()
    {
        return Utilities.waitForVisible(lblSuccessMsg).isDisplayed();
    }

    public boolean verifyBookedTicketInfo(BookTicketData data)
    {

        String xpath = String.format(
                "//table[@class='MyTable WideTable']//tr" +
                "[td[normalize-space()='%s']]" +
                "[td[normalize-space()='%s']]" +
                "[td[normalize-space()='%s']]" +
                "[td[normalize-space()='%s']]" +
                "[td[normalize-space()='%s']]",
                data.getArriveAt().getValue(),
                data.getSeatType().getValue(),
                data.getDepartDate(),
                data.getTicketAmount().getValue()
        );

        return Constant.WEBDRIVER.findElements(By.xpath(xpath)).size() > 0;
    }
}
