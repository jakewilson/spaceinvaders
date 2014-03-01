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
  
  private Color color;
  private int   origX, origY;
  private int   tipX,  tipY;
  private int   length;
  
  private int orientation; // the orientation in which to draw/move the laser
  
  // possible orientations to draw and move the laser
  public static final int ORIENT_DOWN = 0;
  public static final int ORIENT_UP   = 1;
  
  /**
   * Sets color, x and y coordinates, length, and orient to the parameters
   * @param c the color of the laser
   * @param x the x coordinate of the laser
   * @param y the y coordinate of the laser
   * @param len the length of the laser
   * @param orient the orientation of the laser (either ORIENT_UP or ORIENT_DOWN)
   */
  public Laser(Color c, int x, int y, int len, int orient) {
    color       = c;
    origX       = x;
    origY       = y;
    length      = len;
    setOrient(orient);
    assignVertices();
  }
  
  /**
   * No arg constructor. Defaults the color to RED, length to 10, orientation
   * to ORIENT_DOWN, and moves the laser off the screen
   */
  public Laser() {
    color       = Color.RED;
    length      = 10;
    orientation = ORIENT_DOWN;
    this.moveOffScreen();
  }
  
  /**
   * Constructs a new laser with color c, length len, and orientation orient.
   * Defaults the location to off the screen.
   * @param c the color of the laser
   * @param len the length of the laser
   * @param orient the orientation of the laser
   */
  public Laser(Color c, int len, int orient) {
    color  = c;
    length = len;
    this.moveOffScreen();
    setOrient(orient);
  }
  
  /**
   * Constructs a new laser with location (x, y) and length len.
   * Defaults color to RED and orientation to ORIENT_DOWN
   * @param x the x coordinate of the laser
   * @param y the y coordinate of the laser
   * @param len the length of the laser
   */
  public Laser(int x, int y, int len) {
    this(Color.RED, x, y, len, ORIENT_DOWN);
  }
  
  /**
   * Constructs a new laser with location (x, y). Sets color to RED, length to 20,
   * and orientation to ORIENT_DOWN
   * @param x the x coordinate of the laser
   * @param y the y coordinate of the laser
   */
  public Laser(int x, int y) {
    this(Color.RED, x, y, 20, ORIENT_DOWN);
  }
  
  
  /**
   * Draws the laser and decrements the y value, so every time the laser is drawn it is
   * higher than the last time it was drawn
   * @param g The graphics context
   */
  public void draw(Graphics g) {
    g.setColor(color);
    g.drawLine(origX, origY, getTipX(), getTipY());
     if (orientation == ORIENT_DOWN) {
       origY++;
     } else if (orientation == ORIENT_UP) {
       origY--; 
     }
  }
  
  /**
   * Determines whether the laser is off the screen
   * @return origY < 0 (the laser is not on the screen)
   */
  public boolean isOffScreen() {
    return origY < 0 || origY > Handler.FRAME_HEIGHT;
  }
  
  /**
   * Moves the laser off the screen
   */
  public void moveOffScreen() {
    this.setLocation(-50, -50);
    this.assignVertices();
  }
  
  /**
   * Sets the orientation of the laser (either UP or DOWN)
   * @param o the new orientation of the laser
   */
  public void setOrient(int o) {
    if (o != ORIENT_DOWN && o != ORIENT_UP)
      orientation = ORIENT_DOWN;
    else
      orientation = o;
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
   * Assigns the tip location depending on the origX and origY and the orientation
   */
  private void assignVertices() {
    tipX = origX;
    if (orientation == ORIENT_UP) {
      tipY = origY - length;
    } else if (orientation == ORIENT_DOWN) {
      tipY = origY + length;
    }
  }
  
  /**
   * Sets the location of the laser
   * @param x the new x value of the laser
   * @param y the new y value of the laser
   */
  public void setLocation(int x, int y) {
    origX = x;
    origY = y;
  }

}
