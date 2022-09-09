package Model;

import java.util.List;

/**
 * The interface Photo album.
 */
public interface IPhotoAlbum {
  /**
   * Create shape.
   *
   * @param type       the type
   * @param color      the color
   * @param points     the points
   * @param dimension1 the dimension 1
   * @param dimension2 the dimension 2
   * @param name       the name
   * @return the shape
   */
  public IShape createShape(String type, Color color, CoordinatePoints points,
                            double dimension1, double dimension2, String name);

  /**
   * Move shape.
   *
   * @param name the name
   * @param x    the x
   * @param y    the y
   */
  public void moveShape(String name, double x, double y);

  /**
   * Change color.
   *
   * @param name the name
   * @param r    the r
   * @param g    the g
   * @param b    the b
   */
  public void changeColor(String name, double r, double g, double b);

  /**
   * Resize.
   *
   * @param name       the name
   * @param dimension1 the dimension 1
   * @param dimension2 the dimension 2
   */
  public void resize(String name, double dimension1, double dimension2);

  /**
   * Take snapshot of current shapes.
   *
   * @param description the description
   * @return the list
   */
  public void takeSnapshot(String description);

  /**
   * Gets all shapes with unmodifiable list.
   *
   * @return the all shapes
   */
  public List<IShape> getAllShapes();

  /**
   * Remove one shape with the unique name provided.
   *
   * @param name the name
   */
  public void remove(String name);

  /**
   * Reset which would delete everything.
   */
  public void reset();

  /**
   * Gets all snapshot.
   *
   * @return the all snapshot
   */
  public List<Snapshot> getAllSnapshot();

  /**
   * Gets list commands.
   *
   * @return the list commands
   */
  public List<String> getListCommands();
}
