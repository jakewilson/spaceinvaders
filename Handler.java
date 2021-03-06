import javax.swing.JFrame;


/**
 * Handler.java
 * This class is responsible for upper-level game mechanisms.
 * Handler draws the game window, adds the panel, runs the game loop,
 * and detects collisions between game objects.
 *
 * @author  Jake Wilson
 * @version Feb 15, 2014
 */
public class Handler {
  
  private static GamePanel gamePanel;
  private static JFrame    gameFrame;
  
  public static final int FRAME_WIDTH  = 600;
  public static final int FRAME_HEIGHT = 500;
  
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
   * TODO: this could be made much more efficient I think by only repainting whenever the wave advances, or the enemy/laser is moved.
   *       All other calls to repaint are superfluous
   */
  private static void runGame() {
    int counter = 0; // loop counter
    while (gamePanel.keepRunning()) { // game loop
      gamePanel.repaint(); // redraw the screen
      gamePanel.updateScore();
      Ship hero    = gamePanel.getHero();
      Wave wave    = gamePanel.getWave();
      Laser hLaser = hero.getLaser();
      
      if (!gamePanel.isPaused()) { // only move the enemies if the game is not paused
        if (counter % (30000 + (wave.amountOfEnemiesAlive() * 10000)) == 0) {
          wave.advance();
        }
        
        if (counter++ % 500000 == 0) {
          wave.fire(); // fire lasers from enemies in the front randomly
        }
        
        // check if the laser has collided with an enemy
        for (int i = 0; i < wave.length(); i++) {
          // if the laser has hit an enemy
          Enemy e = wave.getEnemyAt(i);
          
          if (e != null) {
            if (detectCollision(hLaser, e)) { // check for collisions between the enemies and ships laser
              if (gamePanel.getDebugMode()) {
                System.out.printf("Laser tip: (%f, %f)\n", hLaser.getTipX(), hLaser.getTipY());
                System.out.printf("Enemy corner: (%f, %f) l: %f\n", e.getCornerX(), e.getCornerY(), e.getLength());
              }
              wave.killEnemyAt(i);
              hero.returnLaser();
            }
            
            // check for collisions between the enemies lasers with the ship and the ship with the enemies
            if (detectCollision(e.getLaser(), hero) || detectCollision(hero, e)) {
              hero.kill();
            }
          }
          
        }
        
        if (wave.amountOfEnemiesAlive() == 0) {
          gamePanel.setGameWon(true);
        }
      } // end if (gamePanel.isPaused())
      
    }
    
    gameFrame.dispose(); // clean up and stop execution
  }
  
  
  /**
   * Initializes game objects
   */
  private static void loadGame() {
    gamePanel = new GamePanel();
    gameFrame = new JFrame("Space Invaders");
    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameFrame.add(gamePanel);
    gameFrame.setSize(FRAME_WIDTH + BORDER_WIDTH, FRAME_HEIGHT + BORDER_HEIGHT);
    gameFrame.setLocationRelativeTo(null);
    gameFrame.validate();
    gameFrame.setVisible(true);
    gamePanel.repaint();
    int uselessCounter = 0;
    // TODO: ask on SO if this is due to compiler optimization
    while (!gamePanel.gameHasStarted() && ++uselessCounter >= 0); // wait until the game has started to run it
  }
  
  /**
   * Detects collisions between a laser and an enemy
   * @param l the laser
   * @param e the enemy
   * @return whether they've collided
   */
  private static boolean detectCollision(Laser l, Enemy e) {
    float x = e.getCornerX(), y = e.getCornerY(), len = e.getLength();
    
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
    float lX = s.getLeftX() , lY = s.getLeftY();
    float rX = s.getRightX(), rY = s.getRightY();
    float tX = s.getTopX()  , tY = s.getTopY();
    
    if ((lX <= l.getTipX() && rX >= l.getTipX()) &&
        (tY <= l.getTipY() && lY >= l.getTipY())) {
      return true;
    }
    
    return false;
  }
  
  /**
   * Detects a collision between a ship and an enemy
   * @param s the ship
   * @param e the enemy
   * @return whether they've collided
   */
  private static boolean detectCollision(Ship s, Enemy e) {
    float lX  = s.getLeftX()  , lY = s.getLeftY();
    float rX  = s.getRightX() , rY = s.getRightY();
    float tX  = s.getTopX()   , tY = s.getTopY();
    float eX  = e.getCornerX(), eY = e.getCornerY();
    float len = e.getLength();
    
    if ((eX >= lX && (eX <= rX)) &&
        ((eY + len) >= tY && (eY + len) <= lY)) {
      System.out.println("Enemy hit ship");
      return true;
    }
    
    return false;
  }

}
