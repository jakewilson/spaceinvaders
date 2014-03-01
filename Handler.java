import java.awt.Color;

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
  public static final int FRAME_HEIGHT = 400;
  
  // approximate sizes of borders. The borders of the windows prevent the panel from being the same
  // size as the FRAME_WIDTH and FRAME_HEIGHT, so we have to add the size of the borders to ensure
  // FRAME_WIDTH and FRAME_HEIGHT are the same as the panels.
  // TODO: this solution is OS specific. Develop a better solution
  public static final int BORDER_WIDTH  = 18;
  public static final int BORDER_HEIGHT = 38;
  
  public static void main(String[] args) {
    loadGame();
    runGame();
  }
  
  /**
   * Runs the main game loop
   */
  private static void runGame() {
    gameFrame.setVisible(true);
    int counter = 0; // loop counter
    while (true) { // game loop
      gamePanel.repaint(); // redraw the screen
      Ship hero   = gamePanel.getHero();
      Wave wave   = gamePanel.getWave();
      Laser laser = hero.getLaser();
      
      // TODO: the '500000' needs to be a variable dependent on how many enemies
      //       are left on the screen. The less enemies are left, the faster they move,
      //       which means the counter should have less and less value
      if (!gamePanel.isPaused()) { // only move the enemies if the game is not paused
        if (counter++ % 500000 == 0)
          wave.advance();
      }
      
      
      // check if the laser has collided with an enemy
      for (int i = 0; i < wave.length(); i++) {
        // if the laser has hit an enemy
        Enemy e = wave.getEnemyAt(i);
        if (detectCollision(laser, e)) {
          if (gamePanel.getDebugMode()) {
            System.out.printf("Laser tip: (%d, %d)\n", laser.getTipX(), laser.getTipY());
            System.out.printf("Enemy corner: (%d, %d) l: %d\n", e.getCornerX(), e.getCornerY(), e.getLength());
          }
          wave.removeEnemyAt(i);
          hero.returnLaser();
        }
      }
      
      if (wave.isEmpty()) {
        break;
      }
      
    }
  }
  
  
  /**
   * Initializes game objects
   */
  private static void loadGame() {
    gamePanel = new GamePanel();
    gameFrame = new JFrame("Space Invaders");
    gameFrame.setSize(FRAME_WIDTH + BORDER_WIDTH, FRAME_HEIGHT + BORDER_HEIGHT);
    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameFrame.setLocationRelativeTo(null);
    gameFrame.add(gamePanel);
  }
  
  /**
   * Detects collisions between a laser and an enemy
   * @param l the laser
   * @param e the enemy
   * @return whether they've collided
   */
  private static boolean detectCollision(Laser l, Enemy e) {
    int x = e.getCornerX(), y = e.getCornerY(), len = e.getLength();
    if ((x <= l.getTipX() && x + len >= l.getTipX()) &&
        (y <= l.getTipY() && y + len >= l.getTipY())) {
      return true;
    }
    
    return false;
  }
  
  /**
   * Detects collisions between a laser and a ship
   * @param l the laser
   * @param s the ship
   * @return whether they've collided
   */
  private static boolean detectCollision(Laser l, Ship s) {
    return true;
    //TODO: implement this fully
  }

}
