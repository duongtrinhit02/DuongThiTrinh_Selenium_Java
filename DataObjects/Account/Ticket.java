package Account;

public class Ticket {

	private int departDate;
	private String departStation;
	private String arriveStation;
	private String seatType;
	private Integer amount;

	// constructor
	public Ticket (int plusDay, String departStation, String arriveStation, String seatType, Integer amount) {
		this.departDate = plusDay;
		this.departStation = departStation;
		this.arriveStation = arriveStation;
		this.seatType = seatType;
		this.amount = amount;
	}

	public int getDepartDate() {
		return departDate;
	}

	public void setDepartDate(int departDate) {
		this.departDate = departDate;
	}

	public String getDepartStation() {
		return departStation;
	}

	public void setDepartStation(String departStation) {
		this.departStation = departStation;
	}

	public String getArriveStation() {
		return arriveStation;
	}

	public void setArriveStation(String arriveStation) {
		this.arriveStation = arriveStation;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}