// ************************************************************************
// Author: Arun Kulkarni
// Date Created: June 23, 2020
// Class Name: HW02_RandomGenerator_Kulkarni_Arun
// Description: Randomly generates doubles and writes them to text file
// ************************************************************************

package cosc336.hw02_randomgenerator_kulkarni_arun;

import java.util.*;
import java.io.*;

public class HW02_RandomGenerator_Kulkarni_Arun {
    
    public static void main(String[] args)throws IOException{
        
        //testing procedure to generate random doubles to file
        
        //file path for my specific machine, so that the main program can
        //access the created file
        PrintStream output = new PrintStream(new File(
            "D:\\Users\\Arun\\Documents\\NetBeansProjects\\HW02_PartC_Kulkarni_Arun\\inputHW02.txt"));
        
        //new Random
        Random random = new Random();
        
        //var s for number of doubles to be generated
        int s = 3000000;
        
        //generates specified number of random doubles < 1000
        //writes line-by-line to text file
        for(int i = 0; i < s; i++){
            double randNum = random.nextInt(100000)/100.0;
            output.println(randNum);
        }
        output.close();
    }
}