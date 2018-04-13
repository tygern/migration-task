package com.tygern.migrations;

import org.flywaydb.core.Flyway;

import java.util.Optional;

import static com.tygern.migrations.JdbcUrlParser.parseUrl;

public class Migrate {
    public static void main(String[] args) {
        Optional<String> jdbcUrl = parseUrl(System.getenv("SERVICE_NAME"), System.getenv("VCAP_SERVICES"));

        if (!jdbcUrl.isPresent()) {
            throw new RuntimeException("Error finding database credentials");
        }

        Flyway flyway = new FlywayBuilder(jdbcUrl.get()).build();
        flyway.migrate();
    }
}
