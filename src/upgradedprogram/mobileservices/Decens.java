package upgradedprogram.mobileservices;

import upgradedprogram.userinput.DurationOfTheCall;

public class Decens extends MobileService {

    final static double TARIFF = 0.5;
    final static double BASE_TARIFF = 1.5;


    public Decens(DurationOfTheCall durationOfTheCall) {
        super(MobileServiceTypes.DECENS, durationOfTheCall);
    }

    @Override
    public double getTariff() {
        return TARIFF;
    }

    @Override
    public double getBaseTariff() {
        return BASE_TARIFF;
    }


}
