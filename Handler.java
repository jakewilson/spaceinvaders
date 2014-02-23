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
  public static final int FRAME_HEIGHT = 600;
  
  public static final boolean DEBUG = false;
  
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
      Ship hero   = gamePanel.getHero();
      Wave wave   = gamePanel.getWave();
      Laser laser = hero.getLaser();
      
      // check if the laser has collided with an enemy
      for (int i = 0; i < wave.length(); i++) {
        // if the laser has hit an enemy
        Enemy e = wave.getEnemyAt(i);
        if (detectCollision(laser, e)) {
          if (DEBUG) {
            System.out.printf("Laser tip: (%d, %d)\n", laser.getTipX(), laser.getTipY());
            System.out.printf("Enemy center: (%d, %d) r: %d\n", e.getCenterX(), e.getCenterY(), e.getRadius());
          }
          wave.removeEnemyAt(i);
          hero.laserHasCollided();
        }
        e.changeColor(Color.GREEN);
      }
      
    }
  }
  
  
  /**
   * Initializes game objects
   */
  private static void loadGame() {
    gamePanel = new GamePanel(DEBUG);
    gameFrame = new JFrame("Space Invaders");
    gameFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameFrame.setLocationRelativeTo(null);
    gameFrame.add(gamePanel);
  }
  
  /**
   * Detects collisions between a laser and an enemy
   * @param l - the laser
   * @param e - the enemy
   * @return whether they've collided
   */
  private static boolean detectCollision(Laser l, Enemy e) {
    if ((e.getCenterY() + e.getRadius() >= l.getTipY()) &&
        (e.getCenterY() - e.getRadius() <= l.getTipY()) &&
        (e.getCenterX() + e.getRadius() >= l.getTipX()) &&
        (e.getCenterX() - e.getRadius() <= l.getTipX())) {
      if (DEBUG) e.changeColor(Color.BLUE);
      return true;
    }
    
    return false;
  }

}
