import java.util.*;
public class Main {
    public static void main(String args[]) {
        
        String mixRadix2DArray01[][];
        String mixRadix2DArray02[][];
        int mixRadix2DArraysum[][];
        
        int rowLwrCnt = 4;
        int rowUprCnt = 9;
        int colLwrCnt = 2;
        int colUprCnt = 10;
        int strLwrCnt = 3;
        int strUprCnt = 8;
        int rowCnt = 5;
        int convParms[] = {rowCnt,colLwrCnt,colUprCnt,strLwrCnt,strUprCnt};
        int radixVals[] = {2, 8, 10, 16};
        
        QuizNov15API na = new QuizNov15API();
        TextUtils tu = new TextUtils();
        Grid grid = new Grid();
         
        int wordcnt = 15;
        int casestyle = (int)(Math.random()*(4));
        int lwrwordsize = 4;
        int uprwordsize = 10;
        int width = 15;
        
        String empty01[][] = na.createEmpty2DArray(rowLwrCnt,rowUprCnt,colLwrCnt,colUprCnt);
        
        int rows = empty01.length;
        
        String[][][] Random3D = na.make3DRandomRadixArray(convParms,radixVals,rows);
        String[][] flatened3d = na.flattened3Darray(Random3D);
        String[][] board = na.tictactoe();
        String gd = grid.build(flatened3d);
        
        String words = tu.makeWords(wordcnt,casestyle,lwrwordsize,uprwordsize);
        String[] wrapped = tu.wrapText(words,width);
        for (int x = 0; x<wrapped.length; x++){
        System.out.println(wrapped[x]);
        }
    }
}

class TextUtils{
    public String makeWords(int cnt, int style, int lwrsize, int uprsize){
        String words = "";
        int UPPER_CASE = 1;
        int LOWER_CASE = 2;
        int MIXED_CASE = 3;
       
    for (int x = 0; x < cnt; x++){ 
        String temp = "";
        int size = (int)(Math.random()*(uprsize-lwrsize+1)+lwrsize);
        for (int j = 0; j < size; j++){
            if (style == UPPER_CASE){
                char rand = (char)(Math.random()*('Z'-'A'+1)+'A');
                temp = temp+rand;
            }
            if (style == LOWER_CASE){
                char rand = (char)(Math.random()*('z'-'a'+1)+'a');
                temp = temp+rand;
            }
            if (style == MIXED_CASE){
                int q = (int)(Math.random()*(3));
                char rand = 't';
                if (q==1){
                    rand = (char)(Math.random()*('z'-'a'+1)+'a');
                }
                if (q==2){
                    rand = (char)(Math.random()*('Z'-'A'+1)+'A');
                }
                temp = temp+rand;
            }
        }
        words = words + temp +" ";
    }
        return words;
    }
    public String[] wrapText(String words, int width){
        int length = words.length();
        int cnt = (int)(length/width);
        String [] wrapped = new String [cnt];
        String temp = "";
        String[] split = words.split(" "); 
        int k = 0;
        for (int x = 0; x<cnt; x++){
            int j = 0;
            temp = "";
            while (j<width){
                temp = temp + split[k] + " ";
                j=temp.length();
                if (j>=width){
                    j=width;
                    k=k-1;
                }
                k=k+1;
            }
            wrapped[x] = temp;
        }
        
        return wrapped;
    }
}

class Grid extends GridConfig { 
    
    public String build(String[][] data) { 
        int maxColumnLength = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j].length() > maxColumnLength) maxColumnLength = data[i][j].length();
            }
        }
        
        int maxElements = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i].length > maxElements) maxElements = data[i].length;
        }
        
        String grid = "";
        //int maxElements = maxElements();
        int cellLength = maxColumnLength + getPadding()*2;
        int totalLength = 0;
        
        //setTitle("Tic Tac Toe");
        
        for (int i = 0; i < data.length; i++) {
            
            String str = "|", curStr;
            for (int j = 0; j < maxElements; j++) {
                
                try {
                    str += centerText(data[i][j], cellLength) + "|";
                } catch (ArrayIndexOutOfBoundsException e) {
                    str += centerText(getEmpty(), cellLength) + "|";
                }
            }
            
            if (i == 0) totalLength = str.length();
            grid += str + "\n";
            grid += Util.fill(getBorder(), totalLength) + "\n";
        }
        
        String str2 = "";
        
        if (getTitle() != null) {
            str2 += Util.fill(getBorder(), totalLength) + "\n";
            str2 += "|" + "" + centerText(getTitle(), totalLength - 2) + "|" + "\n";
        }
        
        str2 += Util.fill(getBorder(), totalLength) + "\n" + grid;
        
        return str2;
    }
    
   
    private String centerText(String str, int length) { 
        int spaces = (length - str.length()) / 2;
        // if ((length-str.length()) % 2 != 0) spaces++;
        
        String text = "";
        for (int i = 0; i < spaces; i++) text += " ";
        
        text += str;
        
        if ((length-str.length()) % 2 != 0) spaces++;
        
        for (int i = 0; i < spaces; i++) text += " ";
        
        return text;
    }
    
}

class GridConfig { 
    
    private int padding = 1;
    private String title = "Grid Maker", border = "-", empty = "---";
    
    protected void setPadding(int padding) { this.padding = padding; }
    
    protected int getPadding() { return padding; }
    
    protected void setTitle(String title) { this.title = title; }
    
    protected String getTitle() { return title; }
    
    protected void setBorder(String border) { this.border = border; }
    
    protected String getBorder() { return border; }
    
    protected void setEmpty(String empty) { this.empty = empty; }
    
    protected String getEmpty() { return empty; }
}

class Util { 
    public static String fill(String data, int amount) {
        String str = "";
        
        for (int i = 0; i < amount; i++) str += data;
        
        return str;
    }
}

class QuizNov15API { 
     
      public String[][] tictactoe(){ 
          String [][] data = new String [3][3];
          for(int x = 0; x<data.length; x++){
              for(int j = 0; j<data[x].length; j++){
                  int d = (int)(Math.random()*(2));
                  if(d%2==0){
                      data[x][j]="O";
                  }
                  if(d%2==1){
                      data[x][j]="X";
                  }
              }
          }
          
          return data;
      }
     
      public int[][] sum2DArrays(String[][] one, String[][] two){  
          int[][] sum = new int[one.length][];
          int [][] sub01 = new int [one.length][];
          int [][] sub02 = new int [one.length][];

          
          for(int j=0; j<sum.length; j++){
              sum[j] = new int [one[j].length];
              sub01[j] = new int [one[j].length];
                sub02[j] = new int [one[j].length]; 

              if(two[j].length>one[j].length){
                  sum[j] = new int [two[j].length];
                  sub01[j] = new int [two[j].length];
                sub02[j] = new int [two[j].length]; 
              }//end of if
              
          }//end of for
          
          for (int s = 0; s<one.length; s++){
              for (int k = 0; k<one[s].length; k++){
                  sub01[s][k] = Integer.valueOf(one[s][k]);
              }
          }
          
          for (int s = 0; s<two.length; s++){
              for (int k = 0; k<two[s].length; k++){
                  sub02[s][k] = Integer.valueOf(two[s][k]);
              }
          }
          
          for(int j = 0; j<sum.length; j++){
              for (int k = 0; k<sum[j].length; k++){
                  sum[j][k] = (sub01[j][k] + sub02[j][k]);
              }//end of for
          }//end of for
          
          for(int j = 0; j<sum.length; j++){
              sum[j][0] = 10;
          }
          
          return sum;
      }//end of sum2DArrays
      
      public String [][] createEmpty2DArray (int lowrow, int uprrow, int lowcol, int uprcol){ 
        int row = (int)(Math.random()*(uprrow-lowrow+1)+lowrow);
        String empty[][] = new String [row][];
        int col = (int)(Math.random()*(uprcol-lowcol+1)+lowcol);
        
        int z = 0;
        while (z<row){
            empty[z] = new String [col];
            z=z+1;
        }
        
        return empty;
    }//end of createEmpty2DArray
      
      public String[][] rnd2DArray(String sub[][], int max, int min){ 
       
       String [][] rand2DArray = new String [sub.length][];
      
        int i = 0;
        int z = 0;
        String randstring = "";
        char letter;
        
        for (int k=0; k<sub.length; k++)
        {
                i=0;
            rand2DArray[k] = new String [sub[k].length];
            
            while(i<sub[k].length){
                    randstring = "";
                    z=0;
                
                int size = (int)(Math.random() * (max-min+1)+min);
                
                while (z<size){
                    letter = (char)(Math.random()*('z'-'a'+1)+'a');
                    randstring = randstring + String.valueOf(letter);
                    
                    z=z+1;
                }
                rand2DArray[k][i] = randstring;
                i=i+1;
            }
            
            i=0;
        }//end of for
        
        return rand2DArray;
    }//end of rand2DArray
      
      public String[][] makeMixedRadixArray(int[] b, int[] c, int row){
        int mincol = b[1];
        int maxcol = b[2];
        int x = 0;
        String ary[][] = new String [row][];
        while (x<ary.length){
            int size = (int)(Math.random()*(maxcol-mincol+1)+mincol);
            ary[x] = new String [size];
            x=x+1;
        }
        
        x = 0;
        int l = c.length;
        int[] ascii = new int [row];
        while (x<row){
            int n = (int)(Math.random()*(l));
            String w = String.valueOf(c[n]);
            if(n==0){
              ascii[x] = 2;  
            }
            if(n==1){
              ascii[x] = 8;  
            }
            if(n==2){
              ascii[x] = 10;  
            }
            if(n==3){
              ascii[x] = 16;  
            }
            ary[x][0] = w;
            x=x+1;
        }

        int maxlength = b[4];
        int minlength = b[3];
        int sub;
        int size;
        String holder = "";
        int count = 0;
        x=0;
        int j=1;

        for (int g = 0; g<row; g++){
            if(ascii[g]==2){
                j=1;
            while(j<ary[g].length){
                  int base2min = 0;
                  int base2max = 1;
                   
                  size = (int)(Math.random()*(maxlength-minlength+1)+minlength);
                    holder = "";
                    count = 0;
                    while (count<size){
                        sub = (int)(Math.random()*(base2max-base2min+1)+base2min);
                        holder = holder + String.valueOf(sub);
                        count = count + 1;
                    }//end of while
                   
                  ary[g][j] = holder;
                   
                  j=j+1; 
                }//end of while
                
            }//end of if 
            if(ascii[g]==8){
                j=1;
        while(j<ary[g].length){
                int base8min = 0;
                  int base8max = 7;
                   
                  size = (int)(Math.random()*(maxlength-minlength+1)+minlength);
                    holder = "";
                    count = 0;
                    while (count<size){
                        sub = (int)(Math.random()*(base8max-base8min+1)+base8min);
                        holder = holder + String.valueOf(sub);
                        count = count + 1;
                    }//end of while
                   
                  ary[g][j] = holder;
                   
                  j=j+1; 
        }//end of while
                
            }//end of if
            if(ascii[g]==10){
                j=1;
        while(j<ary[g].length){
                int base10min = 0;
                  int base10max = 9;
                   
                  size = (int)(Math.random()*(maxlength-minlength+1)+minlength);
                    holder = "";
                    count = 0;
                    while (count<size){
                        sub = (int)(Math.random()*(base10max-base10min+1)+base10min);
                        
                        holder = holder + String.valueOf(sub);
                        
                        count = count + 1;
                    }//end of while
                   
                  ary[g][j] = holder;
                   
                  j=j+1; 
        }//end of while
            }//end of if
            
            if(ascii[g]==16){
                j=1;
        while(j<ary[g].length){
                int base10min = 0;
                  int base10max = 9;
                   
                  size = (int)(Math.random()*(maxlength-minlength+1)+minlength);
                    holder = "";
                    count = 0;
                    while (count<size){
                        int choose = (int)(Math.random()*(2));
                        if(choose == 0){
                        sub = (int)(Math.random()*(base10max-base10min+1)+base10min);
                        holder = holder + String.valueOf(sub);
                        }
                        if(choose == 1){
                        char sub2 = (char)(Math.random()*('F'-'A'+1)+'A');
                        holder = holder + String.valueOf(sub2);
                        }
                        count = count + 1;
                    }//end of while
                  ary[g][j] = holder;
                   
                  j=j+1; 
        }//end of while
            }//end of if
            
        }//end of for
        
        return ary; 
     }//end of makeMixedRadixArray
      
      public String [][] array2DBase10Conv (String[][] convert){
        String[][] base10 = new String [convert.length][];
        
        for (int k = 0; k<base10.length; k++){
            base10[k] = new String [convert[k].length];
        }
        
        int sub01 [] = new int [convert.length];
         for (int c = 0; c<sub01.length; c++){
           sub01[c] = Integer.valueOf(convert[c][0]);//first col (all the ascii values used)
        }
        
        String hold;
         for (int k = 0; k<base10.length; k++){
            if (sub01[k] == 10){
                base10[k] = convert[k];
            }
            
            if (sub01[k] == 2){
                int c = 1;
                int base2 = 0;
                base10[k][0] = "10";
                while (c<convert[k].length){
                    base2 = 0;
                    for (int y = 0; y<convert[k][c].length(); y++){
                        if (convert[k][c].charAt(y) == '0')
                        {
                            base2 = base2;
                        }
                        
                        if (convert[k][c].charAt(y) == '1')
                        {
                        hold = String.valueOf(convert[k][c].charAt(y));
                        int hold2 = Integer.valueOf(hold);
                        int length = convert[k][c].length();
                        base2 = base2 + (int)(hold2*(Math.pow(2,(length-(y+1)))));
                        }
                        
                    }//end of for
                base10 [k][c] = String.valueOf(base2);
                    c=c+1;
                }//end of while
            }
            
            if (sub01[k] == 8){
                int c = 1;
                int base2 = 0;
                base10[k][0] = "10";
                while (c<convert[k].length){
                    base2 = 0;
                    for (int y = 0; y<convert[k][c].length(); y++){
                        hold = String.valueOf(convert[k][c].charAt(y));
                        int hold2 = Integer.valueOf(hold);
                        int length = convert[k][c].length();
                        base2 = base2 + (int)(hold2*(Math.pow(8,(length-(y+1)))));
                        
                    }//end of for
                base10 [k][c] = String.valueOf(base2);
                    c=c+1;
                }//end of while
            }
            
            if (sub01[k] == 16){
                int c = 1;
                int base2 = 0;
                base10[k][0] = "10";
                while (c<convert[k].length){
                    base2 = 0;
                    for (int y = 0; y<convert[k][c].length(); y++){
                       
                       if(convert[k][c].charAt(y)=='0'||convert[k][c].charAt(y)=='1'||convert[k][c].charAt(y)=='2'||convert[k][c].charAt(y)=='3'||convert[k][c].charAt(y)=='4'||convert[k][c].charAt(y)=='5'||convert[k][c].charAt(y)=='6'||convert[k][c].charAt(y)=='7'||convert[k][c].charAt(y)=='8'||convert[k][c].charAt(y)=='9')
                        {
                        hold = String.valueOf(convert[k][c].charAt(y));
                        int hold2 = Integer.valueOf(hold);
                        int length = convert[k][c].length();
                        base2 = base2 + (int)(hold2*(Math.pow(16,(length-(y+1)))));
                        }//end of if
                        if(convert[k][c].charAt(y)=='A')
                        {
                        int hold2 = 10;
                        int length = convert[k][c].length();
                        base2 = base2 + (int)(hold2*(Math.pow(16,(length-(y+1)))));
                        }
                        if(convert[k][c].charAt(y)=='B')
                        {
                        int hold2 = 11;
                        int length = convert[k][c].length();
                        base2 = base2 + (int)(hold2*(Math.pow(16,(length-(y+1)))));
                        }
                        if(convert[k][c].charAt(y)=='C')
                        {
                        int hold2 = 12;
                        int length = convert[k][c].length();
                        base2 = base2 + (int)(hold2*(Math.pow(16,(length-(y+1)))));
                        }
                        if(convert[k][c].charAt(y)=='D')
                        {
                        int hold2 = 13;
                        int length = convert[k][c].length();
                        base2 = base2 + (int)(hold2*(Math.pow(16,(length-(y+1)))));
                        }
                        if(convert[k][c].charAt(y)=='E')
                        {
                        int hold2 = 14;
                        int length = convert[k][c].length();
                        base2 = base2 + (int)(hold2*(Math.pow(16,(length-(y+1)))));
                        }
                        if(convert[k][c].charAt(y)=='F')
                        {
                        int hold2 = 15;
                        int length = convert[k][c].length();
                        base2 = base2 + (int)(hold2*(Math.pow(16,(length-(y+1)))));
                        }
                    }//end of for
                base10 [k][c] = String.valueOf(base2);
                    c=c+1;
                }//end of while
            }
        }//end of for
        
        return base10;
    }//end array2DBase10Conv
      
      public String [][] ascii2DConv (String [][] x){
        String sub[][] = new String [x.length][];
        
        for (int k = 0; k<sub.length; k++){
            sub[k] = new String [x[k].length];
        }
        
        for (int i = 0; i<sub.length; i++){
            for (int k = 0; k<sub[i].length; k++){
            String input = x[i][k];
            
            for (int j = 65; j < 91; j++) {
            input = input.replace(String.valueOf(j),String.valueOf((char)j));
            }
            
            for (int j = 97; j < 123; j++) {
            input = input.replace(String.valueOf(j),String.valueOf((char)j));
            }
             sub[i][k]=input;
            }
        } 
        
        return sub;
    }//end ascii2DConv
      
      public int[] colAvgs(String [][]m){
   int base[][] = new int [m.length][];
   
   for (int i = 0; i<base.length; i++){
       for (int j = 0; j<m[i].length; j++){
           base[i] = new int [m[i].length];
       }
       for (int j = 0; j<m[i].length; j++){
           base[i][j] = Integer.valueOf(m[i][j]);
       }
   }
   
   
   int i = 0;
    int g = 0;
    while (i<base.length){
       if(base[i].length>g)
       g=base[i].length;
       i=i+1;
   }//end of while
    i=0;
    int sub[][] = base;
    int j = 0;
    int sub2[][] = new int [base.length][g];
    int k = 0;
    
    while (j<m.length){
        while (k<base[j].length)
        {
            sub2[j][k] = base[j][k];
            k=k+1;
        }//end of while
        j=j+1;
        k=0;
    }// end of while
    
    int sum = 0;       
    int y = 0;
    i = 0;
    int average[] = new int [sub2[0].length];
    for (int f = 0; f<sub2.length; f++)
   {
    int row = 0;
    int row2 = sub2.length;
    int col = 0;
    int col2 = sub2[0].length;
    int zeros = 0;
    
    while (col < col2){
        while (row < row2){
            sum = sum + sub2[row][col];
            if (sub2[row][col] == 0)
            {
                zeros = zeros +1;
            }//end of if
            row = row+1;
        }//end of while
        
        average[col] = (sum/(row-zeros));
        zeros = 0;
        row = 0;
        sum = 0;
        col = col+1;
    }//enf of while
   }//end of for

  return average;   
 }//end colAvgs  
      
      public String[][][] make3DRandomRadixArray(int[] b, int[] c, int row){
          /*b[] is --> convParms[] = {rowCnt,colLwrCnt,colUprCnt,strLwrCnt,strUprCnt};
            c[] is --> radixVals[] = {2, 8, 10, 16}
            row is --> empty01.length
          
          WHAT I NEED!!!
              
              - length (number of columns per row)
              - number for rows
              - depth (think of cube)
              
              rows is the first dimension
              length is the second dimension
              depth is the third dimension
                - depth will be 2 since it will hold the radix and number of that radix
              
              each element is number of a certian radix
                - the radix is the 3rd dimension
                
              ****IMPORTANT: should be able to implement for both Jagged and Rectangular****
              */
          
          String[][][] random3d = new String [row][][];//assign number of rows
          int lowcol = b[1];
          int maxcol = b[2];
          int col;
          for (int i = 0; i<random3d.length; i++){//assign number of columns to each row
              col = (int)(Math.random()* (10 - 2 + 1) + 2);
              random3d[i] = new String [col][];
              
              for(int j = 0; j<random3d[i].length; j++){//assign depth
                  int depth = 2;
                  random3d[i][j] = new String [depth];
              }//end of for
              
          }//end of for
          
          int rows = 1;
          int convParms[] = {1,2,2,b[3],b[4]};
          int radixVals[] = {2, 8, 10, 16};

          for (int i = 0; i<random3d.length; i++){
              for(int j = 0; j<random3d[i].length; j++){
                 String sub[][] = makeMixedRadixArray(convParms,radixVals,rows);
                 random3d[i][j][0] = sub[0][0];
                 //System.out.println(random3d[i][j][0]);
                 
                 for(int d = 1; d<2; d++){
                        random3d[i][j][d] = sub[0][1];
                        //System.out.println(random3d[i][j][d]);
                 }//end of for
                 
              }//end of for
          }//end of for
          
          return random3d;
      }//end make3DRandomRadixArray
      
      public String[][] flattened3Darray (String[][][] random3d){
          //OBJECTIVE: turn the 3D String into a 2D String
          
          String flat3d[][] = new String [random3d.length][];
          
            for (int i = 0; i<flat3d.length; i++){//assign number of columns to each row
              flat3d[i] = new String [random3d[i].length];
          }//end of for
          
          for (int i = 0; i<flat3d.length; i++){
              for (int j = 0; j<flat3d[i].length; j++){
                  String temp = ""+random3d[i][j][1]+" {"+random3d[i][j][0]+"}";
                  flat3d[i][j] = temp;
              }
          }
             
          return flat3d;
      }//end flattened3Darray
      
}//end of QuizNov15API
