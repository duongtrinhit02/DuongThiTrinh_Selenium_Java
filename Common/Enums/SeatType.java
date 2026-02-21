package Enums;

public enum SeatType {
	SOFT_BED_AC("Soft bed with air conditioner"),
	SOFT_SEAT_AC("Soft seat with air conditioner"),
	HARD_SEAT("Hard seat"),
	SOFT_SEAT("Soft seat"),
	HARD_BED("Hard bed"),
	SOFT_BED("Soft bed");

	private final String seatType;

	public String getSeatType() {
		return seatType;
	}

	SeatType(String seatType) {
		this.seatType = seatType;
	}
	
}