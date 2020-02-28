import java.util.*;
import java.lang.*;
// of the 2d array is jagged or not the title row will only have one element that you set to the certain index of the array and the way you find it is with a boolean. also adjust teh culumns and tghat it
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
        System.out.println(Arrays.toString(styles));
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
class Nov15API{
   
    public String[][] make3DArrayGrid(String[][][] arr3){
      String[][] arr2d = new String[arr3.length][];
      for(int i = 0; i< arr2d.length; i++){
          arr2d[i] = new String[arr3[i].length];
          for(int j = 0; j < arr2d[i].length;j++){
              String str1 = arr3[i][j][0];
              String str2 = "{" +arr3[i][j][1] + "}";
              String str3 = str1 + " " + str2;
              arr2d[i][j] = str3;
          }
      }
      return arr2d;
    }
   
    public String[][][] make3DRandomRadixArray(int[] info, int[] radixes){

       
        String[][][] arr = new String[info[0]][][];
        for(int i = 0; i < arr.length; i++){
            arr[i] = makeMixedRadixArray(info , radixes);
        }
    return arr;
    }
   
    public String[][] makeRectArray(String[][] jagged){
        int max = 0;
     
        for(int i = 0; i < jagged.length; i++){
            if(jagged[i].length > max){
                max = jagged[i].length;
            }
        }
       
        String[][] rect = new String[jagged.length][];
        String empty = "";
        for(int i = 0; i < rect.length; i++){
                rect[i] = new String[max];
             for(int j = 0; j < rect[0].length; j++){
               rect[i][j] = empty;// fill the array first so no out of bounds
             }
        }
        for(int i = 0; i < rect.length; i++){
             for(int j = 0; j < jagged[i].length; j++){
               rect[i][j] = jagged[i][j];// fill the array first so no out of bounds
             }
        }
        return rect;
    }
   
    public String[][] makeMixedRadixArray(int[] info, int[] radixes){
        // tweaked this method aroud so it works for rods 3dArr spec. I needed to make the 3dArr columns jagged and that is equivolent to the rows of the 2dArr so i just randomized that
          int length = (int)(Math.random() * (info[2]-info[1]+1) + info[1]);
        String[][] arr = new String[length][];
        for(int i = 0; i < arr.length; i++){
            // int len = (int)(Math.random() * (info[2]-info[1]+1) + info[1]);
             int len = 2;
            arr[i] = new String[len];
            int radix = radixes[(int)(Math.random()*radixes.length)];
            arr[i][1] = Integer.toString(radix);
           //arr[i][0] == 1 up
           
           // arr[i][j] === 2 down
           
            for(int j=1;j<arr[i].length;j++){
                arr[i][0] = makeRadixNbr(radix, info[3], info[4]);
               
            }
        }
       
        return arr;
    }

    private String makeRadixNbr(int radix,int min,int max){
        int len = (int)(Math.random() * (max-min+1) + min);
       
        String n = "";
        for(int i = 0; i < len; i++){
            int digit = (int)(Math.random()*radix);
            if(digit<10){
            n = String.format("%s%d",n,digit);
            }
            else{
                char dgt = (char)(digit+55);
                n = String.format("%s%c",n,dgt);
            }        
        }
        return n;
    }
      public String[][] createEmpty2DArray(int rowCount, int colLowBound,int colUprBound)
    {
        String[][] empty = new String[rowCount][];
        for(int i=0;i<empty.length;i++ ){
            int randcol = (int)(Math.random() * (colUprBound - colLowBound+1) + colLowBound );
            empty[i] = new String[randcol];
        }      


        return empty;
    }// end empty arr
   
 
}// end class
public class TestGridMaker3D {
    public static void main(String args[]) {

      GridMaker gm = new GridMaker();
      int[] dataRowStyle = {10,10,10};// getting constant varable from the class easier to use this then to remeber all the numbers
      int[] rowBorderStyle ={10,10,10,10};// makes the adjustments
     


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
System.out.println(test);
    //   cuurenly just implemented the title stuff in the


    }//end main
}//end test class
