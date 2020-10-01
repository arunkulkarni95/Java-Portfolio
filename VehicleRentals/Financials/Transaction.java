// ************************************************************************
// Author: Arun Kulkarni
// Date Created: May 5, 2020
// Class Name: Account
// Description: Account object to store corporate account data
// ************************************************************************

package Financials;

public class Transaction {
    
    //instance variables
    private String creditCardNum;
    private String companyName;
    private String vehicleType; //car, SUV, truck
    private String rentalPeriod;
    private String rentalCost;
    
    //constructor
    public Transaction(String creditCardNum,String companyName,String vehicleType,
                       String rentalPeriod,String rentalCost){
        this.creditCardNum = creditCardNum;
        this.companyName = companyName;
        this.vehicleType = vehicleType;
        this.rentalPeriod = rentalPeriod;
        this.rentalCost = rentalCost;
    }
    
    //overridden toString method
    @Override
    public String toString(){
        return "Company Name: " + companyName + " Credit Card: " + creditCardNum + 
               " Vehicle Type: " + vehicleType + " Rental Period: " + rentalPeriod + 
               " days " + "Total Cost: $" + rentalCost;
    } 
}