/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package serverInterfaces;

import java.rmi.*;

import clientInterfaces.clientInterface;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public interface ChatInterface extends Remote{
    public String   LOOKUP_NAME="Chat";
public void sign(clientInterface c)throws RemoteException;
public void RegisterUser(String firstName,String lastName,String userName,String password)throws RemoteException;
public void sendToAll(clientInterface sender,String message)throws RemoteException;
public void sendFile(clientInterface sender,int recieverId,String fileName,byte[]data)throws RemoteException;
public void sendFileAll(clientInterface sender,String fileName,byte[]data)throws RemoteException;
public void sendToMember(clientInterface sender,int recevierId ,String message)throws RemoteException;
public boolean addRoom(int user_id,String nameRoom)throws RemoteException;
public void deleteRoom(String nameRoom)throws RemoteException;
public void showAllusers()throws RemoteException;
public int getLastUserID()throws RemoteException;
public int getUserID(String username)throws RemoteException;
public boolean checkLogin(String userName,String password)throws RemoteException;
public void assginMemberToRoom(clientInterface sender,int recevierId ,int  roomID)throws RemoteException;
public ArrayList<String>getMyrooms(int id)throws RemoteException;
public ArrayList<String>getAvailableRooms(int id)throws RemoteException;
public void joinToRoom(int user_id,String roomName)throws RemoteException;
public ArrayList<String>getMyRoomsJoined(int user_id)throws RemoteException;
public void exitFromRoom(String nameRoom,int user_id)throws RemoteException;
public void sign_to_group(clientInterface c,String roomname)throws RemoteException;
public void sendToGroup(clientInterface sender,String message,String nameGroup)throws RemoteException;
public ArrayList<String> getAllMembersGroup(String nameGroup)throws RemoteException;
public boolean checkUser(String userName)throws RemoteException;
public String getFirstName(String id)throws RemoteException;
public String getLastName(String id)throws RemoteException;
public String getUserName(String id)throws RemoteException;
}
