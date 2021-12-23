package exercise1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class Test {
    private ArrayList<String[]> questions = new ArrayList<String[]>();
    private int nextQuestion;
    private int score;
    private static final String[][] MESSAGES = {
            {"No. Please try again", "Wrong. Try once more", "Don't give up!", "No. Keep trying.."},
            {"Excellent!", "Good!", "Keep up the good work!", "Nice work!"}};
    private static Random random = new Random();

    public Test() {
        try {
            File questionsFile = new File("src\\exercise1\\questions.txt");
            Scanner questionsReader = new Scanner(questionsFile);
            int i = 0;
            String[] array = new String[5];
            while (questionsReader.hasNextLine()) {
                array[i] = questionsReader.nextLine();
                if (i == 4) {
                    questions.add(Arrays.copyOf(array, array.length));
                }
                i = (i + 1) % 5;
            }
            questionsReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public boolean simulateTest() {
        if (nextQuestion >= questions.size()) {
            showScore();
            return false;
        }
        String[] options = new String[4];
        for (int i = 0; i < 4; i++)
            options[i] = questions.get(nextQuestion)[i + 1];
        int answer = shuffleOptions(options);

        Object selected = JOptionPane.showInputDialog(null,
                questions.get(nextQuestion)[0] + "\n ",
                "Question " + ++nextQuestion,
                3,
                null,
                options,
                "0");

        if (selected == null) {

        }else if (selected.toString().equals(options[answer])){
            generateMessage(1);
            ++score;
        }else {
            generateMessage(0);
        }
        return true;
    }
    private static int shuffleOptions(String[] array) {
        int j = 0;
        String s;
        for (int i = 1; i < 4; ++i) {
            i %= 3;
            j = random.nextInt(4 - i) + i;
            s = array[i];
            array[i] = array[j];
            array[j] = s;
            if (i == 0)
                break;
        }
        return j;
    }

    private static void generateMessage(int correctness) {
        JOptionPane.showMessageDialog(null,
                MESSAGES[correctness][random.nextInt(4)],
                "Response",
                JOptionPane.PLAIN_MESSAGE);
    }

    private void showScore() {
        JOptionPane.showMessageDialog(null,
                String.format("Your score is %d%%!", 100 * score / questions.size()),
                "Score",
                JOptionPane.PLAIN_MESSAGE);
    }
}