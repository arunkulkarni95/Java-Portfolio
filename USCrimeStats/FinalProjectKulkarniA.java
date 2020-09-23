/*
Date Completed: December 14, 2018
Author: Arun Kulkarni
Course: CMIS 141

Description: This program reads data about US Crime Statistics from the file
Crime.csv, stores it in a 2D array, and then performs various calculations
with this data based on the user's selection from a menu with 7 options.
*/
package finalprojectkulkarnia; //name of source package for java project

//java imports for various methods and classes called in the program
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FinalProjectKulkarniA{ //name of program
    
    //time variables used to store program start and stop time data
    static LocalTime startTime;
    static LocalTime stopTime;                
    
    public static class USCrimeClass{ //class to read data and perform calculations
        
        //all data fields declared corresponding to data contained in .csv file
        private int year;
        private int population;
        private int violentCrime;
        private double violentCrimeRate;
        private int murder;
        private double murderRate;
        private int rape;
        private double rapeRate;
        private int robbery;
        private double robberyRate;
        private int aggAssault;
        private double aggAssaultRate;
        private int propCrime;
        private double propCrimeRate;
        private int burglary;
        private double burglaryRate;
        private int larcTheft;
        private double larcTheftRate;
        private int motVTheft;
        private double motVTheftRate;
        
        //2D String array declared and initialized to hold Crime.csv values
        public static String crimes[][] = new String[21][20];
        
        //a no-argument constructor with no default values
        //for the USCrimeClass class
        public USCrimeClass(){            
        }//end constructor
        
        //begin method to read data from Crime.csv file
        public void readData() throws IOException{
            
            //create new BufferedReader to read Crime.csv file
            FileReader inputValues = new FileReader("Crime.csv");
            BufferedReader holdValues = new BufferedReader(inputValues);

            String line; //new String to represent each line
            //row and column "counter" variables declared and initialized
            int row = 0;
            int col = 0;
            //begin while loop to read file line-by-line
            while ((line = holdValues.readLine()) != null) {
                    col = 0;
                    //for-each loop to split each line on the commas
                    //and index each element of each line
                    for(String strElement : line.split(",")) { 
                        //each String element of each line indexed into
                        //2D array
                        crimes[row][col]=strElement;                            
                        col++;
                    }//end for-each loop
                    row++;
            }//end while loop
            
            holdValues.close();//close BufferedReader          
        }//end readData() method
        
        //begin DisplayMenu() method to display program menu and call proper
        //methods or end program based on user selection
        public void displayMenu(){
            
            //new Scanner stdin for user input and new string selection
            //to store user input values
            Scanner stdin = new Scanner(System.in);
            String selection;
            
            while(true){//begin while loop to display menu
                
                //print statements to display menu options to user
                System.out.print("Enter the number of the question you want answered. ");
                System.out.println("Enter \"Q\" to quit the program.\n");
                System.out.print("\t\t1. What was the percentage in population growth ");
                System.out.println("between any two consecutive years from 1994 – 2013?");
                System.out.println("\t\t2. What year was the murder rate the highest?");
                System.out.println("\t\t3. What year was the murder rate the lowest?");
                System.out.println("\t\t4. What year was the robbery rate the highest?");
                System.out.println("\t\t5. What year was the robbery rate the lowest?");
                System.out.println("\t\t6. What year was the population the highest?");
                System.out.println("\t\t7. What year was the population the lowest?");
                System.out.println("\t\tQ. Quit the program.\n");
                                
                do{//begin do-while loop to catch exceptions from user input
                    try{//begin try statement
                        selection = stdin.next();//user input stored as string
                        //if selection is "q" or "Q" program ends
                        //with elapsed time printed
                        if (selection.equals("Q") || selection.equals("q")){
                            //program end time stored as stopTime
                            stopTime = LocalTime.now();        
                            //program-end message displayed and
                            //time in seconds calculated and displayed to user
                            System.out.println("\nThank you for trying the US Crime Statistics Program!");
                            System.out.print("Elapsed time: ");
                            System.out.print(ChronoUnit.SECONDS.between(startTime, stopTime));
                            System.out.println(" seconds.");
                            //command to end program
                            System.exit(0);
                        }//end if statement
                        //begin switch statement if user enters int value
                        switch (Integer.parseInt(selection)){
                            //method calls to appropriate method
                            //based on user selection from menu
                            case 1:
                                popGrowth();
                                break;
                            case 2:
                                maxMurder();
                                break;
                            case 3:
                                minMurder();
                                break;
                            case 4:
                                maxRobbery();
                                break;
                            case 5:
                                minRobbery();
                                break;
                            case 6:
                                maxPopulation();
                                break;
                            case 7:
                                minPopulation();
                                break;
                            default:
                                //error message if int out of range
                                System.out.println("Selection out of range! Try again.");                  
                        }//end switch statement
                    }//end try statement
                    //NumberFormatException thrown if user input is anything
                    //other than an int from 1-7 or the chars 'q' or 'Q'
                    catch (NumberFormatException e){
                        System.out.println("Couldn't parse input, please try again.");
                    }//end catch statement
                }while(true);//end do-whiile loop    
            }//end while loop           
        }//end displayMenu() method        
        
        //begin method to calculate percent population growth between
        //any two consecutive years in the range
        public void popGrowth(){
            
            //new scanner stdin
            //all local method variables declared
            Scanner stdin = new Scanner(System.in);
            int yearOne;
            int yearTwo;
            //both population variables initialized to 0
            double popOne = 0;
            double popTwo = 0;
            double pGrowth;
            System.out.println("Enter the first of the two years from 1994-2012:");
            do{//begin do-while loop
                try{//begin try statement
                    String tempYearOne = stdin.next();//user input stored as string
                    //attempt to parse int from string
                    yearOne = Integer.parseInt(tempYearOne);
                    //check for appropriate range of int if parsed
                    if (yearOne < 1994 || yearOne > 2012){
                        System.out.println("Out of range! Please try again");
                    }//end if statement
                    else{//break try statement if exception thrown
                        break; 
                    }//end else statement
                }
                //catch statement if NumberFormatException thrown
                catch (NumberFormatException e){
                    System.out.println("Couldn't parse input, please try again.");
                }//end catch statement
            }while(true);//end do-while loop
            
            //second consecutive year by adding 1 to first year
            yearTwo = yearOne + 1;
            
            //two for loops to "match" user input with corresponding
            //array entries for the two years
            for (int i = 1; i < 21; i++){
                //checks each array index in column 0 and assigns value
                //of population if match with user input found
                if (yearOne == Integer.parseInt(crimes[i][0])){
                    popOne = Double.parseDouble(crimes[i][1]);
                }//end if statement                
            }//end for loop
            for (int i = 1; i < 21; i++){
                //checks each array index in column 0 and assigns value
                //of population if match with user input found
                if (yearTwo == Integer.parseInt(crimes[i][0])){
                    popTwo = Double.parseDouble(crimes[i][1]);
                }//end if statement                
            }//end for loop
            
            //calculate percent population growth
            pGrowth = ((popTwo - popOne)/popOne)*100;
            //prints percent growth to display to user
            System.out.print("\n"+pGrowth+"% population growth from "+yearOne);
            System.out.println(" to "+yearTwo+".\n");
            //call to DisplayMenu() method to display menu for further selection
            displayMenu();
        }//end popGrowth() method
        
        //calculates year with maximum number of murders by number and rate
        public void maxMurder(){
            
            //proper fields initialized to row[1] values as pivot points for searches
            murder = Integer.parseInt(crimes[1][4]);
            murderRate = Double.parseDouble(crimes[1][5]);
            year = Integer.parseInt(crimes[1][0]);
            //searches array for largest value in position [i][4]
            //which corresponds to number of murders
            for (int i = 1; i < 21; i++){
                //if statement using pivot point to compare each value
                if (Integer.parseInt(crimes[i][4]) > murder){
                    //appropriate murder and year values assigned if greater
                    //value found
                    murder = Integer.parseInt(crimes[i][4]);
                    year = Integer.parseInt(crimes[i][0]);
                }//end if statement                                
            }//end for loop
            //searches array for largest value in position [i][5]
            //which corresponds to murder rate
            //a separate for loop is used in case the highest murder number
            //and murder rate occur in different years
            for (int i = 1; i < 21; i++){
                //if statement using pivot point to compare each value
                if (Double.parseDouble(crimes[i][5]) > murderRate){
                    murderRate = Double.parseDouble(crimes[i][5]);
                }//end if statement
            }//end for loop
            
            //prints message displayed to user stating year with most murders
            //by number and rate
            System.out.print("\nThe murder rate was highest in ");
            System.out.print(year+" with a total of "+murder+ " murders ");
            System.out.println("at a rate of "+murderRate+".\n");
            //call to displayMenu() method to display menu for further selection
            displayMenu();       
        }//end maxMurder() method
        
        //calculates year with maximum robberies by number and rate
        public void minMurder(){
            
            //proper fields initialized to row[1] values as pivot points for searches
            murder = Integer.parseInt(crimes[1][4]);
            murderRate = Double.parseDouble(crimes[1][5]);
            year = Integer.parseInt(crimes[1][0]);
            //searches array for smallest value in position [i][4]
            //which corresponds to number of murders
            for (int i = 1; i < 21; i++){
                //if statement using pivot point to compare each value
                if (Integer.parseInt(crimes[i][4]) < murder){
                    //appropriate murder and year values assigned if lesser
                    //value found
                    murder = Integer.parseInt(crimes[i][4]);
                    year = Integer.parseInt(crimes[i][0]);
                }//end if statement                                
            }//end for loop
            //searches array for smallest value in position [i][5]
            //which corresponds to murder rate
            //a separate for loop is used in case the smallest murder number
            //and murder rate occur in different years
            for (int i = 1; i < 21; i++){
                //if statement using pivot point to compare each value
                if (Double.parseDouble(crimes[i][5]) < murderRate){
                    murderRate = Double.parseDouble(crimes[i][5]);
                }//end if statement
            }//end for loop
            
            //prints message displayed to user stating year with fewest murders
            //by number and rate
            System.out.print("\nThe murder rate was lowest in ");
            System.out.print(year+" with a total of "+murder+ " murders ");
            System.out.println("at a rate of "+murderRate+".\n");
            //call to displayMenu() method to display menu for further selection
            displayMenu();       
        }//end minMurder() method
        
        //calculates year with maximum number of robberies by number and rate
        public void maxRobbery(){
            
            //proper fields initialized to row[1] values as pivot points for searches
            robbery = Integer.parseInt(crimes[1][8]);
            robberyRate = Double.parseDouble(crimes[1][9]);
            year = Integer.parseInt(crimes[1][0]);
            //searches array for largest value in position [i][8]
            //which corresponds to number of robberiess
            for (int i = 1; i < 21; i++){
                //if statement using pivot point to compare each value
                if (Integer.parseInt(crimes[i][8]) > robbery){
                    //appropriate robbery and year values assigned if greater
                    //value found
                    robbery = Integer.parseInt(crimes[i][8]);
                    year = Integer.parseInt(crimes[i][0]);
                }//end if statement                                
            }//end for loop
            //searches array for largest value in position [i][9]
            //which corresponds to robbery rate
            //a separate for loop is used in case the highest robbery number
            //and robbery rate occur in different years
            for (int i = 1; i < 21; i++){
                //if statement using pivot point to compare each value
                if (Double.parseDouble(crimes[i][9]) > robberyRate){
                    robberyRate = Double.parseDouble(crimes[i][9]);
                }//end if statement
            }//end for loop
            
            //prints message displayed to user stating year with most robberies
            //by number and rate
            System.out.print("\nThe robbery rate was highest in ");
            System.out.print(year+" with a total of "+robbery+ " robberies ");
            System.out.println("at a rate of "+robberyRate+".\n");
            //call to displayMenu() method to display menu for further selection
            displayMenu();       
        }//end maxRobbery() method
        
        //calculates year with minimum number of robberies by number and rate
        public void minRobbery(){
            
            //proper fields initialized to row[1] values as pivot points for searches
            robbery = Integer.parseInt(crimes[1][8]);
            robberyRate = Double.parseDouble(crimes[1][9]);
            year = Integer.parseInt(crimes[1][0]);
            //searches array for smallest value in position [i][8]
            //which corresponds to number of robberies
            for (int i = 1; i < 21; i++){
                //if statement using pivot point to compare each value
                if (Integer.parseInt(crimes[i][8]) < robbery){
                    //appropriate robbery and year values assigned if greater
                    //value found
                    robbery = Integer.parseInt(crimes[i][8]);
                    year = Integer.parseInt(crimes[i][0]);
                }//end if statement                                
            }//end for loop
            //searches array for smallest value in position [i][9]
            //which corresponds to robbery rate
            //a separate for loop is used in case the smallest robbery number
            //and robbery rate occur in different years
            for (int i = 1; i < 21; i++){
                //if statement using pivot point to compare each value
                if (Double.parseDouble(crimes[i][9]) < robberyRate){
                    robberyRate = Double.parseDouble(crimes[i][9]);
                }//end if statement
            }//end for loop
            
            //prints message displayed to user stating year with fewest robberies
            //by number and rate
            System.out.print("\nThe robbery rate was lowest in ");
            System.out.print(year+" with a total of "+robbery+ " robberies ");
            System.out.println("at a rate of "+robberyRate+".\n");
            //call to displayMenu() method to display menu for further selection
            displayMenu();       
        }//end minRobbery() method
        
        //calculates year with maximum population
        public void maxPopulation(){
            //proper fields initialized to row[1] values as pivot points for searches
            population = Integer.parseInt(crimes[1][1]);
            year = Integer.parseInt(crimes[1][0]);
            //searches array for largest value in position [i][1]
            //which corresponds to population
            for (int i = 1; i < 21; i++){
                //if statement using pivot point to compare each value
                if (Integer.parseInt(crimes[i][1]) > population){
                    //appropriate population and year values assigned if greater
                    //value found
                    population = Integer.parseInt(crimes[i][1]);
                    year = Integer.parseInt(crimes[i][0]);
                }//end if statement                                
            }//end for loop
            
            //prints message displayed to user stating year with highest population
            //by number
            System.out.print("\nThe population was highest in ");
            System.out.println(year+" with a total of "+population+ " people.\n");
            //call to displayMenu() method to display menu for further selection
            displayMenu();       
        }//end maxPopulation() method
        
        //calculates year with minimum population
        public void minPopulation(){
            
            //proper fields initialized to row[1] values as pivot points for searches
            population = Integer.parseInt(crimes[1][1]);
            year = Integer.parseInt(crimes[1][0]);
            //searches array for smallest value in position [i][1]
            //which corresponds to population
            for (int i = 1; i < 21; i++){
                //if statement using pivot point to compare each value
                if (Integer.parseInt(crimes[i][1]) < population){
                    //appropriate population and year values assigned if smaller
                    //value found
                    population = Integer.parseInt(crimes[i][1]);
                    year = Integer.parseInt(crimes[i][0]);
                }//end if statement                                
            }//end for loop
            
            //prints message displayed to user stating year with highest population
            //by number
            System.out.print("\nThe population was lowest in ");
            System.out.println(year+" with a total of "+population+ " people.\n");
            //call to displayMenu() method to display menu for further selection
            displayMenu();       
        }//end minPopulation() method
    
    }//end USCrimeClass    
    
    //begin programming arguments
    public static void main(String[] args) throws IOException{
        
        //startTime variable initialized to starting time of program
        startTime = LocalTime.now();
        
        //prints welcome message to user
        System.out.println("Welcome to the US Crime Statistics Program!\n");
        
        //creates new instance of USCrimeClass class named crimestats
        USCrimeClass crimestats = new USCrimeClass();
        
        //call method to read data from Crime.csv
        crimestats.readData();
        //call method to display menu for user selection
        crimestats.displayMenu();
    }//end main method   

}//end FinalProjectKulkarniA