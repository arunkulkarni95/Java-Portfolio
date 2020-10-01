// ************************************************************************
// Author: Arun Kulkarni
// Date Created: May 5, 2020
// Class Name: RentalDetails
// Description: Object to package rental details to pass from UI to
// system interface
// ************************************************************************

//package statement
package Financials;

public class RentalDetails {
    
    //instance variables
    private int vehicleType;
    private String rentalPeriod;
    private int mileEstimate;
    private boolean insOption;
    private boolean primeCust;
    
    //constructor
    public RentalDetails(int vehicleType,String rentalPeriod,int mileEstimate,
                         boolean insOption,boolean primeCust){
        this.vehicleType = vehicleType;
        this.rentalPeriod = rentalPeriod;
        this.mileEstimate = mileEstimate;
        this.insOption = insOption;
        this.primeCust = primeCust;
    }
    
    //getters for instance variables
    
    public int getVehicleType(){
        return vehicleType;
    }
    
    public String getRentalPeriod(){
        return rentalPeriod;
    }
    
    public int getMileEstimate(){
        return mileEstimate;
    }
    
    public boolean getInsOption(){
        return insOption;
    }
    
    public boolean getPrimeCust(){
        return primeCust;
    }
}