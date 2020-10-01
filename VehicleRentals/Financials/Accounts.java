// ************************************************************************
// Author: Arun Kulkarni
// Date Created: May 5, 2020
// Class Name: Accounts
// Description: Aggregation of Account objects using linked list
// ************************************************************************

package Financials;

import static Main.Utilities.containsNonDigit;
import utilities.Exceptions.InvalidNumberCharsException;
import utilities.Exceptions.NonDigitFoundException;
import Financials.exceptions.AccountNotFoundException;

public class Accounts {
    
    private AccountNode head = null;
    
    //adds new Account to list
    public void add(Account acct){    
        if(head == null){
            head = new AccountNode(acct,null);
        }
        else{
            AccountNode temp = head;
            while(temp.getNext() != null){
                temp = temp.getNext();
            }
            temp.setNext(new AccountNode(acct,null));        
        }
    }
    
    //retrieves Account from list by credit card number
    public Account getAccount(String cardNum){
        
        if(cardNum.length() != 16){
            throw new InvalidNumberCharsException();
        }
        else if(containsNonDigit(cardNum)){
            throw new NonDigitFoundException();
        }
        AccountNode temp = null;
        if(head.getValue().getCreditCardNum().equals(cardNum)){
            temp = head;
        }
        else{
            try{
            AccountNode leadingNode = head.getNext();
            AccountNode trailingNode = head;
            while(!leadingNode.getValue().getCreditCardNum().equals(cardNum)){
                trailingNode = leadingNode;
                leadingNode = leadingNode.getNext();
            }
           // if(!leadingNode.getValue().getCreditCardNum().equals(cardNum) 
               //     && leadingNode.getNext() == null){
              //  throw new AccountNotFoundException();
           // }
            temp = leadingNode;
            }
            catch(NullPointerException e){
                throw new AccountNotFoundException();
            }
        }
        return temp.getValue();
    }
    
    //returns head of list
    public AccountNode getHead(){
        return head;
    }
}