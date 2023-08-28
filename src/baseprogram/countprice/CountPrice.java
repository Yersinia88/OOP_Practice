package baseprogram.countprice;

import baseprogram.mobileservices.*;
import baseprogram.userinput.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class CountPrice {
    private final String phoneNumber;
    private int sec;
    private int min;
    private final DurationOfTheCall durationOfTheCall = new DurationOfTheCall(min, sec);
    private MobileService mobileServiceName;

    public CountPrice() {
        UserInput userInput = new UserInput();
        this.phoneNumber = userInput.getPhoneNumber();
        min = userInput.getMin();
        sec = userInput.getSec();
        durationOfTheCall.setMin(min);
        durationOfTheCall.setSec(sec);
        mobileServiceName = getMobileServiceType();
    }

    private MobileService getMobileServiceType() {
        int number = new ValidatedPhoneNumber(phoneNumber).getDialCode();
        int fecsegteto = MobileServiceTypes.FECSEGTETO.getDialingCode();
        int decens = MobileServiceTypes.DECENS.getDialingCode();
        int lukracio = MobileServiceTypes.LUKRACIO.getDialingCode();

        if (number == fecsegteto) {
            mobileServiceName = new Fecsegteto(durationOfTheCall);
        } else if (number == decens) {
            mobileServiceName = new Decens(durationOfTheCall);
        } else if (number == lukracio) {
            mobileServiceName = new Lukracio(durationOfTheCall);
        } else {
            mobileServiceName = null;
        }
        return mobileServiceName;
    }

    private Map<MobileServiceTypes, Integer> theCheapestService() {
        Map<MobileServiceTypes, Integer> cheapestService = new HashMap<>();
        Map<MobileServiceTypes, Integer> returnWithTheCheapestServices = new HashMap<>();
        cheapestService.put(MobileServiceTypes.DECENS, new Decens(durationOfTheCall).getPriceOfTheCall());
        cheapestService.put(MobileServiceTypes.FECSEGTETO, new Fecsegteto(durationOfTheCall).getPriceOfTheCall());
        cheapestService.put(MobileServiceTypes.LUKRACIO, new Lukracio(durationOfTheCall).getPriceOfTheCall());

        Set<MobileServiceTypes> mobileServices = cheapestService.keySet();


        int min = cheapestService.get(MobileServiceTypes.DECENS);

        for (MobileServiceTypes mobilService : mobileServices) {
            if (cheapestService.get(mobilService) < min) {
                min = cheapestService.get(mobilService);
            }
        }

        for (MobileServiceTypes mobilService : mobileServices) {
            if (cheapestService.get(mobilService) == min) {
                returnWithTheCheapestServices.put(mobilService, cheapestService.get(mobilService));
            }
        }
        return returnWithTheCheapestServices;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(2);
        String nameOfTheUserService = mobileServiceName.getMobileServiceType().getName();
        int traiffOftheCall = mobileServiceName.getPriceOfTheCall();
        double tariffOfTheService = mobileServiceName.getTariff();
        double baseTariffOfTheService = mobileServiceName.getBaseTariff();

        Map<MobileServiceTypes, Integer> theCheapestService = theCheapestService();

        String optionAccorddingToMin = "A hívást a " + nameOfTheUserService + " bonyolította le, díja " + traiffOftheCall + " peták (a díjszámítás perc alapú, minden megkezdett perc díja " + (int)tariffOfTheService + " peták)";
        String optionAccordingToSec = "A hívást a " + nameOfTheUserService + " bonyolította le, díja " + traiffOftheCall + " peták (a díjszámítás másodperc alapú, " + CountPriceAccordingToTariffTypes.TWO_MINS_IN_SEC + " másodpercig " + baseTariffOfTheService + " peták, majd " + tariffOfTheService + " peták)";
        String optionAccordingToCall = "A hívást a " + nameOfTheUserService + " bonyolította le, díja " + traiffOftheCall + " peták (a díjszámítás hívás alapú " + tariffOfTheService + " peták)";

        String printOut1 = "\nA megadott idejű hívásra a " + nameOfTheUserService + " az optimális szolgáltató.";


        switch (mobileServiceName.getMobileServiceType()) {
            case LUKRACIO -> stringBuilder.append(optionAccorddingToMin);
            case DECENS -> stringBuilder.append(optionAccordingToSec);
            case FECSEGTETO -> stringBuilder.append(optionAccordingToCall);
        }


        if (theCheapestService.containsKey(mobileServiceName.getMobileServiceType())) {
            stringBuilder.append(printOut1);
        } else {
            for (Map.Entry<MobileServiceTypes, Integer> entry : theCheapestService.entrySet())
                stringBuilder.append("\nA megadott idejű hívásra az optimális szolgáltató a " + entry.getKey().getName() + " lenne, az ő díjuk csak " + entry.getValue() + " peták.");
        }

        return stringBuilder.toString();
    }

    public MobileService getMobileServiceName() {
        return mobileServiceName;
    }
}
