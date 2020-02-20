import java.util.*;

public class MyClass {
    public static void main(String args[]) {
     int x = 1;
     int y = 1;
     
     BinomialTheorem bt = new BinomialTheorem();
     
     int [][] triangle = bt.pascaltngl(x,y);
     
     for(int j = 0; j<triangle.length; j++){
         System.out.println("For n = "+j+" coefficients: "+Arrays.toString(triangle[j]));
     }
     
    }
}

class BinomialTheorem{
    public int [][] pascaltngl(int x, int y){
        int exponant = 0;
        int maxexponant = 8;
        
        int [][] tngl = new int [maxexponant][];
        int col = 1;
        while (exponant < maxexponant){
            tngl[exponant] = new int [col];
            col++;
            exponant++;
        }//build triangle
        
        tngl[0][0] = 1;
        tngl[1][0] = x;
        tngl[1][1] = y;
        
        for(int c = 2; c<tngl.length; c++){
            int temp [] = new int [tngl[c].length];
            int a = 0;
            while (a<temp.length){
                temp[0]= (int)Math.pow(x,c);
                temp[temp.length-1] = (int)Math.pow(y,c);
                a++;
            }
            tngl[c] = temp;
        }
        for(int c = 2; c<tngl.length; c++){
            for (int w = 1; w<tngl[c].length; w++){
                if (tngl[c][w]==0){
                    tngl[c][w]=tngl[c-1][w]+tngl[c-1][w-1];
                }
            }
        }
        
     return tngl;   
    }
}
