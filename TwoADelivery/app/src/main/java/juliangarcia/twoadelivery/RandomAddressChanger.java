package juliangarcia.twoadelivery;

import android.widget.TextView;

import java.util.Random;

/**
 * Created by Julian on 2/21/2015.
 */
public class RandomAddressChanger extends Random {
    //private int levelType;
    //private double difficultyModifier;
    private String address;

    public RandomAddressChanger() {//int lvlType) {//, int diff) {
        //levelType = lvlType;
        //difficultyModifier = diffModCalc(diff);
        loadAddress();
    }

    public String loadAddress() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(9);
        address = "" + randomInt;
        log("Generated : " + randomInt);
        return address;
    }

    private static void log(String aMessage) {

        System.out.println(aMessage);
    }

}
