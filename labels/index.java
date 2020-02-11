import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) {
    // these arrays store the contents of each section of
    // the input in order
    String[] Names = new String[24];
    String[] Addresses = new String[24];
    String[] Location = new String[24];
    String[] City = new String[24];
    String[] Zip = new String[24];
    String State = "";

    // used to keep track of the current position of the respective arrays when adding values to them
    int namesIndex = 0;
    int addressIndex = 0;
    int locationIndex = 0;
    int cityIndex = 0;
    int zipIndex = 0;

    File fin = new File("input.txt");

    try {
      Scanner input = new Scanner(fin);
      int index = 0; // tracks the current line index of the file
      // runs through every line of the file and gets the current line
      while (input.hasNextLine()) {
        String line = input.nextLine();
        if (index < 24) {
          // If the index of the current line is less than 24, it is a name, so I add the name to the names array. The same process is repeated for the following sections.
          Names[namesIndex] = line;
          namesIndex++;
        } else if (index > 24 && index < 44) {
          Addresses[addressIndex] = line;
          addressIndex++;
        } else if (index > 44 && index < 64) {
          Location[locationIndex] = line;
          locationIndex++;
        } else if (index > 64 && index < 84) {
          City[cityIndex] = line;
          cityIndex++;
        } else if (index == 85) {
          State = line;
        } else if (index > 86) {
          Zip[zipIndex] = line;
          zipIndex++;
        }
        // increment line index
        index++;
      }
      
      File out = new File("output.txt");
      FileWriter fw = new FileWriter(out);
      BufferedWriter bw = new BufferedWriter(fw);

      // loop 0-24 as there are 23 name-lines
      for (int i = 0; i < 24; i++) {
        // don't bother adding blank name-lines
        if (Names[i] != null && !Names[i].equals("")) {
          bw.write(Names[i]);
          if (!Addresses[i].equals("")) {
            bw.newLine(); // equivalent of writing \n
            bw.write(Addresses[i]);
          }
          if (!Location[i].equals("")) {
            bw.newLine();
            bw.write(Location[i]);
          }
          bw.newLine();
          // construct the final line as a single string
          String finalLine = "";
          if (City[i] != null) {
            finalLine += (!City[i].equals("") ? City[i] : "");
          }
          if (State != null) {
            if (finalLine.equals("")) {
              finalLine += State;
            } else {
              finalLine += ", " + State;
            }
          }
          if (Zip[i] != null) {
            finalLine += (!Zip.equals("") ? " " + Zip[i] : "");
          }
          bw.write(finalLine);
          // pad with two new lines
          bw.newLine();
          bw.newLine();
        }
      }
      // close the buffer
      bw.close();
    } catch (IOException error) {
      System.out.println("Error");
    }
  }
}
