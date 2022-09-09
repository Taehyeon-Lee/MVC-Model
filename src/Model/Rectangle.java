package Model;

import java.util.Objects;

/**
 * The type Rectangle.
 */
public class Rectangle extends AbstractShape {
  private double width;
  private double height;
  private String name;

  /**
   * Instantiates a new Rectangle.
   *
   * @param name   the name
   * @param points the points
   * @param color  the color
   * @param width  the width
   * @param height the height
   * @throws IllegalArgumentException the illegal argument exception when name is null or empty                                  or width or height is negative
   */
  public Rectangle(String name, CoordinatePoints points, Color color,
                   double width, double height) throws IllegalArgumentException {
    super(points, color);

    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name for shape is required");
    }
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width or height cannot be negative");
    }

    this.name = name;
    this.width = width;
    this.height = height;
  }

  @Override
  public double getFirstDimension() {
    return this.width;
  }

  @Override
  public double getSecondDimension() {
    return this.height;
  }

  @Override
  public String getTypeName() {
    return "Rectangle";
  }

  public String getName() {
    return this.name;
  }

  @Override
  public void resize(double dimension1, double dimension2) {
    this.width = dimension1;
    this.height = dimension2;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Rectangle rectangle = (Rectangle) o;
    return Double.compare(rectangle.width, width) == 0 && Double.compare(rectangle.height, height)
            == 0 && name.equals(rectangle.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(width, height, name);
  }

  @Override
  public String toString() {
    return "Name:" + this.name + "\n"
            + "Type: " + ShapeType.RECTANGLE.toString() + "\n"
            + "Min corner: " + getPoints().toString() + " "
            + "Width: " + this.width + ", " + "Height: " + this.height + ", "
            + "Color: " + getColor().toString() + "\n\n";
  }
}
