package LeapYearMain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utility {

  public static List<String[]> readfile(String filePath) {
    BufferedReader reader;
    List list = new ArrayList();

    try {
      reader = new BufferedReader(new FileReader(
          filePath));
      String line = reader.readLine();
      while (line != null) {
        String[] temp = line.split(",");
        temp = splitScore(temp);
        list.add(temp);
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return list;
  }

  public static String[] splitScore(String[] input) {
    String[] output = new String[4];
    String first = input[0];
    String second = input[1];
    int posOfA = first.lastIndexOf(" ");
    output[0] = first.substring(0, posOfA).trim();
    output[1] = first.substring(posOfA + 1).trim();

    int posOfB = second.lastIndexOf(" ");
    output[2] = second.substring(0, posOfB).trim();
    output[3] = second.substring(posOfB + 1).trim();

    return output;
  }

}
