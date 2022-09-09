
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Model.Color;
import Model.CoordinatePoints;
import Model.IShape;
import Model.Oval;
import Model.PhotoAlbum;
import Model.Rectangle;


/**
 * The type Photo album test.
 */
public class PhotoAlbumTest {
  private PhotoAlbum photoAlbum;
  private IShape rectangle1;
  private IShape rectangle2;
  private IShape oval1;
  private CoordinatePoints points1;
  private CoordinatePoints points2;
  private CoordinatePoints points3;
  private Color color1;
  private Color color2;
  private Color color3;


  /**
   * Sets up.
   *
   * @throws Exception the exception
   */
  @Before
  public void setUp() throws Exception {
    points1 = new CoordinatePoints(150.0, 250.0);
    points2 = new CoordinatePoints(500.0, 200.0);
    points3 = new CoordinatePoints(100.0, 300.0);

    color1 = new Color(0.0, 0.0, 1.0);
    color2 = new Color(0.0, 1.0, 0.0);
    color3 = new Color(1.0, 0.0, 1.0);

    rectangle1 = new Rectangle("R1", points1, color1, 50.0, 100.0);
    rectangle2 = new Rectangle("R2", points2, color2, 100.0, 200.0);
    oval1 = new Oval("O1", points3, color3, 60.0, 30.0);

    photoAlbum = PhotoAlbum.getInstance();
  }

  /**
   * Test illegal color.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalColor() {
    color3 = new Color(-5.0, 150, 258);
  }

  /**
   * Test no shape.
   */
  @Test(expected = IllegalStateException.class)
  public void testNoShape() {
    photoAlbum.reset();
    photoAlbum.createShape("Rectangle", color2, points2, 100.0, 200.0, "R2");
    photoAlbum.moveShape("R1", 50.0, 100.0);
  }

  /**
   * Test illegal dimension.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalDimension() {
    oval1 = new Oval("O1", points3, color3, -60.0, 30.0);
  }

  /**
   * Test illegal shape name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShapeName() {
    rectangle1 = new Rectangle("", points1, color1, 50.0, 100.0);
  }

  /**
   * Gets instance.
   */
  @Test
  public void getInstance() {
    PhotoAlbum albumTest = PhotoAlbum.getInstance();
    assertEquals(photoAlbum, PhotoAlbum.getInstance());
    assertEquals(albumTest, PhotoAlbum.getInstance());
  }

  /**
   * Create shape.
   */
  @Test
  public void createShape() {
    photoAlbum.reset();

    IShape test = photoAlbum.createShape("recTangle", color1, points1, 50.0, 100.0, "R1");
    assertEquals(rectangle1, test);

    test = photoAlbum.createShape("Rectangle", color2, points2, 100.0, 200.0, "R2");
    assertEquals(rectangle2, test);

    test = photoAlbum.createShape("oval", color3, points3, 60.0, 30.0, "O1");
    assertEquals(oval1, test);


  }

  /**
   * Gets shape.
   */
  @Test
  public void getShape() {
    photoAlbum.reset();

    IShape test = photoAlbum.createShape("recTangle", color1, points1, 50.0, 100.0, "R1");
    assertEquals(test, photoAlbum.getShape("R1"));

    photoAlbum.createShape("Rectangle", color2, points2, 100.0, 200.0, "R2");

    test = photoAlbum.createShape("oval", color3, points3, 60.0, 30.0, "O1");
    assertEquals(test, photoAlbum.getShape("O1"));
  }

  /**
   * Move shape.
   */
  @Test
  public void moveShape() {
    photoAlbum.reset();
    photoAlbum.createShape("Rectangle", color2, points2, 100.0, 200.0, "R2");
    photoAlbum.createShape("Rectangle", color1, points1, 50.0, 200.0, "R1");
    photoAlbum.createShape("OVAL", color3, points3, 100.0, 200.0, "O1");

    photoAlbum.moveShape("R1", 200.0, 300.0);
    assertEquals(200.0, photoAlbum.getShape("R1").getPoints().getX(), 0.01);
    assertEquals(300.0, photoAlbum.getShape("R1").getPoints().getY(), 0.01);

    photoAlbum.moveShape("O1", 250.0, 100.0);
    assertEquals(250.0, photoAlbum.getShape("O1").getPoints().getX(), 0.01);
    assertEquals(100.0, photoAlbum.getShape("O1").getPoints().getY(), 0.01);

  }

  /**
   * Change color.
   */
  @Test
  public void changeColor() {
    photoAlbum.reset();

    photoAlbum.createShape("Rectangle", color2, points2, 100.0, 200.0, "R2");
    photoAlbum.createShape("OVAL", color3, points3, 100.0, 200.0, "O1");

    photoAlbum.changeColor("R2", 10.0, 150.0, 15.0);
    assertEquals(10.0, photoAlbum.getShape("R2").getColor().getR(), 0.01);
    assertEquals(150.0, photoAlbum.getShape("R2").getColor().getG(), 0.01);
    assertEquals(15.0, photoAlbum.getShape("R2").getColor().getB(), 0.01);

    photoAlbum.changeColor("O1", 0.0, 0.0, 0.0);
    assertEquals(0.0, photoAlbum.getShape("O1").getColor().getR(), 0.01);
    assertEquals(0.0, photoAlbum.getShape("O1").getColor().getG(), 0.01);
    assertEquals(0.0, photoAlbum.getShape("O1").getColor().getB(), 0.01);
  }

  /**
   * Resize.
   */
  @Test
  public void resize() {
    photoAlbum.reset();
    photoAlbum.createShape("OVAL", color3, points3, 100.0, 200.0, "O1");

    photoAlbum.resize("O1", 30.0, 60.0);
    assertEquals(30.0, photoAlbum.getShape("O1").getFirstDimension(), 0.01);
    assertEquals(60.0, photoAlbum.getShape("O1").getSecondDimension(), 0.01);

    photoAlbum.resize("O1", 100.0, 70.0);
    assertEquals(100.0, photoAlbum.getShape("O1").getFirstDimension(), 0.01);
    assertEquals(70.0, photoAlbum.getShape("O1").getSecondDimension(), 0.01);

  }

  /**
   * Gets all shapes.
   */
  @Test
  public void getAllShapes() {
    photoAlbum.reset();

    photoAlbum.createShape("OVAL", color3, points3, 100.0, 200.0, "O1");
    photoAlbum.createShape("Rectangle", color2, points2, 100.0, 200.0, "R2");
    assertEquals(2, photoAlbum.getAllShapes().size());

    photoAlbum.createShape("Rectangle", color1, points1, 50.0, 200.0, "R1");
    assertEquals(3, photoAlbum.getAllShapes().size());
  }

  /**
   * Remove.
   */
  @Test
  public void remove() {
    photoAlbum.reset();
    photoAlbum.createShape("OVAL", color3, points3, 100.0, 200.0, "O1");
    photoAlbum.createShape("Rectangle", color2, points2, 100.0, 200.0, "R2");
    photoAlbum.createShape("Rectangle", color1, points1, 50.0, 200.0, "R1");

    photoAlbum.remove("R2");
    assertEquals(2, photoAlbum.getAllShapes().size());

    photoAlbum.remove("R2");
    photoAlbum.remove("R1");
    assertEquals(1, photoAlbum.getAllShapes().size());
  }

  /**
   * Reset.
   */
  @Test
  public void reset() {
    photoAlbum.createShape("OVAL", color3, points3, 100.0, 200.0, "O1");
    photoAlbum.createShape("Rectangle", color2, points2, 100.0, 200.0, "R2");
    photoAlbum.createShape("Rectangle", color1, points1, 50.0, 200.0, "R1");
    photoAlbum.reset();
    assertEquals(0, photoAlbum.getAllShapes().size());
  }

  /**
   * Take snapshot.
   */
  @Test
  public void takeSnapshot() {
    photoAlbum.reset();
    photoAlbum.createShape("OVAL", color3, points3, 100.0, 200.0, "O1");
    photoAlbum.takeSnapshot("first selfie");

    assertEquals(photoAlbum.getAllSnapshot().get(0).getID() + "\n"
                    + photoAlbum.getAllSnapshot().get(0).getTimeStamp() + "\n"
                    + "Description: "
                    + photoAlbum.getAllSnapshot().get(0).getDescription() + "\n"
                    + "Shape Information:\n"
                    + photoAlbum.getAllSnapshot().get(0).getListShape().get(0).toString(),
            photoAlbum.getAllSnapshot().get(0).toString());
  }

  /**
   * Tests getAllCommands.
   */
  @Test
  public void getAllCommands() {
    photoAlbum.reset();
    photoAlbum.createShape("OVAL", color3, points3, 100.0, 200.0, "O1");
    photoAlbum.createShape("Rectangle", color1, points1, 50.0, 200.0, "R1");
    photoAlbum.moveShape("R1", 200.0, 300.0);
    photoAlbum.resize("O1", 60.0, 70.0);
    photoAlbum.changeColor("O1", 0.0, 0.0, 0.0);
    photoAlbum.remove("O1");

    System.out.println(photoAlbum.getListCommands());
    assertEquals(6, photoAlbum.getListCommands().size());
  }

}