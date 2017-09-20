package io.drewbot;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:stats.db")) {
            // CREATE TABLE stats { id INTEGER PRIMARY KEY, name STRING, wins INTEGER, losses INTEGER }
            Statement statement = conn.createStatement();
            statement.executeUpdate("DROP TABLE IF Exists stats;");
            statement.executeUpdate("CREATE TABLE stats (id INTEGER PRIMARY KEY, name STRING, wins INTEGER, losses INTEGER);");
            statement.executeUpdate("INSERT INTO stats (name, wins, losses) VALUES ('drew', 3, 1);");
            ResultSet rs = statement.executeQuery("SELECT * FROM stats;");

            System.out.println("stats database created");

            // While there are result sets next
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int wins = rs.getInt("wins");
                int losses = rs.getInt("losses");

                System.out.printf("id: %s, %s %s %s\n", id, name, wins, losses);
            }

        } catch (SQLException e) {
            System.out.println("We encountered a problem connecting to the database");
            e.printStackTrace();
        }

        System.out.println("Hello world!");
    }
}
