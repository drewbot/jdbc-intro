package io.drewbot;

import io.drewbot.Helpers.DatabaseManager;
import io.drewbot.models.Stat;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:stats.db")) {

            DatabaseManager dbm = new DatabaseManager(conn);

            dbm.dropStatTable();

            dbm.createStatTable();

            System.out.println("stats database created");

            dbm.insertIntoStatTable("Floyd", 50, 0);
            dbm.insertIntoStatTable("Jimmy", 4, 3);

            ArrayList<Stat> results = dbm.getStats();

        } catch (SQLException e) {
            System.out.println("We encountered a problem connecting to the database");
            e.printStackTrace();
        }

        System.out.println("Hello world!");
    }
}
