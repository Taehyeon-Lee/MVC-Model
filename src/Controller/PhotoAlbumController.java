package Controller;

import java.util.List;

import Model.IPhotoAlbum;
import Model.Snapshot;
import View.IView;


/**
 * The type Photo album controller.
 */
public class PhotoAlbumController implements IPhotoAlbumController{
  private IPhotoAlbum model;
  private IView view;

  /**
   * Instantiates a new Photo album controller.
   *
   * @param model the model
   * @param view  the view
   */
  public PhotoAlbumController(IPhotoAlbum model, IView view){
    this.model = model;
    this.view = view;
  }

  @Override
  public void run(List<Snapshot> snapshotList, IView view){
    view.render(snapshotList);
  }

}
