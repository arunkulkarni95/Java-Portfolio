// ************************************************************************
// Author: Arun Kulkarni
// Date Created: May 5, 2020
// Class Name: Account
// Description: Account object to store corporate account data
// ************************************************************************

//package statement
package Financials;

public class Account {
    
    //instance variables
    private String creditCardNum;
    private String companyName;
    private boolean primeCust;
    
    //constructor
    public Account(String creditCardNum,String companyName,boolean primeCust){
        this.creditCardNum = creditCardNum;
        this.companyName = companyName;
        this.primeCust = primeCust;
    }
    
    public String getCompanyName(){
        return companyName;
    }
    
    //getter for credit card number
    public String getCreditCardNum(){
        return creditCardNum;
    }
    
    //returns true if prime customer, otherwise false
    public boolean primeCustomer(){
        return primeCust;
    }
    
    //overridden toString method
    @Override
    public String toString(){
        return "Company Name: " + companyName + " Credit Card: " + creditCardNum +
                " Prime Customer? " + primeCust;
    }
}