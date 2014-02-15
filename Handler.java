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
  
  public static final int frameWidth  = 600;
  public static final int frameHeight = 600;
  
  public static void main(String[] args) {
    loadGame();
    runGame();
  }
  
  private static void runGame() {
    gameFrame.setVisible(true);
    while (true) {
      gamePanel.repaint();
    }
  }
  
  
  // initializes game objects
  private static void loadGame() {
    gamePanel = new GamePanel();
    gameFrame = new JFrame("Space Invaders");
    gameFrame.setSize(frameWidth, frameHeight);
    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameFrame.setLocationRelativeTo(null);
    gameFrame.add(gamePanel);
  }

}
