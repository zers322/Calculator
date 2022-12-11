import java.io.IOException;
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
        int num1 = Integer.parseInt(line[0]);
        int num2 = Integer.parseInt(line[2]);

        int answer = 0;
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
                if (num2 == 0) {
                    try {
                        answer = (int) Math.floor(num1 / num2);
                    }
                    catch (ArithmeticException e) {
                        System.out.println("Деление на 0");
                        System.exit(0);
                    }
                }
                break;
        }
        System.out.println(answer);
    }
}