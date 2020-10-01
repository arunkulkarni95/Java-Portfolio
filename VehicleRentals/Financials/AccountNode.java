// ************************************************************************
// Author: Arun Kulkarni
// Date Created: May 5, 2020
// Class Name: AccountNode
// Description: Nodes for Account object linked list
// ************************************************************************

package Financials;

public class AccountNode {
    
    //instance variables
    private Account acct;
    private AccountNode next;
    
    //constructor for node
    public AccountNode(Account acct, AccountNode next){
        this.acct = acct;
        this.next = next;
    }
    
    //returns value of node
    public Account getValue(){
        return acct;
    }
    
    //returns next node in list
    public AccountNode getNext(){
        return next;
    }
    
    //appends node to current node
    public void setNext(AccountNode next){
        this.next = next;
    } 
}