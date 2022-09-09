package Model;

/**
 * The type Color.
 */
public class Color {
  private double r;
  private double g;
  private double b;

  /**
   * Instantiates a new Color.
   *
   * @param r red
   * @param g green
   * @param b blue
   * @throws IllegalArgumentException the illegal argument exception if r, g, and b are
   *                                  out of range(0-256)
   */
  public Color(double r, double g, double b) throws IllegalArgumentException {
    if ((r > 256.1 || r < 0) || (g > 256.1 || g < 0) || (b > 256.1 || b < 0)) {
      throw new IllegalArgumentException("Value for color cannot exceed 256 or negative");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Get red value.
   *
   * @return the double represent red
   */
  public double getR() {
    return this.r;
  }

  /**
   * Get g value.
   *
   * @return the double represent green
   */
  public double getG() {
    return this.g;
  }

  /**
   * Get b value.
   *
   * @return the double represent blue
   */
  public double getB() {
    return this.b;
  }

  /**
   * Change color.
   *
   * @param newR the new red
   * @param newG the new green
   * @param newB the new blue
   */
  public void changeColor(double newR, double newG, double newB) {
    this.r = newR;
    this.g = newG;
    this.b = newB;
  }

  /**
   * Prints red, blue, green values.
   *
   * @return String
   */
  @Override
  public String toString() {
    return "(" + this.r + "," + this.g + "," + this.b + ")";
  }
}
