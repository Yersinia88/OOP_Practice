package upgradedprogram;

import upgradedprogram.countprice.CountPrice;

public class Main {
    public static void main(String[] args) {

        CountPrice countPrice = new CountPrice();
        if (countPrice.getMobileServiceName() != null) {
            System.out.println(countPrice);
        }


    }
}
