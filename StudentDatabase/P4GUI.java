/* 
Author: Arun Kulkarni 
Date: March 10, 2019
Description: This program creates a custom Java Swing GUI to create and store
a student database
*/

//name of source package for java project
package p4gui; 

//java classes imported for use
import java.text.*;
import java.math.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

//begin P4GUI class to construct swing GUI
public class P4GUI extends JFrame{
    
    //begin P4GUI constructor
    private P4GUI(){
        
        //panel format and size
        super("Rectangle");
        setTitle("Project 4");
        setSize(425,350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //initialize all text fields
        JTextField IDText = new JTextField("",15);
        JTextField nameText = new JTextField("",15);
        JTextField majorText = new JTextField("",15);
        
        //initialize all labels
        JLabel IDLabel = new JLabel("ID:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel majorLabel = new JLabel("Major:");
        JLabel selectionLabel = new JLabel("Choose Selection:");
        JLabel fillerLabel = new JLabel("");
        
        //initialize JButton
        JButton processBtn = new JButton("Process Request");
        
        //initialize JComboBox
        String options[] = {"Insert","Delete","Find","Update"};
        JComboBox optionBox = new JComboBox(options);
        
        //initialize new HashMap with Double key and Student value values
        Map<Double, Student> students = new HashMap<Double,Student>();
        
        //one of two border panels
        setLayout(new BorderLayout());
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(5,1));
        westPanel.add(IDLabel);
        westPanel.add(nameLabel);
        westPanel.add(majorLabel);
        westPanel.add(selectionLabel);
        westPanel.add(processBtn);
        add(westPanel,BorderLayout.WEST);
        
        //two of two border panels
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new GridLayout(5,1));
        eastPanel.add(IDText);
        eastPanel.add(nameText);
        eastPanel.add(majorText);
        eastPanel.add(optionBox);
        eastPanel.add(fillerLabel);
        add(eastPanel,BorderLayout.EAST);
        
        //sets panel to visible
        setVisible(true);
        
        //event listener class for JButton and JComboBox
        class OptionHandler implements ActionListener{
            
            //ActionEvent method
            public void actionPerformed(ActionEvent e){
                //begin try-catch block
                try{
                    //switch statement to listen to JComboBox selections
                    String selectedOption = (String)optionBox.getSelectedItem();
                    switch (selectedOption) {
                        //If Insert is selected, a new key-value pair created
                        case "Insert":
                            //test if key already exists to prevent duplicates
                            if(students.containsKey(Double.parseDouble(IDText.getText())))
                                JOptionPane.showMessageDialog(null,"Duplicate ID numbers not allowed.");
                            else{
                                //checks name and major text fields for any characters other than
                                //letters
                                if (!nameText.getText().matches("[a-zA-Z ]+"))
                                    JOptionPane.showMessageDialog(null,"Please enter a valid name.");
                                else if (!majorText.getText().matches("[a-zA-Z ]+"))
                                    JOptionPane.showMessageDialog(null,"Please enter a valid major.");
                                else{
                                //creates new key-value pair, with a new instance of the Student class
                                //being the value using the specified parameters provided by the user
                                students.put(Double.parseDouble(IDText.getText()),new Student(IDText.getText(),nameText.getText(),majorText.getText()));
                                //JOptionPane showing successful operation
                                JOptionPane.showMessageDialog(null,"Success!\n" + students.get(Double.parseDouble(IDText.getText())));
                                }
                            }
                            //clear all text fields after performed operation
                            IDText.setText("");
                            nameText.setText("");
                            majorText.setText("");
                            break;                            
                        case "Delete": 
                            //gets user-specified ID number
                            Double deleteID = Double.parseDouble(IDText.getText());
                            //throws NullPointerException if key is not used
                            if(students.get(deleteID).equals(""))
                                throw new NullPointerException();
                            //deletes specified student value
                            students.remove(deleteID);
                            //message showing student has been successfully deleted
                            JOptionPane.showMessageDialog(null,"Student " + deleteID + " successfully deleted.");
                            //clear all text fields after performed operation
                            IDText.setText("");
                            nameText.setText("");
                            majorText.setText("");
                            break;
                        case "Find":
                            //gets user-specified ID number
                            Double findID = Double.parseDouble(IDText.getText());                        
                            //throws NullPointerException if key is not used
                            if(students.get(findID).equals(""))
                                throw new NullPointerException();
                            //displays all student information using overridden toString() method
                            if (findID != null)
                                JOptionPane.showMessageDialog(null,students.get(findID));
                            //clear all text fields after performed operation
                            IDText.setText("");
                            nameText.setText("");
                            majorText.setText("");
                            break;
                        case "Update":
                            //gets user-specified ID number
                            Double updateID = Double.parseDouble(IDText.getText());
                            //throws NullPointerException if key is not used
                            if(students.get(updateID).equals(""))
                                throw new NullPointerException();
                            //initialize two JComboBoxes for JOptionPanes
                            //one for grade selection and one for credit selection
                            String[] gradeList = {"A","B","C","D","F"};
                            String[] creditList = {"3","6"};
                            JComboBox grades = new JComboBox(gradeList);
                            JComboBox creditChoice = new JComboBox(creditList);
                            
                            //prompt user to select grade, stores value as string
                            JOptionPane.showMessageDialog(null,grades,"Grade",JOptionPane.QUESTION_MESSAGE);
                            String grade = (String)grades.getSelectedItem();
                            
                            //prompt user to select credits, stores value as double
                            JOptionPane.showMessageDialog(null,creditChoice,"Credits",JOptionPane.QUESTION_MESSAGE);
                            double credits = Double.parseDouble((String)creditChoice.getSelectedItem());                            
                            
                            //passes grade and credit values to courseCompleted()
                            //method for the specified student
                            students.get(updateID).courseCompleted(credits,grade);
                            JOptionPane.showMessageDialog(null,"Success!\n" + students.get(updateID));                            
                            //clear all text fields after performed operation
                            IDText.setText("");
                            nameText.setText("");
                            majorText.setText("");
                            break;
                    }
                }
                //catch NullPointerException and NumberFormatException
                //and display error messages
                catch (NullPointerException n){
                    JOptionPane.showMessageDialog(null,"Student does not exist!");
                }
                catch (NumberFormatException numform){
                    JOptionPane.showMessageDialog(null,"Please enter a valid ID number.");
                }
            }//end ActionEvent method                        
        }//end OptionHandler class
        
        class ExitListener extends WindowAdapter{
            //variables initialized
            int count = 0;
            double totalGPA = 0;
            //windowClosing method
            public void windowClosing(WindowEvent w){
                //begin try block
                try{
                //new PrintWriter object to write to file outData.txt
                PrintWriter pw = new PrintWriter("outputData.txt");
                //new set for all hashmap entries
                Set entries = students.entrySet();
                //for-loop using iterator to check all key-value pairs in hashmap
                for (Iterator entryItr = entries.iterator(); entryItr.hasNext();) { 
                   //count increments
                   count++;
                   //new entry object to check each pair individually
                   Map.Entry entry = (Map.Entry)entryItr.next();
                   //each iteration of loop prints all student parameters
                   //line-by-line using a call to the custom method
                   //textFileString();
                   pw.println(students.get(entry.getKey()).textFileString());
                   //adds all student GPA values for averaging
                   totalGPA = totalGPA + students.get(entry.getKey()).getGPA();
                }
                
                //print total number of students and average GPA
                //at end of file
                pw.println("Total Number of Students: " + count);
                pw.println("Average GPA: " + (totalGPA / count));
                                
                //close PrintWriter object
                pw.close();
                }
                //catch block to throw IOException
                catch(IOException f){
                    JOptionPane.showMessageDialog(null,"Unable to create file.");
                }//end catch block
            }//end windowClosing method            
        }//end WindowAdapter subclass
 
        //add both event listeners to corresponding buttons
        processBtn.addActionListener(new OptionHandler());
        addWindowListener(new ExitListener());       
    }
    
    //begin main method
    public static void main(String[] args) {
        //new instance of P4GUI
        new P4GUI();
    }//end main method    
}//end class P4GUI