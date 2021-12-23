package exercise2;

import java.util.Random;
import javax.swing.JOptionPane;

public class Lotto {
    private int[] randomTriplet = new int[3];
    private int nextTrial;
    private int userInput;
    private static Random random = new Random();

    public void generateLotto() {
        for (int i = 0; i < 3; ++i)
            randomTriplet[i] = random.nextInt(9) + 1;
    }

    public void promptUser() {
        int num = 0;
        Object value = JOptionPane.showInputDialog("Enter a number between 1 and 27:\n ");
        if (value == null)
            return;
        else if (value.toString().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Please provide a number!\n ",
                    "Warning",
                    JOptionPane.ERROR_MESSAGE);
            promptUser();
            return;
        }
        try {
            num = Integer.parseInt(value.toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "That was not a number!\nTry again",
                    "Warning",
                    JOptionPane.ERROR_MESSAGE);
            promptUser();
            return;
        }
        if (num < 1 || num > 27) {
            JOptionPane.showMessageDialog(null,
                    "The number was out of range!\nTry again",
                    "Warning",
                    JOptionPane.ERROR_MESSAGE);
            promptUser();
            return;
        }
        userInput = num;
    }

    public void checkUserInput() {
        if (userInput == 0)
            return;
        if (nextTrial >= 5) {
            JOptionPane.showMessageDialog(null,
                    "You lost",
                    "Response",
                    JOptionPane.PLAIN_MESSAGE);
            return;
        }
        generateLotto();
        if (userInput == randomTriplet[0] + randomTriplet[1] + randomTriplet[2]) {
            JOptionPane.showMessageDialog(null,
                    String.format("You won on trial #%d!%n" +
                                    "The lotto: %d %d %d%n" +
                                    "The sum: %d", ++nextTrial,
                            randomTriplet[0],
                            randomTriplet[1],
                            randomTriplet[2],
                            randomTriplet[0] + randomTriplet[1] + randomTriplet[2]),
                    "Successful",
                    JOptionPane.PLAIN_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null,
                    String.format("Trial #%d was unsuccessful.%n" +
                            "The Lotto: %d %d %d%n" +
                            "The sum: %d", ++nextTrial,
                            randomTriplet[0],
                            randomTriplet[1],
                            randomTriplet[2],
                            randomTriplet[0] + randomTriplet[1] + randomTriplet[2]),
                    "Unsuccessful",
                    JOptionPane.PLAIN_MESSAGE);
            checkUserInput();
        }
    }
}