// ************************************************************************
// Author: Arun Kulkarni
// Date Created: May 5, 2020
// Class Name: TransactionNode
// Description: Nodes for Transaction object linked list
// ************************************************************************

package Financials;

public class TransactionNode {
    
    //instance variables
    private Transaction trans;
    private TransactionNode next;
    
    //constructor for node
    public TransactionNode(Transaction trans, TransactionNode next){
        this.trans = trans;
        this.next = next;
    }
    
    //returns value of node
    public Transaction getValue(){
        return trans;
    }
    
    //returns next node in list
    public TransactionNode getNext(){
        return next;
    }
    
    //appends node to current node
    public void setNext(TransactionNode next){
        this.next = next;
    }   
}