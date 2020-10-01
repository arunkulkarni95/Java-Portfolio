// ************************************************************************
// Author: Arun Kulkarni
// Date Created: May 20, 2020
// Class Name: SystemInterface
// Description: intermediary to pass and receive data between
// Interfaces package and all other packages
// ************************************************************************

//package statement
package Interfaces;

//import statements
import Financials.*;
import vehicles.*;
import utilities.Exceptions.*;
import vehicles.Exceptions.*;
import Financials.exceptions.*;

public class SystemInterface {
    
    //instance variables
    private static CurrentRates agencyRates;
    private static Vehicles agencyVehicles;
    private static Accounts accounts;
    private static Transactions transHistory;
    private static boolean initialized = false;
    
    //initialize system with rates, vehicles, accounts, and transactions
    public static void initSystem(CurrentRates r, Vehicles v, Accounts a, Transactions t){        
        agencyRates = r;
        agencyVehicles = v;
        accounts = a;
        transHistory = t;
        initialized = true;
    }
    
    //returns true if system initialized
    public static boolean initialized(){
        return initialized;
    }
    
    //return Car rates to UI
    public static String[] getCarRates(){
        String[] carRates = new String[1];
        carRates[0] = agencyRates.getCarRates().toString();
        return carRates;   
    }
    
    //return SUV rates to UI
    public static String[] getSUVRates(){
        String[] SUVRates = new String[1];
        SUVRates[0] = agencyRates.getSUVRates().toString();
        return SUVRates;
    }
    
    //return Truck rates to UI
    public static String[] getTruckRates(){
        String[] truckRates = new String[1];
        truckRates[0] = agencyRates.getTruckRates().toString();
        return truckRates;
    }
    
    //update Car rates, return confirmation to UI
    public static String[] updateCarRates(VehicleRates r){        
        String[] carRates = new String[1];
        agencyRates.setCarRates(r);
        carRates[0] = "\nCar Rates Updated: " + agencyRates.getCarRates().toString();
        return carRates;
    }
    
    //update SUV rates, return confirmation to UI
    public static String[] updateSUVRates(VehicleRates r){
        String[] SUVRates = new String[1];
        agencyRates.setSUVRates(r);
        SUVRates[0] = "\nSUV Rates Updated: " + agencyRates.getSUVRates().toString();
        return SUVRates;
    }
    
    //update Truck rates, return confirmation to UI
    public static String[] updateTruckRates(VehicleRates r){
        String[] truckRates = new String[1];
        agencyRates.setTruckRates(r);
        truckRates[0] = "\nTruck Rates Updates: " + agencyRates.getTruckRates().toString();
        return truckRates;
    }
    
    //calculate estimated rental cost, return confirmation to UI
    public static String[] estimatedRentalCost(RentalDetails details){
        String[] estCost = new String[1];
        double cost = agencyRates.calcEstimatedCost(details.getVehicleType(), 
                    details.getRentalPeriod(), details.getMileEstimate(), 
                    details.getInsOption(), details.getPrimeCust());
        cost = Math.round(cost*100.0)/100.0;
        String confirmation = "\nTotal estimated charge for rental: $";
        estCost[0] = confirmation + cost;
        return estCost;        
    }
    
    //process returned vehicle - calculates cost based on rate at time of
    //rental, create new transaction, remove reservation, return confirmation
    //to UI
    public static String[] processReturnedVehicle(String vin, int daysUsed,
                                                  int milesDriven){
        String[] returnInfo = new String[2];
        try{        
            Vehicle veh = agencyVehicles.getVehicle(vin);
            if(!veh.isReserved()){
                throw new UnreservedVehicleException();
            }
            VehicleRates actRate = veh.getQuotedRates();
            boolean ins = veh.getReservation().getInsuranceSelected();
            String cardNum = veh.getReservation().getCreditCardNum();
            Account acct = accounts.getAccount(veh.getReservation().getCreditCardNum());
            String compName = acct.getCompanyName();
            boolean primeCust = acct.primeCustomer();
            double actCost = agencyRates.calcActualCost(actRate, daysUsed, milesDriven, ins, primeCust);
            actCost = Math.round(actCost*100.0)/100.0;
            transHistory.add(new Transaction(cardNum,compName,veh.getType(),Integer.toString(daysUsed),Double.toString(actCost)));
            veh.cancelReservation();
            if(primeCust){
                milesDriven = milesDriven - 100;
            }
            returnInfo[0] = "\nVehicle returned: " + veh.toString();
            returnInfo[1] = "\nTotal miles charged: " + milesDriven + " Total cost: $" + actCost;
        }
        catch(UnreservedVehicleException e){
            System.out.println("\nVehicle is not reserved.");
        }
        catch(AccountNotFoundException i){
            System.out.println("\nAccount not found.");
        }
        return returnInfo;
    }
    
    //return all available Cars to UI
    public static String[] getAvailCars(){        
        int numCars = 0;
        VehNode current = agencyVehicles.getHead();
        while(current != null){
            if(current.getValue() instanceof Car){
                if(!current.getValue().isReserved()){
                    numCars++;
                }
            }        
            current = current.getNext();
        }
        String[] availCars = new String[numCars];
        int i = 0;
        
        current = agencyVehicles.getHead();
        while(current != null){
            if(current.getValue() instanceof Car){                    
                if(!current.getValue().isReserved()){
                    availCars[i] = current.getValue().toString();
                    i++;
                }
            }           
            current = current.getNext();
        }
        return availCars;
    }
    
    //return all available SUVs to UI
    public static String[] getAvailSUVs(){        
        int numSUVs = 0;
        VehNode current = agencyVehicles.getHead();
        while(current != null){
            if(current.getValue() instanceof SUV){
                if(!current.getValue().isReserved()){    
                    numSUVs++;
                }
            }           
            current = current.getNext();
        }
        
        String[] availSUVs = new String[numSUVs];
        int i = 0;
        
        current = agencyVehicles.getHead();
        while(current != null){
            if(current.getValue() instanceof SUV){                    
                if(!current.getValue().isReserved()){
                    availSUVs[i++] = current.getValue().toString();
                }
            }         
            current = current.getNext();
        }
        return availSUVs;
    }
    
    //return all available Trucks to UI
    public static String[] getAvailTrucks(){        
        int numTrucks = 0;
        VehNode current = agencyVehicles.getHead();
        while(current != null){
            if(current.getValue() instanceof Truck){
                if(!current.getValue().isReserved()){
                    numTrucks++;
                }
            }          
            current = current.getNext();
        }
        
        String[] availTrucks = new String[numTrucks];
        int i = 0;
        
        current = agencyVehicles.getHead();
        while(current != null){
            if(current.getValue() instanceof Truck){                   
                if(!current.getValue().isReserved()){    
                    availTrucks[i++] = current.getValue().toString();
                }
            }           
            current = current.getNext();
        }
        return availTrucks;
    }
    
    //return all vehicles to UI
    public static String[] getAllVehicles(){
        int numVehicles = 0;
        VehNode current = agencyVehicles.getHead();        
        while(current != null){
            numVehicles++;
            current = current.getNext();
        }
        
        String[] allVehicles = new String[numVehicles];
        int  i = 0;
        
        current = agencyVehicles.getHead();        
        while(current != null){     
            allVehicles[i++] = current.getValue().toString();
            current = current.getNext();
        }
        return allVehicles;        
    }
    
    //make new reservation - finds account to make reservation,
    //assigns appropriate rate value, creates new reservation,
    //and returns confirmation to UI
    public static String[] makeReservation(ReservationDetails details){        
        String[] resDetails = new String[1];
        try{
            accounts.getAccount(details.getCreditCardNum());
            Vehicle veh = agencyVehicles.getVehicle(details.getVIN());
            if(veh.isReserved()){
                throw new ReservedVehicleException();
            }
            if(veh.getType().equals("Car")){
                veh.setQuotedRates(agencyRates.getCarRates());
            }
            else if(veh.getType().equals("SUV")){
                veh.setQuotedRates(agencyRates.getSUVRates());
            }
            else if(veh.getType().equals("Truck")){
                veh.setQuotedRates(agencyRates.getTruckRates());
            }

            TimeSpan ts = new TimeSpan(details.getRentalPeriod());
            Reservation res = new Reservation(details.getCreditCardNum(),ts,details.getInsOption());

            veh.reserve(res);

            resDetails[0] = "\nReservation details: " + veh.getReservation().toString();
        }
        catch(VINNotFoundException | FailedVINEntryAttemptsException i){
            System.out.println("\nVIN not found.");
        }
        catch(ReservedVehicleException e){
            System.out.println("\nVehicle already reserved.");
        }
        catch(AccountNotFoundException e){
            System.out.println("\nAccount not found.");
        }
        return resDetails;
    }
    
    //cancels reservation, returns confirmation to UI
    public static String[] cancelReservation(String vin){
        String[] cancellation = new String[1];
        try{
            Vehicle veh = agencyVehicles.getVehicle(vin);
            if(!veh.isReserved()){
                throw new UnreservedVehicleException();
            }
            veh.cancelReservation();
            cancellation[0] = "\nReservation cancelled for: " + veh.toString();
        }
        catch(VINNotFoundException | FailedVINEntryAttemptsException i){
            System.out.println("\nVIN not found.");
        }
        catch(UnreservedVehicleException e){
            System.out.println("\nVehicle is not reserved.");
        }
        return cancellation;
    }
    
    //retrieves reservation by VIN, returns confirmation to UI
    public static String[] getReservation(String vin){
        String[] resDetails = new String[1];
        try{
            Vehicle veh = agencyVehicles.getVehicle(vin);
            if(!veh.isReserved()){
                throw new UnreservedVehicleException();
            }
            Reservation res = veh.getReservation();
            resDetails[0] = res.toString();
        }
        catch(UnreservedVehicleException e){
            System.out.println("\nVehicle is not reserved.");
        }
        return resDetails;
    }
    
    //retrieves all reservations, returns confirmation to UI
    //returns message if no reservations found
    public static String[] getAllReservations(){
        VehNode current = agencyVehicles.getHead();
        int resCount = 0;
        while(current != null){
            while(current.getValue().isReserved()){
                resCount++;
                break;
            }            
            current = current.getNext();
        }
        
        String[] allRes = new String[resCount];
        int i = 0;
        
        current = agencyVehicles.getHead();
        while(current != null){
            while(current.getValue().isReserved()){
                allRes[i++] = current.getValue().getReservation().toString();
                break;
            }            
            current = current.getNext();
        }
        if(allRes.length == 0){
            allRes = new String[1];
            allRes[0] = "\nNo reservations found.";
        }
        return allRes;
    }
    
    //adds new account, returns confirmation to UI
    public static String[] addAccount(String cardNum, String compName, boolean primeCust){
        String[] acctDets = new String[1];
        Account acct = new Account(cardNum,compName,primeCust);
        accounts.add(acct);
        acctDets[0] = "\nAccount added: " + acct.toString();
        return acctDets;
    }
    
    //retrieves account by credit card number
    public static String[] getAccount(String cardNum){
        String[] acctDets = new String[1];
        try{
            Account acct = accounts.getAccount(cardNum);
            acctDets[0] = acct.toString();
        }
        catch(AccountNotFoundException e){
            System.out.println("\nAccount not found.");
        }
        return acctDets;
    }
    
    //retrieves all accounts, returns confirmation to UI
    //returns message if no accounts found
    public static String[] getAllAccounts(){
        AccountNode current = accounts.getHead();
        int acctCount = 0;
        while(current != null){
            acctCount++;
            current = current.getNext();
        }
        
        String[] acctList = new String[acctCount];
        int i = 0;
        
        current = accounts.getHead();
        while(current != null){
            acctList[i++] = current.getValue().toString();
            current = current.getNext();
        }
        if(acctList.length == 0){
            acctList = new String[1];
            acctList[0] = "\nNo accounts found.";
        }
        return acctList;
    }
    
    //retrieves all transactions, returns confirmation to UI
    //returns message if no transactions found
    public static String[] getAllTransactions(){
        TransactionNode current = transHistory.getHead();
        int transCount = 0;
        while(current != null){
            transCount++;
            current = current.getNext();
        }
        
        String[] transList = new String[transCount];
        int i = 0;
        
        current = transHistory.getHead();
        while(current != null){
            transList[i++] = current.getValue().toString();
            current = current.getNext();
        }
        if(transList.length == 0){
            transList = new String[1];
            transList[0] = "\nNo transactions found.";
        }
        return transList;
    }
}