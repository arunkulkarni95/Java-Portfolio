// ************************************************************************
// Author: Arun Kulkarni
// Date Created: May 5, 2020
// Class Name: TruckRates
// Description: Subclass of VehicleRates for Trucks
// ************************************************************************

package Financials;

import Financials.VehicleRates;

public class TruckRates extends VehicleRates{
    
    //constructor
    public TruckRates(double dailyRate,double weeklyRate,double monthlyRate,
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