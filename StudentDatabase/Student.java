/* 
Author: Arun Kulkarni 
Date: March 10, 2019
Description: This program creates a custom Java Swing GUI to create and store
a student database. This class creates an object named student with parameters
specified in the project description document.
*/

//name of source package for java project
package p4gui;

//class Student to create Student objects
public class Student{
    
    //all data fields declared, some initialized to 0
    String ID;
    String name;
    String major;
    double totalCredits;
    double QP = 0.0;
    double GP = 0.0;
    double totalQP;
    double GPA = 0.0;
    
    //constructor for Student object taking ID, name, and major as parameters
    public Student(String ID, String name, String major){
        
        this.ID = ID;
        this.name = name;
        this.major = major;
        //totalCredits and totalQP initialized to 0
        this.totalCredits = 0.0;
        this.totalQP = 0.0;        
    }
    
    //courseCompleted() method takes credits and grade as parameters,
    //and calculated cumulative GPA for student
    public void courseCompleted(double credits, String grade){
        GP = 0;
        //switch statement for letter grade to grade point conversion
        switch(grade){
            case "A":
                GP = 4;
                break;
            case "B":
                GP = 3;
                break;
            case "C":
                GP = 2;
                break;
            case "D":
                GP = 1;
                break;
            case "F":
                GP = 0;
                break;
        }
        //arithmetic used to calculate cumulative GPA
        QP = GP * credits;
        totalQP = totalQP + QP;
        totalCredits = totalCredits + credits;
        GPA = totalQP / totalCredits;
    }
    
    //getGPA() method returns GPA rounded to two decimal places
    public double getGPA(){
        GPA = Math.round(GPA*100.0)/100.0;
        return GPA;
    }
    
    //overridden toString() method used to display student information
    @Override
    public String toString(){
        return "ID: " + ID + "\nName: " + name + "\nMajor: " + major + "\nGPA: "
                + getGPA();                
    }
    
    //textFileString() method used to display student information on one line
    //in the outputData.txt file
    public String textFileString(){
        return "ID: "+ ID + "\tName: " + name + "\tMajor: " + major + "\tGPA: " + GPA;
    }    
}//end class Student