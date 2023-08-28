package upgradedprogram.mobileservices;

import upgradedprogram.userinput.DurationOfTheCall;

public class Fecsegteto extends MobileService {
    final static double TARIFF = 300;
    final static double BASE_TARIFF = 0;
    public Fecsegteto(DurationOfTheCall durationOfTheCall) {
        super(MobileServiceTypes.FECSEGTETO, durationOfTheCall);
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
