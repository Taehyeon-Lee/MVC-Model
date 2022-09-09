package View;

import java.util.List;

import Model.Snapshot;

/**
 * The interface View.
 */
public interface IView {
  /**
   * Render:
   * Create a view for individual snapshot from the given List of snapshot.
   *
   * @param snap the snap
   */
  public void render(List<Snapshot> snap);
}
