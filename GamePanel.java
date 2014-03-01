import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;

import javax.swing.JPanel;


/**
 * GamePanel.java
 * This class is responsible for drawing the game features and handling any button presses.
 *
 * @author  Jake
 * @version Feb 15, 2014
 */
public class GamePanel extends JPanel {
  
  private Ship hero; // the ship the user will play as
  private Wave wave; // the wave of enemies the user will fight
  
  private boolean debugMode;
  private boolean gamePaused;
  
  public GamePanel() {
    super();
    hero      = new Ship(new Color(255, 154, 0)); // make the ship orange
    wave      = new Wave(22); // TODO: the size should be a variable depending on the level
    debugMode = false;
    this.setBackground(Color.BLACK);
    this.setFocusable(true);
    this.addKeyListener(new ShipListener(hero, this));
  }
  
  protected void paintComponent(Graphics g) {
    if (!gamePaused) {
      super.paintComponent(g);
      if (debugMode) {
        drawGrid(g);
        wave.draw(g, true);
      } else {
        wave.draw(g);
      }
      hero.draw(g);
    } else { // display pause menu
      g.setColor(Color.WHITE);
      g.setFont(new Font("Courier New", Font.PLAIN, 14));
      displayPauseMenu(g);
    }

  }
  
  /**
   * Draws the pause menu on the screen
   * @param g the graphics context to draw on
   */
  private void displayPauseMenu(Graphics g) {
    String debugString = "";
    g.drawString("Game Paused", Handler.FRAME_WIDTH / 2, Handler.FRAME_HEIGHT / 2 - 60);
    g.drawString("[p] To Unpause", Handler.FRAME_WIDTH / 2, Handler.FRAME_HEIGHT / 2 - 40);
    if (debugMode) {
      debugString = "[d] To Leave Debug Mode";
    } else {
      debugString = "[d] To Enter Debug Mode";
    }
    g.drawString(debugString, Handler.FRAME_WIDTH / 2, Handler.FRAME_HEIGHT / 2 - 20);
  }
  
  /**
   * Draws a grid on the screen for debugging purposes
   */
  private void drawGrid(Graphics g) {
    int i = 20;
    while (i < Handler.FRAME_WIDTH) {
      g.setFont(new Font("Monospace", Font.PLAIN, 9));
      g.setColor(Color.RED);
      g.drawString(i+"", i+2, 15);
      g.drawString(i+"", 5, i-2);
      g.setColor(Color.GRAY);
      g.drawLine(i, 0, i, Handler.FRAME_HEIGHT);
      g.drawLine(0, i, Handler.FRAME_WIDTH, i);
      i += 20;
    }
  }
  
  /**
   * @return the Wave of enemies
   */
  public Wave getWave() {
    return wave;
  }
  
  /**
   * @return the ship (hero)
   */
  public Ship getHero() {
    return hero;
  }
  
  /**
   * @return debugMode
   */
  public boolean getDebugMode() {
    return debugMode;
  }
  
  /**
   * Sets the debugMode
   * @param mode the new debugMode
   */
  public void toggleDebugMode() {
    debugMode = !debugMode;
  }
  
  /**
   * Toggles the pause state. If the game is paused, it is unpaused and vice versa
   */
  public void togglePause() {
    gamePaused = !gamePaused;
  }
  
  /**
   * @return whether the game is paused
   */
  public boolean isPaused() {
    return gamePaused;
  }

}

/**
 * KeyListener class for moving the ship and other in-game actions.
 */
class ShipListener implements KeyListener {
  
  Ship      ship;
  GamePanel panel;
  
  public ShipListener(Ship s, GamePanel gp) {
    ship  = s;
    panel = gp;
  }

  @Override
  public void keyTyped(KeyEvent ke) {
    
  }

  public void keyPressed(KeyEvent ke) {
    switch (ke.getExtendedKeyCode()) {
    case KeyEvent.VK_RIGHT: // move the ship to the right
      if (!panel.isPaused()) {
        ship.move(ship.DIRECTION_RIGHT);
      }
      break;
    case KeyEvent.VK_LEFT: // move the ship to the left
      if (!panel.isPaused()) {
        ship.move(ship.DIRECTION_LEFT);
      }
      break;
    case KeyEvent.VK_SPACE: // fire the laser
      if (!panel.isPaused()) {
        ship.fireLaser();
      }
      break;
    case KeyEvent.VK_P:  // pause the game
      panel.togglePause();
      break;
    case KeyEvent.VK_D:
      if (panel.isPaused()) { // enter debug mode
        panel.toggleDebugMode();
      }
    }
  }

  @Override
  public void keyReleased(KeyEvent ke) {
    
  }
  
}
