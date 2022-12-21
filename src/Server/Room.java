/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import clientInterfaces.clientInterface;


public class Room {
  private String room;
  private clientInterface owner;
  private int roomNumber;

    public Room(String room, clientInterface owner, int roomNumber) {
        this.room = room;
        this.owner = owner;
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

 

    public String getRoom() {
        return room;
    }

    public clientInterface getOwner() {
        return owner;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setOwner(clientInterface owner) {
        this.owner = owner;
    }

    
   
  
}
