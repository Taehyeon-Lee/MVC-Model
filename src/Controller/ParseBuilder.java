package Controller;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import Model.Color;
import Model.CoordinatePoints;
import Model.IPhotoAlbum;

/**
 * The type Parse builder.
 */
public class ParseBuilder {
  private static HashMap<Integer, String[]> fileParse = new HashMap<>();

  /**
   * Read file: from given readable(name of the text file) reads the file line by line
   * and update fileParse:HashMap<Integer, String[]>. It omits all blank line and comments
   * which starts with # and only store command lines to fileParse as string array.
   *
   * @param readable the readable
   */
  public static void readFile(Readable readable) { // maybe change return type to Hashmap
    Scanner s = new Scanner(readable);
    int counter = 0;
    // Split at newline, and ignore # comment lines
    s.useDelimiter(Pattern.compile("(\\n+|#.*)+"));
    while (s.hasNext()) {
      String[] eachLine; // array which would store parsed word
      String word = s.next();
      word = word.strip(); // remove leading space

      // Split one String into array of String and add to fileParse: hashmap
      // differentiate snapshot command and store as one string after snapshot
      if (word.substring(0, 4).equalsIgnoreCase("snap")) {
        eachLine = word.split(" ", 2);
      } else {
        eachLine = word.split("\\s+");
      }
      fileParse.put(counter, eachLine);
      counter++; // update key for hashmap
    }
  }

  /**
   * Process file photo album.
   * The command(a word from the string array) represent certain functionality thus
   * based on the command call appropriate method from the model and return the model.
   *
   * @param photo the photo
   * @return the photo album
   */
  public static IPhotoAlbum processFile(IPhotoAlbum photo) {
    for (String[] each : fileParse.values()) {
      if (each[0].equalsIgnoreCase("shape")) {
        // create color
        Color color = new Color(Double.parseDouble(each[7]), Double.parseDouble(each[8]),
                Double.parseDouble(each[9]));
        // create points
        CoordinatePoints points = new CoordinatePoints(Double.parseDouble(each[3]),
                Double.parseDouble(each[4]));
        // create shape
        photo.createShape(each[2], color, points, Double.parseDouble(each[5]),
                Double.parseDouble(each[6]), each[1]);
      } else if (each[0].equalsIgnoreCase("move")) {
        photo.moveShape(each[1], Double.parseDouble(each[2]), Double.parseDouble(each[3]));
      } else if (each[0].equalsIgnoreCase("color")) {
        photo.changeColor(each[1], Double.parseDouble(each[2]),
                Double.parseDouble(each[3]), Double.parseDouble(each[4]));
      } else if (each[0].equalsIgnoreCase("resize")) {
        photo.resize(each[1], Double.parseDouble(each[2]), Double.parseDouble(each[3]));
      } else if (each[0].equalsIgnoreCase("remove")) {
        photo.remove(each[1]);
      } else if (each[0].equalsIgnoreCase("snapshot")) {
        if (each.length == 1) {
          photo.takeSnapshot("");
        } else {
          photo.takeSnapshot(each[1]);
        }
      } else {
        System.out.println("Command is not identified");
      }
    }
    return photo;
  }

}
