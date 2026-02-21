package Enums;

public enum Station {
	NHA_TRANG("Nha Trang"),
	HUE("Huế"),
	SAI_GON("Sài Gòn"),
	PHAN_THIET("Phan Thiết"),
	QUANG_NGAI("Quảng Ngãi"),
	DA_NANG("Đà Nẵng");

	private final String station;

	Station(String station) {
		this.station = station;
	}

	public String setStation() {
		return station;
	}
}