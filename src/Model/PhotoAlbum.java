package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Photo album and it's singleton class.
 */
public class PhotoAlbum implements IPhotoAlbum {
  private static PhotoAlbum instance = new PhotoAlbum();
  // Where snapshot is going to be stored
  private List<Snapshot> changeLog = new ArrayList<>();
  // List that would contain all shapes after created
  private List<IShape> listShapes = new ArrayList<>();
  // List that would contain all commands
  private List<String> listCommands = new ArrayList<>();


  /**
   * Instantiates a new Photo album.
   */
  public PhotoAlbum() {
  }

  /**
   * Get instance photo album.
   *
   * @return the photo album
   */
  public static PhotoAlbum getInstance() {
    return instance;
  }

  @Override
  public IShape createShape(String type, Color color, CoordinatePoints points,
                            double dimension1, double dimension2, String name) {
    String command = "";
    // based on the type (Rectangle or Oval) create shape
    if (type.equalsIgnoreCase("Rectangle")) {
      IShape rectangle = new Rectangle(name, points, color, dimension1, dimension2);
      listShapes.add(rectangle);

      // save the one string command
      command += "Create" + color.toString() + " " + name + " with corner at " + points.toString()
              + ", width " + dimension1 + "and height " + dimension2 + "\n";
      listCommands.add(command);

      return rectangle;
    } else if (type.equalsIgnoreCase("Oval")) {
      IShape oval = new Oval(name, points, color, dimension1, dimension2);
      listShapes.add(oval);

      // save the one string command
      command += "Create" + color.toString() + " " + name + " with center at " + points.toString()
              + ", radius " + dimension1 + "and " + dimension2 + "\n";
      listCommands.add(command);

      return oval;
    }
    // if type does not match it would not create anything but return null
    return null;
  }

  /**
   * Get shape.
   *
   * @param name the name
   * @return the shape
   * @throws IllegalArgumentException the illegal argument exception when name is null or empty
   */
  public IShape getShape(String name) throws IllegalArgumentException {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Should provide name for the shape");
    }
    // Loop through and gets specific shape with the unique name
    for (int i = 0; i < listShapes.size(); i++) {
      if (listShapes.get(i).getName().equalsIgnoreCase(name)) {
        return listShapes.get(i);
      }
    }
    // if nothing is matched or found return null
    return null;
  }

  @Override
  public void moveShape(String name, double x, double y) {
    // create command variable to store command string
    String command;

    // get shape and move location of the shape to new coordinate
    IShape shape = getShape(name);
    if (shape == null) {
      // if nothing is found then no shape to grab so throw exception
      throw new IllegalStateException();
    }
    shape.moveShape(x, y);

    command = name + " moves to " + shape.getPoints().toString() + "\n";
    listCommands.add(command);
  }

  @Override
  public void changeColor(String name, double r, double g, double b) {
    // get shape and move location of the shape to new coordinate
    IShape shape = getShape(name);
    if (shape == null) {
      // if nothing is found then no shape to grab so throw exception
      throw new IllegalStateException();
    }
    // add command to the list
    listCommands.add(name + " changes from " + shape.getColor().toString()
            + " to (" + r + "," + g + "," + b + ")\n");
    // change color
    shape.changeColor(r, g, b);
  }

  @Override
  public void resize(String name, double dimension1, double dimension2) {
    // get shape and move location of the shape to new coordinate
    IShape shape = getShape(name);
    if (shape == null) {
      // if nothing is found then no shape to grab so throw exception
      throw new IllegalStateException();
    }
    // add command to the list
    if (shape.getTypeName().equalsIgnoreCase("rectangle")) {
      listCommands.add(name + " changes width from " + shape.getFirstDimension()
              + " to " + dimension1 + "height from " + shape.getSecondDimension()
              + " to " + dimension2 + "\n");
    } else if (shape.getTypeName().equalsIgnoreCase("oval")) {
      listCommands.add(name + " changes xRadius from " + shape.getFirstDimension()
              + " to " + dimension1 + " yRadius from " + shape.getSecondDimension()
              + " to " + dimension2 + "\n");
    }
    // resize
    shape.resize(dimension1, dimension2);
  }

  @Override
  public List<IShape> getAllShapes() {
    return listShapes.stream().collect(Collectors.toUnmodifiableList());
  }

  @Override
  public void remove(String name) {
    for (int i = 0; i < listShapes.size(); i++) {
      if (listShapes.get(i).getName().equalsIgnoreCase(name)) {
        listShapes.remove(i);
        listCommands.add("Removed " + name);
      }
    }
  }

  @Override
  public void reset() {
    listShapes.clear();
  }

  @Override
  public void takeSnapshot(String description) {
    String ID = String.valueOf(LocalDateTime.now());
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    Date date = new Date();
    String timeStamp = formatter.format(date);

    // pass unmodifiable list so that it sotre all shapes even after deletion
    Snapshot snapshot = new Snapshot(ID, timeStamp, description,
            listShapes.stream().collect(Collectors.toUnmodifiableList()));
    changeLog.add(snapshot);
    listCommands.add("Take a Snapshot\n");
  }

  @Override
  public List<Snapshot> getAllSnapshot() {
    return changeLog.stream().collect(Collectors.toUnmodifiableList());
  }

  @Override
  public List<String> getListCommands() {
    return listCommands.stream().collect(Collectors.toUnmodifiableList());
  }


  @Override
  public String toString() {
    String aggregated = "";
    for (int i = 0; i < listShapes.size(); i++) {
      aggregated += listShapes.get(i).toString();
    }
    return aggregated;
  }
}
