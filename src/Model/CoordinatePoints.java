package Model;

/**
 * The type Coordinate points.
 */
public class CoordinatePoints {
  private double x, y;

  /**
   * Instantiates a new Coordinate points.
   *
   * @param x the x
   * @param y the y
   */
  public CoordinatePoints(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Get x.
   *
   * @return the double
   */
  public double getX() {
    return this.x;
  }

  /**
   * Get y.
   *
   * @return the double
   */
  public double getY() {
    return this.y;
  }

  /**
   * Move to new x and y.
   *
   * @param newX the new x
   * @param newY the new y
   */
  public void move(double newX, double newY) {
    this.x = newX;
    this.y = newY;
  }

  /**
   * Prints x and y coordinate.
   *
   * @return String
   */
  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }
}
