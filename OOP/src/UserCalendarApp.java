import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import oop.Meeting;
import oop.Task;
import oop.User_Calendar;
import oop.dbConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Mian
 */
public class UserCalendarApp extends javax.swing.JFrame {

    /**
     * Creates new form oop
     */
    boolean is_meeting = false;
    User_Calendar current_user = new User_Calendar();
    Task curr_task = new Task();
    Meeting curr_meeting = new Meeting();
    dbConnection c = new dbConnection();
    DatePicker meet_date = new com.github.lgooddatepicker.components.DatePicker();
    TimePicker meet_start = new com.github.lgooddatepicker.components.TimePicker();
    TimePicker meet_end = new com.github.lgooddatepicker.components.TimePicker();
    String meet_day;
    String checked_day = java.time.LocalDate.now().toString();
    ArrayList<String> users_list = new ArrayList<>();
    String cur_user;
    String title, description, event_type, event_date, participant,progress,start_time,end_time;
    HashMap<String, String> today_meetings = new HashMap<String, String>();
    HashSet<String> reminders = new HashSet<>();
    int task_count = 0;
    int meeting_count = 0;
    public UserCalendarApp() throws SQLException {
        initComponents();
        load_user_data();
        load_user_messages();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        create_btn = new javax.swing.JButton();
        edit_btn = new javax.swing.JButton();
        delete_btn = new javax.swing.JButton();
        type_in = new javax.swing.JComboBox<>();
        create_type_opt = new javax.swing.JLabel();
        edit_del_label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        calendarPanel2 = new com.github.lgooddatepicker.components.CalendarPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        result_table = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        msg_table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        log_out_btn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        stat_table = new javax.swing.JTable();
        title_panel = new javax.swing.JPanel();
        title_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        create_btn.setText("Create");
        create_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_btnActionPerformed(evt);
            }
        });

        edit_btn.setText("Edit");
        edit_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                edit_btnMouseClicked(evt);
            }
        });

        delete_btn.setText("Delete");
        delete_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    delete_btnMouseClicked(evt);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        type_in.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Task", "Meeting" }));

        create_type_opt.setText("Please select a type");
        create_type_opt.setFont(new java.awt.Font("Segoe UI Semibold", 2, 12)); // NOI18N

        edit_del_label.setText("Select a row to edit or delete");
        edit_del_label.setFont(new java.awt.Font("Segoe UI Semibold", 2, 12)); // NOI18N

        jLabel1.setText("Notifications (Read only)");
        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));

        calendarPanel2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calendarPanel2PropertyChange(evt);
            }
        });

        result_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        result_table.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        result_table.setShowHorizontalLines(false);
        result_table.setShowVerticalLines(false);
        jScrollPane2.setViewportView(result_table);

        msg_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(msg_table);

        jLabel2.setText("Tasks && Meetings");
        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));

        log_out_btn.setText("LogOut");
        log_out_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                log_out_btnMouseClicked(evt);
            }
        });

        jLabel4.setText("Today's Statistics");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 153));

        stat_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(stat_table);

        title_label.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        title_label.setText("Personal Calendar");

        javax.swing.GroupLayout title_panelLayout = new javax.swing.GroupLayout(title_panel);
        title_panel.setLayout(title_panelLayout);
        title_panelLayout.setHorizontalGroup(
            title_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, title_panelLayout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(title_label, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
        title_panelLayout.setVerticalGroup(
            title_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(title_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title_label, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(edit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(create_type_opt, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(type_in, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(edit_del_label, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(create_btn)
                                            .addComponent(delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(calendarPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(title_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(640, 640, 640)
                        .addComponent(log_out_btn))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(title_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(calendarPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(create_type_opt)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(type_in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(create_btn))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(log_out_btn)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edit_del_label, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edit_btn)
                            .addComponent(delete_btn))
                        .addGap(38, 38, 38)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 66, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setCurrent_user(String user){
        cur_user = user;
    }

    public void meeting_reminder() throws SQLException, ParseException {
        String current_time = java.time.LocalTime.now().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        long cur_time = sdf.parse(current_time).getTime();
        String m_title, m_time;
        for (Map.Entry m: today_meetings.entrySet()){
            m_title = m.getKey().toString();
            m_time = m.getValue().toString();
            long meet = sdf.parse(m_time).getTime();
            long diff = (meet-cur_time)/60000;
            if (0 < diff && diff <15 && !reminders.contains(m_title)){
                reminders.add(m_title);
                JOptionPane.showMessageDialog(null, String.format("%s is up in %s minutes", m_title, diff));
            }
        }
    }
    private void load_user_data() throws SQLException{

        title_label.setText(String.format(" %s Personal Calendar", cur_user));
        current_user.set_user_name(cur_user);
        today_meetings.clear();
        reminders.clear();
        ResultSet tasks = current_user.get_tasks(c, checked_day);
        DefaultTableModel model = new DefaultTableModel();
        result_table.setModel(model);
        model.addColumn("Title");
        model.addColumn("Description");
        model.addColumn("Type");
        model.addColumn("Event Date");
        model.addColumn("Progress");
        model.addColumn("Start Time");
        model.addColumn("End Time");
        model.addColumn("Participant");

        task_count = 0;
        while (tasks.next()){
            String task_title = tasks.getString("title");
            String task_desc= tasks.getString("description");
            String task_date = tasks.getString("event_date");
            String task_state = tasks.getString("progress");
            String task_users = tasks.getString("participant");
            model.addRow(new Object[]{task_title, task_desc,"Task",task_date, task_state,null,null,task_users});
            task_count ++;
        }

        ResultSet meetings = current_user.get_meetings(c, checked_day);
        HashSet<String> meets = new HashSet<String>();
        meeting_count =0;
        while (meetings.next()){
            String meeting_title = meetings.getString("title");
            String meeting_desc = meetings.getString("description");
            String meeting_date = meetings.getString("event_date");
            String meeting_start = meetings.getString("start_time");
            String meeting_end = meetings.getString("end_time");
            String meeting_membr = meetings.getString("participant");

            if (!meets.contains(meeting_title)){
                model.addRow(new Object[]{meeting_title,meeting_desc,"Meeting", meeting_date,null,meeting_start,meeting_end,meeting_membr}); 
                meeting_count ++;
            }
            meets.add(meeting_title);
            today_meetings.put(meeting_title, meeting_start);
        }
        tasks.close();
        meetings.close();
        DefaultTableModel stat = new DefaultTableModel();
        stat_table.setModel(stat);
        stat.addColumn("Type");
        stat.addColumn("Count");
        stat.addRow(new Object[]{"Tasks", String.valueOf(task_count)});
        stat.addRow(new Object[]{"Meetings", String.valueOf(meeting_count)});

    }
    
    private void load_user_messages() throws SQLException{
        current_user.set_user_name(cur_user);
        ResultSet msgs = current_user.get_messages(c,checked_day);
        DefaultTableModel model = new DefaultTableModel();
        msg_table.setModel(model);
        model.addColumn("Messages");
        model.addColumn("Notification Date");
        model.addColumn("Notification Time");
        while (msgs.next()){
            String msg_content = msgs.getString("notification");
            String msg_date= msgs.getString("notify_date");
            String msg_time= msgs.getString("notify_time");
            model.addRow(new Object[]{msg_content, msg_date, msg_time}); 
        }
        if (msgs != null) msgs.close();
    }    
    

    private void create_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_btnActionPerformed
        // TODO add your handling code here:
        is_meeting = type_in.getSelectedItem().toString().equals("Meeting"); 
        JTextField e_title = new JTextField();
        JTextField descp = new JTextField();
        JTextField usr = new JTextField();

        if (is_meeting){
            Object[] meeting_fields = {
                "Title", e_title,
                "Description", descp,
                "Invite User (Separate usrs by ',')", usr,
                "Start Time", meet_start,
                "End Time", meet_end
             
            };
            
            JOptionPane.showConfirmDialog(null, meeting_fields, "Enter the meeting data", JOptionPane.OK_CANCEL_OPTION); 

            String start = meet_start.getTimeStringOrEmptyString();
            String end = meet_end.getTimeStringOrEmptyString();
            if (e_title.getText().isEmpty() || descp.getText().isEmpty() || usr.getText().isEmpty()
                    || start.isEmpty() || end.isEmpty()) JOptionPane.showMessageDialog(null, "Enter all the infor");
            String usrs = usr.getText();
            curr_meeting.setTitle(e_title.getText());
            curr_meeting.setUserName(cur_user);
            curr_meeting.setDescription(descp.getText());
            curr_meeting.setEventDate(checked_day);
            curr_meeting.setStartTime(start);
            curr_meeting.setEndTime(end);
            curr_meeting.setType("Meeting");

            try {
                try {
                    boolean create_success = curr_meeting.create(c, usrs);
                    if (!create_success){
                        JOptionPane.showMessageDialog(null, "Fail to create meeting, timing conflict, choose another time.");
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(UserCalendarApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserCalendarApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{

            Object[] task_fields = {
                "Title", e_title,
                "Description", descp
            };
            JOptionPane.showConfirmDialog(null, task_fields, "Enter the task info", JOptionPane.OK_CANCEL_OPTION);
            if (e_title.getText().isEmpty() || descp.getText().isEmpty()) JOptionPane.showMessageDialog(null, "Enter all the infor");

            curr_task.setTitle(e_title.getText());
            curr_task.setDescription(descp.getText());
            curr_task.setEventDate(checked_day);
            curr_task.setUserName(cur_user);
            curr_task.setParticipant(cur_user);
            curr_task.setType("Task");

            try {
                curr_task.create(c);
            } catch (SQLException ex) {
                Logger.getLogger(UserCalendarApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            load_user_data();
        } catch (SQLException ex) {
            Logger.getLogger(UserCalendarApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            load_user_messages();
        } catch (SQLException ex) {
            Logger.getLogger(UserCalendarApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_create_btnActionPerformed


    private void edit_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_btnMouseClicked
        // TODO add your handling code here:
        /*        DefaultTableModel model = new DefaultTableModel();
 */
        boolean meeting_change = result_table.getValueAt(result_table.getSelectedRow(),2).toString().equals("Meeting");
        String old_title, old_participant;
        old_title = result_table.getValueAt(result_table.getSelectedRow(),0).toString();
        description = result_table.getValueAt(result_table.getSelectedRow(),1).toString();
        event_type = result_table.getValueAt(result_table.getSelectedRow(),2).toString();
        event_date = result_table.getValueAt(result_table.getSelectedRow(),3).toString();
        old_participant = result_table.getValueAt(result_table.getSelectedRow(),7).toString();


        var title_cb = new JCheckBox();
        var descp_cb = new JCheckBox();
        var usr_cb = new JCheckBox();
        var progress_cb = new JCheckBox();
        JTextField e_title = new JTextField();
        JTextField descp = new JTextField();
        JTextField usr = new JTextField();
        JTextField progr = new JTextField();
        e_title.setText(old_title);
        descp.setText(description);
        usr.setText(old_participant);
        meet_date.setDate(LocalDate.parse(event_date));

        if (meeting_change){
            start_time = result_table.getValueAt(result_table.getSelectedRow(),5).toString();
            end_time = result_table.getValueAt(result_table.getSelectedRow(),6).toString();
            meet_start.setText(start_time);
            meet_end.setText(end_time);
            Object[] changes_fields = {
                    title_cb,"Title", e_title,
                    descp_cb,"Description", descp,
                    usr_cb,"Invite User (Separate usrs by ',')", usr,
                    "Meeting Date", meet_date,
                    "Start Time", meet_start,
                    "End Time", meet_end
             
            };
            JOptionPane.showConfirmDialog(null, changes_fields, "change the event", JOptionPane.OK_CANCEL_OPTION);

            start_time = result_table.getValueAt(result_table.getSelectedRow(),5).toString();
            end_time = result_table.getValueAt(result_table.getSelectedRow(),6).toString();
            meet_day = meet_date.getDateStringOrEmptyString();
            String start = meet_start.getTimeStringOrEmptyString();
            String end = meet_end.getTimeStringOrEmptyString();

            if (title_cb.isSelected()) {
                title = e_title.getText();
            }else{
                title = old_title;
            }
            if (descp_cb.isSelected()) description = descp.getText();
            if (usr_cb.isSelected()) {
                participant = usr.getText();
            }else{
                participant = old_participant;
            }
            if (!meet_day.isEmpty()) event_date = meet_day;
            if (! start.isEmpty()) start_time = start;
            if (! end.isEmpty()) end_time = end;

            curr_meeting.setTitle(title);
            curr_meeting.setUserName(cur_user);
            curr_meeting.setDescription(description);
            curr_meeting.setEventDate(event_date);
            curr_meeting.setStartTime(start_time);
            curr_meeting.setEndTime(end_time);
            curr_meeting.setParticipant(participant);
            curr_meeting.setType("Meeting");


            try {
                curr_meeting.update(c, old_participant,old_title);
            } catch (SQLException ex) {
                Logger.getLogger(UserCalendarApp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(UserCalendarApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else{
        Object[] changes_fields = {
                title_cb,"Title", e_title,
                descp_cb, "Description", descp,
                progress_cb,
                "Progress (range 0-100)", progr,
                "Meeting Date", meet_date
            };
            JOptionPane.showConfirmDialog(null, changes_fields, "change the event", JOptionPane.OK_CANCEL_OPTION);
            progress = result_table.getValueAt(result_table.getSelectedRow(),4).toString();
            meet_day = meet_date.getDateStringOrEmptyString();
            if (title_cb.isSelected()) {
                title = e_title.getText();
            }else{
                title = old_title;
            }
            if (descp_cb.isSelected()) description = descp.getText();
            if (progress_cb.isSelected()) progress = progr.getText();
            if (!meet_day.isEmpty()) event_date = meet_day;

            curr_task.setTitle(title);
            curr_task.setDescription(description);
            curr_task.setEventDate(checked_day);
            curr_task.setUserName(cur_user);
            curr_task.setProgress(progress);
            curr_task.setType("Task");

            try {
                curr_task.update(c, old_title);
                } catch (SQLException ex) {
                    Logger.getLogger(UserCalendarApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        try {
            load_user_data();
        } catch (SQLException ex) {
            Logger.getLogger(UserCalendarApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            load_user_messages();
        } catch (SQLException ex) {
            Logger.getLogger(UserCalendarApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_edit_btnMouseClicked

    private void log_out_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_log_out_btnMouseClicked
        // TODO add your handling code here:
        userLogin login = new userLogin();
        login.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_log_out_btnMouseClicked

    private void delete_btnMouseClicked(java.awt.event.MouseEvent evt) throws SQLException {//GEN-FIRST:event_delete_btnMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = new DefaultTableModel();
        boolean meeting_delete = result_table.getValueAt(result_table.getSelectedRow(),2).toString().equals("Meeting");
        title = result_table.getValueAt(result_table.getSelectedRow(),0).toString();
        participant = result_table.getValueAt(result_table.getSelectedRow(),7).toString();


        if (meeting_delete){
            curr_meeting.setTitle(title);
            curr_meeting.generate_users_list();
            for (String user: curr_meeting.get_users_list()){
                curr_meeting.setUserName(user);
                curr_meeting.delete(c);
            }
        } else{
            curr_task.setUserName(cur_user);
            curr_task.setTitle(title);
            curr_task.setType("Task");
            try {
                curr_task.delete(c);
            } catch (SQLException ex) {
                Logger.getLogger(UserCalendarApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            load_user_data();
        } catch (SQLException ex) {
            Logger.getLogger(UserCalendarApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            load_user_messages();
        } catch (SQLException ex) {
            Logger.getLogger(UserCalendarApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_delete_btnMouseClicked

    private void calendarPanel2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendarPanel2PropertyChange
        // TODO add your handling code here:
        
        LocalDate selected_day = calendarPanel2.getSelectedDate();
        if (selected_day == null) {
            checked_day = java.time.LocalDate.now().toString();
            try {
                load_user_data();
            } catch (SQLException ex) {
                Logger.getLogger(UserCalendarApp.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                load_user_messages();
            } catch (SQLException ex) {
                Logger.getLogger(UserCalendarApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{

            checked_day = selected_day.toString();
            try {
                load_user_data();
            } catch (SQLException ex) {
                Logger.getLogger(UserCalendarApp.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                load_user_messages();
            } catch (SQLException ex) {
                Logger.getLogger(UserCalendarApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }//GEN-LAST:event_calendarPanel2PropertyChange

    
    /**
     * @param args the command line arguments
     */

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserCalendarApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserCalendarApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserCalendarApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserCalendarApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new UserCalendarApp().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(UserCalendarApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.github.lgooddatepicker.components.CalendarPanel calendarPanel2;
    private javax.swing.JButton create_btn;
    private javax.swing.JLabel create_type_opt;
    private javax.swing.JButton delete_btn;
    private javax.swing.JButton edit_btn;
    private javax.swing.JLabel edit_del_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton log_out_btn;
    private javax.swing.JTable msg_table;
    private javax.swing.JTable result_table;
    private javax.swing.JTable stat_table;
    private javax.swing.JLabel title_label;
    private javax.swing.JPanel title_panel;
    private javax.swing.JComboBox<String> type_in;
    // End of variables declaration//GEN-END:variables
}