// ************************************************************************
// Author: Arun Kulkarni
// Date Created: March 21, 2020
// Class Name: SUV
// Description: Creates SUV objects
// ************************************************************************
package vehicles;

public class SUV extends Vehicle {
    
    //instance var for type of vehicle
    //and storage capacity
    private String type;
    private int storage;
    
    //constructor
    public SUV(String type, String descript, int mpg, String VIN, int seating,
               int storage){
        super(descript, mpg, VIN, seating);    
        this.type = type;
        this.storage = storage;
    }
    
    @Override
    public String getType(){
        return type;
    }
    
    //overridden toString method
    @Override
    public String toString(){
        return descript + " (" + type + ") " + " MPG: " + mpg + " Seating: " + seating
                + " Storage: " + storage + " cu. ft. " + " VIN: " + VIN;
    }
}


