// ************************************************************************
// Author: Arun Kulkarni
// Date Created: May 20, 2020
// Class Name: EmployeeUI
// Description: User interface for Employees
// ************************************************************************

//package statement
package Interfaces;

//import statements
import java.util.*;
import Financials.*;
import Main.Utilities;
import utilities.Exceptions.FailedRentalPeriodEntryException;
import utilities.Exceptions.InvalidNumberCharsException;
import utilities.Exceptions.NonDigitFoundException;

public class EmployeeUI implements UserInterface{
    
    //class variables
    Scanner input = new Scanner(System.in);
    private boolean quit = false;
    
    //start method to loop through menu and execute selections
    public void start(Scanner input){
        
        int selection;
        
        while(!quit){
            displayMenu();
            selection = getSelection(input);
            execute(selection, input);
        }
    }
    
    private void execute(int selection, Scanner input){
        int veh_type;
        String vin, creditcard_num; 
        String[] display_lines = new String[10];
        RentalDetails rental_details; 
        ReservationDetails reserv_details;
        switch(selection) {
            // display rental rates
            case 1: veh_type = getVehicleType(input);
                switch(veh_type){
                    case 1: display_lines = SystemInterface.getCarRates(); break;
                    case 2: display_lines = SystemInterface.getSUVRates(); break;
                    case 3: display_lines = SystemInterface.getTruckRates(); break;
                }
                displayResults(display_lines);
                break;
            // display available vehicles
            case 2: veh_type = getVehicleType(input);
                switch(veh_type){
                    case 1: display_lines = SystemInterface.getAvailCars(); break;
                    case 2: display_lines = SystemInterface.getAvailSUVs(); break;
                    case 3: display_lines = SystemInterface.getAvailTrucks(); break;
                }
                displayResults(display_lines);
                break;
            // display estimated rental cost
            case 3: rental_details = getRentalDetails(input);
                display_lines = SystemInterface.estimatedRentalCost(rental_details);
                displayResults(display_lines);
                break;  
            // make a reservation
            case 4: reserv_details = getReservationDetails(input);
                display_lines = SystemInterface.makeReservation(reserv_details);               
                displayResults(display_lines);               
                break;
            // cancel a reservation
            case 5: vin = getVIN(input);
                display_lines = SystemInterface.cancelReservation(vin);
                displayResults(display_lines);
                break;
            // view corporate account (and company reservations)
            case 6: creditcard_num = getCreditCardNum(input);
                display_lines = SystemInterface.getAccount(creditcard_num);
                displayResults(display_lines);
                break;
            // process returned vehicle
            case 7: creditcard_num = getCreditCardNum(input);
                vin = getVIN(input);
                int numDaysUsed = getDaysUsed(input);
                System.out.print("Enter number of miles driven: ");
                int numMilesDriven = getMiles(input);
                display_lines = SystemInterface.processReturnedVehicle(vin,
                                numDaysUsed,numMilesDriven);
                displayResults(display_lines);
                break;
            // quit program
            case 8: quit = true;
        }
    }
    
    //private methods
    
    //displays menu
    private void displayMenu(){
        System.out.println("\nMAIN MENU - EMPLOYEE"); 
        System.out.print("1 - View Current Rates");
        System.out.print("\t\t...\tdisplays rental (and insurance rates)");
        System.out.print("\n\t\t\t\t\tfor one of cars, SUVs, or trucks\n\n");
        
        System.out.print("2 - View Available Vehicles");
        System.out.print("\t...\tdisplays available vehicles (cars, Suvs, or");
        System.out.print("\n\t\t\t\t\ttrucks\n\n");
        
        System.out.print("3 - Calc Estimated Rental Cost");
        System.out.print("\t...\tdisplays estimated rental cost for given vehicle");
        System.out.print("\n\t\t\t\t\ttype, rental period, expected miles driven,");
        System.out.print("\n\t\t\t\t\toptional daily insurance, and if Prime Customer\n\n");
        
        
        System.out.print("4 - Make a Reservation");
        System.out.print("\t\t...\tcreates a reservation for VIN, credit card num,");
        System.out.print("\n\t\t\t\t\trental period, and insurance option\n\n");
        
        System.out.print("5 - Cancel a Reservation");
        System.out.print("\t...\tcancels a reservation by VIN\n\n");
        
        System.out.print("6 - View Corporate Account");
        System.out.print("\t...\tdisplays account information for a given account");
        System.out.print("\n\t\t\t\t\tnumber, incuding all current reservations\n\n");
        
        System.out.print("7 - Process Returned Vehicle");
        System.out.print("\t...\trequests VIN and actual number of miles driven");
        System.out.print("\n\t\t\t\t\tand processes returned vehicle and displays");
        System.out.print("\n\t\t\t\t\ttotal charge\n\n");
        
        System.out.println("8 - Quit\n"); 
    }
    
    //get menu selection from user
    private int getSelection(Scanner input){
        System.out.print("Enter menu selection: ");
        boolean success = false;
        int selection = 1;        
        do{
            try{
                selection = Integer.parseInt(input.next());
                if(selection >= 1 && selection <= 8){
                    success = true;
                }
                else{
                    System.out.print("Invalid input. Try again: ");
                    break;
                }
            }
            catch(NumberFormatException e){
                System.out.print("Invalid input. Try again: ");
            }
        }while(!success);
        return selection;
    }
    
    //get VIN from user
    private String getVIN(Scanner input){
        System.out.print("Enter VIN: ");
        String VIN = input.next();
        return VIN;
    }
    
    //get vehicle type from user
    private int getVehicleType(Scanner input){
        System.out.print("Enter vehicle type (1 - Car, 2 - SUV, 3 - Truck): ");
        boolean success = false;
        int selection = 1;        
        do{
            try{
                selection = Integer.parseInt(input.next());
                if(selection >= 1 && selection <= 3){
                    success = true;
                }
                else{
                    System.out.print("Invalid input. Try again: ");
                    break;
                }
            }
            catch(NumberFormatException e){
                System.out.print("Invalid input. Try again: ");
            }
        }while(!success);
        return selection;
    }
    
    //get credit card number from user
    private String getCreditCardNum(Scanner input){
        System.out.print("Enter Credit Card Number: ");
        boolean success = false;
        String cardNum = "";
        do{
            try{
                cardNum = input.next();
                Utilities.validateCreditCard(cardNum);
                success = true;
            }
            catch(InvalidNumberCharsException | NonDigitFoundException e){
                System.out.print("Invalid card number. Try again: ");
            }
        }while(!success);
        return cardNum;
    }
    
    //get rental period from user
    private String getRentalPeriod(Scanner input){
        System.out.print("Enter rental period in following format: ");
        System.out.print("D2 - two days, W3-three weeks, M1-one month, etc.\n");
        boolean success = false;
        String rentalPeriod = "";
        do{
            try{
                rentalPeriod = input.next();
                if(rentalPeriod.length() != 2){
                    throw new FailedRentalPeriodEntryException();
                }
                char timeunits = rentalPeriod.charAt(0);
                if(timeunits != 'D' && timeunits != 'd' && timeunits != 'M' && timeunits != 'm'
                    && timeunits != 'W' && timeunits != 'w'){
                    throw new FailedRentalPeriodEntryException();
                }
                if(!Character.isDigit(rentalPeriod.charAt(1))){
                    throw new FailedRentalPeriodEntryException();
                }
                success = true;
            }
            catch(FailedRentalPeriodEntryException e){
                System.out.print("Invalid entry. Try again: ");
            }
        }while(!success);
        return rentalPeriod;
    }
    
    //get mileage from user
    private int getMiles(Scanner input){
        boolean success = false;
        int miles = 0;        
        do{
            try{
                miles = Integer.parseInt(input.next());
                if(miles >= 0){
                    success = true;
                }
                else{
                    System.out.print("Invalid input. Try again: ");
                    break;
                }
            }
            catch(NumberFormatException e){
                System.out.print("Invalid input. Try again: ");
            }
        }while(!success);
        return miles;
    }
    
    //get days used from user
    private int getDaysUsed(Scanner input){
        System.out.print("Enter number of days used: ");
        boolean success = false;
        int days = 0;
        do{
            try{
                days = Integer.parseInt(input.next());
                if(days >= 0){
                    success = true;
                }
                else{
                    System.out.print("Invalid input. Try again: ");
                    break;
                }
            }
            catch(NumberFormatException e){
                System.out.print("Invalid input. Try again: ");
            }
        }while(!success);
        return days; 
    }
    
    //get insurance option from user
    private boolean getIns(Scanner input){    
        char selection;
        boolean ins = true;
        do{
            System.out.print("Insurance? Y/N: ");
            selection = input.next().charAt(0);
        }while(selection != 'Y' && selection != 'y' && selection != 'N' &&
               selection != 'n');
        if(selection == 'Y' || selection == 'y'){
            ins = true;
        }
        else if(selection == 'N' || selection == 'n'){
            ins = false;
        }
        return ins;
    }
    
    //get prime customer option from user
    private boolean getPrimeCust(Scanner input){      
        char selection;
        boolean primeCust = true;
        do{
            System.out.print("Prime Customer? Y/N: ");
            selection = input.next().charAt(0);
        }while(selection != 'Y' && selection != 'y' && selection != 'N' &&
               selection != 'n');
        if(selection == 'Y' || selection == 'y'){
            primeCust = true;
        }
        else if(selection == 'N' || selection == 'n'){
            primeCust = false;
        }
        return primeCust;
    }
    
    //get rental details from user
    private RentalDetails getRentalDetails(Scanner input){
        
        System.out.println("Enter rental details as follows:");
        int vehicleType = getVehicleType(input);
        String rentalPeriod = getRentalPeriod(input);
        System.out.print("Enter estimated number of miles: ");
        int estMiles = getMiles(input);
        boolean ins = getIns(input);
        boolean primeCust = getPrimeCust(input);
        
        return new RentalDetails(vehicleType,rentalPeriod,estMiles,ins,primeCust);
    }
    
    //get reservation details from user
    private ReservationDetails getReservationDetails (Scanner input){
        
        System.out.println("Enter reservation details as follows: ");
        String vin = getVIN(input);
        String cardNum = getCreditCardNum(input);
        String rentalPeriod = getRentalPeriod(input);
        boolean ins = getIns(input);
        
        return new ReservationDetails(vin,cardNum,rentalPeriod,ins);       
    }
    
    //display results from String[] passed line by line
    private void displayResults(String[] lines){
        System.out.println();
        for (String line : lines) {
            if(line != null){
                  System.out.println(line);
            }             
        }
    }
}