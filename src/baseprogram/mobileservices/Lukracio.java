package baseprogram.mobileservices;

import baseprogram.countprice.DurationOfTheCall;

public class Lukracio extends MobileService {

    private final static double TARIFF = 50;
    final static double BASE_TARIFF = 0;

    public Lukracio(DurationOfTheCall durationOfTheCall) {
        super(MobileServiceTypes.LUKRACIO, durationOfTheCall, TARIFF, BASE_TARIFF);
    }



}
