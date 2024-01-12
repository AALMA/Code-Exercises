/*
	Write a function, persistence, that takes in a positive parameter num and returns its multiplicative persistence, which is the number of times you must multiply the digits in num until you reach a single digit.

	For example (Input --> Output):

	39 --> 3 (because 3*9 = 27, 2*7 = 14, 1*4 = 4 and 4 has only one digit)
	999 --> 4 (because 9*9*9 = 729, 7*2*9 = 126, 1*2*6 = 12, and finally 1*2 = 2)
	4 --> 0 (because 4 is already a one-digit number)
*/
import java.util.Arrays;

class Persist {
	public static int persistence(long n) {
		int count = 0;

	    while (n > 9) {
	    	count++;
	    	System.out.println("count: " + count + " number: " + n);
	    	Long newNumber = null;

	    	do {
	    		long currentDigit = Math.abs(n % 10);
	    		System.out.println(" number: " + n + " currentDigit: " + currentDigit);


	    		if (newNumber == null) {
	    			newNumber = currentDigit;
	    		} else {
	    			newNumber = newNumber * currentDigit;
	    		}

	    		n /= 10;
	    	} while (n > 0);

	    	n = newNumber;
	    }

	    return count;
	}
}