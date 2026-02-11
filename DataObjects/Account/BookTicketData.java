package Account;

public class BookTicketData {

    private String departDate;
    private Station departFrom;
    private Station arriveAt;
    private SeatType seatType;
    private TicketAmount ticketAmount;

    // ===== Getter =====

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public Station getDepartFrom() {
        return departFrom;
    }

    public void setDepartFrom(Station departFrom) {
        this.departFrom = departFrom;
    }

    public Station getArriveAt() {
        return arriveAt;
    }

    public void setArriveAt(Station arriveAt) {
        this.arriveAt = arriveAt;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public TicketAmount getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(TicketAmount ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    // ================= ENUM =================

    public enum Station {
        SAI_GON("Sài Gòn"),
        PHAN_THIET("Phan Thiết"),
        NHA_TRANG("Nha Trang"),
        DA_NANG("Đà Nẵng"),
        HUE("Huế"),
        QUANG_NGAI("Quảng Ngãi");

        private final String value;

        Station(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum SeatType {
        HARD_SEAT("Hard seat"),
        SOFT_SEAT("Soft seat"),
        SOFT_SEAT_AC("Soft seat with air conditioner"),
        SOFT_BED_AC("Soft bed with air conditioner");

        private final String value;

        SeatType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum TicketAmount {
        ONE("1"),
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5");

        private final String value;

        TicketAmount(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
