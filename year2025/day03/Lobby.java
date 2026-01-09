package year2025.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lobby {
    public static void main(String[] args) {
        List<String> batteriesBank = getBatteriesBank();
        for(String battery : batteriesBank){
            System.out.println(battery);
        }
        long outputJoltagePart1 = batteriesBank.stream()
                .mapToLong(Lobby::maxJoltage)
                .sum();
        long outputJoltagePart2 = batteriesBank.stream()
                                         .mapToLong(Lobby::maxJoltage12)
                                         .sum();
        System.out.println(outputJoltagePart1);
        System.out.println(outputJoltagePart2);
    }

    public static List<String> getBatteriesBank() {
        Scanner scanner = new Scanner(System.in);
        List<String> batteries = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String stdIn = scanner.nextLine();
            if (stdIn == null) break;
            stdIn = stdIn.trim();
            if (stdIn.isEmpty()) break;
            batteries.add(stdIn);
        }
        scanner.close();
        return batteries;
    }

    public static int maxJoltage(String s) {
        if (s == null || s.length() < 2) return 0;
        int n = s.length();
        int[] sufMax = new int[n];
        sufMax[n - 1] = s.charAt(n - 1) - '0';
        for (int i = n - 2; i >= 0; i--) {
            int d = s.charAt(i) - '0';
            sufMax[i] = Math.max(d, sufMax[i + 1]);
        }
        int best = 0;
        for (int i = 0; i < n - 1; i++) {
            int first = s.charAt(i) - '0';
            int secondMax = sufMax[i + 1];
            int val = first * 10 + secondMax;
            if (val > best) best = val;
        }
        return best;
    }

    public static long maxJoltageK(String s, int k) {
        if (s == null || k <= 0) return 0L;
        int n = s.length();
        if (n < k) return 0L;

        char[] stack = new char[k];
        int top = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            while (top > 0 && stack[top - 1] < c && (top - 1) + (n - i) >= k) {
                top--;
            }
            if (top < k) {
                stack[top++] = c;
            }
        }

        long val = 0L;
        for (int i = 0; i < k; i++) {
            val = val * 10 + (stack[i] - '0');
        }
        return val;
    }

    public static long maxJoltage12(String s) {
        return maxJoltageK(s, 12);
    }
}
