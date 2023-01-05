/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Mian
 */
public class Event {

    String event_id;
    String title;
    String description;
    String event_date;
    String participant;
    String user_name;
    String type;
    ArrayList<String> user_list = new ArrayList<>();;

    Event(){
    }

    public void setUserName(String user){
        user_name = user;
    }

    public void setDescription(String descp){
        description = descp;
    }

    public void setTitle(String e_title){
        title = e_title;
    }

    public void setEventDate(String e_date){
        event_date = e_date;
    }
    public void setParticipant(String user_list_str){
        participant = user_list_str;
    }
    public void setType(String e_type){
        type = e_type;
    }

    public void generate_users_list(){
        user_list.clear();
        String[] users = participant.split("\\s*,\\s*");
        user_list.addAll(Arrays.asList(users));
    }
    public ArrayList<String>  get_users_list(){
        return user_list;
    }


    public void create(dbConnection c) throws SQLException {
        c.insert_data(event_id, title, description,event_date, null, null, user_name, type,"0",participant);
    }

    public void update(dbConnection c, String old_title) throws SQLException {
        String sql = String.format("UPDATE event SET title = '%s', description = '%s', event_date = '%s', user_name = '%s',  participant = '%s' WHERE title = '%s' AND user_name = '%s'",
                event_id, title, description,event_date, user_name, user_name, old_title,user_name);
        ResultSet res = c.execute_query(sql);
        if (res != null) res.close();
    }
    
    public void delete(dbConnection c) throws SQLException{
        String sql = String.format("DELETE FROM EVENT WHERE user_name = '%s' AND title = '%s' AND event_type ='%s' ",user_name, title, type);
        ResultSet res = c.execute_query(sql);
        if (res != null) res.close();
    }
}