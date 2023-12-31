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
import model.Group;
import model.Instructor;
import model.Student;

/**
 *
 * @author AD
 */
public class GroupDBContext extends DBContext<Group> {
    
    public ArrayList<Group> getGroupByInsId(int insid){
        ArrayList<Group> gs = new ArrayList<>();
        try {
            String sql  = "SELECT i.insid, i.insname, g.gid, g.gname FROM Instructor i JOIN [GROUP] g ON i.insid = g.insid WHERE i.insid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, insid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Instructor i = new Instructor();
                i.setId(insid);
                i.setName(rs.getString("insname"));
                
                Group g = new Group();
                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                g.setSupervisor(i);
                
                gs.add(g);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gs;
    }
    
    public ArrayList<Student> getStudentByGroup(int gid){
        ArrayList <Student> stu = new ArrayList<>();
        
        try {
            String sql = "SELECT gs.gid, s.stuid, s.stuname FROM Group_Student gs JOIN Student s ON gs.stuid = s.stuid WHERE gs.gid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1,gid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Student s = new Student();
                s.setId(rs.getInt("stuid"));
                s.setName(rs.getString("stuname"));
                stu.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stu;
    }

    @Override
    public ArrayList<Group> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
