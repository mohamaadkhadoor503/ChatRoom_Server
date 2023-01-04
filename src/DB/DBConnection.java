/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;
import java.sql.*;

/**
 *
 * @author LENOVO
 */
public class DBConnection {
    static Connection con=null;
   private DBConnection(){
       
   } 
   public static Connection getCon() throws ClassNotFoundException, SQLException{
       
   
           if(con==null){
               Class.forName("com.mysql.cj.jdbc.Driver");
               con=DriverManager.getConnection("jdbc:mysql://localhost/chatroom","root","");
               System.out.println("DB.DBConnection.getCon()");
           }
       return con;
   }
}
