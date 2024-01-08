import java.io.*;
import java.util.*;
// import javafx.util.Pair;

/*
You are analyzing data for Aquaintly, a hot new social network.

On Aquaintly, connections are always symmetrical. If a user Alice is connected to Bob, then Bob is also connected to Alice.

You are given a sequential log of CONNECT and DISCONNECT events of the following form:
- This event connects users Alice and Bob: ["CONNECT", "Alice", "Bob"]
- This event disconnects the same users: ["DISCONNECT", "Bob", "Alice"] (order of users does not matter)

We want to separate users based on their popularity (number of connections). To do this, write a function that takes in the event log and a number N and returns two collections:
[Users with less than N connections], [Users with N or more connections]

Example:
events = [
    ["CONNECT","Alice","Bob"],
    ["DISCONNECT","Bob","Alice"],
    ["CONNECT","Alice","Charlie"],
    ["CONNECT","Dennis","Bob"],
    ["CONNECT","Pam","Dennis"],
    ["DISCONNECT","Pam","Dennis"],
    ["CONNECT","Pam","Dennis"],
    ["CONNECT","Edward","Bob"],
    ["CONNECT","Dennis","Charlie"],
    ["CONNECT","Alice","Nicole"],
    ["CONNECT","Pam","Edward"],
    ["DISCONNECT","Dennis","Charlie"],
    ["CONNECT","Dennis","Edward"],
    ["CONNECT","Charlie","Bob"]
]

Using a target of 3 connections, the expected results are:
Users with less than 3 connections: ["Alice", "Charlie", "Pam", "Nicole"]
Users with 3 or more connections: ["Dennis", "Bob", "Edward"]

All test cases:
grouping(events, 3) => [["Alice", "Charlie", "Pam", "Nicole"], ["Dennis", "Bob", "Edward"]]
grouping(events, 1) => [[], ["Alice", "Charlie", "Dennis", "Bob", "Pam", "Edward", "Nicole"]]
grouping(events, 10) => [["Alice", "Charlie", "Dennis", "Bob", "Pam", "Edward", "Nicole"], []]
Complexity Variables:
E = number of events
U = number of users

*/

public class ConnectionLogParsing {
  public static void main(String[] argv) {
   String[][] events = {
      {"CONNECT","Alice","Bob"},
      {"DISCONNECT","Bob","Alice"},
      {"CONNECT","Alice","Charlie"},
      {"CONNECT","Dennis","Bob"},
      {"CONNECT","Pam","Dennis"},
      {"DISCONNECT","Pam","Dennis"},
      {"CONNECT","Pam","Dennis"},
      {"CONNECT","Edward","Bob"},
      {"CONNECT","Dennis","Charlie"},
      {"CONNECT","Alice","Nicole"},
      {"CONNECT","Pam","Edward"},
      {"DISCONNECT","Dennis","Charlie"},
      {"CONNECT","Dennis","Edward"},
      {"CONNECT","Charlie","Bob"}
    };

    HashMap<String, String[]> groupedUsers = grouping(events, 2);

    System.out.println("Below threshold:");
    System.out.println(Arrays.toString(groupedUsers.get("below threshold")));

    System.out.println("Above threshold:");
    System.out.println(Arrays.toString(groupedUsers.get("above threshold")));
  }

  public static HashMap<String, String[]> grouping (String[][] events, int connections) {
      HashMap<String, Integer> userConnectionCounts = new HashMap<String, Integer>();
      ArrayList<String> belowThreshold = new ArrayList<String>();
      ArrayList<String> aboveThreshold = new ArrayList<String>();
      int currentCount = 0;

      for (int i = 0; i < events.length; i++) {
        if (events[i][0] == "CONNECT") {
          userConnectionCounts = AddConnection(events[i][1], userConnectionCounts);
          userConnectionCounts = AddConnection(events[i][2], userConnectionCounts);
        } else {
          userConnectionCounts = RemoveConnection(events[i][1], userConnectionCounts);
          userConnectionCounts = RemoveConnection(events[i][2], userConnectionCounts);
        }
      }

      Iterator<Map.Entry<String, Integer>> iterator = userConnectionCounts.entrySet().iterator();

      while (iterator.hasNext()) {
        Map.Entry<String, Integer> entry = iterator.next();
        String key = entry.getKey();
        Integer value = entry.getValue();
        // System.out.println("Key=" + key + ", Value=" + value);

        if (entry.getValue() < connections) {
          belowThreshold.add(entry.getKey());
        } else {
          aboveThreshold.add(entry.getKey());
        }
      }

      HashMap<String, String[]> results = new HashMap<String, String[]>();
      results.put("below threshold", belowThreshold.toArray(new String[]{}));
      results.put("above threshold", aboveThreshold.toArray(new String[]{}));

      return results;

  }

  public static HashMap<String, Integer> AddConnection(String user, HashMap<String, Integer> connections) {
    int currentCount = 0;

    if (connections.get(user) != null) {
      currentCount = connections.get(user);
    }

    currentCount++;

    connections.put(user, currentCount ++);

    return connections;
  }

  public static HashMap<String, Integer> RemoveConnection(String user, HashMap<String, Integer> connections) {
    int currentCount = 0;

    if (connections.get(user) != null) {
      currentCount = connections.get(user);
    }

    if (currentCount > 0) {
      currentCount--;
    }

    connections.put(user, currentCount ++);

    return connections;
  }
}
