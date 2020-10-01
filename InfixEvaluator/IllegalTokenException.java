/* 
Author: Arun Kulkarni 
Date: March 29, 2019
Course: CMSC350
Description: This class contains the custom checked exception
IllegalTokenException
*/

//name of source package for java project
package p1gui;

//begin DivideByZeroException class
public class IllegalTokenException extends Exception{    
    public IllegalTokenException() {
        super();
    }
    public IllegalTokenException(String msg){
        super(msg);
    }
}//end IllegalTokenException class