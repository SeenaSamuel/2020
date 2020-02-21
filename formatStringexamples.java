public class MyClass {
    public static void main(String args[]) {
		String str = "GeeksforGeeks."; 

		// Concatenation of two strings 
		String gfg1 = String.format("My Company name is %s", str); 

		// Output is given upto 8 decimal places 
		String str2 = String.format("My answer is %.8f", 47.65734); 

		// between "My answer is" and "47.65734000" there are 15 spaces 
		String str3 = String.format("My answer is %15.8f", 47.65734); 

		System.out.println(gfg1); 
		System.out.println(str2); 
		System.out.println(str3); 
	
		String str1 = "GFG"; 
		String str4 = "GeeksforGeeks"; 

		//%1$ represents first argument, %2$ second argument 
		String gfg2 = String.format("My Company name" + 
				" is: %1$s, %1$s and %2$s", str1, str4); 

		System.out.println(gfg2); 
		
		
		int num = 7044; 
  
        // Output is 3 zero's("000") + "7044", 
        // in total 7 digits 
        String gfg3 = String.format("%07d", num); 
  
        System.out.println(gfg3);
		
	
    }
}
