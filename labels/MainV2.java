import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

class Main {
  public static void main(String[] args) {
    File fout = new File("output.txt");

    try {
      Path path = Paths.get("input.txt");
      List<String> data = Files.readAllLines(path);

      FileWriter fw = new FileWriter(fout);
      BufferedWriter bw = new BufferedWriter(fw);

      String state = data.get(85);

      for (int index = 0; index < 24; index++) {
        String name = data.get(index);
        if (!name.equals("")) {
          String address = data.get(24 + index);
          String location = data.get(44 + index);
          String city = data.get(64 + index);
          String zip;
          if (86 + index < data.size()) {
            zip = data.get(86 + index);
          } else {
              zip = data.get(86 + (index % 5));
          }

          bw.write(name);
          bw.newLine();
          if (!address.equals("")) {
            bw.write(address);
            bw.newLine();
          }
          if (!location.equals("")) {
            bw.write(location);
            bw.newLine();
          }
          bw.write(city);
          bw.write(!city.equals("") ? ", " + state : state);
          bw.write("  " + zip);
          bw.newLine();
          bw.newLine();
        }
      }
      bw.close();
    } catch (IOException error) {
      System.out.println("Error");
    }
  }
}
