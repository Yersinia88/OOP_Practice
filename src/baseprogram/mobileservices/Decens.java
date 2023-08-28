package baseprogram.mobileservices;

import baseprogram.countprice.DurationOfTheCall;

public class Decens extends MobileService {

    final static double TARIFF = 0.5;
    final static double BASE_TARIFF = 1.5;


    public Decens(DurationOfTheCall durationOfTheCall) {
        super(MobileServiceTypes.DECENS, durationOfTheCall, TARIFF, BASE_TARIFF);
    }




}
