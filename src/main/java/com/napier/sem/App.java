package com.napier.sem;

import java.sql.*;


public class App
{
    private Connection con = null;
    public Connection getConnection()
    {
        return con;
    }

    public static void main(String[] args) {
        App a = new App();

        a.connect();

        if (a.getConnection() != null) {

            Database_queries queries = new Database_queries(a.getConnection());
            queries.getCountriesByPop();
        }
        else {
            System.out.println("No database connection found");
        }

        a.disconnect();
    }

        public void connect()
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
            catch (ClassNotFoundException e)
            {
                System.out.println("Could not load SQL driver");
                System.exit(-1);
            }

            int retries = 10;
            for (int i = 0; i < retries; ++i)
            {
                System.out.println("Connecting to database...");
                try
                {
                // Wait a bit for db to start
                Thread.sleep(30000);

                String host = System.getenv().getOrDefault("DB_HOST", "localhost");
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":33060/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
                }
            }
        }

        public void disconnect()
        {
            if (con != null)
                {
                    try
                     {
                         // Close connection
                         con.close();
                      }
                    catch (Exception e)
                    {
                         System.out.println("Error closing connection to database");
                      }
                 }
        }
}