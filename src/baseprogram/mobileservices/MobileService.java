package baseprogram.mobileservices;

import baseprogram.countprice.CountPriceAccordingToTariffTypes;
import baseprogram.countprice.DurationOfTheCall;

public abstract class MobileService {
    private final MobileServiceTypes mobileServiceType;
    private final DurationOfTheCall durationOfTheCall;
    private final double tariff;
    private final double baseTariff;

    private final int priceOfTheCall;

    public MobileService(MobileServiceTypes mobileServiceType, DurationOfTheCall durationOfTheCall, double tariff, double baseTariff) {
        this.mobileServiceType = mobileServiceType;
        this.durationOfTheCall = durationOfTheCall;
        this.tariff = tariff;
        this.baseTariff = baseTariff;
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
