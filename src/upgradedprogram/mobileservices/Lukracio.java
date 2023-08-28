package upgradedprogram.mobileservices;

import upgradedprogram.userinput.DurationOfTheCall;

public class Lukracio extends MobileService {

    private final static double TARIFF = 50;
    final static double BASE_TARIFF = 0;

    public Lukracio(DurationOfTheCall durationOfTheCall) {
        super(MobileServiceTypes.LUKRACIO, durationOfTheCall);
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
