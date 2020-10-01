// ************************************************************************
// Author: Arun Kulkarni
// Date Created: May 20, 2020
// Class Name: Main
// Description: Contains main method for Vehicle Rental Agency program
// ************************************************************************

//package statement
package Main;

//import statements
import Interfaces.*;
import java.util.*;
import Financials.*;
import vehicles.*;

public class Main {
    
    //class variables
    private static UserInterface ui;
    static Scanner input = new Scanner(System.in);
    
    //main method
    public static void main(String[] args){
        
        //initialize agency rates with given values
        CurrentRates agencyRates = new CurrentRates(new CarRates(24.95,159.95,514.95,.15,14.95),
                                                    new SUVRates(29.95,189.95,679.95,.15,14.95),
                                                    new TruckRates(35.95,224.95,787.95,.26,22.95));
        
        //create new Vehicles linked list
        Vehicles agencyVehicles = new Vehicles();
        //populate Vehicles with given vehicle descriptions
        populate(agencyVehicles);
        
        //create new Accounts linked list
        Accounts accounts = new Accounts();
        
        //two hard-coded accounts, mainly for usability purposes
        accounts.add(new Account("1234123412341234","Arun, Inc.",true));
        accounts.add(new Account("0000111100001111","Cars Corp.",false));
        
        //create new Transactions linked list
        Transactions transactions = new Transactions();
        
        //get UI option, initialize system interface if not
        //already initialized
        boolean quit = false;
        while(!quit){
            ui = getUI(input);
            
            if(ui == null){
                System.out.println("\nThank you for your business! Goodbye.");
                quit = true;
            }
            else{
                if(!SystemInterface.initialized()){
                    SystemInterface.initSystem(agencyRates, agencyVehicles, accounts, transactions);
                }
                ui.start(input);
            }
        }
    }
    
    //get UI option from user
    public static UserInterface getUI(Scanner input){
        boolean validSelection = false;
        int selection;
        do{
            try{
                System.out.print("1 - Employee, 2 - Manager, 3 - Quit: ");
                selection = Integer.parseInt(input.next());
                if(selection == 1){
                    ui = new EmployeeUI();
                    validSelection = true;
                }
                else if(selection == 2){
                    ui = new ManagerUI();
                    validSelection = true;
                }
                else if(selection == 3){
                    ui = null;
                    validSelection = true;
                }
                else{
                    System.out.print("Invalid input. Try again: ");
                    System.out.println(validSelection);
                }
            }
            catch(NumberFormatException e){
                System.out.print("Invalid selection - reenter: ");
            }
        }while(!validSelection);
        return ui;
    }
    
    //populate Vehicles linked list with all given vehicles
    private static void populate(Vehicles vehs){
        vehs.add(new Car("Car","Chevrolet Camaro - 2018",30,"HK4GM4564GD",2));
        vehs.add(new Car("Car","Ford Fusion - 2018",34,"AB4EG5689GM",4));
        vehs.add(new Car("Car","Ford Fusion Hybrid - 2017",32,"KU4EG3245RW",4));
        vehs.add(new Car("Car","Chevrolet Impala - 2019",30,"RK3BM4356YH",4));
        
        //SUVs
        vehs.add(new SUV("SUV","Honda Odyssey - 2020",28,"VM9RE2635TD",7,6));
        vehs.add(new SUV("SUV","Dodge Caravan - 2019",25,"QK3FL4273ME",5,4));
        vehs.add(new SUV("SUV","Ford Expedition - 2018",20,"JK2RT9264HY",5,3));
        
        //Trucks
        vehs.add(new Truck("Truck","Ten-Foot",12,"EJ5KU2435BC",2,2810));
        vehs.add(new Truck("Truck","Eighteen-Foot",10,"KG4DM5472RK",2,5930));
        vehs.add(new Truck("Truck","Twenty-Four-Foot",8,"EB2WR3082QB",2,6500));
        vehs.add(new Truck("Truck","Twenty-Four-Foot",8,"TV3GH4290EK",2,6500));  
    }   
}