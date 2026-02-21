package Enums;

public enum TicketHeader {
	DEPART_STATION("Depart Station"),
	ARRIVE_STATION("Arrive Station"),
	SEAT_TYPE("Seat Type"),
	AMOUNT("Amount"),
	OPERATION("Operation");

	private final String headerText;

	TicketHeader(String headerText) {
		this.headerText = headerText;
	}

	public String getHeaderText() {
		return headerText;
	}

}