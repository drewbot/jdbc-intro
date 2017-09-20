package io.drewbot;

import io.drewbot.Helpers.DatabaseManager;
import io.drewbot.Helpers.UserInterface;
import io.drewbot.models.Stat;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        System.out.println("Welcome to my stat tracker!");

        Scanner scanner = new Scanner(System.in);
        Class.forName("org.sqlite.JDBC");

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:stats.db")) {

            DatabaseManager dbm = new DatabaseManager(conn);

            while (true) {
                System.out.println("What would you like to do?");
                System.out.println("(1) Add a stat");
                System.out.println("(2) See all stats");
                System.out.println("(3) Refresh data");

                String userChoice = scanner.nextLine();

                if (userChoice.equals("1")) {
                    Stat myStat = UserInterface.createStat();
                    dbm.insertIntoStatTable(myStat);
                } else if (userChoice.equals("2")) {
                    ArrayList<Stat> results = dbm.getStats();

                    for (Stat stat : results) {
                        System.out.println(stat);
                    }
                } else if (userChoice.equals("3")) {
                    dbm.dropStatTable();
                    dbm.createStatTable();
                } else {
                    break;
                }
            }


            dbm.dropStatTable();

            dbm.createStatTable();

            System.out.println("stats database created");

            dbm.insertIntoStatTable("Floyd", 50, 0);
            dbm.insertIntoStatTable("Jimmy", 4, 3);

        } catch (SQLException e) {
            System.out.println("We encountered a problem connecting to the database");
            e.printStackTrace();
        }

        System.out.println("Hello world!");
    }
}
