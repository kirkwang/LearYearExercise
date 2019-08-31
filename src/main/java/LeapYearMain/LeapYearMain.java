package LeapYearMain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class LeapYearMain {

  private static void updateBorad(HashMap<String, Integer> hm, Team teamScore) {
    String teamName = teamScore.getName();

    if (hm.containsKey(teamName)) {
      Integer existCount = hm.get(teamName);
      hm.put(teamName, existCount + teamScore.getScore());
    } else {
      hm.put(teamName, teamScore.getScore());
    }
  }

  private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap,
      final boolean order) {

    List<Entry<String, Integer>> list = new LinkedList(
        unsortMap.entrySet());

    // Sorting the list based on values
    Collections.sort(list, new Comparator<Entry<String, Integer>>() {
      public int compare(Entry<String, Integer> o1,
          Entry<String, Integer> o2) {
        if (order) {
          return o1.getValue().compareTo(o2.getValue());
        } else {
          return o2.getValue().compareTo(o1.getValue());

        }
      }
    });

    // Maintaining insertion order with the help of LinkedList
    Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
    for (Entry<String, Integer> entry : list) {
      sortedMap.put(entry.getKey(), entry.getValue());
    }

    return sortedMap;
  }


  public static HashMap<String, Integer> countTeamPoint(List<String[]> input) {

    HashMap<String, Integer> board = new HashMap();
    Team teamA = new Team();
    Team teamB = new Team();

    for (String[] line : input) {
      if (Integer.parseInt(line[1]) > Integer.parseInt(line[3])) {
        // team A win
        teamA.setNameScore(line[0], 3);
        teamB.setNameScore(line[2], 0);

      } else if (Integer.parseInt(line[1]) < Integer.parseInt(line[3])) {
        // team B win
        teamA.setNameScore(line[0], 0);
        teamB.setNameScore(line[2], 3);

      } else {
        //It is tie
        teamA.setNameScore(line[0], 1);
        teamB.setNameScore(line[2], 1);

      }

      updateBorad(board, teamA);
      updateBorad(board, teamB);
    }

    return board;
  }

  public static void main(String[] args) throws IOException {

    String filepath = "./src/main/resources/sample-input.txt";
    int index = 1;
    File file = new File("./src/main/resources/output.txt");
    FileWriter writer = null;


    Map<String, Integer> teamScore = countTeamPoint(Utility.readfile(filepath));
    teamScore = sortByComparator(teamScore, false);

    try {
      if (file.createNewFile()) {
      }
      //Write Content
      writer = new FileWriter(file);
      for (Map.Entry<String, Integer> entry : teamScore.entrySet()) {
        writer.write(index++ + ". " + entry.getKey() +
            ", " + entry.getValue() + " pts" + "\n");
      }
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
      writer.close();
    }
  }
}
