package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


import javax.swing.*;

import Model.Snapshot;

/**
 * The type Graphical view.
 */
public class GraphicalView extends JFrame implements IView, ActionListener {
  private JPanel topPanel;
  private JPanel bottomPanel;
  private SnapPanel middlePanel;
  private JButton previous;
  private JButton next;
  private JButton select;
  private JButton quit;
  private List<Snapshot> snapshots;
  private int currentIndex = 0;

  private int dimension1;
  private int dimension2;

  /**
   * Instantiates a new Graphical view.
   *
   * @param dimension1 the dimension 1
   * @param dimension2 the dimension 2
   */
  public GraphicalView(int dimension1, int dimension2) {
    this.dimension1 = dimension1;
    this.dimension2 = dimension2;
  }

  /**
   * Set up:
   * It sets up the frame with basic elements(everything but the snapshot in the middle)
   * because those remains the same everytime other snapshot is loaded.
   */
  public void setUp() {
    this.setTitle("Graphical View of the Snapshots");
    topPanel = new JPanel();
    this.setSize(this.dimension1, this.dimension2);
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

    topPanel.setBackground(Color.pink);
    topPanel.setSize(dimension1, 100);

    bottomPanel = new JPanel();
    bottomPanel.setBackground(Color.orange);

    previous = new JButton("<< Previous <<");
    select = new JButton("^^ Select ^^");
    next = new JButton(">> Next >>");
    quit = new JButton("xx Quit xx");

    previous.addActionListener(this);
    next.addActionListener(this);
    select.addActionListener(this);
    quit.addActionListener(this);

    bottomPanel.add(previous);
    bottomPanel.add(select);
    bottomPanel.add(next);
    bottomPanel.add(quit);

    middlePanel = new SnapPanel();
    middlePanel.setBackground(new Color(135, 206, 235));

    this.add(topPanel, BorderLayout.NORTH);
    this.add(middlePanel, BorderLayout.CENTER);
    this.add(bottomPanel, BorderLayout.SOUTH);

    this.setVisible(true); // move it to render
  }

  @Override
  public void render(List<Snapshot> snap) {
    setUp();
    snapshots = snap;

    String uniqueId = snapshots.get(currentIndex).getID();
    String description = snapshots.get(currentIndex).getDescription();
    JLabel id = new JLabel(uniqueId);
    JLabel shotDescription = new JLabel(description);

    topPanel.add(id);
    topPanel.add(shotDescription);

    middlePanel.draw(snapshots.get(currentIndex).getListShape());
    this.setVisible(true);
  }

  /**
   * This method is how the click is registered and turns into the specific action
   *
   * @param e
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    // when the previous button is clicked
    if (e.getSource() == previous) {
      // if the first snapshot is showing then indicate user that there is no previous
      if (currentIndex == 0) {
        JOptionPane.showMessageDialog(this,
                "There is no previous snapshot available");
      } else { // show previous snapshot
        currentIndex--;
        middlePanel.draw(snapshots.get(currentIndex).getListShape());
      }

    }
    // when next button is clicked
    else if (e.getSource() == next) {
      //
      if (currentIndex >= snapshots.size() - 1) { // There is no next then show popup message
        JOptionPane.showMessageDialog(this,
                "There is no next snapshot available");
      } else {
        currentIndex++;
        middlePanel.draw(snapshots.get(currentIndex).getListShape());
      }
    }
    // when select is clicked
    else if (e.getSource() == select) {
      // create String array that only contains listings of snapsthos
      String[] uniqueIDs = new String[snapshots.size()];
      for (int i = 0; i < snapshots.size(); i++) {
        uniqueIDs[i] = snapshots.get(i).getID();
      }

      String selection = (String) JOptionPane.showInputDialog(
              this,
              "Please select a snapshot that you want to view\n",
              "Selecting Snapshot",
              JOptionPane.PLAIN_MESSAGE,
              null,
              uniqueIDs,
              uniqueIDs[currentIndex]);

      //update current index to draw snapshot
      currentIndex = java.util.Arrays.asList(uniqueIDs).indexOf(selection);

      // selection is selected draw that snapshot
      if ((selection != null) && (selection.length() > 0)) {
        middlePanel.draw(snapshots.get(currentIndex).getListShape());
      }

      // when quit is clicked
    } else if (e.getSource() == quit) {
      JOptionPane.showMessageDialog(this,
              "Bye Bye");
      System.exit(0);
    }
  }

}
