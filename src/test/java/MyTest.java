package com.napier.sem;

import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import com.napier.sem.db_models.Country;

public class MyTest {
    @Test
    void testGetCountries() throws Exception {
        String host = System.getenv().getOrDefault("DB_HOST", "localhost");
        String port = System.getenv().getOrDefault("DB_PORT", "33060");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":" + port + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");


        Database_queries queries = new Database_queries(connection);

        List<Country> countries = queries.getCountries();

        assertNotNull(countries);
        assertTrue(countries.size() > 0);

        connection.close();
    }
}
