//string.format
//place different structures into a single data structure

import java.util.*;

public class MyClass {
    public static void main(String args[]) {
     double x = 1;
     double y = 1;
     
    BinomialTheorem bt = new BinomialTheorem();
     
    double [][] triangle = bt.pascaltngl(x,y);
    double [][] expandedtngl = bt.expand(triangle);
    
    
    for(int j = 0; j<triangle.length; j++){
         System.out.println("For n = "+j+" coefficients: "+Arrays.toString(triangle[j]));
        }
System.out.println("");
    for(int j = 0; j<expandedtngl.length; j++){
         System.out.println("For n = "+j+" coefficients: "+Arrays.toString(expandedtngl[j]));
        }    
     
    }
}

class BinomialTheorem{
    public double [][] pascaltngl (double x, double y){
        int exponant = 0;
        int maxexponant = 8;
        
        double [][] tngl = new double [maxexponant][];
        int col = 1;
        while (exponant < maxexponant){
            tngl[exponant] = new double [col];
            col++;
            exponant++;
        }//build triangle
        
        tngl[0][0] = 1;
        tngl[1][0] = x;
        tngl[1][1] = y;
        
        for(int c = 2; c<tngl.length; c++){
            double temp [] = new double [tngl[c].length];
            int a = 0;
            while (a<temp.length){
                temp[0]= Math.pow(x,c);
                temp[temp.length-1] = Math.pow(y,c);
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
    public double [][] expand (double[][]tngl){
        int max = 0;
        for (int x = 0; x<tngl.length; x++){
            if (tngl.length>max){
                max = tngl.length;
            }
        }
        
    double[][] expanded = new double[tngl.length][max];
        
        for(int x = 0; x<expanded.length; x++){
            for(int y = 0; y<expanded[x].length; y++){
                double [] temp = [expanded[x].length];
                
            }
        }
        
        return expanded;
    }
}
