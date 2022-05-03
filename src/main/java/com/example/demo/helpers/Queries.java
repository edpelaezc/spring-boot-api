package com.example.demo.helpers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.stream.Collectors;
import java.sql.*;

public class Queries {
    /*public static String getAllGenres() throws ClassNotFoundException, SQLException {
        // create our mysql database connection
        String myDriver = "org.gjt.mm.mysql.Driver";
        String myUrl = "jdbc:mysql://http://recomendation-project-database.cajn3aappwsy.us-east-1.rds.amazonaws.com/recomendation_project_database";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "admin", "abc123**");

        // our SQL SELECT query.
        // if you only need a few columns, specify them by name instead of using "*"
        String query = "SELECT * FROM MOVIES_TABLE;";

        // create the java statement
        Statement st = conn.createStatement();

        // execute the query, and get a java resultset
        ResultSet rs = st.executeQuery(query);

        // iterate through the java resultset
        while (rs.next())
        {
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            Date dateCreated = rs.getDate("date_created");
            boolean isAdmin = rs.getBoolean("is_admin");
            int numPoints = rs.getInt("num_points");

            // print the results
            System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints);
        }
        st.close();
        return "";
    }*/

    public static String[] getAllGenres(){
        var response = new String[] { "Action", "Adventure", "Animation", "Biography", "Comedy", "Crime", "Documentary",
                                      "Drama", "Family", "Fantasy", "Film-Noir", "Game-Show", "History", "Horror", "Music",
                                      "Musical", "Mystery", "News", "Reality-TV", "Romance", "Sci-Fi", "Short", "Sport",
                                      "Thriller", "War", "Western"
                                    };
        return response;
    }
}
