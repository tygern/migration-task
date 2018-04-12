package com.tygern.migrations;

import org.flywaydb.core.Flyway;

public class Migrate {
    public static void main(String[] args) {
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:mysql://localhost:3306/movies", "root", "");
        flyway.setOutOfOrder(false);
        flyway.migrate();
    }
}
