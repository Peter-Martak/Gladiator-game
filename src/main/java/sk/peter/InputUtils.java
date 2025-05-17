package sk.peter;

import java.util.Scanner;

public class InputUtils {
    private final static Scanner scanner = new Scanner(System.in);

    public static String readString(){
        return scanner.nextLine();
    }

    public static int readInt() {
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine();
                if (input > 6){
                    System.out.println("Invalid input");
                    continue;
                }
                return input;
            } catch (Exception e) {
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }
    }
}
