/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.IBaseModel;

/**
 *
 * @author AD
 */
public abstract class DBContext <T extends IBaseModel> {
    protected Connection connection;
    public DBContext()
    {
        try {
            String url = "jdbc:sqlserver://DESKTOP-078GCOQ\\SQLEXPRESS:1433;databaseName=Assignment_PRJ301";
            String user = "sa";
            String pass = "huycow123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public abstract ArrayList<T> list();
}
