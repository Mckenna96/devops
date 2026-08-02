package com.napier.sem;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


class MyTest
{
    @Test
    void testGetCountries()
    {
        Database_queries queries = new Database_queries();

        List<Country> countries = queries.getCountries();

        assertNotNull(countries);
        assertTrue(countries.size() >0);
    }
}