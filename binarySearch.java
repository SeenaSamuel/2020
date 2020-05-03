//sheena samuel
//period 1
//April 13th Spec

import java.util.*;
public class TestSortSearchUtils {
    public static void main(String args[]) {
      //***LIST 1***
      int size = (int)(Math.random()*(20)+1);
      int list01 [] = new int[size];
      for(int x = 0; x<size; x++){
        int num = (int)(Math.random()*(50)+1);
        list01[x] = num;
      }//filling up list01 with random numbers
      
      System.out.println("First integer array: "+Arrays.toString(list01));
      
      //***LIST 2***
      size = (int)(Math.random()*(20)+1);
      int list02 [] = new int[size];
      for(int x = 0; x<size; x++){
        int num = (int)(Math.random()*(50)+1);
        list02[x] = num;
      }//filling up list02 with random numbers
      
        System.out.println("Second integer array: "+Arrays.toString(list02));

      
      SortSearchUtils sort = new SortSearchUtils();
      
      //**SORTING FIRST ARRAY**
      sort.insertionSort(list01);
     
       System.out.println("\nSORTED FIRST ARRAY: "+Arrays.toString(list01)+"\n");
       
      for(int x = 0; x<list02.length; x++){
      int index = sort.binarySearch(list01,list02[x]);
      if (index == -1){
          System.out.println("There is no "+list02[x]+" in the list");
      }
      else
      {
          System.out.println("The value "+list02[x]+ " is found in the list at index "+index+" of the first array (sorted)");
      }
      }//end of for
      
    }
}

class SortSearchUtils{
    public void insertionSort(int list[]){
        for (int i = 1; i < list.length; ++i) { 
            int swap = list[i]; 
            int j = i - 1; 
            while (j >= 0 && list[j] > swap) { 
                list[j + 1] = list[j]; 
                j = j - 1; 
                list[j + 1] = swap;
            } 
        }
    }//end of insertionSort
    
    public int binarySearch(int sort[], int value){
        //Start at middle
            //if value isn't at middle then
                //if middleval < value go to the right of array 
                    //- find new middle 
                    //- repeat previous steps
                //if middleval > value go to the left of array
                    //- find new middle 
                    //- repeat previous steps
                    
        int end = sort.length-1;
        int start = 0;
        int middleval = 0;
        while (end >= start){
            middleval = (int)(start+end)/2;
                if(sort[middleval]>value){
                    end = middleval -1;
                }
                else if(sort[middleval]<value){
                    start = middleval +1;
                }
                else{
                    return middleval;
                }
        }
        
        return -1;
    }//end of binarySearch
}
