// ************************************************************************
// Author: Arun Kulkarni
// Date Created: May 5, 2020
// Class Name: Transactions
// Description: Aggregation of Transaction objects using linked list
// ************************************************************************

package Financials;

public class Transactions {
    
    private TransactionNode head = null;
    
    //adds new Transaction to list
    public void add(Transaction trans){    
        if(head == null){
            head = new TransactionNode(trans,null);
        }
        else{
            TransactionNode temp = head;
            while(temp.getNext() != null){
                temp = temp.getNext();
            }
            temp.setNext(new TransactionNode(trans,null));        
        }
    }
    
    //returns head node of list
    public TransactionNode getHead(){
        return head;
    }   
}