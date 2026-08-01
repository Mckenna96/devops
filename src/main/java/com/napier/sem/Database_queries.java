package com.napier.sem;

import com.napier.sem.db_models.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Database_queries
{
    private Connection con;
    public Database_queries(Connection con)
    {
        this.con = con;
    }


    public List<Country> getCountriesByPop()
    {
        List<Country> countriesByPop = new ArrayList<>();
        try
    {
        PreparedStatement stmt = con.prepareStatement(
                "SELECT Name, Population " +
                        "FROM country " +
                        "ORDER BY Population DESC"
                );

        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            System.out.println(
                    rs.getString("Name") + " : " + rs.getInt("Population"));

            Country country = new Country();
            country.name = rs.getString("Name");
            country.population = rs.getInt("Population");

            countriesByPop.add(country);

        }
    }
    catch (SQLException e)
    {
        System.out.println(e.getMessage());
    }
    return countriesByPop;
    }
}

