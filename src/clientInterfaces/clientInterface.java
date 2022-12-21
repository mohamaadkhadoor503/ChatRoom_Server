/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package clientInterfaces;
import java.rmi.*;
/**
 *
 * @author LENOVO
 */
public interface clientInterface extends Remote{
    public void reciveMessage(String name)throws RemoteException;
    public String getClientName() throws RemoteException;
    public int getId() throws RemoteException;
        public void recieveFile(String fileName,byte[]data)throws RemoteException;

}
