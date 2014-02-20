import javax.swing.JFrame;


/**
 * Handler.java
 * This class is responsible for drawing the window and adding the game panel.
 *
 * @author  Jake
 * @version Feb 15, 2014
 */
public class Handler {
  
  private static GamePanel gamePanel;
  private static JFrame    gameFrame;
  
  public static final int FRAME_WIDTH  = 600;
  public static final int FRAME_HEIGHT = 600;
  
  public static void main(String[] args) {
    loadGame();
    runGame();
  }
  
  /**
   * Runs the main game loop
   */
  private static void runGame() {
    gameFrame.setVisible(true);
    while (true) {
      gamePanel.repaint(); // redraw the screen
    }
  }
  
  
  /**
   * Initializes game objects
   */
  private static void loadGame() {
    gamePanel = new GamePanel();
    gameFrame = new JFrame("Space Invaders");
    gameFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameFrame.setLocationRelativeTo(null);
    gameFrame.add(gamePanel);
  }

}
