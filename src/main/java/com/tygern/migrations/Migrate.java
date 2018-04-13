package com.tygern.migrations;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.flywaydb.core.Flyway;

import java.util.Optional;

import static com.tygern.migrations.JdbcUrlParser.parseUrl;

public class Migrate {
    public static void main(String[] args) {
        Optional<String> jdbcUrl = parseUrl(System.getenv("SERVICE_NAME"), System.getenv("VCAP_SERVICES"));

        if (jdbcUrl.isPresent()) {
            Flyway flyway = createFlyway(jdbcUrl.get());

            flyway.migrate();
        } else {
            throw new RuntimeException("Error parsing VCAP_SERVICES");
        }
    }

    private static Flyway createFlyway(String url) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(url);

        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setOutOfOrder(false);
        return flyway;
    }
}
