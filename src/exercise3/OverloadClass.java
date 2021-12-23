package exercise3;

import java.util.ArrayList;

public class OverloadClass {
    public static void baseConverter(int number, int base, int digit) {
        ArrayList<String> digitArray = new ArrayList<String>();
        while (number != 0) {
            digitArray.add(Integer.toString(number % base));
            number /= base;
        }
        System.out.print(digitArray.get(digit) + "\n");
    }

    public static void baseConverter(int number, int base) {
        ArrayList<String> digitArray = new ArrayList<String>();
        while (number != 0) {
            digitArray.add(Integer.toString(number % base));
            number /= base;
        }
        for (int i = digitArray.size() - 1; i >= 0; --i) {
            System.out.print(digitArray.get(i));
        }
        System.out.println();
    }

    public static void baseConverter(int number) {
        baseConverter(number, 2);
    }
}