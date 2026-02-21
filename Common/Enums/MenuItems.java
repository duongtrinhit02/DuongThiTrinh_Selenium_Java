package Enums;

public enum MenuItems {

	HOME("Home"), FAQ("FAQ"), CONTACT("Contact"), TIMETABLE("Timetable"), TICKET_PRICE("Ticket price"),
	BOOK_TICKET("Book ticket"), MY_TICKET("My ticket"), CHANGE_PASSWORD("Change password"), REGISTER("Register"),
	LOGIN("Login"), LOGOUT("Log out");

	private final String menu;

	MenuItems(String menu) {
		this.menu = menu;
	}

	public String getTab() {
		return menu;
	}

}