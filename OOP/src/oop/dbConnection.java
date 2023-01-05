/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mian
 */
public class dbConnection {
    private static final String oracleURL = "jdbc:oracle:thin:@localhost:1522:XE";
    private static final String user = "system";
    private static final String pass = "oracle";
    public Connection c= null;
    public dbConnection(){
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        } catch (SQLException e) {
            System.err.println("Unable to load driver.");
        }
        try{
            c = DriverManager.getConnection(oracleURL,user, pass);
        } catch (SQLException e) {
            System.out.println("SQL exception: " + e.getMessage());
            System.out.println("SQL state " + e.getSQLState());
        }
    }
    public void create_table() throws SQLException{
        String user;
        user = "CREATE TABLE Users("
                + "user_name varchar2(20) primary key,"
                + "password varchar2(20))";
        String event;

        System.out.println("user table created");
        event = "CREATE TABLE Event ("
                + "event_id varchar2(10),"
                + "title varchar2(50), "
                + "description varchar2(300), "
                + "event_date varchar2(50),"
                + "start_time varchar2(50),"
                + "end_time varchar2(50), "
                + "user_name varchar2(20),"
                + "event_type varchar2(10), "
                + "progress varchar2(10),"
                + "participant varchar2(50),"
                + "foreign key(user_name) references users(user_name))";
        String messages;
        messages = "CREATE TABLE MESSAGES("
                + "user_name varchar2(20),"
                + "notification varchar2(200),"
                + "notify_date varchar2(20),"
                + "notify_time varchar2(20))";
        
        Statement s = c.createStatement();
        s.execute(user);
        s.execute(event);
        s.execute(messages);
        s.close();
    }

    void insert_data(String event_id, String title, String description, String e_date, String start, String end, String user, String type, String progress, String usr_lst_str) throws SQLException {
        PreparedStatement s = c.prepareStatement("INSERT INTO EVENT VALUES (?,?,?,?,?,?,?,?,?,?)");
        s.setString(1, event_id);
        s.setString(2, title);
        s.setString(3, description);
        s.setString(4, e_date);
        s.setString(5, start);
        s.setString(6, end);
        s.setString(7, user);
        s.setString(8, type);
        s.setString(9, progress);
        s.setString(10, usr_lst_str);
        s.executeUpdate();
        s.close();
    }
    public ResultSet execute_query(String sql) throws SQLException{
        Statement s = c.createStatement();
        s.execute(sql);
        ResultSet res = s.getResultSet();
        return res;
    }
}
