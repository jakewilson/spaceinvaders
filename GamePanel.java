import java.awt.Color;
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
  
  private Ship    hero;
  private Wave wave;
  
  public GamePanel() {
    super();
    hero = new Ship(new Color(255, 154, 0)); // make the ship orange
    wave = new Wave(22); // TODO: the size should be a variable depending on the leve
    this.setBackground(Color.BLACK);
    this.setFocusable(true);
    this.addKeyListener(new ShipListener(hero));
  }
  
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    hero.draw(g);
    wave.draw(g);
  }
  
  public Wave getWave() {
    return wave;
  }
  
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
  public void keyTyped(KeyEvent e) {
    
  }
  
  // moves the ship  
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
  public void keyReleased(KeyEvent e) {

  }
  
}
