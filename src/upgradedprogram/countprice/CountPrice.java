package upgradedprogram.countprice;

import upgradedprogram.mobileservices.*;
import upgradedprogram.userinput.DurationOfTheCall;
import upgradedprogram.userinput.UserInput;
import upgradedprogram.userinput.ValidatedPhoneNumber;


public class CountPrice {
    private final String phoneNumber;
    private int sec;
    private int min;
    private final DurationOfTheCall durationOfTheCall = new DurationOfTheCall(min, sec);
    private MobileService mobileServiceName;


    public CountPrice() {       //adatok bevitele + megnézi, hogy létező szolgáltató-e
        UserInput userInput = new UserInput();
        this.phoneNumber = userInput.getPhoneNumber();

        try {
            min = userInput.getMin();
            sec = userInput.getSec();
            if (sec > 59 || sec < 0) {
                throw new IllegalArgumentException();
            }
            durationOfTheCall.setMin(min);
            durationOfTheCall.setSec(sec);
            mobileServiceName = getMobileServiceType();
        } catch (NumberFormatException e) {
            System.out.println("A megadott perc, vagy másodperc érték nem értelmezhető!");
        } catch (IllegalArgumentException e) {
            System.out.println("A megadott másodperc érték nem értelmezhető!");
        }


    }

    private MobileService getMobileServiceType() { //amikor megnézi a mobilszolgáltató típust  nézi  meg, hogy jó-e a telefonszám és visszatér egy értékkel
        int number = new ValidatedPhoneNumber(phoneNumber).getDialCode();
        int fecsegteto = MobileServiceTypes.FECSEGTETO.getDialingCode();
        int decens = MobileServiceTypes.DECENS.getDialingCode();
        int lukracio = MobileServiceTypes.LUKRACIO.getDialingCode();
        int rifranger = MobileServiceTypes.RIFRANGER.getDialingCode();


        if (number == fecsegteto) {
            mobileServiceName = new Fecsegteto(durationOfTheCall);
        } else if (number == decens) {
            mobileServiceName = new Decens(durationOfTheCall);
        } else if (number == lukracio) {
            mobileServiceName = new Lukracio(durationOfTheCall);
        } else if (number == rifranger) {
            mobileServiceName = new Rifranger(durationOfTheCall);
        } else {
            mobileServiceName = null;
        }
        return mobileServiceName;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(2);
        String nameOfTheUserService = mobileServiceName.getMobileServiceType().getName();
        int traiffOftheCall = mobileServiceName.getPriceOfTheCall();
        double tariffOfTheService = mobileServiceName.getTariff();
        double baseTariffOfTheService = mobileServiceName.getBaseTariff();


        String optionAccorddingToMin = "A hívást a " + nameOfTheUserService + " bonyolította le, díja " + traiffOftheCall + " peták (a díjszámítás perc alapú, minden megkezdett perc díja " + (int) tariffOfTheService + " peták)";
        String optionAccordingToSec = "A hívást a " + nameOfTheUserService + " bonyolította le, díja " + traiffOftheCall + " peták (a díjszámítás másodperc alapú, " + CountPriceAccordingToTariffTypes.TWO_MINS_IN_SEC + " másodpercig " + (String.format("%.2f", baseTariffOfTheService)) + " peták, majd " + (String.format("%.2f", tariffOfTheService)) + " peták)";
        String optionAccordingToCall = "A hívást a " + nameOfTheUserService + " bonyolította le, díja " + traiffOftheCall + " peták (a díjszámítás hívás alapú " + tariffOfTheService + " peták)";

        String printBestService = "\nA megadott idejű hívásra a " + nameOfTheUserService + " az optimális szolgáltató.";


        switch (mobileServiceName.getMobileServiceType()) {
            case LUKRACIO -> stringBuilder.append(optionAccorddingToMin);
            case DECENS -> stringBuilder.append(optionAccordingToSec);
            case FECSEGTETO -> stringBuilder.append(optionAccordingToCall);
            case RIFRANGER -> {
                if (mobileServiceName.getMobileServiceType().isTariffAccordingToMin()) {
                    stringBuilder.append(optionAccorddingToMin);
                    stringBuilder.append(printBestService);
                } else if (mobileServiceName.getMobileServiceType().isTariffAccordingToCall()) {
                    stringBuilder.append(optionAccordingToCall);
                    stringBuilder.append(printBestService);
                } else {
                    stringBuilder.append(optionAccordingToSec);
                    stringBuilder.append(printBestService);
                }
            }
        }

        if (!(mobileServiceName.getMobileServiceType().getName().equals("Rifranger Kft."))) {
            mobileServiceName = new Rifranger(durationOfTheCall);
            stringBuilder.append("\nA megadott idejű hívásra az optimális szolgáltató a " + mobileServiceName.getMobileServiceType().getName() + " lenne, az ő díjuk csak " + mobileServiceName.getPriceOfTheCall() + " peták.");
        }


        return stringBuilder.toString();
    }

    public MobileService getMobileServiceName() {
        return mobileServiceName;
    }

}
