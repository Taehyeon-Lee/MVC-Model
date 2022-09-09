import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.print.DocFlavor;

import Controller.ParseBuilder;
import Model.IPhotoAlbum;
import Model.PhotoAlbum;
import View.WebView;

import static org.junit.Assert.*;

public class WebViewTest {
  private IPhotoAlbum album;
  private String curDir;
  private StringBuilder htmlSVG;
  private WebView webView;



  @Before
  public void setUp() throws FileNotFoundException {
    album = PhotoAlbum.getInstance();
    curDir = System.getProperty("user.dir");
    String filename = "/src/demo_input.txt";

    try {
      InputStream inputStream = new FileInputStream(curDir + filename);
      ParseBuilder.readFile(new InputStreamReader(inputStream));

      album = ParseBuilder.processFile(album);// update and pass it to the processFile
    } catch (FileNotFoundException e) { // parsing file not found exception

      System.out.println("File not found");
    }

    webView = new WebView("WebView_testing.html", 1000, 1000);
    webView.render(album.getAllSnapshot());
    String outputName = "/WebView_testing.html";
    System.out.println(curDir+outputName);
    InputStream testing = new FileInputStream(curDir + outputName);

    htmlSVG = new StringBuilder();
    Scanner s = new Scanner(new InputStreamReader(testing));

    while(s.hasNextLine()){
      String line =  s.nextLine();
      line = line.strip();

      if(!line.contains("<h1 class=\"snap-ID\">")){
        htmlSVG.append(line + "\n");
      }
//      htmlSVG.append(s.next() + "\n");
    }



  }

  @Test
  public void render() throws FileNotFoundException {

    System.out.println(htmlSVG.toString());
    assertEquals("<!DOCTYPE html>\n" +
            "<html>\n" +
            "\n" +
            "<head>\n" +
            "<style>\n" +
            ".snapshot {border: 3px solid red; margin-bottom: 20px;}\n" +
            ".snap-head {background-color: lightblue;}\n" +
            ".snap-ID {margin-top: 0;}\n" +
            ".shapes {background-color: blue;}\n" +
            ".snap-description {margin: 0;}\n" +
            "</style>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "\n" +
            "<div id=\"page-wrap\">\n" +
            "<div id=\"0\" class=\"snapshot\">\n" +
            "<div class=\"snap-head\">\n" +
            "<h2 class=\"snap-description\">After first selfie</h2>\n" +
            "</div>\n" +
            "<div class=\"shapes\">\n" +
            "<svg width=\"1000\" height=\"1000\">\n" +
            "<rect x=\"100.0\" y=\"300.0\" width=\"25.0\" height=\"100.0\" style=\"fill:rgb(0.0,0.0,255.0);stroke-width:3;stroke:rgb(0,0,0)\" />\n" +
            "<ellipse cx=\"500.0\" cy=\"400.0\" rx=\"60.0\" ry=\"30.0\" style=\"fill:rgb(0.0,255.0,1.0);stroke-width:3;stroke:rgb(0,0,0)\" />\n" +
            "</svg>\n" +
            "</div>\n" +
            "</div>\n" +
            "<div id=\"1\" class=\"snapshot\">\n" +
            "<div class=\"snap-head\">\n" +
            "<h2 class=\"snap-description\">2nd selfie</h2>\n" +
            "</div>\n" +
            "<div class=\"shapes\">\n" +
            "<svg width=\"1000\" height=\"1000\">\n" +
            "<rect x=\"100.0\" y=\"300.0\" width=\"25.0\" height=\"100.0\" style=\"fill:rgb(0.0,0.0,255.0);stroke-width:3;stroke:rgb(0,0,0)\" />\n" +
            "<ellipse cx=\"500.0\" cy=\"400.0\" rx=\"60.0\" ry=\"30.0\" style=\"fill:rgb(0.0,255.0,1.0);stroke-width:3;stroke:rgb(0,0,0)\" />\n" +
            "</svg>\n" +
            "</div>\n" +
            "</div>\n" +
            "<div id=\"2\" class=\"snapshot\">\n" +
            "<div class=\"snap-head\">\n" +
            "<h2 class=\"snap-description\"></h2>\n" +
            "</div>\n" +
            "<div class=\"shapes\">\n" +
            "<svg width=\"1000\" height=\"1000\">\n" +
            "<rect x=\"100.0\" y=\"300.0\" width=\"25.0\" height=\"100.0\" style=\"fill:rgb(0.0,0.0,255.0);stroke-width:3;stroke:rgb(0,0,0)\" />\n" +
            "<ellipse cx=\"500.0\" cy=\"400.0\" rx=\"60.0\" ry=\"30.0\" style=\"fill:rgb(0.0,255.0,1.0);stroke-width:3;stroke:rgb(0,0,0)\" />\n" +
            "</svg>\n" +
            "</div>\n" +
            "</div>\n" +
            "<div id=\"3\" class=\"snapshot\">\n" +
            "<div class=\"snap-head\">\n" +
            "<h2 class=\"snap-description\">Selfie after removing the rectangle from the picture</h2>\n" +
            "</div>\n" +
            "<div class=\"shapes\">\n" +
            "<svg width=\"1000\" height=\"1000\">\n" +
            "<ellipse cx=\"500.0\" cy=\"400.0\" rx=\"60.0\" ry=\"30.0\" style=\"fill:rgb(0.0,255.0,1.0);stroke-width:3;stroke:rgb(0,0,0)\" />\n" +
            "</svg>\n" +
            "</div>\n" +
            "</div>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "</body>\n" +
            "\n" +
            "</html>\n", htmlSVG.toString());
  }
}