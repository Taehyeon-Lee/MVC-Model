package View;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import Model.IShape;

/**
 * The type Snap panel.
 */
public class SnapPanel extends JPanel {
  private List<IShape> shapes = null;

  /**
   * Instantiates a new Snap panel.
   */
  public SnapPanel() {
    super(); // gets all functionality from the JPanel
  }


  /**
   * Iterates each shape from the List of snapshot and draw each of them
   * on the panel.
   *
   * @param graphics
   * @throws NullPointerException
   */
  @Override
  public void paintComponent(Graphics graphics) throws NullPointerException {
    if (graphics == null) {
      throw new NullPointerException("Graphics cannot be null");
    }

    super.paintComponents(graphics);
    Graphics2D g = (Graphics2D) graphics;

    for (IShape shape : shapes) {
      // set color for the shape based on the info from the file
      g.setColor(new Color((int) shape.getColor().getR(),
              (int) shape.getColor().getG(), (int) shape.getColor().getB()));
      // if the shape is rectangle then draw
      if (shape.getTypeName().equalsIgnoreCase("Rectangle")) {
        g.fillRect((int) shape.getPoints().getX(), (int) shape.getPoints().getY(),
                (int) shape.getFirstDimension(), (int) shape.getSecondDimension());
      }
      // when the shape is oval then draw
      else if (shape.getTypeName().equalsIgnoreCase("Oval")) {
        g.fillOval((int) shape.getPoints().getX(), (int) shape.getPoints().getY(),
                (int) shape.getFirstDimension(), (int) shape.getSecondDimension());
      }
    }
  }

  /**
   * Draw each snapshot based on paintComponent method.
   *
   * @param shapes the shapes
   * @throws IllegalArgumentException the illegal argument exception
   */
  public void draw(List<IShape> shapes) throws IllegalArgumentException {
    if (shapes == null) {
      throw new IllegalArgumentException("Required to have Snapshot");
    }
    this.shapes = shapes;
    this.repaint();
  }

}
