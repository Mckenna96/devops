package com.napier.sem;

import com.napier.sem.db_models.Country;
import com.napier.sem.db_models.City;

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
//Country reports
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

    //City reports

    public List<City> getCities()
    {
        List<City> cities = new ArrayList<>();

        try
        {
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT Name, CountryCode, District, Population " +
                            "FROM city " +
                            "ORDER BY name ASC"

            );

            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                City city = new City();

                city.name = rs.getString("Name");
                city.countryCode = rs.getString("CountryCode");
                city.district = rs.getString("District");
                city.population = rs.getInt("Population");

                cities.add(city);

                System.out.println(
                        city.name + " | " + city.countryCode + " | " + city.district + " | " + city.population
                );
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return cities;
    }


    public List<City> getCapitalsByPop()
    {
        List<City> capitalsByPop = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT city.ID, city.Name, city.Population " +
                            "FROM country " +
                            "JOIN city ON country.Capital = city.ID " +
                            "ORDER BY city.Population DESC"
                    );

                    ResultSet rs = stmt.executeQuery();

                    while (rs.next())
                    {
                        City capital = new City();

                        capital.ID = rs.getInt("ID");
                        capital.name = rs.getString("Name");
                        capital.population = rs.getInt("Population");

                        capitalsByPop.add(capital);

                        System.out.println(
                                capital.name + " : " + capital.population
                        );

                    }
                }
                catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }
                return capitalsByPop;
            }


public void getCitiesByPop()
{
    List<City> citiesByPop = new ArrayList<>();
    try
    {
        PreparedStatement stmt = con.prepareStatement(
                "SELECT Name, Population " +
                        "FROM city " +
                        "ORDER BY Population DESC"
        );

        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            System.out.println(
                    rs.getString("Name") + " : " + rs.getInt("Population"));

            City city = new City();
            city.name = rs.getString("Name");
            city.population = rs.getInt("Population");

            citiesByPop.add(city);

        }
    }
    catch (SQLException e)
    {
        System.out.println(e.getMessage());
    }
}


}