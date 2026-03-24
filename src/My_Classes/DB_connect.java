/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package My_Classes;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Chrisitian Dedil
 */
public class DB_connect {
    private static final String URL = "jdbc:mysql://localhost:3306/library_inventory_system ";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static Connection getConnection(){
        Connection cn = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(URL, USER, PASSWORD);
//            JOptionPane.showMessageDialog(null, "connected");
        }catch(ClassNotFoundException | SQLException err){
            JOptionPane.showMessageDialog(null, err);
        }
        return cn;
    }

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
