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
  
  private Ship hero;
  
  public GamePanel() {
    super();
    hero = new Ship(new Color(255, 154, 0)); // make the ship orange
    this.setBackground(Color.BLACK);
    this.setFocusable(true);
    this.addKeyListener(new ShipListener(hero));
  }
  
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    hero.draw(g);
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
      System.out.println("Fire!!");
      ship.fireLaser();
      break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
  
}
