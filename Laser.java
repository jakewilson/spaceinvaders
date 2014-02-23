import java.awt.Color;
import java.awt.Graphics;


/**
 * Laser.java
 * Laser projectile used by the ship.
 *
 * @author  Jake
 * @version Feb 20, 2014
 */
public class Laser implements GameObject {
  
  Color color;
  int   origX, origY;
  int   length;
  
  public Laser(Color c, int x, int y, int len) {
    color  = c;
    origX  = x;
    origY  = y;
    length = len;
  }
  
  public Laser(int x, int y, int len) {
    this(Color.RED, x, y, len);
  }
  
  public Laser(int x, int y) {
    this(Color.RED, x, y, 20);
  }
  
  
  /**
   * Draws the laser and decrements the y value, so everytime the laser is drawn it is
   * higher than the last time it was drawn
   * @param g - The graphics context
   */
  public void draw(Graphics g) {
    g.setColor(color);
    g.drawLine(origX, origY, getTipX(), getTipY());
    origY--; // move the laser vertically up the screen
  }
  
  /**
   * Determines whether the laser is off the screen
   * @return origY < 0 (the laser is not on the screen)
   */
  public boolean isOffScreen() {
    return origY < 0;
  }
  
  /**
   * Moves the laser off the screen
   */
  public void moveOffScreen() {
    this.setLocation(-50, -50);
  }
  
  /**
   * @return the y value of the lasers tip (origY - len)
   */
  public int getTipY() {
    return origY - length;
  }
  
  /**
   * @return the x value of the lasers tip (same as origX)
   */
  public int getTipX() {
    return origX;
  }
  
  /**
   * @return the length of the laser
   */
  public int getLength() {
    return length;
  }
  
  /**
   * Sets the location of the laser
   * @param x - the new x value of the laser
   * @param y - the new y value of the laser
   */
  public void setLocation(int x, int y) {
    origX = x;
    origY = y;
  }

}
