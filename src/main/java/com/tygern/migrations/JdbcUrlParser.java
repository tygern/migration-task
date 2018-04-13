package com.tygern.migrations;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Optional;

public class JdbcUrlParser {
    public static Optional<String> parseUrl(String serviceName, String vcapServices) {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode root;

        try {
            root = objectMapper.readTree(vcapServices);
        } catch (IOException e) {
            return Optional.empty();
        }

        JsonNode node = root.path(serviceName).path(0).path("credentials").path("jdbcUrl");

        if (node.isMissingNode()) {
            return Optional.empty();
        } else {
            return Optional.of(node.asText());
        }

    }
}
