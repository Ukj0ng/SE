import java.util.Random;

public class Dice {
    int roll(){
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }
}
