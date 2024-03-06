package com.alta;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootTest
@Testcontainers
@ActiveProfiles(profiles = "test")
public abstract class AbstractDataBase {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:15-alpine")
                    .withDatabaseName("alta_test_db")
                    .withUsername("alta_test")
                    .withPassword("alta_test");

    static {
        postgreSQLContainer.start();
        initSqlScript();
    }

    private static void initSqlScript() {
        String url = postgreSQLContainer.getJdbcUrl();
        String user = postgreSQLContainer.getUsername();
        String pass = postgreSQLContainer.getPassword();

        try(Connection connection = DriverManager.getConnection(url, user, pass)) {
            String filePath = Paths.get("static", "sql", "test_data.sql").toString();
            ClassPathResource classPathResource = new ClassPathResource(filePath);
            ScriptUtils.executeSqlScript(connection, classPathResource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
