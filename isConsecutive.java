import java.util.*;
public class TestTempAPI {
    public static void main(String args[]) {
      
      TempAPI temp = new TempAPI();
      
      //INCREASING POS
      int length = (int)(Math.random()*25+1);
      int increasepos [] = new int [length];
      for(int x = 0; x<length; x++){
          increasepos[x] = x+1;
      }
      System.out.println(Arrays.toString(increasepos));
      int [][] output;
      //
      if(temp.isConsecutive(increasepos) != null){
          output = temp.isConsecutive(increasepos);
        System.out.println("Is NOT a Consecutive Array. Non-Consecutive Indexes are:");
        int j = 0;
        while(j<output.length){
        System.out.println(Arrays.toString(output[j]));  
        j++;
        }
      }
      else{
        System.out.println("Is a Consecutive Array");
      }

      System.out.println("");
      
      
      //DECREASING POS
      length = (int)(Math.random()*25+1);
      int decreasepos [] = new int [length];
      for(int x = 0; x<length; x++){
          decreasepos[x] = length-x;
      }
      System.out.println(Arrays.toString(decreasepos));
      //output = temp.isConsecutive(decreasepos);
      if(temp.isConsecutive(decreasepos) != null){
          output = temp.isConsecutive(decreasepos);
        System.out.println("Is NOT a Consecutive Array. Non-Consecutive Indexes are:");
        int j = 0;
        while(j<output.length){
        System.out.println(Arrays.toString(output[j]));  
        j++;
        }
      }
      else{
        System.out.println("Is a Consecutive Array");
      }
      System.out.println("");

      
      //INCREASING NEG
      length = (int)(Math.random()*25+1);
      int increaseneg [] = new int [length];
      for(int x = 0; x<length; x++){
          increaseneg[x] = -length+x;
      }
      System.out.println(Arrays.toString(increaseneg));
      //output = temp.isConsecutive(increaseneg);
      if(temp.isConsecutive(increaseneg) != null){
          output = temp.isConsecutive(increaseneg);
        System.out.println("Is NOT a Consecutive Array. Non-Consecutive Indexes are:");
        int j = 0;
        while(j<output.length){
        System.out.println(Arrays.toString(output[j]));  
        j++;
        }
      }
      else{
        System.out.println("Is a Consecutive Array");
      }
      System.out.println("");
      
      
      //DECREASING NEG
      length = (int)(Math.random()*25+1);
      int decreaseneg [] = new int [length];
      for(int x = 0; x<length; x++){
          decreaseneg[x] = -x-1;
      }
      System.out.println(Arrays.toString(decreaseneg));
     // output = temp.isConsecutive(decreaseneg);
      if(temp.isConsecutive(decreaseneg) != null){
          output = temp.isConsecutive(decreaseneg);
        System.out.println("Is NOT a Consecutive Array. Non-Consecutive Indexes are:");
        int j = 0;
        while(j<output.length){
        System.out.println(Arrays.toString(output[j]));  
        j++;
        }
      }
      else{
        System.out.println("Is a Consecutive Array");
      }
      System.out.println("");
      
      
      //START NEG END POS
      length = (int)(Math.random()*25+1);
      int negtopos [] = new int [length];
      for(int x = 0; x<length; x++){
          int length02 = (int)length/2;
          negtopos[x] = -length02+x;
      }
      System.out.println(Arrays.toString(negtopos));
     // output = temp.isConsecutive(negtopos);
      if(temp.isConsecutive(negtopos) != null){
          output = temp.isConsecutive(negtopos);
        System.out.println("Is NOT a Consecutive Array. Non-Consecutive Indexes are:");
        int j = 0;
        while(j<output.length){
        System.out.println(Arrays.toString(output[j]));  
        j++;
        }
      }
      else{
        System.out.println("Is a Consecutive Array");
      }
      System.out.println("");
      
      
      //START POS END NEG
      length = (int)(Math.random()*25+1);
      int postoneg [] = new int [length];
      for(int x = 0; x<length; x++){
          int length02 = (int)length/2;
          postoneg[x] = length02-x;
      }
      System.out.println(Arrays.toString(postoneg));
     // output = temp.isConsecutive(postoneg);
     if(temp.isConsecutive(postoneg) != null){
          output = temp.isConsecutive(postoneg);
        System.out.println("Is NOT a Consecutive Array. Non-Consecutive Indexes are:");
        int j = 0;
        while(j<output.length){
        System.out.println(Arrays.toString(output[j]));  
        j++;
        }
      }
      else{
        System.out.println("Is a Consecutive Array");
      }
      System.out.println("");
      
      
      //RANDOM
      length = (int)(Math.random()*25+1);
      int random [] = new int [length];
      for(int x = 0; x<random.length; x++){
          random[x] = (int)(Math.random()*25+1);
      }
      System.out.println(Arrays.toString(random));
     // output = temp.isConsecutive(random);
      if(temp.isConsecutive(random) != null){
          output = temp.isConsecutive(random);
        System.out.println("Is NOT a Consecutive Array. Non-Consecutive Indexes are:");
        int j = 0;
        while(j<output.length){
        System.out.println(Arrays.toString(output[j]));  
        j++;
        }
      }
      else{
        System.out.println("Is a Consecutive Array");
      }
      System.out.println("");

      
    }//end of main method
}

class TempAPI{
    public int[][] output;
    
    public int[][] isConsecutive(int[] elements){
        int check = 0;
        boolean test = false;
        for(int x = 1; x<elements.length; x++){
            if(elements[x-1]+1 == elements[x] || elements[x-1]-1 == elements[x]){
                //good
            }
            if(elements[0]+x == elements[x] || elements[0]-x == elements[x]){
                //(anything like 2,3,2,3,2,3 should not work)
            }
            else{
                check++;
                test = true;
            }
        }
        
        if(test==true){
            check= check -1;
        }
        
    if(check>0){
        output = new int [check][2];
        int j = 0;
        for(int x = 0; x<elements.length-1; x++){
            if(elements[x]+1 == elements[x+1] || elements[x]-1 == elements[x+1]){
                //good
            }
            if(elements[0]+x == elements[x] || elements[0]-x == elements[x]){
                //(anything like 2,3,2,3,2,3 should not work)
            }
            else{
                output[j][0]=x;
                output[j][1]=x+1;
                j++;
            }
            }//end of for
            return output;
        }//end of if
    else{
        return null;
    }
    
    }//end of isConsecutive
}//end of tempAPI


