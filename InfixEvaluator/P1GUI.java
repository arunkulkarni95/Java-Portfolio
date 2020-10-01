/* 
Author: Arun Kulkarni 
Date: March 29, 2019
Course: CMSC350
Description: This class creates a custom Java Swing GUI to evaluate 
unsigned integer infix expressions
*/

//name of source package for java project
package p1gui;

//java classes imported for use
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

//begin P1GUI class to construct swing GUI
public class P1GUI extends JFrame{

    //GUI constructor
    private P1GUI(){
        
        //size and shape of GUI window
        super("Rectangle");
        setTitle("Infix Expression Evaluator");
        setSize(400,150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //create text fields and set output field to uneditable
        JTextField inputText = new JTextField("",15);
        JTextField outputText = new JTextField("",15);
        outputText.setEditable(false);
        
        //create buttons
        JButton evalBtn = new JButton("Evaluate");
        JButton clearBtn = new JButton("Clear");
        
        //create labels
        JLabel inputLabel = new JLabel("Enter Infix Expression");
        JLabel outputLabel = new JLabel("Result");
        
        //create layout panels for GUI
        setLayout(new BorderLayout());
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        //add all elements to the proper panels
        northPanel.add(inputLabel);
        northPanel.add(inputText);
        centerPanel.add(evalBtn);
        centerPanel.add(clearBtn);
        southPanel.add(outputLabel);
        southPanel.add(outputText);
        
        //add all panels to the main layout
        add(northPanel,BorderLayout.NORTH);
        add(centerPanel,BorderLayout.CENTER);
        add(southPanel,BorderLayout.SOUTH);
        
        //set GUI window to visible
        setVisible(true);
        
        //action listener class EvalListener for Evaluate button
        class EvalListener implements ActionListener{
            
            //ActionEvent method
            public void actionPerformed(ActionEvent e){
                //try-catch statement
                try{
                    //calls method to get input string, which in turn calls
                    //all algorithm methods in InfixEval class
                    //and sets value of output text field to final result
                    outputText.setText(String.valueOf(InfixEval.eval(inputText.getText())));                    
                }
                //catch blocks for custom DivideByZeroException, IllegalTokenException,
                //and EmptyStackException (if no expression entered)
                catch(DivideByZeroException z){
                    JOptionPane.showMessageDialog(null,"Cannot divide by zero.");
                }
                catch(IllegalTokenException i){
                    JOptionPane.showMessageDialog(null,"Illegal character detected.");
                }
            }            
        }//end EvalListener class
        
        //action listener class  ClearListener for Clear button
        class ClearListener implements ActionListener{
            
            //ActionEvent method
            public void actionPerformed(ActionEvent e){
                //clears all text fields
                inputText.setText("");
                outputText.setText("");                
            }
        }//end ClearListener class
        
        //add action listeners to corresponding buttons
        evalBtn.addActionListener(new EvalListener());
        clearBtn.addActionListener(new ClearListener());
    }//end P1GUI constructor
    
    //begin main method
    public static void main(String[] args) {
        //new instance of P1GUI()
        new P1GUI();
    }//end main method
}//end P1GUI class