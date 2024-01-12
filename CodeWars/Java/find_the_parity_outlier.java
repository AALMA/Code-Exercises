/*
  You are given an array (which will have a length of at least 3, but could be very large) containing integers. The array is either entirely comprised of odd integers or entirely comprised of even integers except for a single integer N. Write a method that takes the array as an argument and returns this "outlier" N.

  Examples

  [2, 4, 0, 100, 4, 11, 2602, 36] -->  11 (the only odd number)

  [160, 3, 1719, 19, 11, 13, -21] --> 160 (the only even number)
*/

public class FindOutlier {
  static int find(int[] integers) {
    Integer firstEven = null;
    Integer firstOdd = null;

    for(int i = 0; i < integers.length; i++) {
      if ((integers[i] & 1) == 0) {
        //Even
        if (firstEven == null) {
          if (firstOdd != null && i > 1) {
            return integers[i];
          }
        } else if(firstOdd != null) {
          return firstOdd;
        }

        firstEven = integers[i];
      } else {
        //Odd
        if (firstOdd == null) {
          if (firstEven != null && i > 1) {
            return integers[i];
          }
        } else if(firstEven != null) {
          return firstEven;
        }

        firstOdd = integers[i];
      }
    }

    return 0;
  }
}