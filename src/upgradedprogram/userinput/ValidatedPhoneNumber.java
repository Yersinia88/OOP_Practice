package upgradedprogram.userinput;


import upgradedprogram.mobileservices.MobileServiceTypes;

public class ValidatedPhoneNumber {
    private static final int MIN_LENGTH = 6;
    private static final int MAX_LENGTH = 15;
    public static final int MAX_DIGIT_IN_COUNTRY_CALL = 10;
    public static final int MIN_DIGIT_IN_COUNTRY_CALL = 6;
    private static final String BERGENGOCY_COUNTRY_CODE = "355";
    private static final String TRUNK_CALL_PREFIX = "00";
    private static final String TRUNK_CALL_IN_COUNTRY_PREFIX = "06";
    private static final int MAX_DIGIT_WITH_COUNTRY_CODE = 13;
    private static final int MIN_DIGIT_WITH_COUNTRY_CODE = 9;
    private static final int MAX_DIGIT_WITH_TRUNK_CALL_PREFIX = 15;
    private static final int MIN_DIGIT_WITH_TRUNK_CALL_PREFIX = 12;
    private int dialCode;

//TODO: switch to regex
    public ValidatedPhoneNumber(String phoneNumber) {
        if(validationOfThePhoneNumber(phoneNumber)) {
            this.dialCode = getDialCode();
        } else {
            this.dialCode = -1;
        }
    }

    public boolean validationOfThePhoneNumber(String phoneNumber) {

        StringBuilder stringBuilder = new StringBuilder(phoneNumber);

        char[] validCharacters = {'(', ')',' ', '-', '+', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        String[] dialingCodes = {String.valueOf(MobileServiceTypes.DECENS.getDialingCode()), String.valueOf(MobileServiceTypes.FECSEGTETO.getDialingCode()), String.valueOf(MobileServiceTypes.LUKRACIO.getDialingCode()),String.valueOf(MobileServiceTypes.RIFRANGER.getDialingCode()) };

        String cleanedPhoneNumber = cleaningPhoneNumber(phoneNumber);

        if (cleanedPhoneNumber.length() < MIN_LENGTH || cleanedPhoneNumber.length() > MAX_LENGTH) {
            System.out.println("A megadott telefonszám nem értelmezhető!");
            return false;
        }

        if (phoneNumber.contains("+") && !phoneNumber.startsWith("+")) {
            System.out.println("A megadott telefonszám nem értelmezhető!");
            return false;
        }

        for (int i = 0; i < phoneNumber.length(); i++) {
            boolean isContainsInvalidChar = true;
            for (char c : validCharacters) {
                if (c == phoneNumber.charAt(i)) {
                    isContainsInvalidChar = false;
                    break;
                }
            }
            if (isContainsInvalidChar) {
                System.out.println("A megadott telefonszám nem értelmezhető!");
                return false;
            }
        }

        for (String code : dialingCodes) {
            int phoneNumberLengthOnlyWithDialingCode = code.length() + (cleanedPhoneNumber.length() - code.length());
            int cleanedPhoneNumberLength = cleanedPhoneNumber.length();
            if (isInCountryCall(cleanedPhoneNumber, code, phoneNumberLengthOnlyWithDialingCode)) {
                dialCode = Integer.parseInt(code);
                return true;
            } else if (isInCountryCallWithCountryCode(cleanedPhoneNumber, phoneNumberLengthOnlyWithDialingCode)) {
                if (cleanedPhoneNumber.substring(BERGENGOCY_COUNTRY_CODE.length(), cleanedPhoneNumberLength).startsWith(code)) {
                    dialCode = Integer.parseInt(code);
                    return true;
                }
            } else if (isTrunkCall(cleanedPhoneNumber, phoneNumberLengthOnlyWithDialingCode)) {
                if (cleanedPhoneNumber.substring((TRUNK_CALL_PREFIX.length() + BERGENGOCY_COUNTRY_CODE.length()), cleanedPhoneNumberLength).startsWith(code)) {
                    dialCode = Integer.parseInt(code);
                    return true;
                }
            }
        }
        System.out.println("A megadott telefonszám Bergengóciában nem mobilszám!");
        return false;
    }

    private static boolean isTrunkCall(String cleanedPhoneNumber, int phoneNumberLengthOnlyWithDialingCode) {
        return (cleanedPhoneNumber.startsWith(TRUNK_CALL_PREFIX) || cleanedPhoneNumber.startsWith(TRUNK_CALL_IN_COUNTRY_PREFIX)) && (phoneNumberLengthOnlyWithDialingCode <= MAX_DIGIT_WITH_TRUNK_CALL_PREFIX && phoneNumberLengthOnlyWithDialingCode >= MIN_DIGIT_WITH_TRUNK_CALL_PREFIX);
    }

    private static boolean isInCountryCallWithCountryCode(String cleanedPhoneNumber, int phoneNumberLengthOnlyWithDialingCode) {
        return cleanedPhoneNumber.startsWith(BERGENGOCY_COUNTRY_CODE) && (phoneNumberLengthOnlyWithDialingCode <= MAX_DIGIT_WITH_COUNTRY_CODE && phoneNumberLengthOnlyWithDialingCode >= MIN_DIGIT_WITH_COUNTRY_CODE);
    }

    private static boolean isInCountryCall(String cleanedPhoneNumber, String code, int phoneNumberLengthOnlyWithDialingCode) {
        return cleanedPhoneNumber.startsWith(code) && (phoneNumberLengthOnlyWithDialingCode <= MAX_DIGIT_IN_COUNTRY_CALL && phoneNumberLengthOnlyWithDialingCode >= MIN_DIGIT_IN_COUNTRY_CALL);
    }

    private String cleaningPhoneNumber(String phoneNumber) {
        StringBuilder stringBuilder = new StringBuilder(phoneNumber);
        char[] nonNumberCharacters = {'(', ')', '-', '+', ' '};

        for (int i = 0; i < nonNumberCharacters.length; i++) {
            int index = stringBuilder.indexOf(String.valueOf(nonNumberCharacters[i]));
            while (index != -1 && index + 1 < phoneNumber.length()) {
                stringBuilder.delete(index, index + 1);
                index = stringBuilder.indexOf(String.valueOf(nonNumberCharacters[i]), index + 1);
            }
        }

        String cleanedPhoneNumber = stringBuilder.toString();

        return cleanedPhoneNumber;
    }

    public int getDialCode() {
        return dialCode;
    }
}




