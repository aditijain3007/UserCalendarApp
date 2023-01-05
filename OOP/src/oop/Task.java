/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Mian
 */
public class Task extends Event {
    String progress = "0";

    public void setProgress(String val) {
        progress = val;
    }

    public void update(dbConnection c, String old_title) throws SQLException {
        String sql = String.format("UPDATE event SET title = '%s', description = '%s', event_date = '%s', user_name = '%s', progress = '%s', participant = '%s' WHERE title = '%s' AND user_name = '%s'",
                title, description, event_date, user_name, progress, user_name, old_title, user_name);
        ResultSet res = c.execute_query(sql);
        if (res != null) res.close();
    }

}
