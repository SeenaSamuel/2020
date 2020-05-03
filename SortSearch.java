import java.util.*;
public class TestSortSearchUtils {
    public static void main(String args[]) {
      int test01 [] = new int[] {34,3,11,99,5,17,4,0,31,8,10,66,6};
      
      SortSearchUtils sort = new SortSearchUtils();
      
     // sort.bubbleSort(test01);
      sort.selectionSort(test01);
     // sort.insertionSort(test01);
      
    }
}

class SortSearchUtils{
    public void bubbleSort(int list[]){
        boolean sort = false;
        while (sort == false){
            int check = 0;
            for(int x = 0; x<list.length-1; x++){
                if(list[x]>list[x+1]){
                    int swap = list[x];
                    list[x]=list[x+1];
                    list[x+1]=swap;
                    check++;
                }//end of if
            }//end of for
            if (check == 0){
                sort = true;
            }//end of if
        }//end of while
        
    }//end of bubbleSort
    
    public void selectionSort(int list[]){
    System.out.println(Arrays.toString(list));
        for (int i = 0; i < list.length-1; i++){ 
            int minindex = i+1;
            int compare = list[i];
            for(int j = i+1; j<list.length; j++){
                if(list[j]<list[minindex]){
                    minindex = j;
                }
            }
            if(list[minindex]<compare){
                 int swap = list[minindex]; 
            list[minindex] = list[i]; 
            list[i] = swap;
            }
    System.out.println(Arrays.toString(list));

        }//end of for
    }//end of selectionSort
    
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
}

