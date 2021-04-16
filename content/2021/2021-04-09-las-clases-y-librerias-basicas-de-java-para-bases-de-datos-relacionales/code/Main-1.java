package io.github.picodotdev.blgbitix.javasql;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        DriverManager.drivers().forEach(d -> {
            System.out.printf("Driver: %s%n", d.getClass().getName());
        });

        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:database", "sa", "")) {
            connection.setAutoCommit(false);

            ...
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
