package Model;

import java.util.Objects;

/**
 * The type Oval.
 */
public class Oval extends AbstractShape {
  private double xRadius;
  private double yRadius;
  private String name;

  /**
   * Instantiates a new Oval.
   *
   * @param name    the name
   * @param points  the points
   * @param color   the color
   * @param xRadius the x radius
   * @param yRadius the y radius
   * @throws IllegalArgumentException the illegal argument exception when name is null or empty
   *                                  or x radius or y radius is negative
   */
  public Oval(String name, CoordinatePoints points, Color color,
              double xRadius, double yRadius) throws IllegalArgumentException {
    super(points, color);

    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name for shape is required");
    }
    if (xRadius < 0 || yRadius < 0) {
      throw new IllegalArgumentException("x radius or y radius cannot be negative");
    }

    this.name = name;
    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  @Override
  public double getFirstDimension() {
    return this.xRadius;
  }

  @Override
  public double getSecondDimension() {
    return this.yRadius;
  }

  @Override
  public String getTypeName() {
    return "Oval";
  }

  public String getName() {
    return this.name;
  }

  @Override
  public void resize(double dimension1, double dimension2) {
    this.xRadius = dimension1;
    this.yRadius = dimension2;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Oval oval = (Oval) o;
    return Double.compare(oval.xRadius, xRadius) == 0 && Double.compare(oval.yRadius, yRadius)
            == 0 && name.equals(oval.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(xRadius, yRadius, name);
  }

  @Override
  public String toString() {
    return "Name:" + this.name + "\n"
            + "Type: " + ShapeType.OVAL.toString() + "\n"
            + "Center: " + getPoints().toString() + " "
            + "X radius: " + this.xRadius + ", " + "Y radius: " + this.yRadius + ", "
            + "Color: " + getColor().toString() + "\n\n";
  }
}
