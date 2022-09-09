package Model;

/**
 * The enum Shape type.
 */
public enum ShapeType {
  /**
   * Rectangle shape type.
   */
  RECTANGLE("rectangle"),
  /**
   * Oval shape type.
   */
  OVAL("oval");

  private String type;

  ShapeType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return this.type;
  }
}
