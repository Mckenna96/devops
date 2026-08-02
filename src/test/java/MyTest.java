package com.napier.sem;

import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import com.napier.sem.db_models.Country;
import com.napier.sem.db_models.City;
import com.napier.sem.db_models.CountryLanguage;

public class MyTest {

    private Connection connection;
    private Database_queries queries;

    @BeforeEach
    void setup() throws Exception {
        String host = System.getenv().getOrDefault("DB_HOST", "localhost");
        String port = System.getenv().getOrDefault("DB_PORT", "33060");

        connection = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":" + port + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");


        queries = new Database_queries(connection);
    }

    @Test
    void testGetCountries() throws Exception {

        List<Country> countries = queries.getCountries();

        assertNotNull(countries);
        assertTrue(countries.size() > 0);

    }

    @Test
    void testGetCountriesByPop() throws Exception {
        List<Country> countries = queries.getCountriesByPop() ;

        assertNotNull(countries);
        assertFalse(countries.isEmpty());

    }

    @Test
    void testGetCities() throws Exception {
        List<City> cities = queries.getCities();

        assertNotNull(cities);
        assertFalse(cities.isEmpty());
    }

    @Test
    void testGetCapitalsByPop() throws Exception {
        List<City> capitalsByPop = queries.getCapitalsByPop();

        assertNotNull(capitalsByPop);
        assertFalse(capitalsByPop.isEmpty());
    }

    @Test
    void testGetCitiesByPop() throws Exception {
        List<City> citiesByPop = queries.getCitiesByPop();

        assertNotNull(citiesByPop);
        assertFalse(citiesByPop.isEmpty());
    }

    @Test
    void testLanguages() throws Exception {
        List<CountryLanguage> languages = queries.getLanguages();

        assertNotNull(languages);
        assertFalse(languages.isEmpty());
    }



    @AfterEach
            void closeConnection() throws Exception {
        connection.close();
    }



}
