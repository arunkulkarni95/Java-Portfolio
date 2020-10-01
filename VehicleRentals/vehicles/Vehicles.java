// ************************************************************************
// Author: Arun Kulkarni
// Date Created: March 30, 2020
// Class Name: Vehicles
// Description: Creates a linked list of Vehicle objects and provides
// methods to traverse the linked list, as well as adding, removing, and 
// retrieving vehicles by VIN
// ************************************************************************

package vehicles;

import utilities.Exceptions.FailedVINEntryAttemptsException;
import vehicles.Exceptions.VINNotFoundException;

public class Vehicles {
    
    //instance variable
    private VehNode head = null;
    
    //appends vehicle to end of list
    public void add(Vehicle veh){    
        if(head == null){
            head = new VehNode(veh,null);
        }
        else{
            VehNode temp = head;
            while(temp.getNext() != null){
                temp = temp.getNext();
            }
            temp.setNext(new VehNode(veh,null));        
        }
    }
    
    //removes vehicle (found by VIN) from list
    public void remove(String VIN)throws VINNotFoundException{
        if(head.getValue().getVIN().equals(VIN)){
            head = head.getNext();
        }
        else{
            VehNode leadingNode = head.getNext();
            VehNode trailingNode = head;
            while(!leadingNode.getValue().getVIN().equals(VIN)){
                trailingNode = leadingNode;
                leadingNode = leadingNode.getNext();
            }
            if(!leadingNode.getValue().getVIN().equals(VIN) 
                    && leadingNode.getNext() == null){
                throw new VINNotFoundException();
            }
            trailingNode.setNext(leadingNode.getNext());
        }
    }
    
    //traverses list to find vehicle by VIN
    //returns vehicle if match found
    public Vehicle getVehicle(String vin){
        if(!vin.matches("[0-9A-Z]+")){
            throw new FailedVINEntryAttemptsException();
        }
        VehNode temp = null;
        if(head.getValue().getVIN().equals(vin)){
            temp = head;
        }
        else{
            try{
            VehNode leadingNode = head.getNext();
            VehNode trailingNode = head;
            while(!leadingNode.getValue().getVIN().equals(vin)){
                trailingNode = leadingNode;
                leadingNode = leadingNode.getNext();
            }
           // if(!leadingNode.getValue().getVIN().equals(vin) 
            //        && leadingNode.getNext() == null){
            //    throw new VINNotFoundException();
           // }
            temp = leadingNode;
            }
            catch(NullPointerException e){
                throw new VINNotFoundException();
            }
        }
        return temp.getValue();
    }
    
    //returns head node of list
    public VehNode getHead(){
        return head;
    }
}