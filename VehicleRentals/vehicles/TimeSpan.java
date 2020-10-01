// ************************************************************************
// Author: Arun Kulkarni
// Date Created: March 21, 2020
// Class Name: TimeSpan
// Description: creates TimeSpan objects, used to construct reservations
// ************************************************************************

package vehicles;

public class TimeSpan {
    
    //instance variables
    private char timeUnits;
    private int numUnits;
    
    //constructor
    public TimeSpan(String rentalPeriod){
        char timeUnits = rentalPeriod.charAt(0);
        int numUnits = (int)rentalPeriod.charAt(1) - 48;
        this.timeUnits = timeUnits;
        this.numUnits = numUnits;
    }
    
    //returns units of time
    public char getTimeUnits(){
        return timeUnits;
    }
    
    //returns number of units
    public int getNumUnits(){
        return numUnits;
    }
    
    //overridden toString method
    @Override
    public String toString(){
        String convertedTime = "";
        
        //converts entered char to appropriate word
        switch(timeUnits){
            case 'D': convertedTime = "Days"; break;
            case 'd': convertedTime = "Days"; break;
            case 'W': convertedTime = "Weeks"; break;
            case 'w': convertedTime = "Weeks"; break;
            case 'M': convertedTime = "Months"; break;
            case 'm': convertedTime = "Months"; break;
        }
        
        return getNumUnits() + " " + convertedTime;
    }
}