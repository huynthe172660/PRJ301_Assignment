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
import model.Account;

/**
 *
 * @author AD
 */
public class AccountDBContext extends DBContext<Account> {



    public Account get(Account model) {
        try {
            String sql = "SELECT accid, accname, accpass FROM Account WHERE accname = ? AND accpass = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, model.getAccName());
            stm.setString(2, model.getAccPass());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account user = new Account();
                user.setAccId(rs.getInt("accid"));
                user.setAccName(model.getAccName());
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Account> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}
