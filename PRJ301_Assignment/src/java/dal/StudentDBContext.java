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

    public ArrayList<Attendance> getAttendancesByStuid(int stuid) {
        ArrayList<Attendance> att = new ArrayList<>();
        try {
            String sql = "SELECT a.stuid, a.sesid, a.status, s.stuname FROM Attendance a JOIN Student s ON a.stuid = s.stuid WHERE a.stuid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, stuid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();

                Session ses = new Session();
                ses.setId(rs.getInt("sesid"));

                Student s = new Student();
                s.setId(rs.getInt("stuid"));

                a.setSession(ses);
                a.setStatus(rs.getBoolean("status"));
                a.setStudent(s);


                att.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return att;
    }

    @Override
    public ArrayList<Student> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        StudentDBContext sdb = new StudentDBContext();
        ArrayList<Attendance> att = new ArrayList<>();
        att = sdb.getAttendancesByStuid(1);
        System.out.println(att.size());
        
    }

}
