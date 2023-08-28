package baseprogram.userinput;

import java.util.Scanner;

public class UserInput {
    private Scanner scanner = new Scanner(System.in);

    private String phoneNumber;
    private String minSec;

    private String[] minSecArray;



    public UserInput() {
        System.out.print("Add meg a hívás időtartamát (<perc>:<másodperc>): ");
        minSec = scanner.nextLine();
        System.out.print("Add meg a telefonszámot (a körzetszámtól kezdve, vagy távhívás előhívókkal): ");
        phoneNumber = scanner.nextLine();
        minSecArray = minSec.split(":");
    }




    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getMin() {
        return Integer.parseInt(minSecArray[0]);
    }

    public int getSec() {
        return Integer.parseInt(minSecArray[1]);
    }
}
