/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import DB.DBConnection;
import static Server.Main.con;
import java.rmi.server.UnicastRemoteObject;
import clientInterfaces.clientInterface;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import serverInterfaces.ChatInterface;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class ChatService extends UnicastRemoteObject implements ChatInterface {

    ArrayList<clientInterface> clients;
    ArrayList<clientInterface> clients_group;
    ArrayList<String> users_id;
    public static ArrayList<Room> rooms;

    public ChatService() throws RemoteException, ClassNotFoundException, SQLException {
        clients = new ArrayList<>();
        clients_group = new ArrayList<>();
    }

    @Override
    public void sign(clientInterface c) throws RemoteException {
        clients.add(c);

    }

    @Override
    public void sendToAll(clientInterface sender, String message) throws RemoteException {
       
        for (int i = 0; i < clients.size(); i++) {
            clients.get(i).reciveMessage(sender.getClientName() + ": " + message);
        }
    }

    @Override
    public void sendFile(clientInterface sender, int recieverId, String fileName, byte[] data) throws RemoteException {
        for (clientInterface c : clients) {
            if (recieverId == c.getId()) {
                c.recieveFile(fileName, data);
                c.reciveMessage(sender.getClientName() + " 'send you :" + fileName + "'");
                sender.reciveMessage("'you send  :" + fileName + "to" + recieverId + "'");

                break;
            }

        }

    }

    @Override
    public void sendToMember(clientInterface sender, int recevierId, String message) throws RemoteException {


   sender.reciveMessage("'you send  :'" + message  + "'<<private>>");
        for (int i = 0; i < clients.size(); i++) {
             if ((clients.get(i).getId()+"").equals(recevierId+"")) {
         clients.get(i).reciveMessage(sender.getClientName() + "(ID:"+sender.getId()+")<<private>> :" + message);
       
            
        }
        }
     
        
    }
    

    @Override
    public void assginMemberToRoom(clientInterface sender, int recevierId, int roomID) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addRoom(int user_id, String nameRoom) throws RemoteException {
        int roomid = -1;
        boolean check = true;

        try {

            Connection con = DBConnection.getCon();

            String query = "SELECT * FROM rooms where name='" + nameRoom + "'LIMIT 1";
            ResultSet rs = con.createStatement().executeQuery(query);
            {
                while (rs.next()) {
                    return false;

                }
            }

            rs.close();

            query = "Insert into rooms (name,owner_id) values(?,?)";
            PreparedStatement statemnt = con.prepareStatement(query);
            statemnt.setString(1, nameRoom);
            statemnt.setInt(2, user_id);
            statemnt.execute();
            statemnt.close();
            query = "SELECT * FROM rooms where name='" + nameRoom + "'LIMIT 1";
            rs = con.createStatement().executeQuery(query);
            {
                while (rs.next()) {
                    roomid = rs.getInt("id");

                }
            }
            statemnt.close();
            rs.close();
            LocalDate date = LocalDate.now();
            query = "Insert into room_users_join (room_id,user_id,joinied_date,name_room) values(?,?,?,?)";
            statemnt = con.prepareStatement(query);
            statemnt.setInt(1, roomid);
            statemnt.setInt(2, user_id);
            statemnt.setString(3, date.toString());
            statemnt.setString(4, nameRoom);
            statemnt.execute();
            statemnt.close();

            check = true;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return check;
    }

    @Override
    public void deleteRoom(String nameRoom) throws RemoteException {

        try {
            Connection con = DBConnection.getCon();

            String query = "delete from room_users_join  where name_room='" + nameRoom + "'";
            PreparedStatement statemnt = con.prepareStatement(query);

            statemnt.execute();

            statemnt.close();
            query = "delete from rooms  where name='" + nameRoom + "'";
            statemnt = con.prepareStatement(query);

            statemnt.execute();

            statemnt.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void RegisterUser(String firstName, String lastName, String userName, String password) throws RemoteException {

        try {

            Connection con = DBConnection.getCon();

            String query = "Insert into users (firstName,lastName,userName,password) values(?,?,?,?)";

//        Statement statemnt=con.createStatement();
//        ResultSet res=statemnt.executeQuery(query);
            PreparedStatement statemnt = con.prepareStatement(query);
            statemnt.setString(1, firstName);
            statemnt.setString(2, lastName);
            statemnt.setString(3, userName);
            statemnt.setString(4, password);
            statemnt.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void showAllusers() throws RemoteException {

        try {
            Connection con = DBConnection.getCon();

            String query = "select * from users";

            Statement statemnt = con.createStatement();
            ResultSet res = statemnt.executeQuery(query);
            while (res.next()) {
                System.out.println(res.getInt("id"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public int getLastUserID() throws RemoteException {
        int id = -1;
        try {

            Connection con = DBConnection.getCon();

            String query = "SELECT * FROM users ORDER BY id DESC LIMIT 1";

//        Statement statemnt=con.createStatement();
//        ResultSet res=statemnt.executeQuery(query);
//Statement  pst = con.prepareStatement(query);
            ResultSet res = con.createStatement().executeQuery(query);
            {
                while (res.next()) {
                    id = res.getInt("id");
                }
            }
            System.out.println(id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;

    }

    @Override
    public boolean checkLogin(String userName, String password) throws RemoteException {
        boolean check = false;
        try {

            Connection con = DBConnection.getCon();
            String query = "select * from users where userName='" + userName + "' and Password='" + password + "'";

            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {

                check = true;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return check;

    }

    @Override
    public int getUserID(String username) throws RemoteException {

        int id = -1;
        try {

            Connection con = DBConnection.getCon();

            String query = "SELECT * FROM users  where userName='" + username + "'ORDER BY id DESC LIMIT 1";

            ResultSet res = con.createStatement().executeQuery(query);
            {
                while (res.next()) {
                    id = res.getInt("id");
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;

    }

    @Override
    public ArrayList<String> getMyrooms(int id) {
        ArrayList<String> myrooms = new ArrayList<>();
        try {

            Connection con = DBConnection.getCon();
            String query = "select * from rooms where owner_id='" + id + "'";
            ResultSet res = con.createStatement().executeQuery(query);
            {
                while (res.next()) {
                    myrooms.add(res.getString("name"));
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return myrooms;
    }

    @Override
    public ArrayList<String> getAvailableRooms(int id) {
        ArrayList<String> avilablerooms = new ArrayList<>();
        try {

            Connection con = DBConnection.getCon();
            //   String query = "select * from rooms  left join room_users_join  on rooms.id = room_users_join.room_id where room_users_join.user_id !='" + id + "'";
            String query = "select id,name from rooms  except  select room_id,name_room from  room_users_join where user_id ='" + id + "'";

            ResultSet res = con.createStatement().executeQuery(query);
            {
                while (res.next()) {
                    avilablerooms.add(res.getString("name"));
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return avilablerooms;
    }

    @Override
    public void joinToRoom(int user_id, String roomName) throws RemoteException {
        int id = 0;
        String roomname = null;
        try {

            Connection con = DBConnection.getCon();
            String query = "SELECT * FROM rooms  where name='" + roomName + "'" + "ORDER BY id DESC LIMIT 1";

            ResultSet res = con.createStatement().executeQuery(query);
            {
                while (res.next()) {
                    id = res.getInt("id");
                    roomname = res.getString("name");
                }
            }

            System.out.println(id);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();

            query = "Insert into room_users_join (room_id,user_id,joinied_date,name_room) values(?,?,?,?)";

            PreparedStatement statemnt = con.prepareStatement(query);
            statemnt.setInt(1, id);
            statemnt.setInt(2, user_id);
            statemnt.setString(3, date.toString());
            statemnt.setString(4, roomname);
            statemnt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ArrayList<String> getMyRoomsJoined(int user_id) throws RemoteException {
        ArrayList<String> myrooms_joined = new ArrayList<>();
        try {

            Connection con = DBConnection.getCon();
            String query = "select name_room from room_users_join where user_id='" + user_id + "'";
            ResultSet res = con.createStatement().executeQuery(query);
            {
                while (res.next()) {
                    myrooms_joined.add(res.getString("name_room"));
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return myrooms_joined;

    }

    @Override
    public void exitFromRoom(String nameRoom, int user_id) throws RemoteException {

        try {
            Connection con = DBConnection.getCon();
            String query = "delete from room_users_join  where user_id='" + user_id + "' and name_room='" + nameRoom + "'";
            PreparedStatement statemnt = con.prepareStatement(query);
            statemnt.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void sign_to_group(clientInterface c, String roomname) throws RemoteException {

        clients.add(c);

    }

    @Override
    public void sendToGroup(clientInterface sender, String message, String nameGroup) throws RemoteException {

        users_id = null;
        users_id = new ArrayList<>();

        users_id = getAllMembersGroup(nameGroup);

        clients_group = null;
        clients_group = new ArrayList<>();
        for (int i = 0; i < clients.size(); i++) {
            for (int j = 0; i < users_id.size(); j++) {

                if (((clients.get(i).getId()) + "").equals(users_id.get(j))) {
                    clients_group.add(clients.get(i));

                    break;
                }
            }
        }
        for (int i = 0; i < clients_group.size(); i++) {

             clients_group.get(i).reciveMessage(sender.getClientName() + "(ID:"+sender.getId()+"):" + message);
        }

    }

    @Override
    public ArrayList<String> getAllMembersGroup(String nameGroup) throws RemoteException {

        ArrayList<String> memebers = new ArrayList<>();
        try {

            Connection con = DBConnection.getCon();
            String query = "select DISTINCT user_id from room_users_join where name_room='" + nameGroup + "'";
            ResultSet res = con.createStatement().executeQuery(query);
            {
                while (res.next()) {
                    memebers.add(res.getInt("user_id") + "");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return memebers;

    }

    @Override
    public boolean checkUser(String userName) throws RemoteException {

        boolean check = false;
        try {

            Connection con = DBConnection.getCon();
            String query = "select * from users where userName='" + userName + "'";

            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {

                check = true;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return check;

    }

    @Override
    public String getFirstName(String id) throws RemoteException {
        String firstname = "";
        try {

            Connection con = DBConnection.getCon();
            String query = "select firstName from users where id='" + id + "'";
            ResultSet res = con.createStatement().executeQuery(query);
            {
                while (res.next()) {
                    firstname = res.getString("firstName");
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return firstname;
    }

    @Override
    public String getLastName(String id) throws RemoteException {
        String lastname = "";
        try {

            Connection con = DBConnection.getCon();
            String query = "select lastName from users where id='" + id + "'";
            ResultSet res = con.createStatement().executeQuery(query);
            {
                while (res.next()) {
                    lastname = res.getString("lastName");
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lastname;
    }

    @Override
    public String getUserName(String id) throws RemoteException {

        String username = "";
        try {

            Connection con = DBConnection.getCon();
            String query = "select userName from users where id='" + id + "'";
            ResultSet res = con.createStatement().executeQuery(query);
            {
                while (res.next()) {
                    username = res.getString("userName");
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return username;

    }

    @Override
    public void sendFileAll(clientInterface sender, String fileName, byte[] data) throws RemoteException {

for (clientInterface c : clients) {
            
                c.recieveFile(fileName, data);
                c.reciveMessage(sender.getClientName() + " 'send you :" + fileName + "'");
//                sender.reciveMessage("'you send  :" + fileName + "to" +  + "'");

              
            

        }


    }

}
