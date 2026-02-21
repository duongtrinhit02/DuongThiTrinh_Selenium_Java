package Railway;

import org.openqa.selenium.By;
import Common.Utilities;

public class TicketPricePage extends GeneralPage {

    private final By lblTableTitle =
            By.xpath("//table[@class='MyTable MedTable']//tr[@class='TableSmallHeader']//th");

    private static final String PRICE_BY_SEAT_XPATH =
            "(//tr[td[normalize-space()='%s']]//following-sibling::tr//td)" +
            "[count(//tr[td[normalize-space()='%s']]//td[normalize-space()='%s']//preceding-sibling::td) + 1]";

    private String getPriceXpath(String seatCode) {
        return String.format(PRICE_BY_SEAT_XPATH, seatCode, seatCode, seatCode);
    }

    public String getTableName() {
        return Utilities.getText(lblTableTitle).trim();
    }

    public String getTicketPrice(SeatCoat seatCode) {
        return Utilities
                .getText(By.xpath(getPriceXpath(seatCode.getValue())))
                .trim();
    }

    public boolean verifyRouteTitle(String depart, String arrive) {

        String actualTitle = getTableName();

        System.out.println("Actual Title: " + actualTitle);

        return actualTitle.contains(depart)
                && actualTitle.contains(arrive);
    }

    public boolean verifySeatPrice(SeatCoat seatCode, String expectedPrice) {

        String actualPrice = getTicketPrice(seatCode);

        System.out.println("Seat: " + seatCode.getValue()
                + " | Expected: " + expectedPrice
                + " | Actual: " + actualPrice);

        return actualPrice.equals(expectedPrice);
    }

    public boolean verifyAllSeatPrices() {
        return verifySeatPrice(SeatCoat.HS, "310000")
            && verifySeatPrice(SeatCoat.SS, "335000")
            && verifySeatPrice(SeatCoat.SSC, "360000")
            && verifySeatPrice(SeatCoat.HB, "410000")
            && verifySeatPrice(SeatCoat.SB, "460000")
            && verifySeatPrice(SeatCoat.SBC, "510000");
    }
}