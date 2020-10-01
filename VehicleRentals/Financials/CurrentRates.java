// ************************************************************************
// Author: Arun Kulkarni
// Date Created: May 5, 2020
// Class Name: CurrentRates
// Description: Aggregation of VehicleRates objects
// ************************************************************************

package Financials;

public class CurrentRates {
    
    //array of VehicleRates objects
    private VehicleRates[] rates = new VehicleRates[3];
    
    //constructor
    public CurrentRates(CarRates carRate,SUVRates suvRate,TruckRates truckRate){
        rates[0] = carRate;
        rates[1] = suvRate;
        rates[2] = truckRate;
    }
    
    //getter for Car rates
    public VehicleRates getCarRates(){
        return rates[0];
    }
    
    public void setCarRates(VehicleRates r){
        rates[0] = r;
    }
    
    //getter for SUV rates
    public VehicleRates getSUVRates(){
        return rates[1];
    }
    
    public void setSUVRates(VehicleRates r){
        rates[1] = r;
    }
    
    //getter for Truck rates
    public VehicleRates getTruckRates(){
        return rates[2];
    }
    
    public void setTruckRates(VehicleRates r){
        rates[2] = r;
    }
    
    //Calculates estimated rental cost
    public double calcEstimatedCost(int vehicleType,String estimatedRentalPeriod,
                                    int estimatedNumMiles,boolean dailyIns,
                                    boolean primeCust){
        double rentalRate = 0;
        double insRate = 0;
        double mileRate = 0;
        
        //discounts 100 miles if prime customer
        if(primeCust){
            if(estimatedNumMiles <= 100){
                estimatedNumMiles = 0;
            }
            else if(estimatedNumMiles > 100){
                estimatedNumMiles = estimatedNumMiles - 100;
            }
        }
        
        //The following is a triple nested switch statement; it accounts for
        //all 9 possible combinations of vehicle type and rental period units
        //each "leg" of the statement calculates an adjusted rental and insurance
        //rate, as well as an adjusted mileage rate
        //the last statements in the method add all sub-rates and return final
        //calculated value
        switch(vehicleType){
            case 1:
                switch(estimatedRentalPeriod.charAt(0)) {
                    case 'D':
                        rentalRate = getCarRates().getDailyRate();
                        insRate = ((int)estimatedRentalPeriod.charAt(1) - 48) * getCarRates().getInsRate();
                        break;
                    case 'W':
                        rentalRate = getCarRates().getWeeklyRate();
                        insRate = ((int)estimatedRentalPeriod.charAt(1) - 48) * getCarRates().getInsRate() * 7;
                        break;
                    case 'M':
                        rentalRate = getCarRates().getMonthlyRate();
                        insRate = ((int)estimatedRentalPeriod.charAt(1) - 48) * getCarRates().getInsRate() * 30;
                        break;
                }
                rentalRate = rentalRate * estimatedRentalPeriod.charAt(1);
                mileRate = getCarRates().getMileCharge() * estimatedNumMiles;
                break;
            case 2:
                switch (estimatedRentalPeriod.charAt(0)) {
                    case 'D':
                        rentalRate = getSUVRates().getDailyRate();
                        insRate = ((int)estimatedRentalPeriod.charAt(1) - 48) * getSUVRates().getInsRate();
                        break;
                    case 'W':
                        rentalRate = getSUVRates().getWeeklyRate();
                        insRate = ((int)estimatedRentalPeriod.charAt(1) - 48) * getSUVRates().getInsRate() * 7;
                        break;
                    case 'M':
                        rentalRate = getSUVRates().getMonthlyRate();
                        insRate = ((int)estimatedRentalPeriod.charAt(1) - 48) * getSUVRates().getInsRate() * 30;
                        break;
                }
                rentalRate = rentalRate * estimatedRentalPeriod.charAt(1);
                mileRate = getSUVRates().getMileCharge() * estimatedNumMiles;
                break;
            case 3:
                switch (estimatedRentalPeriod.charAt(0)) {
                    case 'D':
                        rentalRate = getTruckRates().getDailyRate();
                        insRate = ((int)estimatedRentalPeriod.charAt(1) - 48) * getTruckRates().getInsRate();
                        break;
                    case 'W':
                        rentalRate = getTruckRates().getWeeklyRate();
                        insRate = ((int)estimatedRentalPeriod.charAt(1) - 48) * getTruckRates().getInsRate() * 7;
                        break;
                    case 'M':
                        rentalRate = getTruckRates().getMonthlyRate();
                        insRate = ((int)estimatedRentalPeriod.charAt(1) - 48) * getTruckRates().getInsRate() * 30;
                        break;
                }
                rentalRate = rentalRate * estimatedRentalPeriod.charAt(1);
                mileRate = getTruckRates().getMileCharge() * estimatedNumMiles;
                break;
        }
        if(dailyIns){
            return mileRate + rentalRate + insRate;
        }
        else{
            return mileRate + rentalRate;
        }
    }
    
    //this method calculates the actual rental cost, using the quoted rate
    //from a given reservation, days the vehicle was used, miles that were driven,
    //insurance, and prime customer status
    public double calcActualCost(VehicleRates rates,int daysUsed,int milesDriven,
                                 boolean dailyIns,boolean primeCust){
        double rentalRate = 0;
        double mileRate = 0;
        double insRate = 0;
        
        //100 miles discounted if prime customer
        if(primeCust){
            if(milesDriven <= 100){
                milesDriven = 0;
            }
            else if(milesDriven > 100){
                milesDriven = milesDriven - 100;
            }
        }
        
        if(daysUsed < 7){
            rentalRate = rates.getDailyRate() * daysUsed;
        }
        else if(daysUsed > 7 && daysUsed <= 30){
            rentalRate = rates.getWeeklyRate() * daysUsed;
        }
        else if(daysUsed > 30){
            rentalRate = rates.getMonthlyRate() * daysUsed;
        }
        
        mileRate = milesDriven * rates.getMileCharge();
        insRate = daysUsed * rates.getInsRate();
        
        if(dailyIns){
            return mileRate + insRate + rentalRate;
        }
        else{
            return mileRate + rentalRate;
        }
    }
}