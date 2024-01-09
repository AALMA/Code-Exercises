import java.util.*;

/*
  Write a function that takes in a string of one or more words, and returns the same string, but with all words that have five or more letters reversed (Just like the name of this Kata). Strings passed in will consist of only letters and spaces. Spaces will be included only when more than one word is present.

  Examples:

  "Hey fellow warriors"  --> "Hey wollef sroirraw"
  "This is a test        --> "This is a test"
  "This is another test" --> "This is rehtona test"
*/

public class SpinWords {

  public String spinWords(String sentence) {
    Integer limit = 5;
    String[] words = sentence.split(" ");

    for(int i = 0; i < words.length; i++) {
      if(words[i].length() >= limit) {
        words[i] = reverseWord(words[i]);
      }
    }

    return String.join(" ", words);
  }
  
  private String reverseWord(String word) {
    String newWord = "";
    
    for(int i = word.length() - 1; i >= 0; i--) {
      newWord += word.charAt(i);
    }
    
    return newWord;
  }
}