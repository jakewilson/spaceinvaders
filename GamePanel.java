import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
  
  public GamePanel(boolean debug) {
    super();
    hero      = new Ship(new Color(255, 154, 0)); // make the ship orange
    wave      = new Wave(22); // TODO: the size should be a variable depending on the level
    debugMode = debug;
    this.setBackground(Color.BLACK);
    this.setFocusable(true);
    this.addKeyListener(new ShipListener(hero));
  }
  
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    hero.draw(g);
    wave.draw(g);
    if (debugMode) drawGrid(g);
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

}

/**
 * KeyListener class for moving the ship and other in-game actions.
 */
class ShipListener implements KeyListener {
  
  Ship ship;
  
  public ShipListener(Ship s) {
    ship = s;
  }

  @Override
  public void keyTyped(KeyEvent ke) {
    
  }

  public void keyPressed(KeyEvent ke) {
    switch (ke.getExtendedKeyCode()) {
    case KeyEvent.VK_RIGHT: // move the ship to the right
      ship.move(ship.DIRECTION_RIGHT);
      break;
    case KeyEvent.VK_LEFT: // move the ship to the left
      ship.move(ship.DIRECTION_LEFT);
      break;
    case KeyEvent.VK_SPACE:
      ship.fireLaser();
      break;
    }
  }

  @Override
  public void keyReleased(KeyEvent ke) {
    
  }
  
}
