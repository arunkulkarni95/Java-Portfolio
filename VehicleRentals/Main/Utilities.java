// ************************************************************************
// Author: Charles Dierbach
// Class Name: Utilities
// Description: Utilities for Car Rental program to get a Yes/No response,
// validate credit card numbers, and check strings for non-digit chars
// ************************************************************************
package Main;

import utilities.Exceptions.NonDigitFoundException;
import utilities.Exceptions.InvalidNumberCharsException;
import java.util.Scanner;
import utilities.Exceptions.InvalidNumberCharsException;
import utilities.Exceptions.NonDigitFoundException;
/**
 *
 * @author C. Dierbach
 */
public class Utilities {
    
    // supporting validation methods
    
    public static char getYesNoResponse(String prompt_mesg, Scanner input){
    // reprompts user until valid response entered
    // returns either 'Y' or 'N'
        
        String yesno_response;
        char YN_char = ' ';  // init
        boolean valid_entry = false;
        
        while(!valid_entry){
            System.out.print(prompt_mesg + " (y/n): ");
            yesno_response = input.next();
            YN_char = Character.toUpperCase(yesno_response.charAt(0));
            
            if(yesno_response.length() != 1 ||
               YN_char != 'Y' && YN_char != 'N')
                   System.out.println("Invalid Entry - Please reenter");
            else
               valid_entry = true;
        }
        
        return YN_char;
    }
     
    public static boolean validateCreditCard(String credit_card) 
            throws InvalidNumberCharsException, NonDigitFoundException {
    // ---------------------------------------------------------------------
        if(credit_card.length() != 16)
            throw new InvalidNumberCharsException();
        else
            if(containsNonDigit(credit_card))
                throw new NonDigitFoundException();
            
        return true;
    }

    public static boolean containsNonDigit(String str){
    // returns true if str contains only digit characters
    // otherwise, returns false
    // ---------------------------------------------------------------------
        boolean non_digit_found = false;
        int index = 0;
        
        while(index < str.length() && !non_digit_found)
        {
            if(!Character.isDigit(str.charAt(index)))
                non_digit_found = true;
            else
                index = index + 1;
        }    
        
        // no nondigits found if reach end of string
        return index < str.length();
    }
}