package baseprogram;

import baseprogram.countprice.*;

public class BaseProgramMain {
    public static void main(String[] args) {

        CountPrice countPrice = new CountPrice();
        if (countPrice.getMobileServiceName() != null) {
            System.out.println(countPrice);
        }


    }
}
