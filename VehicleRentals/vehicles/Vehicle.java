// ************************************************************************
// Author: Arun Kulkarni
// Date Created: March 21, 2020
// Class Name: Vehicle
// Description: Abstract class that provides instructions to create
// objects of type vehicle
// ************************************************************************
package vehicles;

import Financials.VehicleRates;
import vehicles.Exceptions.UnreservedVehicleException;

public abstract class Vehicle {
    
    // attributes
    protected int seating;
    protected String descript;
    protected int mpg;
    protected String VIN;
    private Reservation resv;
    private VehicleRates cost;
    
    // constructor
    public Vehicle(String descript, int mpg, String VIN, int seating){
        this.descript = descript;
        this.mpg = mpg;
        this.VIN = VIN;
        this.seating = seating;
        
        // init as "no reservation"
        resv = null;
        cost = null;
    }
    
    //abstract toString method
    public abstract String toString();
    
    public abstract String getType();
    
    public VehicleRates getQuotedRates(){
        return cost;
    }
    
    public void setQuotedRates(VehicleRates cost){
        this.cost = cost;
    }
    
    //returns VIN
    public String getVIN(){
        return VIN;
    }
    
    //returns true if vehicle reserved
    public boolean isReserved(){
        return resv != null;
    }
    
    //adds reservation to vehicle
    public void reserve(Reservation resv){
        this.resv = resv;
    }
    
    //returns reservation
    public Reservation getReservation(){
        return resv;
    }
    
    //cancels reservation
    public void cancelReservation() throws UnreservedVehicleException{
        if(resv == null)
            throw new UnreservedVehicleException();
        
        resv = null;
    }
}