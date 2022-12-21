/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package serverInterfaces;

import java.rmi.*;

import clientInterfaces.clientInterface;

/**
 *
 * @author LENOVO
 */
public interface ChatInterface extends Remote{
    public String   LOOKUP_NAME="Chat";
    public void sign(clientInterface c)throws RemoteException;
public void sendToAll(clientInterface sender,String message)throws RemoteException;
public void sendFile(clientInterface sender,int recieverId,String fileName,byte[]data)throws RemoteException;
public void sendToMember(clientInterface sender,int recevierId ,String message)throws RemoteException;
public void addRoom(clientInterface owner,String nameRoom,int roomNumber)throws RemoteException;
public void deleteRoom(clientInterface owner,String nameRoom,int roomNumber)throws RemoteException;



}
