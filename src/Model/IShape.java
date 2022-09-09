package Model;

import java.util.List;

/**
 * The interface Shape.
 */
public interface IShape {
  /**
   * Gets points.
   *
   * @return points where the shape is created
   */
  public CoordinatePoints getPoints();

  /**
   * Gets color.
   *
   * @return the color of the shape
   */
  public Color getColor();

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName();

  /**
   * Move shape to different coordinate points.
   *
   * @param x the x
   * @param y the y
   */
  public void moveShape(double x, double y);

  /**
   * Change color.
   *
   * @param newR the new r
   * @param newG the new g
   * @param newB the new b
   */
  public void changeColor(double newR, double newG, double newB);

  /**
   * Resize.
   *
   * @param dimension1 the dimension 1
   * @param dimension2 the dimension 2
   */
  public void resize(double dimension1, double dimension2);

  /**
   * Gets first dimension.
   *
   * @return the first dimension
   */
  public double getFirstDimension();

  /**
   * Gets second dimension.
   *
   * @return the second dimension
   */
  public double getSecondDimension();

  /**
   * Gets type name.
   *
   * @return the type name
   */
  public String getTypeName();
}
