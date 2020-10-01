// ************************************************************************
// Author: Arun Kulkarni
// Date Created: March 21, 2020
// Class Name: Truck
// Description: Creates Truck objects
// ************************************************************************
package vehicles;

public class Truck extends Vehicle {
    
    //instance var for type of vehicle
    //and storage capacity
    private String type;
    private int loadCap;
    
    //constructor
    public Truck(String type, String descript, int mpg, String VIN, int seating,
                 int loadCap){
        super(descript, mpg, VIN, seating);    
        this.type = type;
        this.loadCap = loadCap;
    }
    
    @Override
    public String getType(){
        return type;
    }
    
    //overridden toString method
    @Override
    public String toString(){
        return descript + " (" + type + ") " + " MPG: " + mpg + " Seating: " + seating
                + " Load Capacity: " + loadCap + " lbs. " + " VIN: " + VIN;
    }
    
}

