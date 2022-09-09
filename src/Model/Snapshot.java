package Model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Snapshot.
 */
public class Snapshot {
  private String ID;
  private String timeStamp;
  private String description;
  private List<IShape> listShape;

  /**
   * Instantiates a new Snapshot.
   *
   * @param ID          the id
   * @param timeStamp   the time stamp
   * @param description the description
   * @param listShape   the list shape
   */
  public Snapshot(String ID, String timeStamp, String description, List<IShape> listShape) {
    this.ID = ID;
    this.timeStamp = timeStamp;
    this.description = description;
    this.listShape = listShape;
  }

  /**
   * Get id string.
   *
   * @return the string
   */
  public String getID() {
    return this.ID;
  }

  /**
   * Gets time stamp.
   *
   * @return the time stamp
   */
  public String getTimeStamp() {
    return this.timeStamp;
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets list shape.
   *
   * @return the list shape
   */
  public List<IShape> getListShape() {
    return this.listShape.stream().collect(Collectors.toUnmodifiableList());
  }

  @Override
  public String toString() {
    String shapes = "";
    for (int i = 0; i < this.listShape.size(); i++) {
      shapes += this.listShape.get(i).toString();
    }
    return this.ID + "\n" + this.timeStamp + "\n" + "Description: " + this.description + "\n"
            + "Shape Information:\n" + shapes;
  }

}
