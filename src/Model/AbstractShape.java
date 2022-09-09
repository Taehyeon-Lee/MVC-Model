package Model;

/**
 * The type Abstract shape.
 */
public abstract class AbstractShape implements IShape {
  private CoordinatePoints points;
  private Color color;

  /**
   * Instantiates a new Abstract shape.
   *
   * @param points the points
   * @param color  the color
   * @throws IllegalArgumentException the illegal argument exception
   *                                  if points or color is null
   */
  public AbstractShape(CoordinatePoints points, Color color) throws IllegalArgumentException {
    if (points == null || color == null) {
      throw new IllegalArgumentException("Points and color is needed");
    }
    this.points = points;
    this.color = color;
  }

  @Override
  public CoordinatePoints getPoints() {
    return this.points;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public void moveShape(double x, double y) {
    this.points.move(x, y);
  }

  @Override
  public void changeColor(double newR, double newG, double newB) {
    this.color.changeColor(newR, newG, newB);
  }
}
