package org.example.aircraftsystem;


import java.sql.Connection;
import java.sql.DriverManager;

public class database {

    public static Connection connectDb(){

        try{
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Airport", "postgres", "12345");

            return connect;
        }
        catch(Exception e){e.printStackTrace();}
        return null;
    }

}