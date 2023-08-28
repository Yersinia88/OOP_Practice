package baseprogram.mobileservices;

public enum MobileServiceTypes {
    FECSEGTETO("Fecsegtető Telekom Zrt.", 21, true, false, false),
    LUKRACIO("Lukráció Távközlési Zrt.", 7, false, false, true),
    DECENS("Decens Távközlési Zrt.", 876, false, true, false),
    ;

    private final String name;
    private final int dialingCode;
    private boolean tariffAccordingToCall;
    private boolean tariffAccordingToSec;
    private boolean tariffAccordingToMin;


    MobileServiceTypes(String name, int dialingCode, boolean tariffAccordingToCall, boolean tariffAccordingToSec, boolean tariffAccordingToMin) {
        this.name = name;
        this.dialingCode = dialingCode;
        this.tariffAccordingToCall = tariffAccordingToCall;
        this.tariffAccordingToSec = tariffAccordingToSec;
        this.tariffAccordingToMin = tariffAccordingToMin;
    }

    public String getName() {
        return name;
    }

    public int getDialingCode() {
        return dialingCode;
    }

    public boolean isTariffAccordingToCall() {
        return tariffAccordingToCall;
    }

    public boolean isTariffAccordingToSec() {
        return tariffAccordingToSec;
    }

    public boolean isTariffAccordingToMin() {
        return tariffAccordingToMin;
    }
}
