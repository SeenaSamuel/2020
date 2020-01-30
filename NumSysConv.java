class NumSysConv{ 
    
    public String [][] convertToRadix(String[][] convert, String ascii, String[][] sub){
      String converted [][] = new String [4][2];
      converted [0][0] = "2";
      converted [1][0] = "8";
      converted [2][0] = "10";
      converted [3][0] = "16";
      
     converted[2][1] = convert[0][1];
        
        int finval = 0;//final value after conversions
        int math = 0;//to break up math when you're going thru the loop >-<
        
        for(int i = 0; i<converted.length; i++){
           StringBuilder rem = new StringBuilder();//sb for 
           int baseDesired = Integer.valueOf(converted[i][0]);
           int val = Integer.valueOf(convert[0][1]);
            if(baseDesired!=10)
             {
            math = (val)/baseDesired;
            rem.insert(0, (val) % baseDesired);//the remainder
            while(0<math)
            {
                rem.insert(0, math % baseDesired);//adding the new remainder to the start of the stringbuilder
                math = math / baseDesired;//continue to divide
            }//end while for dividing until nothing is left 0w0
            
            math = 0;
            converted[i][1] = (rem.toString());
             }//end if for converting from base 10 to desired base
        }
        for (int i = 3; i<4; i++){
            
            for (int k = 1; k<2; k++){
            
            String input = converted[i][k];
            
            for (int j = 10; j < 11; j++) {
            input = input.replace(String.valueOf(j),"A");
            }
             for (int j = 11; j < 12; j++) {
            input = input.replace(String.valueOf(j),"B");
            }
             for (int j = 12; j < 13; j++) {
            input = input.replace(String.valueOf(j),"C");
            }
             for (int j = 13; j < 14; j++) {
            input = input.replace(String.valueOf(j),"D");
            }
             for (int j = 14; j < 15; j++) {
            input = input.replace(String.valueOf(j),"E");
            }
             for (int j = 15; j < 16; j++) {
            input = input.replace(String.valueOf(j),"F");
            }
             
             converted[i][k]=input;
               
            }
        }
               return converted;
    }//end
}//end class NumSysConv
