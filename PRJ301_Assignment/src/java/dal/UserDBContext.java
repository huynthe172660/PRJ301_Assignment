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
import model.Role;
import model.User;

/**
 *
 * @author AD
 */
public class UserDBContext extends DBContext<User> {

    @Override
    public void insert(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void remove(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User get(User model) {
        try {
            String sql = "SELECT username,displayname FROM [User]\n"
                    + "WHERE username = ? AND [password] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, model.getUsername());
            stm.setString(2, model.getPassword());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUsername(model.getUsername());
                user.setDisplayname(rs.getString("displayname"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<User> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Role> getRolesByUsernameAndUrl(String username, String url) {
        ArrayList<Role> roles = new ArrayList<>();
        try {
            String sql = "SELECT r.rid,r.rname FROM \n"
                    + "[User] u INNER JOIN Role_User ru ON ru.username = u.username\n"
                    + "		INNER JOIN [Role] r ON r.rid = ru.rid\n"
                    + "		INNER JOIN [Role_Feature] rf ON rf.rid = r.rid\n"
                    + "		INNER JOIN Feature f ON f.fid = rf.fid\n"
                    + "		WHERE u.username = ? AND f.[url] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, url);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Role r = new Role();
                r.setId(rs.getInt("rid"));
                r.setName(rs.getString("rname"));
                roles.add(r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roles;

    }

}
