/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Meeting extends Event {
    String start_time="null";
    String end_time="null";
    ArrayList<String> old_mem_list = new  ArrayList<>();

    public void setStartTime(String start){
        start_time = start;
    }
    public void setEndTime(String end){
        end_time = end;
    }
    public boolean create(dbConnection c, String users_lst_str) throws SQLException, ParseException{
        if ( !check_availability(users_lst_str, c)) return false;
        setParticipant(users_lst_str);
        generate_users_list();
        String msg_to_deliver = String.format("Meeting %s has been created.",title);
        for (String user : user_list){
            user_name = user;
            c.insert_data(event_id, title, description, event_date, start_time, end_time, user_name, "Meeting", null, participant);
            notify_listener(user, c, msg_to_deliver);
        }
        return true;
    }
    public void get_member_list(String usr){
        String[] users = usr.split("\\s*,\\s*");
        old_mem_list.clear();
        old_mem_list.addAll(Arrays.asList(users));
    }
    
    public boolean update(dbConnection c, String old_participant, String old_title) throws SQLException, ParseException{

        String sql = String.format("title = '%s', description = '%s', event_date = '%s', participant = '%s', start_time = '%s', end_time = '%s'",
                title, description, event_date, participant, start_time,end_time);
        String update_query = "UPDATE event SET " + sql + String.format(" WHERE title = '%s' AND event_type = 'Meeting' AND event_date = '%s'",
                                                                        old_title, event_date);
        String msg = String.format("%s has changed. Updated meeting: title: %s,description: %s,event_date: %s,participant: %s,start_time: %s,end_time: %s",
                                   old_title,title, description,event_date,participant,start_time,end_time);
        String update_sql ;
        generate_users_list(); //get current users list
        if (participant.equals(old_participant)){
            for (String u: user_list){
                user_name = u;
                update_sql =update_query + String.format(" AND user_name = '%s'", u);
                c.execute_query(update_sql);
                notify_listener(u, c, msg);
            }

        }else{
            if (!check_availability(participant, c )) return false;
            get_member_list(old_participant);
            for (String u: user_list){
                user_name = u;
                if (old_mem_list.contains(u)){
                    c.execute_query(update_query);
                    notify_listener(u, c, msg);
                }else{
                    c.insert_data(event_id, title, description, event_date, start_time, end_time, user_name, "Meeting", null, participant);
                    notify_listener(u, c, msg);
                }
            }
            for (String u: old_mem_list){
                user_name = u;
                if (!user_list.contains(u)){

                    delete(c);
                }
            }

        }
        return true;
    }
    
    public void notify_listener(String usr, dbConnection c, String msg_of_update) throws SQLException{
        String date = java.time.LocalDate.now().toString();
        String time = java.time.LocalTime.now().toString();
        String msg_to_delivery = String.format("INSERT INTO messages values('%s', '%s', '%s', '%s' )", usr, msg_of_update, date, time);
        c.execute_query(msg_to_delivery);
    }
    
    public void delete(dbConnection c) throws SQLException{
        String msg_to_deliver = String.format(" %s has been cancelled",title);
        String sql = String.format("DELETE FROM EVENT WHERE user_name = '%s' AND title = '%s' AND event_type = 'Meeting'", user_name, title);
        ResultSet res = c.execute_query(sql);
        notify_listener(user_name, c, msg_to_deliver);
        if (res != null) res.close();
        
    }
    public boolean check_availability(String users_lst, dbConnection c ) throws SQLException, ParseException{
        String[] users = users_lst.split("\\s*,\\s*");

        long s1, e1;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        long s2 = sdf.parse(start_time).getTime();
        long e2= sdf.parse(end_time).getTime();
        for (String u: users){
            String sql = String.format("SELECT start_time, end_time FROM EVENT WHERE event_type ='Meeting' AND user_name = '%s' AND event_date = '%s'", user_name, event_date);
            ResultSet res = c.execute_query(sql);
            if (res != null){
                while (res.next()){
                    s1 = sdf.parse(res.getString("start_time")).getTime();
                    e1 = sdf.parse(res.getString("end_time")).getTime();
                    if (! (e1 <= s2 || s1>=e2)) return false;
                }
                res.close();
            }
        }
        return true;
    }
}
