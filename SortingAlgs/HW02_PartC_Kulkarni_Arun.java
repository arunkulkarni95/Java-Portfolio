// ************************************************************************
// Author: Arun Kulkarni
// Date Created: June 23, 2020
// Class Name: HW02_PartC_Kulkarni_Arun
/* Description: 
    1. Opens the given file name and reads all double numbers. For simplicity, we assume this file only contains
    numbers and nothing else.
    2. Implements the function INSERTION-SORT() that only sort an array of maximum 25 numbers. The idea is that
    INSERTION-SORT() will be used as a sub-procedure to sort any sub-array when its size is small enough.
    3. Four versions of MERGE-SORT() namely
        a. MERGE-SORT-A(): Using recursive calls and NO INSERTION-SORT() as a sub-procedure
        b. MERGE-SORT-B(): Using ITERATIVE loops (i.e, NO recursion) and NO INSERTION-SORT() as a subprocedure.
        c. MERGE-SORT-C(): Using recursive calls and INSERTION-SORT() as a sub-procedure.
        d. MERGE-SORT-D(): Using ITERATIVE loops (i.e, NO recursion) and INSERTION-SORT() as a subprocedure.
    4. For testing purpose, write another procedure to randomly generate N numbers and write them to a given file
    name filename where N and filename are input parameters.
*/
// ************************************************************************

package cosc336.hw02_partc_kulkarni_arun;

import java.io.*;
import java.util.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class HW02_PartC_Kulkarni_Arun {
    
    public static void main(String[] args)throws IOException{
        
        //declare time variables
        LocalTime startTime;
        LocalTime stopTime;
        
        //s to hold array size 
        int s = 3000000;
        
        //array initialized along with four copies
        double[] nums = new double[s];
        double[] numsA = new double[nums.length];
        double[] numsB = new double[nums.length];
        double[] numsC = new double[nums.length];
        double[] numsD = new double[nums.length];
        
        //doubles read into array, assuming 1 double per line of text file
        BufferedReader reader = new BufferedReader(new FileReader("inputHW02.txt"));
        String text = null;
        int n = 0;
        while((text = reader.readLine()) != null){
            nums[n] = Double.parseDouble(text);
            n++;
        }
        
        //nums copied to numsA, numsB, numsC, numsD
        System.arraycopy(nums, 0, numsA, 0, nums.length);
        System.arraycopy(nums, 0, numsB, 0, nums.length);
        System.arraycopy(nums, 0, numsC, 0, nums.length);
        System.arraycopy(nums, 0, numsD, 0, nums.length);
        
        //for each sorting implementation, starting and stopping time is
        //recorded and printed
        startTime = LocalTime.now();
        mergeSortA(numsA);
        stopTime = LocalTime.now();
        System.out.print("MergeSortA Time: ");
        System.out.print(ChronoUnit.MILLIS.between(startTime, stopTime));
        System.out.print(" milliseconds\n");
        
        startTime = LocalTime.now();
        mergeSortB(numsB);
        stopTime = LocalTime.now();
        System.out.print("MergeSortB Time: ");
        System.out.print(ChronoUnit.MILLIS.between(startTime, stopTime));
        System.out.print(" milliseconds\n");
        
        startTime = LocalTime.now();
        mergeSortC(numsC);
        stopTime = LocalTime.now();
        System.out.print("MergeSortC Time: ");
        System.out.print(ChronoUnit.MILLIS.between(startTime, stopTime));
        System.out.print(" milliseconds\n");
        
        startTime = LocalTime.now();
        mergeSortD(numsD);
        stopTime = LocalTime.now();
        System.out.print("MergeSortD Time: ");
        System.out.print(ChronoUnit.MILLIS.between(startTime, stopTime));
        System.out.print(" milliseconds\n");
        
    }
    
    //Implementation of MergeSortA using recursion with no sub-procedure
    
    public static double[] mergeSortA(double[] nums){
        
        //if split array is less than or equal to size 1, it is returned
        if(nums.length <=1){
            return nums;
        }
        
        //split array in half
        double[] first = new double[nums.length / 2];
        double[] second = new double[nums.length - first.length];
        //copy data into corresponding halves
        System.arraycopy(nums, 0, first, 0, first.length);
        System.arraycopy(nums, first.length, second, 0, second.length);
        
        //recursive calls to split the halves in half
        mergeSortA(first);
        mergeSortA(second);
        
        //call to mergeA to merge split arrays into sorted array
        mergeA(first,second,nums);
        return nums;
    }
    
    public static void mergeA(double[] first, double[] second, double[] nums){
        
        int firstIndex = 0;
        int secondIndex = 0;
        int mergedIndex = 0;
        
        //sort remaining data in split arrays, copy into final sorted array
        while(firstIndex < first.length && secondIndex < second.length){
            if(first[firstIndex] < second[secondIndex]){
                nums[mergedIndex] = first[firstIndex];
                firstIndex++;
            }
            else{
                nums[mergedIndex] = second[secondIndex];
                secondIndex++;
            }
            mergedIndex++;
        }
        
        //copy remaining data into sorted array
        System.arraycopy(first, firstIndex, nums, mergedIndex, first.length - firstIndex);
        System.arraycopy(second, secondIndex, nums, mergedIndex, second.length - secondIndex);       
    }
      
    //Implementation of MergeSortB using iteration with no sub-procedure
    
    public static double[] mergeSortB(double[] nums){
        
        //if split array is less than or equal to size 1, it is returned
        if(nums.length <=1){
            return nums;
        }
        
        //vars for size and starting index of array
        int size;
        int start;
        
        //bottom-up sort of array, starting with sub-arrays of size 1
        //sub-arrays passed to merge method, where they are sorted and merged
        for(size = 1; size <= nums.length - 1; size = 2*size){    
            for(start = 0; start < nums.length - 1; start += 2*size){
                int middle = Math.min(start + size - 1, nums.length -1);
                int end = Math.min(start + 2*size - 1, nums.length - 1);
                mergeB(nums, start, middle, end);
            }
        }
        return nums;
    }
    
    public static void mergeB(double[] nums, int start, int middle, int end){
        
        //vars for size of sub-arrays
        int n1 = middle - start + 1;
        int n2 = end - middle;
        
        //Left and Right sub-arrays
        double Left[] = new double[n1];
        double Right[] = new double[n2];
        
        //Left and Right sub-arrays populated
        for(int i = 0; i < n1; i++){
            Left[i] = nums[start + i];
        }
        for(int j = 0; j < n2; j++){
            Right[j] = nums[middle + 1 + j];
        }
        
        //indeces of sub-arrays and merged array
        int leftIndex = 0;
        int rightIndex = 0;
        int mergedIndex = start;
        
        //data sorted by comparison in sub-arrays, copied into final array
        while(leftIndex < n1 && rightIndex < n2){
            if(Left[leftIndex] <= Right[rightIndex]){
                nums[mergedIndex] = Left[leftIndex];
                leftIndex++;
            }
            else{
                nums[mergedIndex] = Right[rightIndex];
                rightIndex++;
            }
            mergedIndex++;
        }
        
        //remaining data copied into final array
        while(leftIndex < n1){
            nums[mergedIndex] = Left[leftIndex];
            leftIndex++;
            mergedIndex++;
        }
        
        while(rightIndex < n2){
            nums[mergedIndex] = Right[rightIndex];
            rightIndex++;
            mergedIndex++;
        } 
    }
    
    //Implementation of MergeSortC using recursion with insertion sort sub-procedure
    
    public static void mergeSortC(double[] nums){
        
        //if subarray less than or equal to 25 length, insertion sort method
        //called as sub-procedure
        if(nums.length <=25){
            insertionSort(nums);
        }
        
        //rest is identical to mergeSortA
        else{
            double[] first = new double[nums.length / 2];
            double[] second = new double[nums.length - first.length];
            System.arraycopy(nums, 0, first, 0, first.length);
            System.arraycopy(nums, first.length, second, 0, second.length);

            mergeSortC(first);
            mergeSortC(second);

            mergeC(first,second,nums);
        }
    }
    
    public static void mergeC(double[] first, double[] second, double[] nums){
        
        //identical to mergeA
        int firstIndex = 0;
        int secondIndex = 0;
        int mergedIndex = 0;

        while(firstIndex < first.length && secondIndex < second.length){
            if(first[firstIndex] < second[secondIndex]){
            nums[mergedIndex] = first[firstIndex];
            firstIndex++;
            }
            else{
                nums[mergedIndex] = second[secondIndex];
                secondIndex++;
            }
            mergedIndex++;
        }

        System.arraycopy(first, firstIndex, nums, mergedIndex, first.length - firstIndex);
        System.arraycopy(second, secondIndex, nums, mergedIndex, second.length - secondIndex);

    }
    
    public static void insertionSort(double[] nums){
        
        //insertion sort sub-procedure
        
        //array sorted 1 index at a time, smallest value moved to front
        int n = nums.length;
        for(int i = 1; i < n; ++i){
            double key = nums[i];
            int j = i - 1;
            
            while(j >= 0 && nums[j] > key){
                nums[j + 1] = nums[j];
                j = j - 1;
            }
            nums[j + 1] = key;
        }   
    }
    
    //Implementation of MergeSortD using iteration with insertion sort sub-procedure
    
    public static double[] mergeSortD(double[] nums){
        
        //identical to mergeSortB
        if(nums.length <=1){
            return nums;
        }

        int size;
        int start;

        for(size = 1; size <= nums.length - 1; size = 2*size){    
            for(start = 0; start < nums.length - 1; start += 2*size){
                int middle = Math.min(start + size - 1, nums.length -1);
                int end = Math.min(start + 2*size - 1, nums.length - 1);
                mergeB(nums, start, middle, end);
            }
        }
        return nums;
    }
    
    public static void mergeD(double[] nums, int start, int middle, int end){
        
        //identical to mergeB
        int n1 = middle - start + 1;
        int n2 = end - middle;
        
        double Left[] = new double[n1];
        double Right[] = new double[n2];
        
        for(int i = 0; i < n1; i++){
            Left[i] = nums[start + i];
        }
        for(int j = 0; j < n2; j++){
            Right[j] = nums[middle + 1 + j];
        }
        
        //if Right/Left sub-arrays less than or equal to length 25
        //insertion sort called as sub-procedure
        if(Right.length <= 25){
            insertionSort(Right);
        }
        if(Left.length <= 25){
            insertionSort(Left);
        }
        
        //rest is identical to mergeB
        int leftIndex = 0;
        int rightIndex = 0;
        int mergedIndex = start;
        
        while(leftIndex < n1 && rightIndex < n2){
            if(Left[leftIndex] <= Right[rightIndex]){
                nums[mergedIndex] = Left[leftIndex];
                leftIndex++;
            }
            else{
                nums[mergedIndex] = Right[rightIndex];
                rightIndex++;
            }
            mergedIndex++;
        }
        
        while(leftIndex < n1){
            nums[mergedIndex] = Left[leftIndex];
            leftIndex++;
            mergedIndex++;
        }
        
        while(rightIndex < n2){
            nums[mergedIndex] = Right[rightIndex];
            rightIndex++;
            mergedIndex++;
        } 
    }
}