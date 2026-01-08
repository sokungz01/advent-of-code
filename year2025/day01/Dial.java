package year2025.day01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dial{
    public static void main(String[] args){
        List<Integer> dialedNumbers = getDialedNumbers();
        System.out.println("Password:");
        System.out.println(calculatePassword(50,dialedNumbers));
    }

    public static List<Integer> getDialedNumbers() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String stdIn = scanner.nextLine();
            if (stdIn == null) break;
            stdIn = stdIn.trim();
            if (stdIn.isEmpty()) break;
            try {
                int number = parseDialedNumber(stdIn);
                numbers.add(number);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter lines like 'L68' or 'R48' or plain integers.");
            }
        }
        scanner.close();
        return numbers;
    }

    public static int parseDialedNumber(String input) {
        if (input == null) throw new NumberFormatException("null input");
        String s = input.trim();
        if (s.isEmpty()) throw new NumberFormatException("empty input");
        char first = s.charAt(0);
        if (first == 'L' || first == 'l') {
            String rest = s.substring(1).trim();
            return -Integer.parseInt(rest);
        } else if (first == 'R' || first == 'r') {
            String rest = s.substring(1).trim();
            return Integer.parseInt(rest);
        } else {
            return Integer.parseInt(s);
        }
    }

    public static int calculatePassword(Integer startNumber, List<Integer> dialedNumbers) {
        int password = startNumber;
        System.out.printf("%d \n",password);
        int zeros = 0;
        for (int number : dialedNumbers) {
            password = ((password + number) % 100 + 100) % 100;
            System.out.printf("%d %d \n",password, number);
            if (password == 0) {
                zeros++;
            }
        }
        return zeros;
    }
}