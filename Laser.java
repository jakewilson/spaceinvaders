import java.awt.Color;
import java.awt.Graphics;


/**
 * Laser.java
 * Laser projectile used by the ship
 *
 * @author  Jake
 * @version Feb 20, 2014
 */
public class Laser {
  
  Color color;
  int   origX, origY;
  int   length;
  
  public Laser(Color c, int x, int y, int len) {
    color  = c;
    origX  = x;
    origY  = y;
    length = len;
  }
  
  /**
   * Draws the laser and decrements the y value, so everytime the laser is drawn it is
   * higher than the last time it was drawn
   * @param g - The graphics context
   */
  public void draw(Graphics g) {
    g.setColor(color);
    g.drawLine(origX, origY, origX, origY - length);
    origY--; // move the laser vertically up the screen
  }
  
  /**
   * Determines whether the laser is off the screen
   * @return origY < 0 (the laser is not on the screen)
   */
  public boolean isOffScreen() {
    return origY < 0;
  }

}
