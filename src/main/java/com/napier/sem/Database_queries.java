package com.napier.sem;

import com.napier.sem.db_models.Country;

import java.sql.*;


public class Database_queries
{
    private Connection con;
    public Database_queries(Connection con)
    {
        this.con = con;
    }


    public Country getCountry(String name)
    {
    try
    {
        PreparedStatement stmt = con.prepareStatement(
                "SELECT Name, Code, Population, Capital " +
                        "FROM country WHERE Name = ?");

        stmt.setString(1, name);

        ResultSet rs = stmt.executeQuery();

        if (rs.next())
        {
            Country country = new Country();

            country.code = rs.getString("Code");
            country.name = rs.getString("Name");
            country.population = rs.getInt("Population");
            country.capital = rs.getInt("Capital");

            return country;
        }
    }
    catch (SQLException e)
    {
        System.out.println(e.getMessage());
    }
    return null;
    }
}

