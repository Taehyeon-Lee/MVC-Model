package View;

/**
 * The type View factory.
 */
public class ViewFactory {

  /**
   * Create view based on the viewType and returns it.
   *
   * @param viewType   the view type
   * @param dimension1 the dimension 1
   * @param dimension2 the dimension 2
   * @param out        the out
   * @return the view
   */
  public IView create(String viewType, int dimension1, int dimension2, String out) {
    // create IView and return based on the viewType
    try {
      if (viewType.equalsIgnoreCase("Graphical")) {
        return new GraphicalView(dimension1, dimension2);
      } else if (viewType.equalsIgnoreCase("Web")) {
        return new WebView(out, dimension1, dimension2);
      }
      throw new IllegalArgumentException("Invalid view type");
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }
}
