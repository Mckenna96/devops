package com.napier.sem;
//importing models for queries
import com.napier.sem.db_models.Country;
import com.napier.sem.db_models.City;
import com.napier.sem.db_models.CountryLanguage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//database queries class
public class Database_queries {
    private Connection con;

    public Database_queries(Connection con) {
        this.con = con;
    }

    //Country reports
    //countries population report
    public List<Country> getCountriesByPop() {
        List<Country> countriesByPop = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT Name, Population " +
                            "FROM country " +
                            "ORDER BY Population DESC"
            );

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getString("Name") + " : " + rs.getInt("Population"));

                Country country = new Country();
                country.name = rs.getString("Name");
                country.population = rs.getInt("Population");

                countriesByPop.add(country);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return countriesByPop;
    }

    //country report
    public List<Country> getCountries() {
        List<Country> countries = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT country.code, country.name, country.continent, country.region, country.population, city.name AS Capital " +
                            "FROM country " +
                            "LEFT JOIN city ON country.Capital = city.ID " +
                            "ORDER BY country.Name ASC"

            );

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Country country = new Country();
                City capital = new City();

                country.code = rs.getString("Code");
                country.name = rs.getString("Name");
                country.continent = rs.getString("Continent");
                country.region = rs.getString("Region");
                country.population = rs.getInt("Population");
                country.capitalName = rs.getString("Capital");

                countries.add(country);

                System.out.println(
                        country.code + " | " + country.name + " | " + country.continent + " | " + country.region + " | " + country.population + " | " + country.capitalName
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return countries;
    }

    //City reports
//city report
    public List<City> getCities() {
        List<City> cities = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT Name, CountryCode, District, Population " +
                            "FROM city " +
                            "ORDER BY name ASC"

            );

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cities;
    }

//capital population report
    public List<City> getCapitalsByPop() {
        List<City> capitalsByPop = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT city.ID, city.Name, city.Population " +
                            "FROM country " +
                            "JOIN city ON country.Capital = city.ID " +
                            "ORDER BY city.Population DESC"
            );

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                City capital = new City();

                capital.ID = rs.getInt("ID");
                capital.name = rs.getString("Name");
                capital.population = rs.getInt("Population");

                capitalsByPop.add(capital);

                System.out.println(
                        capital.name + " : " + capital.population
                );

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return capitalsByPop;
    }

//cities population report
    public List<City> getCitiesByPop() {
        List<City> citiesByPop = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT Name, Population " +
                            "FROM city " +
                            "ORDER BY Population DESC"
            );

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getString("Name") + " : " + rs.getInt("Population"));

                City city = new City();
                city.name = rs.getString("Name");
                city.population = rs.getInt("Population");

                citiesByPop.add(city);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return citiesByPop;
    }
//language report
//world population and percentage report for languages
    public List<CountryLanguage> getLanguages() {
        List<CountryLanguage> languages = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT countrylanguage.Language, " +
                            " SUM(country.Population * countrylanguage.Percentage / 100) AS Speakers, " +
                            "(SUM(country.population * countrylanguage.Percentage / 100) / " +
                            "(SELECT SUM(population) FROM country)) * 100 AS WorldPercentage " +
                            "FROM country " +
                            "JOIN countrylanguage ON country.Code = countrylanguage.CountryCode " +
                            "WHERE countrylanguage.Language IN ('Chinese', 'English', 'Spanish') " +
                            "GROUP BY countrylanguage.Language " +
                            "ORDER BY Speakers DESC"
            );

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CountryLanguage language = new CountryLanguage();

                language.language = rs.getString("Language");
                language.speakers = rs.getLong("Speakers");
                language.worldPercentage = rs.getDouble("WorldPercentage");

                languages.add(language);

                System.out.println(
                        language.language + " | " + language.speakers + " | " + String.format("%.2f", language.worldPercentage) + "%"
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return languages;
    }
}