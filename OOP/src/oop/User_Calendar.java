/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop;

/**
 *
 * @author Mian
 */
import java.sql.*;
public class User_Calendar {
    private String user_name;
    private String password;

    
    public User_Calendar(){
        
    }
    public void set_user_name(String name){
        user_name = name;
    }
    public ResultSet get_tasks(dbConnection c, String selected_day) throws SQLException{
        String sql = String.format("SELECT * FROM EVENT WHERE user_name = '%s' AND event_type = 'Task' AND event_date = '%s' ", user_name, selected_day);
//        System.out.println(sql);
        ResultSet res = c.execute_query(sql);
        return res;
    }
    public ResultSet get_meetings(dbConnection c, String selected_day) throws SQLException{
        String sql = String.format("SELECT * FROM EVENT WHERE user_name = '%s' AND event_type = 'Meeting' AND event_date = '%s' ", user_name, selected_day);
//        System.out.println(sql);
        ResultSet res = c.execute_query(sql);
        return res;
    }
    public ResultSet get_messages(dbConnection c, String selected_day) throws SQLException{
        String sql = String.format("SELECT * FROM MESSAGES WHERE user_name = '%s' AND notify_date = '%s' ", user_name, selected_day);
//        System.out.println(sql);
        ResultSet res = c.execute_query(sql);
        return res;
    }
    
}
