// ************************************************************************
// Author: Arun Kulkarni
// Date Created: March 30, 2020
// Class Name: VehNode
// Description: Defines nodes with values of objects of type Vehicle
// ************************************************************************

//package statement
package vehicles;

public class VehNode {
    
    //instance variables
    private Vehicle veh;
    private VehNode next;
    
    //constructor for node
    public VehNode(Vehicle veh, VehNode next){
        this.veh = veh;
        this.next = next;
    }
    
    //returns value of node
    public Vehicle getValue(){
        return veh;
    }
    
    //returns next node in list
    public VehNode getNext(){
        return next;
    }
    
    //appends node to current node
    public void setNext(VehNode next){
        this.next = next;
    } 
}