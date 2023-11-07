/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;
import model.Group;
import model.Room;
import model.Session;
import model.Subject;
import model.TimeSlot;

/**
 *
 * @author AD
 */
public class SessionDBContext extends DBContext<Session> {

    public ArrayList<Session> getSessions(int iid, Date from, Date to) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = "SELECT  \n"
                    + "	ses.sesid,ses.[date],ses.[index],ses.isAtt,r.rid,r.rname,sub.subid,sub.subname,g.gid,g.gname,t.tid,t.[description]\n"
                    + "FROM [Session] ses INNER JOIN [Group] g ON ses.gid = g.gid\n"
                    + "							INNER JOIN [Subject] sub ON g.subid = sub.subid\n"
                    + "							INNER JOIN Room r ON r.rid = ses.rid\n"
                    + "							INNER JOIN TimeSlot t ON ses.tid = t.tid\n"
                    + "WHERE ses.insid = ? AND ses.[date] >= ? AND ses.[date] <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, iid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session session = new Session();
                session.setId(rs.getInt("sesid"));
                session.setDate(rs.getDate("date"));
                session.setIndex(rs.getInt("index"));
                session.setIsAtt(rs.getBoolean("isAtt"));
                Room r = new Room();
                r.setName(rs.getString("rname"));
                session.setRoom(r);

                Group g = new Group();
                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                session.setGroup(g);

                Subject sub = new Subject();
                sub.setId(rs.getInt("subid"));
                sub.setName(rs.getString("subname"));
                g.setSubject(sub);

                TimeSlot time = new TimeSlot();
                time.setId(rs.getInt("tid"));
                time.setDescription(rs.getString("description"));
                session.setSlot(time);

                sessions.add(session);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;
    }

    public Session getSessions(int sesid) {
        try {
            String sql = "SELECT  \n"
                    + "	ses.sesid,ses.[date],ses.[index],ses.isAtt,r.rid,r.rname,sub.subid,sub.subname,g.gid,g.gname,t.tid,t.[description]\n"
                    + "FROM [Session] ses INNER JOIN [Group] g ON ses.gid = g.gid\n"
                    + "							INNER JOIN [Subject] sub ON g.subid = sub.subid\n"
                    + "							INNER JOIN Room r ON r.rid = ses.rid\n"
                    + "							INNER JOIN TimeSlot t ON ses.tid = t.tid\n"
                    + "WHERE ses.sesid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sesid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session session = new Session();
                session.setId(rs.getInt("sesid"));
                session.setDate(rs.getDate("date"));
                session.setIndex(rs.getInt("index"));
                session.setIsAtt(rs.getBoolean("isAtt"));
                Room r = new Room();
                r.setName(rs.getString("rname"));
                session.setRoom(r);

                Group g = new Group();
                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                session.setGroup(g);

                Subject sub = new Subject();
                sub.setId(rs.getInt("subid"));
                sub.setName(rs.getString("subname"));
                g.setSubject(sub);

                TimeSlot time = new TimeSlot();
                time.setId(rs.getInt("tid"));
                time.setDescription(rs.getString("description"));
                session.setSlot(time);
                return session;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addAttendances(Session ses) {
        try {
            connection.setAutoCommit(false);
            //Update isAtt
            String sql_update_isAtt = "UPDATE Session SET isAtt = 1 WHERE sesid = ?";
            PreparedStatement stm_update_isAtt = connection.prepareStatement(sql_update_isAtt);
            stm_update_isAtt.setInt(1, ses.getId());
            stm_update_isAtt.executeUpdate();

            //Remove existing attendences
            String sql_remove_atts = "DELETE Attendance WHERE sesid = ?";
            PreparedStatement stm_remove_atts = connection.prepareStatement(sql_remove_atts);
            stm_remove_atts.setInt(1, ses.getId());
            stm_remove_atts.executeUpdate();

            //Insert attendences 
            for (Attendance att : ses.getAtts()) {
                String sql_insert_att = "INSERT INTO [Attendance]\n"
                        + "           ([stuid]\n"
                        + "           ,[sesid]\n"
                        + "           ,[status]\n"
                        + "           ,[comment]\n"
                        + "           ,[date_time])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,GETDATE())";
                PreparedStatement stm_insert_att = connection.prepareStatement(sql_insert_att);
                stm_insert_att.setInt(1, att.getStudent().getId());
                stm_insert_att.setInt(2, ses.getId());
                stm_insert_att.setBoolean(3, att.isStatus());
                stm_insert_att.setString(4, att.getComment());
                stm_insert_att.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Session> getSessionByGroup(int gid) {
        ArrayList<Session> ses = new ArrayList<>();

        PreparedStatement stm;
        try {
            String sql = "  SELECT sesid, gid, [index] FROM Session WHERE gid = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, gid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Session s = new Session();
                s.setId(rs.getInt("sesid"));
                s.setIndex(rs.getInt("index"));
                
                Group g = new Group();
                g.setId(rs.getInt("gid"));
                s.setGroup(g);
                ses.add(s);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ses;
    }

    @Override
    public ArrayList<Session> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    public static void main(String[] args) {
//        Session s = new Session();
//        s.setId(2);
//        s.setIsAtt(false);
//        SessionDBContext sdb = new SessionDBContext();
//        sdb.addAttendances(s);
//        System.out.println(s.isIsAtt());
//        
//    }
}
