package Railway;

public enum SeatCoat {

    HS("HS"),
    SS("SS"),
    SSC("SSC"),
    HB("HB"),
    SB("SB"),
    SBC("SBC");

    private final String value;

    SeatCoat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
