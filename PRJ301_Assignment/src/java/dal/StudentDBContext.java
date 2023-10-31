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
public class StudentDBContext extends DBContext<Student> {

    public ArrayList<Student> getAttendances(int stuid) {
        ArrayList<Student> stu = new ArrayList<>();
        try {
            String sql = "SELECT a.stuid, a.sesid, a.status, s.stuname FROM Attendance a JOIN Student s ON a.stuid = s.stuid WHERE a.stuid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, stuid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();

                Session ses = new Session();
                ses.setId(rs.getInt("sesid"));

                a.setSession(ses);
                a.setStatus(rs.getBoolean("status"));

                Student s = new Student();
                s.setId(rs.getInt("stuid"));
                s.setName("stuname");
                s.getAtt().add(a);
                
                stu.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stu;
    }
    
    

    @Override
    public ArrayList<Student> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
