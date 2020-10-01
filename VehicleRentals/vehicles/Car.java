// ************************************************************************
// Author: Arun Kulkarni
// Date Created: March 21, 2020
// Class Name: Car
// Description: Creates Car objects
// ************************************************************************

package vehicles;

public class Car extends Vehicle {
    
    //instance var for type of vehicle
    private String type;
    
    //constructor
    public Car(String type, String descript, int mpg, String VIN, int seating){
        super(descript, mpg, VIN, seating);    
        this.type = type;
    }
    
    @Override
    public String getType(){
        return type;
    }
    
    //overridden toString method
    @Override
    public String toString(){
        return descript + " (" + type + ") " + " MPG: " + mpg + " Seating: " + seating
                + " VIN: " + VIN;
    }
}