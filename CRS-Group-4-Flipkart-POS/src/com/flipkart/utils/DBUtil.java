package com.flipkart.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtil {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/CRSDatabase";
    static final String USER = "root";
    static final String PASS = "rootuser";

    public static Connection getConnection() {

        Connection conn = null;
        PreparedStatement stmt = null;

        try{

            // Step 3 Regiater Driver here and create connection

            Class.forName("com.mysql.jdbc.Driver");

            // Step 4 make/open  a connection

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //stmt = conn.prepareStatement(sql);
            //stmt.close();
            //conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }/*finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }*///end try

           /* if (connection != null) {
            try {
                if (connection.isClosed()) {
                    //System.out.println("Connection was closed...");
                    connection = null;
                    return getConnection();
                } else {
                    //System.out.println("Connection good...");
                    return connection;
                }
            } catch (SQLException e) {
                //System.out.println("Error2345: " + e.getMessage());
                //e.printStackTrace();
                //connection = null;
                return getConnection();
            }
        } else {

                //System.out.println("Connection was NULL...");
            static final
                /*Properties prop = new Properties();
                InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("./config.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                //System.out.println(driver + url + user + password);
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);*/

            return conn;
        }

    }



