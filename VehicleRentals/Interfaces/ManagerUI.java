// ************************************************************************
// Author: Arun Kulkarni
// Date Created: May 20, 2020
// Class Name: ManagerUI
// Description: User interface for Managers
// ************************************************************************

//package statements
package Interfaces;

//import statements
import java.util.*;
import Financials.*;
import Main.Utilities;
import utilities.Exceptions.*;

public class ManagerUI implements UserInterface{
    
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
            //display/update rates
            case 1: veh_type = getVehicleType(input);
                switch(veh_type){
                    //option for Car rates
                    case 1: 
                        int choice = getRateSelection(input);
                        if(choice == 1){
                            display_lines = SystemInterface.getCarRates();
                        }
                        else if(choice == 2){
                            System.out.print("Enter new daily rate: ");
                            double dailyRate = getRate(input);
                            System.out.print("Enter new weekly rate: ");
                            double weeklyRate = getRate(input);
                            System.out.print("Enter new monthly rate: ");
                            double monthlyRate = getRate(input);
                            System.out.print("Enter new mileage rate: ");
                            double mileRate = getRate(input);
                            System.out.print("Enter new insurance rate: ");
                            double insRate = getRate(input);
                            
                            display_lines = SystemInterface.updateCarRates
                                (new CarRates(dailyRate, weeklyRate,monthlyRate,
                                              mileRate,insRate));
                        }
                        break;
                        //option for SUV rates
                    case 2: 
                        choice = getRateSelection(input);
                        if(choice == 1){
                            display_lines = SystemInterface.getSUVRates();
                        }
                        else if(choice == 2){
                            System.out.print("Enter new daily rate: ");
                            double dailyRate = getRate(input);
                            System.out.print("Enter new weekly rate: ");
                            double weeklyRate = getRate(input);
                            System.out.print("Enter new monthly rate: ");
                            double monthlyRate = getRate(input);
                            System.out.print("Enter new mileage rate: ");
                            double mileRate = getRate(input);
                            System.out.print("Enter new insurance rate: ");
                            double insRate = getRate(input);
                            
                            display_lines = SystemInterface.updateSUVRates
                                (new CarRates(dailyRate, weeklyRate,monthlyRate,
                                              mileRate,insRate));
                        }
                        break;
                        //option for Truck rates
                    case 3: 
                        choice = getRateSelection(input);
                        if(choice == 1){
                            display_lines = SystemInterface.getTruckRates();
                        }
                        else if(choice == 2){
                            System.out.print("Enter new daily rate: ");
                            double dailyRate = getRate(input);
                            System.out.print("Enter new weekly rate: ");
                            double weeklyRate = getRate(input);
                            System.out.print("Enter new monthly rate: ");
                            double monthlyRate = getRate(input);
                            System.out.print("Enter new mileage rate: ");
                            double mileRate = getRate(input);
                            System.out.print("Enter new insurance rate: ");
                            double insRate = getRate(input);
                            
                            display_lines = SystemInterface.updateTruckRates
                                (new CarRates(dailyRate, weeklyRate,monthlyRate,
                                              mileRate,insRate));
                        }
                        break;
                }
                displayResults(display_lines);
                break;
            //display all vehicles
            case 2:
                display_lines = SystemInterface.getAllVehicles();
                displayResults(display_lines);
                break;
            //add new account  
            case 3: 
                String cardNum = getCreditCardNum(input);
                input.nextLine();
                System.out.print("Enter company name: ");
                String compName = input.nextLine();
                boolean primeCust = getPrimeCust(input);
                display_lines = SystemInterface.addAccount(cardNum, compName, 
                                                           primeCust);
                displayResults(display_lines);
                break;
            //display all reservations
            case 4:
                display_lines = SystemInterface.getAllReservations();
                displayResults(display_lines);
                break;
            //display all accounts
            case 5: 
                display_lines = SystemInterface.getAllAccounts();
                displayResults(display_lines);
                break;
            //display all transactions
            case 6: 
                display_lines = SystemInterface.getAllTransactions();
                displayResults(display_lines);
                break;
            case 7: 
                quit = true;
        }
    }
    
    //displays menu
    private void displayMenu(){
        System.out.println("\nMAIN MENU - MANAGER"); 
        System.out.print("1 - View/Update Rates");
        System.out.print("\t\t...\tallows updating of rental and insurance rates\n\n");
        
        System.out.print("2 - View All Vehicles");
        System.out.print("\t\t...\tdisplays all vehicles of the agency\n\n");
        
        System.out.print("3 - Add Account");
        System.out.print("\t\t\t...\tallows entry of a new customer account\n\n");
        
        System.out.print("4 - View All Reservations");
        System.out.print("\t...\tdisplays all current reservations\n\n");
        
        System.out.print("5 - View All Accounts");
        System.out.print("\t\t...\tdisplays all customer accounts\n\n");
        
        System.out.print("6 - View Transactions");
        System.out.print("\t\t...\tdisplays all transactions\n\n");
        
        System.out.println("7 - Quit\n"); 
    }
    
    //get menu selection from user
    private int getSelection(Scanner input){
        System.out.print("Enter menu selection: ");
        boolean success = false;
        int selection = 1;        
        do{
            try{
                selection = Integer.parseInt(input.next());
                if(selection >= 1 && selection <= 7){
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
    
    //get vehicle type from user
    private int getVehicleType(Scanner input){
        System.out.print("Enter vehicle type: ");
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
    
    //get rate option for menu option 1 from user
    private int getRateSelection(Scanner input){
        System.out.print("Enter 1 to view rates, or 2 to update: ");
        boolean success = false;
        int selection = 1;        
        do{
            try{
                selection = Integer.parseInt(input.next());
                if(selection >= 1 && selection <= 2){
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
    
    //get rate value from user
    private double getRate(Scanner input){
        boolean success = false;
        double miles = 0;        
        do{
            try{
                miles = Double.parseDouble(input.next());
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
     
    //get prime customer option from user
    private boolean getPrimeCust(Scanner input){
        System.out.print("Prime Customer? Y/N: ");
        char selection;
        boolean primeCust = true;
        do{
            selection = input.next().charAt(0);
        }while(selection != 'Y' && selection != 'y' && selection != 'N' &&
               selection != 'n');
        if(selection == 'Y' || selection == 'y'){
            return primeCust;
        }
        else{
            return !primeCust;
        }
    }
    
    //display results from String[] passed line by line
    private void displayResults(String[] lines){
        for (String line : lines) {
            System.out.println(line);
        }
    }   
}