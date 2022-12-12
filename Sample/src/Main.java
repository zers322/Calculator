import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myVar = new Scanner(System.in);
        String[] line = myVar.nextLine().split(" ");

        if (line.length != 3) {
            try {
                throw new IOException();
            }
            catch (IOException e) {
                System.out.println("Строка не является математической операцией");
                System.exit(0);
            }
        }
        if (!line[1].equals("+") && !line[1].equals("-") && !line[1].equals("*") && !line[1].equals("/")) {
            try {
                throw new IOException();
            }
            catch (IOException e) {
                System.out.println("Строка не является математической операцией");
                System.exit(0);
            }
        }

        int roman1 = 0;
        boolean isRoman1 = false;
        try {
            roman1 = romanToArabic(line[0]);
            isRoman1 = true;
        }
        catch (IllegalArgumentException ignored) {}
        int num1 = 0;
        boolean isArabic1 = false;
        try {
            num1 = Integer.parseInt(line[0]);
            isArabic1 = true;
        }
        catch (NumberFormatException ignored) {}
        if (!isRoman1 && !isArabic1) {
            System.out.println("Первое число не римское и не арабское");
            System.exit(0);
        }

        int roman2 = 0;
        boolean isRoman2 = false;
        try {
            roman2 = romanToArabic(line[2]);
            isRoman2 = true;
        }
        catch (IllegalArgumentException ignored) {}
        int num2 = 0;
        boolean isArabic2 = false;
        try {
            num2 = Integer.parseInt(line[2]);
            isArabic2 = true;
        }
        catch (NumberFormatException ignored) {}
        if (!isRoman2 && !isArabic2) {
            System.out.println("Второе число не римское и не арабское");
            System.exit(0);
        }

        if (isRoman1 != isRoman2) {
            System.out.println("Используются одновременно разные системы счисления");
            System.exit(0);
        }

        int answer = 0;
        if (isArabic1) {
            switch (line[1]) {
                case "+":
                    answer = num1 + num2;
                    break;
                case "-":
                    answer = num1 - num2;
                    break;
                case "*":
                    answer = num1 * num2;
                    break;
                case "/":
                    try {
                        answer = (int) Math.floor(num1 / num2);
                    }
                    catch (ArithmeticException e) {
                        System.out.println("Деление на 0");
                        System.exit(0);
                    }
                    break;
            }
            System.out.println(answer);
        }
        else {
            switch (line[1]) {
                case "+":
                    answer = roman1 + roman2;
                    break;
                case "-":
                    answer = roman1 - roman2;
                    break;
                case "*":
                    answer = roman1 * roman2;
                    break;
                case "/":
                    answer = (int) Math.floor(roman1 / roman2);
            }
            if (answer <= 0) {
                System.out.println("В римской системе только положительные");
                System.exit(0);
            }
            System.out.println(arabicToRoman(answer));
        }
    }

    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return result;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 100)) {
            throw new IllegalArgumentException(number + " is not in range (0,100]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}