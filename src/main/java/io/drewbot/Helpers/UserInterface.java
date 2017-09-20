package io.drewbot.Helpers;

import io.drewbot.models.Stat;

import java.util.Scanner;

public class UserInterface {
    public static Stat createStat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name?");
        String myName = scanner.nextLine();
        System.out.println("Number of wins?");
        int myWins = scanner.nextInt();
        System.out.println("Number of losses?");
        int myLosses = scanner.nextInt();

        return new Stat(myName, myWins, myLosses);
    }
}
