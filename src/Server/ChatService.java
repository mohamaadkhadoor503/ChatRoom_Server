/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;
import java.rmi.server.UnicastRemoteObject;
import clientInterfaces.clientInterface;
import java.rmi.RemoteException;
import java.util.ArrayList;
import serverInterfaces.ChatInterface;

/**
 *
 * @author LENOVO
 */
public class ChatService extends UnicastRemoteObject implements ChatInterface{
ArrayList<clientInterface> clients;
    ArrayList<Room> rooms; 
    public ChatService() throws RemoteException{
clients=new ArrayList<>();
    }
    @Override
    public void sign(clientInterface c) throws RemoteException {
clients.add(c);

    }

    @Override
    public void sendToAll(clientInterface sender, String message) throws RemoteException {
for(int i=0;i<clients.size();i++){
    clients.get(i).reciveMessage(sender.getClientName()+": "+message);
}
    }

    @Override
    public void sendFile(clientInterface sender, int recieverId, String fileName, byte[] data) throws RemoteException {
        for (clientInterface c : clients) {
    if(recieverId==c.getId()){
        c.recieveFile(fileName, data);
        c.reciveMessage(sender.getClientName()+" 'send you :"+fileName+"'");
            sender.reciveMessage( "'you send  :"+fileName+"to"+recieverId+"'");

        break;
    }        
            
        }


    }

    @Override
    public void sendToMember(clientInterface sender, int recevierId, String message) throws RemoteException {

        for (clientInterface client : clients) {
            if(client.getId()==recevierId)
            {
                client.reciveMessage(sender.getClientName()+"(private):"+message);
          break;
            }
        }

    }

    @Override
    public void addRoom(clientInterface owner,String nameRoom,int roomNumber) throws RemoteException {
        Room room =new Room(nameRoom,owner,roomNumber);
        rooms.add(room);

    }

    @Override
    public void deleteRoom(clientInterface owner, String nameRoom, int roomNumber) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
