// ************************************************************************
// Author: Arun Kulkarni
// Date Created: May 5, 2020
// Class Name: VehicleRates
// Description: Abstract class for VehicleRates objects
// ************************************************************************

//package statement
package Financials;

public abstract class VehicleRates{
    
    //instance variables
    private double dailyRate;
    private double weeklyRate;
    private double monthlyRate;
    private double mileCharge;
    private double insRate;
    
    //constructor
    public VehicleRates(double dailyRate,double weeklyRate,double monthlyRate,
                        double mileCharge,double insRate){
        this.dailyRate = dailyRate;
        this.weeklyRate = weeklyRate;
        this.monthlyRate = monthlyRate;
        this.mileCharge = mileCharge;
        this.insRate = insRate;
    }
    
    //getters for all rates/charges
    
    public double getDailyRate(){
        return dailyRate;
    }
    
    public double getWeeklyRate(){
        return weeklyRate;
    }
    
    public double getMonthlyRate(){
        return monthlyRate;
    }
    
    public double getMileCharge(){
        return mileCharge;
    }
    
    public double getInsRate(){
        return insRate;
    }
    
    //abstract toString method
    public abstract String toString();
}