import java.util.*;
import java.lang.*;
class GridMakerConfig {
    
    private char rowBorderChar = '*'; 
    private String colBorderChar = "|";//new String(new int[] {0xD83E, 0xDD8D}, 0, 2);
    private char emptyCellChar = '-';
    private String colLabelText = "";
    private String rowLabelText = "";
    private int cellPaddingSize  = 2;   // the number of spaces before and after the cell text. Like a  lawn around a house
    private int[] rowBorderType = {};
    private int[] dataRowType = {};
    private int[][] titleRowType = {};
    
    // RowBorderChar
public void setRowBorderChar(char charVal) {
        rowBorderChar = charVal;
    } //end method setRowBorderChar
   
    public char getRowBorderChar() {
        return rowBorderChar;
    } //end method getRowBorderChar
   
    public void setColBorderChar(String charVal) {
        colBorderChar = charVal;
    } //end method setColBorderChar
   
    public String getColBorderChar() {
        return colBorderChar;
    } //end method getColBorderChar
   
    public void setEmptyCellChar(char charVal) {
        emptyCellChar = charVal;
    } //end method setEmptyCellChar
   
    public char getEmptyCellChar() {
        return emptyCellChar;
    } //end method getEmptyCellChar
   
    public void setColLabelText(String stringVal) {
        colLabelText = stringVal;
    } //end method setColLabelText
   
    public String getColLabelText() {
        return colLabelText;
    } //end method getColLabelText
   
    public void setRowLabelText(String stringVal) {
        rowLabelText = stringVal;
    } //end method setRowLabelText
   
    public String getRowLabelText(){
        return rowLabelText;
    } //end method getRowLabelText
   
    public void setCellPaddingSize(int intVal) {
        cellPaddingSize = intVal;
    } //end method setCellPaddingSize
   
    public int getCellPaddingSize(){
        return cellPaddingSize;
    } //end method getCellPaddingSize
   
    public void setRowBorderType(int[] intVals) {
        rowBorderType = intVals;
    } //end method setRowBorderType
   
    public int[] getRowBorderType() {
        return rowBorderType;
    } //end method getRowBorderType
    public void setDataRowType(int[] intVals) {
        dataRowType = intVals;    
    } //end method setDataRowType
   
    public int[] getDataRowType() {
        return dataRowType;
    } //end method getDataRowType
    public void setDataTitleRowType(int[][] intVals) {
        titleRowType = intVals;
    } //end method setDataTitleRowType
   
    public int[][] getDataTitleRowType() {
        return titleRowType;
    } //end method getDataTitleRowType
    
    
    
     public static final int SOLID_ROW_BORDER = 1; // // |  bla  |  blaa  |  baala  |
     public static final int COL_ROW_BORDER = 2;//      |*******|******|******|


    // DATA ROW 
    public static final int DATA_COL_ROW_CLOSED = 1 ;      //- a data row, includes column separators, endpoints are given the colBorderChar. 
    public static final int DATA_COL_ROW_OPEN  = 2 ;      //- a data row, includes column separators, endpoints are spaces.
    public static final int DATA_ONLY_ROW_CLOSED  =  3 ;  //- a data row, excludes column separators, endpoints are given the colBorderChar. 
    public static final int DATA_ONLY_ROW_OPEN =  4 ;     //- a data row, excludes column separators, endpoints are space
    public static final int DATA_TITLE_ROW_CLOSED = 5;
    public static final int DATA_TITLE_ROW_OPEN = 6;
    
    // ROW BORDER ***************************************************
    public static final int SOLID_CLOSED_ROW_BORDER = 1;// - a solid row border, excludes column separators, endpoints are given the colBorderChar. 
    //|*********************************************|
    public static final int SOLID_OPEN_ROW_BORDER = 2;//- a solid rowborder, excludes column separators, endpoints are non-visible characters.  .
    // *********************************************
    public static final int COL_CLOSED_ROW_BORDER = 3;// - a solid rowborder, includes column separators, endpoints are given the colBorderChar.
    //|*********|*************|************|********|
    public static final int COL_OPEN_ROW_BORDER = 4;// - a solid rowborder, includes column separators, endpoints are non-visible characters.  .
    // *********|*************|************|******** 
    public static final int COL_OPEN_BLANK_BORDER = 5;// - a border of blank spaces that contains column separators and  non-visible endpoint characters.  
    //          |             |            |
    public static final int COL_CLOSED_BLANK_BORDER= 6;// - a border of blank spaces but contains column separators and visible endpoint separators
    //|         |             |            |        |
    public static final int CLOSED_BLANK_BORDER = 7;// - a border of spaces with visible endpoints
    //|                                             |
    public static final int OPEN_BLANK_BORDER = 8;//- a border of spaces with non-visible endpoints.
    // THIS IS ALL EMPTY SPACES


}// end class
class GridMaker extends GridMakerConfig{

    private String centorText = "";
    private String grid = "";
    private int maxColWidths [];
    private int minColWidths[];
    private int gridSize = 0;
    public String buildGrid(String[][] arr2d){// style represents a cnstant number
        String buildDataRow = "";
        if(getRowBorderType().length == 0 ){// if it doesnt exist fill it wilh default
            int[]defualtBorders = new int[arr2d.length+1];
            Arrays.fill(defualtBorders,1);
            setRowBorderType(defualtBorders);
        }
        if(getDataRowType().length == 0){
            int[] defualtData = new int[arr2d.length];
            Arrays.fill(defualtData,1);
            setDataRowType(defualtData);
        }
        this.findColWidths(arr2d);
        String border = "";
       
        for(int i = 0; i < arr2d.length; i++){
            grid = grid + buildRowBorder(getRowBorderType()[i]) + "\n";
            if(i< arr2d.length){
                    grid = grid + buildDataRow(arr2d[i], i) +"\n";// builds row border, data row, row border
                }
               
        }
        grid = "\n" + grid+ buildRowBorder(getRowBorderType()[arr2d.length]);
           
        // make new loop for
           
        return grid;
        }
    private String buildRowBorder( int borderType ){
        String rowBorder = "";// use rowBorder in each case and set the value to the first thing that the constants represent
        int len = 0;
       
        switch(borderType){
            case(SOLID_CLOSED_ROW_BORDER):// 1
                   rowBorder = getColBorderChar();
                for(int j = 0; j < maxColWidths.length; j++){ // what i will do is make a int that loops through all the valuesand then-1 and then concat a row border from that
                    for(int i = 0; i < maxColWidths[j]; i++){
                        len++;
                    }
                    len++;
                }
                for(int i = 0; i < len -1; i++){// made length loop through every element and acounted for the column sperators and then made a string from theri
                    rowBorder+=getRowBorderChar();
                }
                rowBorder+=getColBorderChar();
            break;
            case(SOLID_OPEN_ROW_BORDER):// 2
                rowBorder = " ";
                for(int j = 0; j < maxColWidths.length; j++){ // what i will do is make a int that loops through all the valuesand then-1 and then concat a row border from that
                    for(int i = 0; i < maxColWidths[j]; i++){
                        len++;
                    }
                    len++;
                }
                for(int i = 0; i < len -1; i++){// made length loop through every element and acounted for the column sperators and then made a string from theri
                    rowBorder+=getRowBorderChar();
                }
                rowBorder+=" ";
            break;
            case(COL_CLOSED_ROW_BORDER):// 3
                rowBorder = getColBorderChar();
                for(int j = 0; j < maxColWidths.length; j++){
                    for(int i = 0; i < maxColWidths[j]; i++){
                    rowBorder = rowBorder + getRowBorderChar();    
                    }
                    rowBorder += this.getColBorderChar();
                }
            break;
            case(COL_OPEN_ROW_BORDER):// 4
                rowBorder = " ";
                for(int j = 0; j < maxColWidths.length; j++){
                    for(int i = 0; i < maxColWidths[j]; i++){
                    rowBorder = rowBorder + getRowBorderChar();    
                    }
                    if(j<maxColWidths.length-1){// becuase it starts at 0, use -1
                        rowBorder += this.getColBorderChar();
                    }
                }
            break;
            case(COL_OPEN_BLANK_BORDER):// 5
                rowBorder = " ";
                for(int j = 0; j < maxColWidths.length; j++){
                    for(int i = 0; i < maxColWidths[j]; i++){
                    rowBorder = rowBorder + " ";    
                    }
                    if(j<maxColWidths.length - 1){
                    rowBorder += this.getColBorderChar();    
                    }
                }
            break;
             case(COL_CLOSED_BLANK_BORDER):// 6
                rowBorder = getColBorderChar();
                for(int j = 0; j < maxColWidths.length; j++){
                    for(int i = 0; i < maxColWidths[j]; i++){
                    rowBorder = rowBorder + " ";    
                    }
                    rowBorder += this.getColBorderChar();
                }
            break;
            case(CLOSED_BLANK_BORDER):// 7
                rowBorder = "|";
               
                for(int j = 0; j < maxColWidths.length; j++){ // what i will do is make a int that loops through all the valuesand then-1 and then concat a row border from that
                    for(int i = 0; i < maxColWidths[j]; i++){
                        len++;
                    }
                    len++;
                }
                for(int i = 0; i < len -1; i++){// made length loop through every element and acounted for the column sperators and then made a string from theri
                    rowBorder+=  " ";
                }
                rowBorder+= "|";
            break;
           
            case(OPEN_BLANK_BORDER)://CASE 8
                rowBorder = " ";
                for(int j = 0; j < maxColWidths.length; j++){ // what i will do is make a int that loops through all the valuesand then-1 and then concat a row border from that
                    for(int i = 0; i < maxColWidths[j]; i++){
                        len++;
                    }
                    len++;
                }
                for(int i = 0; i < len -1; i++){// made length loop through every element and acounted for the column sperators and then made a string from theri
                    rowBorder+= " ";
                }
                rowBorder+=" ";
            break;
            default:
                rowBorder = getColBorderChar();
                for(int j = 0; j < maxColWidths.length; j++){ // what i will do is make a int that loops through all the valuesand then-1 and then concat a row border from that
                    for(int i = 0; i < maxColWidths[j]; i++){
                        len++;
                    }
                    len++;
                }
                for(int i = 0; i < len -1; i++){// made length loop through every element and acounted for the column sperators and then made a string from theri
                    rowBorder+=getRowBorderChar();
                }
                rowBorder+=getColBorderChar();
        }
       
        return rowBorder;
    }
     private String buildTitleRow (String[] rowData, int titleRowIndex){// its set 1 number higher so subtract 1
        // loop through the value in the getDataTtleRowType 2d int arr
        String s = "";
        int[] titleStyles = getDataTitleRowStyles(titleRowIndex);
            if(titleStyles[1] == DATA_TITLE_ROW_CLOSED){
                 s = getColBorderChar()+centorText(rowData[0], gridSize)+getColBorderChar();

            }
            else if(titleStyles[1] == DATA_TITLE_ROW_OPEN){
                 s = " " + centorText(rowData[0], gridSize) + " ";
            }
            else{
                 s = getColBorderChar()+centorText(rowData[0], gridSize)+getColBorderChar();
            }

        return s;
    }
     private int[] getDataTitleRowStyles(int row){
        int[] styles = new int[1];
        for(int i = 0; i < getDataTitleRowType().length ; i++){
            if(getDataTitleRowType()[i][0] ==  row){
                styles = getDataTitleRowType()[i];
            }
           
            
        }
       // System.out.println(Arrays.toString(styles));
        return styles;
    }

    private String buildDataRow(String[] rowData, int rowNum){
        if(isDataTitleRow(rowNum) == true){
            return buildTitleRow(rowData, rowNum);// return the method that return a string of the title row... new concept
        }
        else{
            // array will hold row length in order to alighn properly.
            // each time through the loop the array will be reset... Pretty sure

           
           
            int spacing =(int)Math.ceil(gridSize / rowData.length);
            int style = getDataRowType()[rowNum];
            String row = "";
            switch(style){
                case DATA_COL_ROW_CLOSED://  equol to 1 -----  |  bla  |  blaa  |  baala  |
                    row = "" + getColBorderChar();
                    for(int i = 0; i < maxColWidths.length; i++){
                        if(i < rowData.length){
                        row = row + centorText(rowData[i],maxColWidths[i]) + getColBorderChar();

                        }
                        else{
                        row = row + centorText(Character.toString(getEmptyCellChar()),maxColWidths[i]) + getColBorderChar();
                        }
                       
                    }// end fr
                break;
                case DATA_COL_ROW_OPEN: // (2)   rod  |  rod  |  rod
                    String empty = "";  // getting column border length so if its 1 or 2 it wont change the array
                    for(int i = 0; i < getColBorderChar().length(); i++ ){
                        empty += " ";
                    }
                    row = empty;
                    if(rowData.length == 1){
                        for( int i = 0; i < rowData.length; i++){
                            if(rowData[i] == null || rowData[i].isEmpty()){
                        rowData[i] = Character.toString(getEmptyCellChar());
                            }
                            row = row +  centorText(rowData[i],spacing) + getColBorderChar() ;
                        }
                       
                    }
                   
                    for( int i = 0; i < rowData.length; i++){
                        if(rowData[i] == null || rowData[i].isEmpty()){
                        rowData[i] = Character.toString(getEmptyCellChar());
                        }
                       
                        if(rowData.length - 1 == i){
                             row = row +  centorText(rowData[i],spacing);
                        }
                        else{
                         row = row +  centorText(rowData[i],spacing) + getColBorderChar() ;
                         
                        }// end else
                    }// end for
                break;
                case DATA_ONLY_ROW_CLOSED: // (3)  row should look like, |  rod    rod    rod  |
                    empty = "";  
                    for(int i = 0; i < getColBorderChar().length(); i++ ){
                        empty += " ";
                    }// end for
                    row = empty;
                    // probloms with setting the first border value, if i = 0 or i = get colBorder set it as the border before the loop
                    for(int i = 0; i < rowData.length; i++){
                        if(rowData[i] == null || rowData[i].isEmpty()){
                        rowData[i] = Character.toString(getEmptyCellChar());
                        }
                        if(i == 0){ // if its thefirst element put a colum border in front
                            row = getColBorderChar() + centorText(rowData[i],spacing) + empty;// first value no need to make it row+=
                            }// end if
                        else if (i == rowData.length - 1){// if i last element put border at the end
                            row =  row  + centorText(rowData[i],spacing) +  getColBorderChar();  
                            }// end if
                        else if (i != 0 || i != rowData.length - 1) {//  leave the border empty space
                            row = row + centorText(rowData[i],spacing) + empty;
                            }// end else
                       
                        }// end for
                break;
                case DATA_ONLY_ROW_OPEN: //  should look like,  rod  rod  rod
                    empty = "";  // getting column border length so if its 1 or 2 it wont change the array
                    for(int i = 0; i < getColBorderChar().length(); i++ ){
                        empty += " ";
                    }
                    row = empty;
                    for(int i = 0; i < rowData.length; i++){
                         if(rowData[i] == null || rowData[i].isEmpty()){
                        rowData[i] = Character.toString(getEmptyCellChar());
                        }
                        row = row +  centorText(rowData[i],maxColWidths[i]) + empty;
                    }
                break;
                default:
                    row = "" + getColBorderChar();
                    for(int i = 0; i < rowData.length; i++){
                        if(rowData[i] == null || rowData[i].isEmpty()){
                            rowData[i] = Character.toString(getEmptyCellChar());
                        }
                        row = row + centorText(rowData[i],maxColWidths[i]) + getColBorderChar();// call centertext method and concat it to make a row
                    }// end fr
                   
                }// END SWITCH
                return row;
        }
    }
    public int[] addElements(int[] arr,int len){// if buildDataRow  is not == to 2dArr length add the defualt variable
        int[] tempArr = new int[len];
        if(tempArr.length > arr.length){
            for(int i = 0; i < arr.length;i++){
                tempArr[i] = arr[i]; // set temp arr values to arrays
            }
            for(int i = arr.length; i<len; i++){
                tempArr[i] = GridMaker.DATA_COL_ROW_CLOSED;
            }
        }
       // len - arr.length then loop and add default values
       else{
           tempArr = arr;
       }
        return tempArr;
    }
    public void reverse(int[] arr){
        int half = arr.length / 2;
        for(int i = 0; i < half; i++){
            int temp = arr[arr.length -1-i];
            arr[arr.length -1-i] = arr[i];
            arr[i] = temp;
        }  
    }
    public boolean isDataTitleRow(int index){
        boolean num = false;
        for(int i = 0; i < getDataTitleRowType().length; i++){
            if(getDataTitleRowType()[i][0] == index){
                num = true;
            }
        }//end for loop
     return num;  
    }//end method isDataTitleRow
    public void adjustMaxColWidths(int col, int title) {
         
        if(title > col){
            double difference = title-col;
            difference = Math.ceil(difference/maxColWidths.length);
           
            for(int i = 0; i < maxColWidths.length; i++){
                maxColWidths[i] += difference;
            }
           
        }
        int finalLength = col;
        if(title > col){
            finalLength = title;
        }
        gridSize = finalLength;

    }
    private void findColWidths(String[][] arr){
        int maxTitleLength = 0;
        int colLength = 0;
        int finalLength = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i].length > colLength){
                colLength = arr[i].length;
            }
        }
        minColWidths = new int[colLength];
        maxColWidths = new int[colLength];
        for(int i = 0; i < arr.length; i++){
            if(!isDataTitleRow(i)){
                for(int k = 0; k < arr[i].length; k++){
                    if((arr[i][k].length()+2*getCellPaddingSize()) > maxColWidths[k]){
                        maxColWidths[k] = arr[i][k].length() + 2*getCellPaddingSize();
                    }
                }
            }
        }
        for(int i = 0; i < arr.length; i++){
            if(!isDataTitleRow(i)){
                for(int k = 0; k < arr[i].length; k++){
                    if((arr[i][k].length()+2*getCellPaddingSize()) < minColWidths[k]){
                        minColWidths[k] = arr[i][k].length() + 2*getCellPaddingSize();
                    }
                }
            }
        }
        for(int i = 0; i < getDataTitleRowType().length; i++){
            if(arr[getDataTitleRowType()[i][0]][0].length() + 2*getCellPaddingSize() > maxTitleLength){
                maxTitleLength = arr[getDataTitleRowType()[i][0]][0].length() + 2*getCellPaddingSize();
            }
        }
        colLength = 0;
        for(int i = 0; i < maxColWidths.length;i++){
            colLength += (maxColWidths[i]+1);
        }
        colLength--;
        adjustMaxColWidths(colLength, maxTitleLength);
       


    }// end method
        //make this loop after finding the max
    private String centorText(String text, int maxColSize){
        String spaces = "";
        String total;
        int padCell = 0;
        padCell = (maxColSize-text.length()) / 2; // finding the number of stpaces that will needed to be made in each colum based on the max and the size of the string
        // if the number of spaces is odd add one becuase java deosnt round up
        for(int k = 0; k<padCell; k++){
            spaces =  spaces + " ";
            }
            // text = trent
            total =  spaces + text + spaces;//
            if((maxColSize - text.length()) % 2 != 0){//check to see if the spaces remainding is odd if so add one space to the right
                total = total + " ";
            }
          return total;
       
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
class TextUtils{
    public String makeWords(int cnt, int style, int lwrsize, int uprsize){
        String words = "";
        int UPPER_CASE = 1;
        int LOWER_CASE = 2;
        int MIXED_CASE = 3;
        int NUMBER_CASE = 4;
       
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
                int q = (int)(Math.random()*(4));
                char rand = 't';
                if (q==1){
                    rand = (char)(Math.random()*('z'-'a'+1)+'a');
                }
                if (q==2){
                    rand = (char)(Math.random()*('Z'-'A'+1)+'A');
                }
                if (q==3){
                    rand = (char)(Math.random()*('9'-'0'+1)+'0');
                }
                temp = temp+rand;
            }
            if (style == NUMBER_CASE){
                char rand = (char)(Math.random()*('9'-'0'+1)+'0');
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
                temp = temp +" "+ split[k];
                j=temp.length();
                if (j>=width){
                    j=width;
                    k=k-1;
                }
                k=k+1;
            }
            wrapped[x] = temp;
        }
        
        
        String[] wrapped2 = wrapped;
        wrapped2[0] = wrapped[0].substring(1,wrapped.length);
        
        
        
        return wrapped2;
    }
    public int[] buildRandomGridData(int[] data){
        int rowCntUpr = data[0];
        int rowCntLwr = data[1];
        int wordSizeUpr = data[2];
        int wordSizeLwr = data[3];
        int wordCntUp = data[4];
        int wordCntLwr = data[5];
        int wrapLenUpr = data[6];
        int wrapLenLwr = data[7];
        int rows = (int)(Math.random()*(rowCntUpr-rowCntLwr+1)+rowCntLwr);
        
        String [][] wt = new String [rows][];
        
        int datatimes = (int)(Math.random()*(rows+1));
        int datarows[] = new int [datatimes+1];
        datarows[0]=rows;
        int count = 0;
        for (int x = 1; x<datarows.length-1; x++){
           int rowtimes = (int)(Math.random()*(4)+1);
           datarows[x] = rowtimes;
           count = count + rowtimes;
           if(count > rows){
               datarows[x] = 0;
           }
        }
        int sum = 0;
        for(int x = 0; x<datarows.length; x++){
            sum = sum + datarows[x];
        }
        
     String word = "";
    for (int x = 0; x<sum; x++){
        int cnt = (int)(Math.random()*(wordCntUp-wordCntLwr+1)+wordCntLwr);
        int style = (int)(Math.random()*(4)+1);
        word = word + makeWords(cnt,style,wordSizeLwr,wordSizeUpr);
    }//end of for
        
       int datatemp[] = new int [2];
        datatemp[0] = sum;
        datatemp[1] = rows;
        
        
        
        
        return datatemp; 
    }
}
public class TestGridMaker3D {
    public static void main(String args[]) {

      GridMaker gm = new GridMaker();
      
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
        GridMaker grid = new GridMaker();
         
        int wordcnt = 15;
        int casestyle = (int)(Math.random()*(4)+1);
        int lwrwordsize = 4;
        int uprwordsize = 10;
        int width = 15;
        
        String empty01[][] = na.createEmpty2DArray(rowLwrCnt,rowUprCnt,colLwrCnt,colUprCnt);
        
        int rows = empty01.length;
        
        String[][][] Random3D = na.make3DRandomRadixArray(convParms,radixVals,rows);
        String[][] flatened3d = na.flattened3Darray(Random3D);
        String[][] board = na.tictactoe();
        String gd = grid.buildGrid(flatened3d);
        String words = tu.makeWords(wordcnt,casestyle,lwrwordsize,uprwordsize);
        String[] wrap = tu.wrapText(words,width);
      
      int[] dataRowStyle = {10,10,10};// getting constant varable from the class easier to use this then to remeber all the numbers
      int[] rowBorderStyle ={10,10,10,10};// makes the adjustments
     
        int maxrowscnt = 20;
        int minrowscnt = 1;
        int maxstringleng = 20;
        int minstringleng = 1;
        int maxwordcnt = 20;
        int minwordcnt = 1;
        int maxwrapLen = 20;
        int minwrapLen = 1;
        
        int instructions[] = new int [] {maxrowscnt,minrowscnt,maxstringleng,minstringleng,maxwordcnt,minwordcnt,maxwrapLen,minwrapLen};
        
        int [] data = tu.buildRandomGridData(instructions);

    // titleStyle will read through the code and if its of that given index it will style that row acordingly.
    int titleStyle[][]= new int[1][];
    String trace[][]=new String[3][];
    trace[0] = new String[] {"Element 1","Element 2","Element 3"};
    trace[1] = new String[] {"124","39","94"};
    trace[2] = new String[] {"This is a title LOGIC  hgjhghgjhkjhjkhjhjhjhjhjhjhhj"};
    
    
    
    // isDataTitleROw will loop through the index of the 2d Array and compare to see if its the same as titleStyle, if their the same then return true else false
    titleStyle[0] = new int[]{2,GridMaker.DATA_TITLE_ROW_OPEN};
   
    gm.setDataTitleRowType(titleStyle);
    gm.setRowBorderType(rowBorderStyle);
    gm.setDataRowType(dataRowStyle);// temp is the adjusted dataRowStyle arr in order for the code to function properly!
     
      String test = gm.buildGrid(trace);
      
      System.out.println(Arrays.toString(data));
      

    }//end main
}//end test class
