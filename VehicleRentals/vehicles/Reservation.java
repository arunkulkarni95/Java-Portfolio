// ************************************************************************
// Author: Arun Kulkarni
// Date Created: March 21, 2020
// Class Name: Reservation
// Description:
// ************************************************************************

package vehicles;

import vehicles.Exceptions.InvalidRentalPeriodFormatException;

public class Reservation {
    
    //instance variables
    private String creditCardNum;
    private TimeSpan rentalPeriod;
    private boolean insuranceSelected;
    
    //constructor
    public Reservation(String creditCardNum, TimeSpan rentalPeriod, 
                       boolean insuranceSelected){
        this.creditCardNum = creditCardNum;
        this.rentalPeriod = rentalPeriod;
        this.insuranceSelected = insuranceSelected;
    }
    
    //returns credit card number
    public String getCreditCardNum(){
        return creditCardNum;
    }
    
    //returns rental period
    public TimeSpan getRentalPeriod(){
        return rentalPeriod;
    }
    
    //renturns whether insurance is selected
    public boolean getInsuranceSelected(){
        return insuranceSelected;
    }
    
    //overridden toString method
    @Override
    public String toString(){
        String insurance;
        if(getInsuranceSelected()){
            insurance = "yes";
        }
        else{
            insurance = "no";
        }
        
        return "Vehicle reserved for " + rentalPeriod + "."
                + "\nInsurance selected? " + insurance
                + " \nCredit Card Number: " + creditCardNum + "\n";        
    }
}