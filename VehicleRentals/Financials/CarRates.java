// ************************************************************************
// Author: Arun Kulkarni
// Date Created: May 5, 2020
// Class Name: CarRates
// Description: Subclass of VehicleRates for Cars
// ************************************************************************

package Financials;

public class CarRates extends VehicleRates{
    
    //constructor
    public CarRates(double dailyRate,double weeklyRate,double monthlyRate,
                    double mileCharge,double insRate){
        super(dailyRate,weeklyRate,monthlyRate,mileCharge,insRate);
    }
    
    //overridden toString method
    @Override
    public String toString(){
        return "Daily Rate: $" + getDailyRate() + ", Weekly Rate: $" + getWeeklyRate() + 
                ", Monthly Rate: $" + getMonthlyRate() + ", Mileage Charge: $" + 
                getMileCharge() + " per mile" + ", Insurance Rate: $" + getInsRate();
    }
}