package baseprogram.mobileservices;

import baseprogram.countprice.DurationOfTheCall;

public class Fecsegteto extends MobileService {
    final static double TARIFF = 300;
    final static double BASE_TARIFF = 0;
    public Fecsegteto(DurationOfTheCall durationOfTheCall) {
        super(MobileServiceTypes.FECSEGTETO, durationOfTheCall, TARIFF, BASE_TARIFF);
    }


}
