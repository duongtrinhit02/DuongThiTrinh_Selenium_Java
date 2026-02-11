package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Account.Account;
import Common.AccountHelper;
import Common.Utilities;
import Common.WindowManager;
import Constant.Constant;
import Mail.RandomMail;
import Account.BookTicketData;
import Account.BookTicketData.SeatType;
import Account.BookTicketData.Station;
import Menu.MenuRailway;

public class BookTicketTest extends BaseTest {
	@Test
	public void TC12() {

		 System.out.println("TC12 - User can book 1 ticket at a time");
		 System.out.println("Pre-condition: an activated account is existing");
		 Account account = AccountHelper.createAndActivateAccount();
		 
		 System.out.println("Step 1: Navigate to QA Railway Website");
		 HomePage homePage = new HomePage();
		 homePage.openRailway();
		 
		 System.out.println("Step 2: Login with a valid account");
		    LoginPage loginPage = homePage.goToLoginPage(); 
		    loginPage.login(account);
		    
		System.out.println("Step 3: Click on 'Book ticket' tab");
	    BookTicketPage bookTicketPage =
	            (BookTicketPage) homePage.navigateMenu(MenuRailway.BOOK_TICKET);
	    
	    System.out.println("Step 4: Select the next 2 days from 'Depart date'");
	    System.out.println("Step 5: Select Depart from 'Nha Trang' and Arrive at 'Huế'");
	    System.out.println("Step 6: Select 'Soft bed with air conditioner' for Seat type");
	    System.out.println("Step 7: Select '1' for Ticket amount");
	    System.out.println("Step 8: Click on 'Book ticket' button");
	    BookTicketData data = new BookTicketData();
	    data.setDepartFrom(BookTicketData.Station.NHA_TRANG);
	    data.setArriveAt(BookTicketData.Station.HUE);
	    data.setSeatType(BookTicketData.SeatType.SOFT_BED_AC);
	    data.setTicketAmount(BookTicketData.TicketAmount.ONE);
	    String departDate = bookTicketPage.bookTicket(data, 2);
	    data.setDepartDate(departDate); 
	  
	    System.out.println("VP: Ticket booked successfully");
	    Assert.assertTrue(
	            bookTicketPage.isBookTicketSuccess(),
	            "Ticket booked successfully message is NOT displayed"
	    );

	    System.out.println("VP: Ticket information displays correctly");
	    Assert.assertTrue(
	            bookTicketPage.verifyBookedTicketInfo(data),
	            "Booked ticket information is incorrect"
	    );

	}

	@Test
	public void TC13() {
		System.out.println("TC13 - User can book ticket with valid information");
		System.out.println("Pre-condition: an activated account is existing");
		Account account = AccountHelper.createAndActivateAccount();
		
		System.out.println("Step 1: Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
	    homePage.open();
	    
	    System.out.println("Step 2: Login with a valid account");
	    LoginPage loginPage =
	        (LoginPage) homePage.navigateMenu(MenuRailway.LOGIN);
	    loginPage.login(account);
	    
	    System.out.println("Step 3: Click on 'Book ticket' tab");
	    BookTicketPage bookTicketPage =
	        (BookTicketPage) homePage.navigateMenu(MenuRailway.BOOK_TICKET);
	    System.out.println("Step 4: Select the next 25 days from 'Depart date'");
	    System.out.println("Step 5: Select 'Nha Trang' for Depart from and 'Sài Gòn' for Arrive at");
	    System.out.println("Step 6: Select 'Soft seat with air conditioner' for Seat type");
	    System.out.println("Step 7: Select '5' for Ticket amount");
	    System.out.println("Step 8: Click on 'Book ticket' button");
	    BookTicketData data = new BookTicketData();
	    String departDate = bookTicketPage.bookTicket(data, 25);
	    data.setDepartDate(departDate);
	    data.setDepartFrom(BookTicketData.Station.NHA_TRANG);
	    data.setArriveAt(BookTicketData.Station.SAI_GON);
	    data.setSeatType(BookTicketData.SeatType.SOFT_SEAT_AC);
	    data.setTicketAmount(BookTicketData.TicketAmount.FIVE);

	    System.out.println("VP: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
	    Assert.assertTrue(
	        bookTicketPage.isBookTicketSuccess(),
	        "TC13 failed: Ticket booked successfully page not displayed"
	    );
	    Assert.assertTrue(
	        bookTicketPage.verifyBookedTicketInfo(data),
	        "TC13 failed: Booked ticket information is incorrect"
	    );
	}
	
	@Test
	public void TC14() {

	    System.out.println("TC14 - User can check price from Timetable");
		 System.out.println("Pre-condition: an activated account is existing");
		 Account account = AccountHelper.createAndActivateAccount();
		 
		 System.out.println("Step 1: Navigate to QA Railway Website");
		 HomePage homePage = new HomePage();
		 homePage.openRailway();
		 
		 System.out.println("Step 2: Login with a valid account");
		 LoginPage loginPage = homePage.goToLoginPage();  
		 loginPage.login(account);

	    System.out.println("Step 3: Navigate to Timetable page");
	    TimetablePage timetablePage =
	            (TimetablePage) homePage.navigateMenu(MenuRailway.TIMETABLE);
	    System.out.println("Step 4: Click 'Check Price' for route Đà Nẵng → Sài Gòn");
	    TicketPricePage ticketPricePage =
	            timetablePage.clickCheckPrice(
	                    Station.DA_NANG,
	                    Station.SAI_GON
	            );
	    System.out.println("Verify:"
	    		+ "Ticket Price\" page is loaded.\r\n"
	    		+ "Ticket table shows \"Ticket price from Đà Nẵng to Sài Gòn\".\r\n"
	    		+ "Price for each seat displays correctly\r\n"
	    		+ "HS = 310000, SS = 335000, SSC = 360000, HB = 410000, SB = 460000, SBC = 510000");	
	    Assert.assertTrue(
	            ticketPricePage.verifyRouteTitle(
	                    Station.DA_NANG.getValue(),
	                    Station.SAI_GON.getValue()
	            ),
	            "VP: Ticket table shows \"Ticket price from Đà Nẵng to Sài Gòn\""
	    );
	        
	    Assert.assertEquals( ticketPricePage.verifyAllSeatPrices(),
	    	    true,"VP: Price for each seat displays correctly\r\n"
	    	    + "HS = 310000, SS = 335000, SSC = 360000, HB = 410000, SB = 460000, SBC = 510000");


	}
	
	@Test
	public void TC15() {

		System.out.println("TC15 - User can book 1 ticket at a time");
		 System.out.println("Pre-condition: an activated account is existing");
		 Account account = AccountHelper.createAndActivateAccount();
		 
		 System.out.println("Step 1: Navigate to QA Railway Website");
		 HomePage homePage = new HomePage();
		 homePage.openRailway();
		 
		 System.out.println("Step 2: Login with a valid account");
		    LoginPage loginPage = homePage.goToLoginPage(); 
		    loginPage.login(account);

	    System.out.println("Step 3: Click on 'Timetable' tab");
	    TimetablePage timetablePage =
	            (TimetablePage) homePage.navigateMenu(MenuRailway.TIMETABLE);

	    System.out.println("Step 4: Click on book ticket of route \"Quảng Ngãi\" to \"Huế\"");
	    BookTicketPage bookTicketPage =
	            timetablePage.clickBookTicket(
	                    BookTicketData.Station.QUANG_NGAI,
	                    BookTicketData.Station.HUE
	            );
	    
	    
	    System.out.println("Step 5. Select Depart date = tomorrow");
	    System.out.println("Step 6. Select Ticket amount = 5");
	    System.out.println("Step 7: Click Book ticket");
	    BookTicketData data = new BookTicketData();
	    String departDate = bookTicketPage.selectTomorrowDepartDate();
	    data.setDepartDate(departDate);
	    data.setDepartFrom(BookTicketData.Station.QUANG_NGAI);
	    data.setArriveAt(BookTicketData.Station.HUE);
	    data.setSeatType(BookTicketData.SeatType.SOFT_SEAT_AC); // mặc định route này
	    data.setTicketAmount(BookTicketData.TicketAmount.FIVE);
	    
	    Assert.assertEquals(
	            bookTicketPage.getSelectedDepartFrom(),
	            BookTicketData.Station.QUANG_NGAI.getValue(),
	            "Depart From is incorrect"
	    );

	    Assert.assertEquals(
	            bookTicketPage.getSelectedArriveAt(),
	            BookTicketData.Station.HUE.getValue(),
	            "Arrive At is incorrect"
	    );


	    System.out.println("VP: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
	    Assert.assertTrue(
	        bookTicketPage.isBookTicketSuccess(),
	        "TC15 failed: Ticket booked successfully page not displayed"
	    );
	    Assert.assertTrue(
	        bookTicketPage.verifyBookedTicketInfo(data),
	        "TC15 failed: Booked ticket information is incorrect"
	    );

	}
	

	@Test
	public void TC16() {
		System.out.println("TC16 - User can cancel a ticket");
		System.out.println("Pre-condition: an activated account is existing");
		 Account account = AccountHelper.createAndActivateAccount();
		 
		 System.out.println("Step 1: Navigate to QA Railway Website");
		 HomePage homePage = new HomePage();
		 homePage.openRailway();
		 
		 System.out.println("Step 2: Login with a valid account");
		    LoginPage loginPage = homePage.goToLoginPage();
		    loginPage.login(account);
		    
	    System.out.println("Step 3: Book a ticket");
	    BookTicketPage bookTicketPage =
	        (BookTicketPage) homePage.navigateMenu(MenuRailway.BOOK_TICKET);
	    BookTicketData data = new BookTicketData();
	    data.setDepartFrom(BookTicketData.Station.NHA_TRANG);
	    data.setArriveAt(BookTicketData.Station.SAI_GON);
	    data.setSeatType(BookTicketData.SeatType.SOFT_SEAT_AC);
	    data.setTicketAmount(BookTicketData.TicketAmount.FIVE);
	    String departDate = bookTicketPage.bookTicket(data, 5);
	    data.setDepartDate(departDate);
	    Assert.assertTrue(
		        bookTicketPage.isBookTicketSuccess(),
		        "TC13 failed: Ticket booked successfully page not displayed"
		    );
	    
	    
	    System.out.println("Step 4: Click on 'My ticket' tab");
	    MyTicketPage myTicketPage =
	    	    (MyTicketPage) bookTicketPage.navigateMenu(MenuRailway.MYTICKET);


	    System.out.println("Step 5: Click on \"Cancel\" button of ticket which user want to cancel.");
	    myTicketPage.clickCancelButton(data);

	    System.out.println("Step 6: Click on \"OK\" button on Confirmation message \"Are you sure?\"");
	    Constant.WEBDRIVER.switchTo().alert().accept();

	    System.out.println("VP: The canceled ticket is disappeared.");
	    Assert.assertFalse(
	            myTicketPage.isTicketDisplayed(data),
	            "The canceled ticket is still displayed"
	    );

	   
	}

	
	

}
