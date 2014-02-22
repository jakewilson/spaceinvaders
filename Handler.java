import javax.swing.JFrame;


/**
 * Handler.java
 * This class is responsible for upper-level game mechanisms.
 * Handler draws the game window, adds the panel, runs the game loop,
 * and detects collisions between game objects.
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
    while (true) { // game loop
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
  
  /**
   * Detects collisions between two game objects.
   * @param x - the first game object
   * @param y - the second game object
   * @return whether they've collided
   */
  private static boolean detectCollision(GameObject x, GameObject y) {
    return true;
  }

}
