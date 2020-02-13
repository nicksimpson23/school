import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) {
    File fin = new File("input.txt");
    File out = new File("output.txt");

    try {
      Scanner input = new Scanner(fin);
      FileWriter fw = new FileWriter(out);
      BufferedWriter bw = new BufferedWriter(fw);
      while (input.hasNextLine()) {
        String line = input.nextLine();
        String[] sections = line.split(",", -1);
        if (!sections[0].equals("")) {
          bw.write(sections[0]);
          bw.newLine();
          if (!sections[1].equals("")) {
            bw.write(sections[1]);
            bw.newLine();
          }
          if (!sections[2].equals("")) {
            bw.write(sections[2]);
            bw.newLine();
          }
          bw.write((!sections[3].equals("") ? sections[3] + ", " : ""));
          bw.write((!sections[4].equals("") ? sections[4] + "  " : ""));
          bw.write((!sections[5].equals("") ? sections[5] : ""));
        }
        bw.newLine();
        bw.newLine();
      }
      bw.close();
    } catch (IOException error) {
      System.out.println("Error");
    }
  }
}
