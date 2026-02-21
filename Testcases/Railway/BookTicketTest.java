package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Account.Account;
import Common.AccountHelper;
import Common.Utilities;
import Common.WindowManager;
import Constant.Constant;
import Mail.GuerrillaMail;
import Account.Ticket;
//import Account.BookTicketData.SeatType;
//import Account.BookTicketData.Station;
import Menu.MenuRailway;
import Enums.Station;
import Enums.MenuItems;
import Enums.SeatType;

public class BookTicketTest extends BaseTest {

	@Test
	public void TC12() {
	    int plusDay = 2;
	    Ticket ticket = new Ticket(
	            plusDay,
	            Station.NHA_TRANG.setStation(),
	            Station.HUE.setStation(),
	            SeatType.SOFT_BED_AC.getSeatType(),
	            1
	    );
	    System.out.println("TC12-User can book 1 ticket at a time ");
		System.out.println("Pre-condition: an actived account is existing");
		System.out.println("Step 1: Navigate to QA Railway Website ");
	    Account account = AccountHelper.createAndActivateAccount();
	    HomePage homePage = new HomePage();
	    homePage.openRailway();
	    
	    System.out.println("Step 2: Login with a valid account  ");
	    LoginPage loginPage = (LoginPage) homePage.navigateMenu(MenuRailway.LOGIN);
	    loginPage.login(account);

		System.out.println("Step 3: Click on \"Book ticket\" tab ");
		System.out.println("Step 4: Select the next 2 days from \"Depart date\" ");
		System.out.println("Step 5: Select Depart from \"Nha Trang\" and Arrive at \"Huế\"");
		System.out.println("Step 6: Select \"Soft bed with air conditioner\" for \"Seat type\" ");
		System.out.println("Step 7: Select \"1\" for \"Ticket amount\"");
		System.out.println("Step 8: Click on \"Book ticket\" button");
	    BookTicketPage bookTicketPage = (BookTicketPage) homePage.navigateMenu(MenuRailway.BOOK_TICKET);
	    bookTicketPage.bookTicket(ticket);

	    System.out.println("VP: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
	    Assert.assertTrue(
	            bookTicketPage.isBookTicketSuccess(),
	            "Ticket booked successfully message is NOT displayed"
	    );

	    Assert.assertTrue(
	            bookTicketPage.isTicketRowDisplayed(ticket),
	            "Booked ticket information is incorrect"
	    );
	}


	@Test
	public void TC13() {
	    int plusDay = 25;
	    Ticket ticket = new Ticket(
	            plusDay,
	            Station.NHA_TRANG.setStation(),
	            Station.SAI_GON.setStation(),
	            SeatType.SOFT_SEAT_AC.getSeatType(),
	            5
	    );
	    System.out.println("TC13-User can book many tickets at a time ");
		System.out.println("Pre-condition: an actived account is existing");
		System.out.println("Step 1: Navigate to QA Railway Website ");
	    Account account = AccountHelper.createAndActivateAccount();
	    HomePage homePage = new HomePage();
	    homePage.openRailway();

	    System.out.println("Step 2: Login with a valid account  ");
	    LoginPage loginPage =
	            (LoginPage) homePage.navigateMenu(MenuRailway.LOGIN);
	    loginPage.login(account);

	    System.out.println("Step 3: Click on \"Book ticket\" tab ");
		System.out.println("Step 4: Select the next 25 days from \"Depart date\"");
		System.out.println("Step 5: Select \"Nha Trang\" for \"Depart from\" and \"Sài Gòn\" for \"Arrive at\"");
		System.out.println("Step 6: Select \"Soft seat with air conditioner\" for \"Seat type\"");
		System.out.println("Step 7: Select \"5\" for \"Ticket amount\"");
		System.out.println("Step 8: Click on \"Book ticket\" button");
	    BookTicketPage bookTicketPage = (BookTicketPage) homePage.navigateMenu(MenuRailway.BOOK_TICKET);
	    bookTicketPage.bookTicket(ticket);

	    System.out.println("VP: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
	    Assert.assertTrue(
	            bookTicketPage.isBookTicketSuccess(),
	            "Ticket booked successfully message is NOT displayed"
	    );

	    Assert.assertTrue(
	            bookTicketPage.isTicketRowDisplayed(ticket),
	            "Booked ticket information is incorrect"
	    );
	}



	@Test
	public void TC14() {
		System.out.println("TC14-User can check price of ticket from Timetable");
		System.out.println("Pre-condition: an actived account is existing");
		System.out.println("Step 1: Navigate to QA Railway Website ");	
	    Account account = AccountHelper.createAndActivateAccount();
	    HomePage homePage = new HomePage();
	    homePage.openRailway();
	    
	    System.out.println("Step 2: Login with a valid account");
	    LoginPage loginPage =
	            (LoginPage) homePage.navigateMenu(MenuRailway.LOGIN);
	    loginPage.login(account);
	    
	    System.out.println("Step 3: Click on \"Timetable\" tab");
	    TimetablePage timetablePage =
	            (TimetablePage) homePage.navigateMenu(MenuRailway.TIMETABLE);

	    System.out.println("Step 4: Click on \"check price\" link of the route from \"Đà Nẵng\" to \"Sài Gòn\"");
	    TicketPricePage ticketPricePage =
	            timetablePage.clickCheckPrice(
	                    Station.DA_NANG.setStation(),
	                    Station.SAI_GON.setStation()
	            );

	    System.out.println("VP:\"Ticket Price\" page is loaded.\r\n"
	    		+ "Ticket table shows \"Ticket price from Đà Nẵng to Sài Gòn\".\r\n"
	    		+ "Price for each seat displays correctly\r\n"
	    		+ "HS = 310000, SS = 335000, SSC = 360000, HB = 410000, SB = 460000, SBC = 510000");
	    Assert.assertTrue(
	            ticketPricePage.verifyRouteTitle(
	                    Station.DA_NANG.setStation(),
	                    Station.SAI_GON.setStation()
	            ),
	            "Route title is incorrect"
	    );
	    Assert.assertTrue(
	            ticketPricePage.verifyAllSeatPrices(),
	            "Seat prices are incorrect"
	    );
	}


	@Test
	public void TC15() {		
	    Ticket ticket = new Ticket(
	            0,
	            Station.QUANG_NGAI.setStation(),
	            Station.HUE.setStation(),
	            SeatType.SOFT_SEAT_AC.getSeatType(),
	            5
	    );
	    System.out.println("TC15-User can book ticket from Timetable");
		System.out.println("Pre-condition: an actived account is existing");
		System.out.println("Step 1: Navigate to QA Railway Website ");
		
	    Account account = AccountHelper.createAndActivateAccount();
	    HomePage homePage = new HomePage();
	    homePage.openRailway();
	    
	    System.out.println("Step 2: Login with a valid account");
	    LoginPage loginPage =
	            (LoginPage) homePage.navigateMenu(MenuRailway.LOGIN);
	    loginPage.login(account);
	    
	    System.out.println("Step 3: Click on \"Timetable\" tab");
	    TimetablePage timetablePage =
	            (TimetablePage) homePage.navigateMenu(MenuRailway.TIMETABLE);
	    
	    System.out.println("Step 4: Click on book ticket of route \"Quảng Ngãi\" to \"Huế\"");
	    System.out.println("Step 5: Select Depart date = tomorrow");
		System.out.println("Step 6: Select Ticket amount = 5");
		System.out.println("Step 7: Click on \"Book ticket\" button");
		BookTicketPage bookTicketPage =
		        timetablePage.clickBookTicket(
		                ticket.getDepartStation(),
		                ticket.getArriveStation()
		        );  
	    bookTicketPage.selectTomorrowDate();
	    bookTicketPage.bookTicket(ticket);
	    
	    System.out.println("VP: Book ticket form is shown with the corrected \"depart from\" and \"Arrive at\"");
	    Assert.assertTrue(
	            bookTicketPage.isBookTicketSuccess(),
	            "Ticket booked successfully message is NOT displayed"
	    );
	    
	    System.out.println("VP: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
	    Assert.assertTrue(
	            bookTicketPage.isTicketRowDisplayed(ticket),
	            "Booked ticket information is incorrect"
	    );
	}

	
	@Test
	public void TC16() {

	    int plusDay = 2;
	    Ticket ticket = new Ticket(
	            plusDay,
	            Station.NHA_TRANG.setStation(),
	            Station.SAI_GON.setStation(),
	            SeatType.SOFT_SEAT_AC.getSeatType(),
	            5
	    );
	    System.out.println("TC16-User can cancel a ticket");
		System.out.println("Pre-condition: an actived account is existing");
		System.out.println("Step 1: Navigate to QA Railway Website ");
	    Account account = AccountHelper.createAndActivateAccount();
	    HomePage homePage = new HomePage();
	    homePage.openRailway();

		System.out.println("Step 2: Login with a valid account  ");
	    LoginPage loginPage = 
	        (LoginPage) homePage.navigateMenu(MenuRailway.LOGIN);
	    loginPage.login(account);
	    
	    System.out.println("Step 3: Book a ticke");
	    BookTicketPage bookTicketPage =
	        (BookTicketPage) homePage.navigateMenu(MenuRailway.BOOK_TICKET);
	    bookTicketPage.bookTicket(ticket);
	    
	    System.out.println("Step 4:Click on \"My ticket\" tab");
	    MyTicketPage myTicketPage =
	        (MyTicketPage) homePage.navigateMenu(MenuRailway.MYTICKET);
	    
	    System.out.println("Step 5:  Click on \"Cancel\" button of ticket which user want to cancel.");
	    myTicketPage.clickCancelButton();
	    
	    System.out.println("Step 6: Click on \"OK\" button on Confirmation message \"Are you sure?\"");
	    Constant.WEBDRIVER.switchTo().alert().accept();
	    
	    System.out.println("Step VP: The canceled ticket is disappeared.");
	    Assert.assertFalse(
	            myTicketPage.isTicketDisplayed(ticket),
	            "Ticket was not cancelled"
	    );
	    
	}
}

