package View;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Model.IShape;
import Model.Snapshot;

/**
 * The type Web view.
 */
public class WebView implements IView{
  private List<Snapshot> snapshots;
  private String output;
  private int windowSize1;
  private int windowSize2;


  /**
   * Instantiates a new Web view.
   *
   * @param output      the output
   * @param windowSize1 the window size 1
   * @param windowSize2 the window size 2
   */
  public WebView(String output, int windowSize1, int windowSize2){
    this.output = output;
    this.windowSize1 = windowSize1;
    this.windowSize2 = windowSize2;
  }

  /**
   * Html text string:
   * It generates one string for html code with data from the List of snapshot.
   *
   * @return the string
   * @throws IOException the io exception
   */
  public String HTMLText() throws IOException {
    int counter = 0;
    StringBuilder textBuilder = new StringBuilder();
    textBuilder.append("<!DOCTYPE html>\n" +
            "<html>\n" +
            "\n" +
            "\t<head>\n" +
            "\t\t<style>\n" +
            "\t\t  .snapshot {border: 3px solid red; margin-bottom: 20px;}\n" +
            "\t\t  .snap-head {background-color: lightblue;}\n" +
            "\t\t  .snap-ID {margin-top: 0;}\n" +
            "\t\t  .shapes {background-color: blue;}\n" +
            "\t\t  .snap-description {margin: 0;}\n" +
            "\t\t</style>\n" +
            "\t</head>\n" +
            "\n" +
            "\t<body>\n" +
            "\n" +
            "\t\t<div id=\"page-wrap\">\n");
    for (Snapshot s : snapshots) {
      textBuilder.append("\t\t\t<div id=\"" + counter++ + "\" class=\"snapshot\">\n" +
              "\t\t\t\t<div class=\"snap-head\">\n" +
              "\t\t\t\t\t<h1 class=\"snap-ID\">" + s.getID() +"</h1>\n" +
              "\t\t\t\t\t<h2 class=\"snap-description\">" + s.getDescription() + "</h2>\n" +
              "\t\t\t\t</div>\n");

      List<IShape> allShapes = s.getListShape();
      textBuilder.append("\t\t\t\t<div class=\"shapes\">\n");

      // adding svg before for loop
      textBuilder.append("\t\t\t\t\t\t<svg width=\"" + windowSize1 + "\" height=\"" + windowSize2+ "\">\n");

      // loop through and draw shape
      for (IShape shape : allShapes) {
        if (shape.getTypeName().equalsIgnoreCase("rectangle")) {
          textBuilder.append(
                  "\t\t\t\t\t\t  <rect x=\"" + shape.getPoints().getX()+ "\" y=\"" + shape.getPoints().getY()
                  + "\" width=\"" + shape.getFirstDimension()+ "\" height=\""
                  + shape.getSecondDimension()+ "\" style=\"fill:rgb" + shape.getColor().toString()
                  + ";stroke-width:3;stroke:rgb(0,0,0)\" />\n");

        } else if (shape.getTypeName().equalsIgnoreCase("oval")) {
          textBuilder.append(
                  "\t\t\t\t\t\t  <ellipse cx=\"" + shape.getPoints().getX()+ "\" cy=\"" + shape.getPoints().getY()
                  + "\" rx=\"" + shape.getFirstDimension()+ "\" ry=\""
                  + shape.getSecondDimension()+ "\" style=\"fill:rgb" + shape.getColor().toString()
                  + ";stroke-width:3;stroke:rgb(0,0,0)\" />\n");
        }

      }
      textBuilder.append("\t\t\t\t\t\t</svg>\n" +
                  "\t\t\t\t\t</div>\n" +
              "\t\t\t\t</div>\n");
    }

    textBuilder.append(
            "\n" +
            "\t\t</div>\n" +
            "\n" +
            "\t</body>\n" +
            "\n" +
            "</html>");


    return textBuilder.toString();
  }


  @Override
  public void render(List<Snapshot> snapshotList){
    this.snapshots = snapshotList;
    try{
      FileWriter f = new FileWriter(this.output);
      f.write(this.HTMLText());
      f.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}