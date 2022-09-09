import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Controller.IPhotoAlbumController;
import Controller.ParseBuilder;
import Controller.PhotoAlbumController;
import Model.IPhotoAlbum;
import Model.PhotoAlbum;
import View.IView;
import View.ViewFactory;

/**
 * The type Photo album main.
 */
public class PhotoAlbumMain {

  /**
   * Main:
   * From the user input reads necessary data and create model, then present
   * either web view or graphical view through the controller.
   *
   * @param args the args
   */
  public static void main(String[] args){
    IPhotoAlbum model = PhotoAlbum.getInstance();
    String fileName = "";
    String viewType = "";
    String output = "";
    ViewFactory viewFactory = new ViewFactory();
    List<Integer> dimensions = new ArrayList<>();



    for(int i = 0; i < args.length; i++){
      try {
        // When the command represent input
        if (args[i].equals("-in")) {
          fileName =  args[i + 1];

          // getting full file path
          String curDir = System.getProperty("user.dir");
          curDir += "/" + fileName;

          // parsing input file and when file is not found exception throw
          try {
            InputStream inputStream = new FileInputStream(curDir);
            ParseBuilder.readFile(new InputStreamReader(inputStream));

            model = ParseBuilder.processFile(model);// update and pass it to the processFile
          } catch (FileNotFoundException e) { // parsing file not found exception
            System.out.println("File not found");
          }
        }

        // When the command represent view type
        else if(args[i].equals("-v") || args[i].equals("-view")){
          viewType = args[i + 1];
        }
        else if (args[i].equals("-out") || args[i].equals("-o")){
          output = args[i+1];
        }
        // get dimension for window/frame size
        dimensions.add(Integer.parseInt(args[i]));


      // first try catch phrase
      } catch (Exception e) {
        e.getMessage(); // warn user
      }
    } // end of the for loop

    // when the size of frame or window is not given set it as 1000
    if (dimensions.isEmpty()){
      dimensions.add(1000);
      dimensions.add(1000);
    }

    // Exceptions if input output or view is empty String
    if (fileName.equals("")) {
      System.out.println("Error: File to read is not given");
      System.exit(-1);
    }
    if (viewType.equals("")) {
      System.out.println("Error: View type is not defined");
      System.exit(-1);
    }
    if (viewType.equalsIgnoreCase("Web") && output.equals("")){
      System.out.println("Error: Web view output name is not given");
      System.exit(-1);
    }

    // Through controller create and present view
    IView view = viewFactory.create(viewType, dimensions.get(0), dimensions.get(1), output);
    IPhotoAlbumController controller = new PhotoAlbumController(model, view);
    controller.run(model.getAllSnapshot(), view);
  }
}
