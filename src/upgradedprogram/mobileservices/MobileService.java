package upgradedprogram.mobileservices;

import upgradedprogram.countprice.CountPriceAccordingToTariffTypes;
import upgradedprogram.userinput.DurationOfTheCall;

public abstract class MobileService {
    private final MobileServiceTypes mobileServiceType;
    private final DurationOfTheCall durationOfTheCall;
    private  double tariff;
    private double baseTariff;

    private int priceOfTheCall;

    public MobileService(MobileServiceTypes mobileServiceType, DurationOfTheCall durationOfTheCall) {
        this.mobileServiceType = mobileServiceType;
        this.durationOfTheCall = durationOfTheCall;
        this.priceOfTheCall = countPrice();
    }

    public int countPrice() {
        CountPriceAccordingToTariffTypes countPriceAccordingToTariffTypes = new CountPriceAccordingToTariffTypes(getMobileServiceType(), getDurationOfTheCall(), getTariff(), getBaseTariff());
        return countPriceAccordingToTariffTypes.countPrice();
    }

    public MobileServiceTypes getMobileServiceType() {
        return mobileServiceType;
    }

    public DurationOfTheCall getDurationOfTheCall() {
        return durationOfTheCall;
    }

    public double getTariff() {
        return tariff;
    }

    public double getBaseTariff() {
        return baseTariff;
    }

    public int getPriceOfTheCall() {
        return priceOfTheCall;
    }
}
