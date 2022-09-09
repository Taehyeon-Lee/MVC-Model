package Controller;

import java.util.List;

import Model.Snapshot;
import View.IView;

/**
 * The interface Photo album controller.
 */
public interface IPhotoAlbumController {

  /**
   * Run method: from given snapshot List and view object initiate and present view.
   *
   * @param snapshotList the snapshot list
   * @param view         the view
   */
  public void run(List<Snapshot> snapshotList, IView view);
}
