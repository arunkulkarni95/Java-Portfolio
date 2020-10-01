// ************************************************************************
// Author: Arun Kulkarni
// Date Created: May 5, 2020
// Class Name: ReservationDetails
// Description: Object to package reservation details to pass from UI to
// system interface
// ************************************************************************

//package statement
package Financials;

public class ReservationDetails {
    
    //instance variables
    private String vin;
    private String creditCardNum;
    private String rentalPeriod;
    private boolean insOption;
    
    //constructors
    public ReservationDetails(String vin,String creditCardNum,String rentalPeriod,
                              boolean insOption){
        this.vin = vin;
        this.creditCardNum = creditCardNum;
        this.rentalPeriod = rentalPeriod;
        this.insOption = insOption;
    }
    
    //getters for instance variables
    
    public String getVIN(){
        return vin;
    }
    
    public String getRentalPeriod(){
        return rentalPeriod;
    }
    
    public String getCreditCardNum(){
        return creditCardNum;
    }
    
    public boolean getInsOption(){
        return insOption;
    }
}