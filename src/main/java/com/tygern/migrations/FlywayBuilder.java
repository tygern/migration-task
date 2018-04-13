package com.tygern.migrations;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.flywaydb.core.Flyway;

class FlywayBuilder {
    private final String jdbcUrl;


    FlywayBuilder(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    Flyway build() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(jdbcUrl);

        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setOutOfOrder(false);
        return flyway;
    }
}
