import java.util.*;

public class MyClass {
    public static void main(String args[]) {
     double x = 1;
     double y = 1;
     
    BinomialTheorem bt = new BinomialTheorem();
     
    double [][] triangle = bt.pascaltngl(x,y);
    String [][] expandedtngl = bt.expand(triangle);
    
    
    for(int j = 0; j<triangle.length; j++){
         System.out.println("For n = "+j+" coefficients: "+Arrays.toString(triangle[j]));
        }
                System.out.println("");
    for(int j = 0; j<expandedtngl.length; j++){
         System.out.println("For n = "+j+" coefficients: "+Arrays.toString(expandedtngl[j]));
        }    
     
                System.out.println("");
     for (int i = 0; i < expandedtngl.length; i++) { 
         String f = " ";
        for (int k = 0; k < expandedtngl[i].length; k++) { 
            if (expandedtngl[i][k]==null){
                f = f+"   ";
            }
            if (expandedtngl[i][k]!=null)
            {
                f=f+expandedtngl[i][k];
            }
		} 
	    System.out.println(f);
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
    
public String [][] expand (double[][]tngl){
        int max = 0;
        for (int x = 0; x<tngl.length; x++){
            if (tngl.length>max){
                max = tngl.length;
            }
        }
        
        String [][] expanded = new String[tngl.length][(max*2)-1];
        int center = (int)(expanded[0].length/2);
        String temp[] = new String [expanded[0].length];
            temp[center]=String.valueOf(tngl[0][0]);
            expanded[0]=temp;
            
            
        for(int x = 1; x<expanded.length; x++){
            String temp2[] = new String [expanded[x].length];
            int d = (int)tngl[x].length/2;
            if (tngl[x].length%2==0){
                temp2[center]="0.0";
            }
            if (tngl[x].length%2!=0){
                temp2[center]=String.valueOf(tngl[x][d]);
            }
        for (int y = 0; y<tngl[x].length; y++){ 
            if(x%2!=0){
                int count = x;
                int half = (int)tngl[x].length/2;
                int t = 0;
                while (count>=1){
                    if(count%2!=0){
                        temp2[center-count]=String.valueOf(tngl[x][t]);
                        temp2[center+count]=String.valueOf(tngl[x][t]);
                        t++;
                    }
                count--;
                }
            }
            
            if(x%2==0){
                int count = x;
                int half = (int)tngl[x].length/2;
                int t = 0;
                while (count>=1){
                    if(count%2==0){
                        temp2[center-count]=String.valueOf(tngl[x][t]);
                        temp2[center+count]=String.valueOf(tngl[x][t]);
                        t++;
                    }
                count--;
                }
            }
        }
            
            expanded[x]=temp2;
        }
        
        
        for(int h = 0; h<expanded.length; h++){
            for(int t = 0; t<expanded[h].length; t++){
                if(expanded[h][t]=="0.0"){
                    expanded[h][t]=null;
                }
            }
        }
        
        
        
        
        
        return expanded;
    }
    
}
