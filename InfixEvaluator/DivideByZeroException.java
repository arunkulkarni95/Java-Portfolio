/* 
Author: Arun Kulkarni 
Date: March 29, 2019
Course: CMSC350
Description: This class contains the custom checked exception
DivideByZeroException
*/

//name of source package for java project
package p1gui;

//begin DivideByZeroException class
public class DivideByZeroException extends Exception{    
    public DivideByZeroException() {
        super();
    }
    public DivideByZeroException(String msg){
        super(msg);
    }
}//end DivideByZeroException class
