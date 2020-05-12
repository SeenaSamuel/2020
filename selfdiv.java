import java.util.*;
public class MyClass {
    public static void main(String args[]) {
      int num = 26;
      
      test t = new test();
      boolean r = t.isSelfDivisor(num);
      int[] l = t.firstNumSelfDivisors(10,3);
      System.out.println(Arrays.toString(l));
    }
}

class test{
public static boolean isSelfDivisor(int num){
    int original = num;
    boolean selfdiv = false;
    while (num > 0) {
        int dig = num%10;
        if(original % dig == 0){
            selfdiv = true;
        }
        if(dig == 0){
            return false;
        }
        else{
            return false;
        }
        
        num = num / 10;
    }
    return selfdiv;
}

public static int[] firstNumSelfDivisors(int startval,int count){
    int values[] = new int[count];
    int x = 0;
    while (x<count){
        startval++;
        boolean SD = isSelfDivisor(startval);
        if(SD == true){
            values[x]= startval;
            x++;
        }
    }
    return values;
}
}//end of test
