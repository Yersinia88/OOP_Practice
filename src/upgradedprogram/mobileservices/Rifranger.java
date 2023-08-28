package upgradedprogram.mobileservices;

import upgradedprogram.countprice.CheapestService;
import upgradedprogram.countprice.CountPriceAccordingToTariffTypes;
import upgradedprogram.userinput.DurationOfTheCall;

public class Rifranger extends MobileService {

    private final double discount = 0.08;

    private final double factor = 1 - discount;
    private double[] parameters = new double[2];
    private double tariff = parameters[0];
    private double baseTariff = parameters[1];
    private int priceOfTheCall;
    private MobileService mobileService;

    public Rifranger(DurationOfTheCall durationOfTheCall) {
        super(MobileServiceTypes.RIFRANGER, durationOfTheCall);
        setPartameters();
        priceOfTheCall = countPrice();
    }

    private double[] setPartameters() {
        CheapestService cheapestService = new CheapestService(getDurationOfTheCall());
        MobileServiceTypes mobileServiceTemplateForRifranger = cheapestService.getCheapestPriceService();
        MobileService template = getMobileServiceTemplateForRifranger(mobileServiceTemplateForRifranger);

        tariff = parameters[0] = template.getTariff() * factor;
        baseTariff = parameters[1] = template.getBaseTariff() * factor;
        setBooleansAccordingToTariffType(mobileServiceTemplateForRifranger);

        return parameters;
    }

    private void setBooleansAccordingToTariffType(MobileServiceTypes mobileServiceTemplateOfRifranger) {
        switch (mobileServiceTemplateOfRifranger) {
            case LUKRACIO -> getMobileServiceType().setTariffAccordingToMin(true);
            case DECENS -> getMobileServiceType().setTariffAccordingToSec(true);
            case FECSEGTETO -> getMobileServiceType().setTariffAccordingToCall(true);
        }
    }

    private MobileService getMobileServiceTemplateForRifranger(MobileServiceTypes mobileServiceType) {
        switch (mobileServiceType) {
            case LUKRACIO -> mobileService = new Lukracio(getDurationOfTheCall());
            case DECENS -> mobileService = new Decens(getDurationOfTheCall());
            case FECSEGTETO -> mobileService = new Fecsegteto(getDurationOfTheCall());
        }
        return mobileService;
    }

    @Override
    public int countPrice() {
        CountPriceAccordingToTariffTypes countPriceAccordingToTariffTypes = new CountPriceAccordingToTariffTypes(getMobileServiceType(), getDurationOfTheCall(), getTariff(), getBaseTariff());
        return countPriceAccordingToTariffTypes.countPrice();

    }

    @Override
    public double getTariff() {
        return tariff;
    }

    @Override
    public double getBaseTariff() {
        return baseTariff;
    }

    @Override
    public int getPriceOfTheCall() {
        return priceOfTheCall;
    }
}
