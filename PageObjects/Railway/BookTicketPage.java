package Railway;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Account.Ticket;
import Common.Utilities;
import Constant.Constant;

public class BookTicketPage extends GeneralPage {

    /* ===================== LOCATORS ===================== */

    private final By ddlDepartFrom   = By.name("DepartStation");
    private final By ddlArriveAt     = By.name("ArriveStation");
    private final By ddlSeatType     = By.name("SeatType");
    private final By ddlDepartDate   = By.name("Date");
    private final By ddlTicketAmount = By.name("TicketAmount");
    private final By btnBookTicket   = By.cssSelector("input[value='Book ticket']");
    private final By lblSuccessMsg   = By.xpath("//h1[contains(text(),'Ticket booked successfully')]");

    private final WebDriverWait wait =
            new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("M/d/yyyy");

    private String bookedDate;


    /* ===================== PRIVATE METHODS ===================== */

    private String calculateFromDropdown(int plusDays) {

        Select select = new Select(
                Constant.WEBDRIVER.findElement(ddlDepartDate));

        String defaultDate =
                select.getFirstSelectedOption().getText().trim();

        LocalDate date =
                LocalDate.parse(defaultDate, DATE_FORMAT);

        return date.plusDays(plusDays).format(DATE_FORMAT);
    }


    public void selectTomorrowDate() {

        bookedDate = LocalDate.now()
                .plusDays(1)
                .format(DATE_FORMAT);

        Utilities.selectDropdownByVisibleText(
                ddlDepartDate, bookedDate);
    }

    private void selectDepartStation(String station) {

        WebElement oldArriveDropdown =
                Constant.WEBDRIVER.findElement(ddlArriveAt);

        Select depart =
                new Select(Constant.WEBDRIVER.findElement(ddlDepartFrom));
        depart.selectByVisibleText(station);

        wait.until(ExpectedConditions.stalenessOf(oldArriveDropdown));
        wait.until(ExpectedConditions.presenceOfElementLocated(ddlArriveAt));
    }

    private void selectArriveStation(String station) {

        wait.until(driver -> {
            Select select =
                    new Select(driver.findElement(ddlArriveAt));
            return select.getOptions()
                    .stream()
                    .anyMatch(opt ->
                            opt.getText().trim().equals(station));
        });

        Select arrive =
                new Select(Constant.WEBDRIVER.findElement(ddlArriveAt));
        arrive.selectByVisibleText(station);

        String selected =
                arrive.getFirstSelectedOption().getText().trim();

        if (!selected.equals(station)) {
            throw new RuntimeException(
                    "Arrive station was NOT selected correctly!");
        }
    }


    /* ===================== BUSINESS METHODS ===================== */

    public void bookTicket(Ticket data) {

        if (bookedDate == null) {

            bookedDate = calculateFromDropdown(data.getDepartDate());

            Utilities.selectDropdownByVisibleText(
                    ddlDepartDate, bookedDate);
        }

        selectDepartStation(data.getDepartStation());
        selectArriveStation(data.getArriveStation());

        Utilities.selectDropdownByVisibleText(
                ddlSeatType, data.getSeatType());

        Utilities.selectDropdownByVisibleText(
                ddlTicketAmount,
                String.valueOf(data.getAmount()));

        Utilities.click(btnBookTicket);
    }

    public boolean isBookTicketSuccess() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        lblSuccessMsg))
                .isDisplayed();
    }

    public boolean isTicketRowDisplayed(Ticket data) {

        System.out.println("=========== VERIFY BOOKED TICKET ===========");

        System.out.println("EXPECTED:");
        System.out.println("Depart Station : " + data.getDepartStation());
        System.out.println("Arrive Station : " + data.getArriveStation());
        System.out.println("Seat Type      : " + data.getSeatType());
        System.out.println("Depart Date    : " + bookedDate);
        System.out.println("Amount         : " + data.getAmount());
        System.out.println("============================================");

        List<WebElement> rows =
                Constant.WEBDRIVER.findElements(
                        By.xpath("//table[@class='MyTable WideTable']//tr[td]")
                );

        int index = 1;

        for (WebElement row : rows) {

            String rowText = row.getText();

            System.out.println("----- ROW " + index + " -----");
            System.out.println(rowText);
            System.out.println("-----------------------------");

            boolean match =
                    rowText.contains(data.getDepartStation()) &&
                    rowText.contains(data.getArriveStation()) &&
                    rowText.contains(data.getSeatType()) &&
                    rowText.contains(bookedDate) &&
                    rowText.contains(String.valueOf(data.getAmount()));

            if (match) {
                System.out.println("MATCH FOUND at row " + index);
                return true;
            }

            index++;
        }

        System.out.println("NO MATCH FOUND");

        return false;
    }
}