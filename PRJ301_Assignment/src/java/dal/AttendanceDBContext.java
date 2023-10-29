/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;
import model.Session;
import model.Student;

/**
 *
 * @author AD
 */
public class AttendanceDBContext extends DBContext<Attendance> {

        public ArrayList<Attendance> getAttendancesBySession(int sesid) {
        ArrayList<Attendance> atts = new ArrayList<>();
        try {
            String sql = "SELECT s.stuid,s.stuname,\n" +
"	  ISNULL(a.status,0) as [status]\n" +
"	  ,ISNULL(a.comment,'') as [comment],\n" +
"	   ISNULL(a.date_time,GETDATE()) as date_time\n" +
"	  FROM [Session] ses INNER JOIN [Group] g ON g.gid = ses.gid\n" +
"									INNER JOIN Group_Student gs ON g.gid = gs.gid\n" +
"									INNER JOIN Student s ON s.stuid = gs.stuid\n" +
"									LEFT JOIN Attendance a ON a.sesid = ses.sesid AND s.stuid = a.stuid\n"
                    + "	  WHERE ses.sesid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sesid);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Attendance att = new Attendance();
                Student s = new Student();
                Session ses = new Session();
                s.setId(rs.getInt("stuid"));
                s.setName(rs.getString("stuname"));
                att.setStudent(s);
                ses.setId(sesid);
                att.setSession(ses);
                att.setStatus(rs.getBoolean("status"));
                att.setComment(rs.getString("comment"));
                att.setDatetime(rs.getTimestamp("date_time"));
                atts.add(att);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return atts;
    }
    @Override
    public ArrayList<Attendance> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
