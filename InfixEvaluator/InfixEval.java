/* 
Author: Arun Kulkarni 
Date: March 29, 2019
Course: CMSC350
Description: This class contains the algorithm to perform the infix
expression evaluation
*/

//name of source package for java project
package p1gui;

//java classes imported for use
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

//begin InfixEval class
public class InfixEval{
    
    //two stacks declared, one integer stack for the operands
    //and one character stack for the operators
    private static Stack<Integer> operandStack = new Stack<>();
    private static Stack<Character> operatorStack = new Stack<>();
    
    //begin eval method containing stack organization logic
    static int eval(String inputString) throws DivideByZeroException,IllegalTokenException{
        
        //new string tokenizer to split string into tokens using the specified
        //symbols and whitespaces
        StringTokenizer tokens =  new StringTokenizer(inputString," ()*/+-",true);
        
        //loop iterates as long as there are more tokens
        while(tokens.hasMoreTokens()){
            
            //tkn string to represent the next token
            String tkn = tokens.nextToken();
            //method called to check for illegal tokens, custom checked exception
            //thrown if illegal tokens found
            checkToken(tkn);
            
            //if the next token is a number it is pushed onto the operand stack,
            //else if it is a left parenthesis it is pushed onto the operator stack
            if(tkn.matches("[0-9]+")){
                operandStack.push(Integer.parseInt(tkn));
            }
            else if(tkn.equals("(")){
                operatorStack.push(tkn.charAt(0));
            }
            //if the token is a right parenthesis
            else if(tkn.equals(")")){
                //while the top of the operator stack is not a left parenthesis
                while(!operatorStack.peek().equals('(')){
                    //arithmetic method called, result pushed to top of
                    //operand stack
                    operandStack.push(arithmetic());
                }
                //pops unnecessary leftover left parenthesis
                operatorStack.pop();                
            }
            //if the token is an arithmetic operator
            else if(tkn.equals("/")
                    || tkn.equals("*")
                    || tkn.equals("+")
                    || tkn.equals("-")){
                //if the operator stack is not empty, and if the top of the 
                //operator stack is not a left parenthesis
                if(!operatorStack.isEmpty() && !operatorStack.peek().equals('(')){
                    
                    //checkPrecedence method called to check operator precedence
                    //by comparing the current operator to the operator
                    //at the top of the operator stack
                    if(checkPrecedence(operatorStack.peek(),tkn.charAt(0))){
                        //if the current operator is of higher precedence
                        //arithmetic method called and result pushed to top 
                        //of operand stack
                        operandStack.push(arithmetic());                        
                    }
                }
                //if token is a left parenthesis it is pushed to the top of
                //the operator stack
                operatorStack.push(tkn.charAt(0));
            }            
        }//end hasmoretokens loop
        //whiile operator stack is not empty
        while(!operatorStack.isEmpty()){
            //arithmetic method called and result pushed to top of operand stack
            operandStack.push(arithmetic());
        }
        //return top value of operand stack, which is the final result
        return(operandStack.peek());        
    }//end eval() method
    
    //begin arithmetic method to perform individual operations
    static int arithmetic()throws DivideByZeroException{
        //local variables to represent two popped operands and one popped
        //operator - note value2 is popped first to account for division
        //and subtraction not being commutative
        int value2 = operandStack.pop();
        int value1 = operandStack.pop();
        char oprtr = operatorStack.pop();
        //local variable result initialized to 0
        int result = 0;
        
        //if-else chain to determine operation based on current operator
        if(oprtr == '/'){
            //if value2 is 0, DivideByZeroException thrown
            if(value2 == 0){
                throw new DivideByZeroException();
            }
            result = value1 / value2;
        }
        else if(oprtr == '*'){
            result = value1 * value2;
        }
        else if(oprtr == '+'){
            result = value1 + value2;
        }
        else if(oprtr == '-'){
            result = value1 - value2;
        }
        //result returned
        return result;        
    }//end arithmetic() method
    
    //begin checkPrecedence() method to check operator precedence
    static boolean checkPrecedence(Character operator1, Character operator2) {
        
        //new ArrayList to store operators in order of precedence
        List<Character> precedenceList = new ArrayList<>();
        precedenceList.add('(');
        precedenceList.add(')');
        precedenceList.add('/');
        precedenceList.add('*');
        precedenceList.add('+');
        precedenceList.add('-');

        //if left parenthesis, value returned is false
        if(operator2 == '(' ){
            return false;
        }
        //the rest of the operator indices are compared to represent precedence
        if(precedenceList.indexOf(operator2) > precedenceList.indexOf(operator1)) {
            return true;
        }
        //if the precedence of the current operator is not higher than the operator
        //at the top of the operator stack, the returned value is false
        else{
            return false;
        }
    }//end checkPrecedence() method
    
    //begin checkToken() method to check for illegal tokens
    static void checkToken(String tkn)throws IllegalTokenException{
        
        //if the token does not match any of the accepted tokens
        //then IllegalTokenException thrown
        if(!tkn.matches("[ 0-9]+") && !tkn.equals("(")
                && !tkn.equals(")") && !tkn.equals("/")
                && !tkn.equals("*") && !tkn.equals("+")
                && !tkn.equals("-")){
            throw new IllegalTokenException();
        }
    }//end checkToken() method
}//end InfixEval class