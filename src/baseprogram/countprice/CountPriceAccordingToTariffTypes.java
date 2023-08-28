package baseprogram.countprice;

import baseprogram.mobileservices.MobileServiceTypes;

public class CountPriceAccordingToTariffTypes {
    private final static int ONE_MIM_IN_SEC = 60;
    public static final int TWO_MINS_IN_SEC = 120;
    private final MobileServiceTypes mobileServiceTypes;
    private final DurationOfTheCall durationOfTheCall;
    private final double tariff;
    private final double baseTariff;

    public CountPriceAccordingToTariffTypes(MobileServiceTypes mobileServiceTypes, DurationOfTheCall durationOfTheCall, double tariff, double baseTariff) {
        this.mobileServiceTypes = mobileServiceTypes;
        this.durationOfTheCall = durationOfTheCall;
        this.tariff = tariff;
        this.baseTariff = baseTariff;
        countPrice();
    }

    public int countPrice() {
        int priceOfTheCall;
        if (mobileServiceTypes.isTariffAccordingToCall()) {
            priceOfTheCall = countAccordingToCall();
        } else if (mobileServiceTypes.isTariffAccordingToSec()) {
            priceOfTheCall = countAccordingToSec();
        } else {
            priceOfTheCall = countAccordingToMin();
        }

        return priceOfTheCall;
    }

    private int countAccordingToMin() {
        int priceOfTheCall;
        int callInSecundum = durationOfTheCall.DurationOfTheCallInSec();
        if (callInSecundum % ONE_MIM_IN_SEC > 0) {
            priceOfTheCall = (int) (((callInSecundum / ONE_MIM_IN_SEC) + 1) * tariff);
        } else {
            priceOfTheCall = (int) (callInSecundum / ONE_MIM_IN_SEC * tariff);
        }
        return priceOfTheCall;
    }

    private int countAccordingToSec() {
        int tariffOfTheCall;
        int callInSecundum = durationOfTheCall.DurationOfTheCallInSec();

        if (callInSecundum <= TWO_MINS_IN_SEC) {
            tariffOfTheCall = (int) (callInSecundum * baseTariff);
        } else {
            tariffOfTheCall = (int) ((TWO_MINS_IN_SEC * baseTariff) + ((callInSecundum - 120) * tariff));
        }
        return tariffOfTheCall;
    }

    private int countAccordingToCall() {
        return (int) tariff;
    }


}
