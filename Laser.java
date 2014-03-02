import java.awt.Color;
import java.awt.Graphics;


/**
 * Laser.java
 * Laser projectile used by the ship.
 *
 * @author  Jake Wilson
 * @version Feb 20, 2014
 */
public class Laser implements GameObject {
  
  private Color  color;
  private float origX, origY;
  private float tipX,  tipY;
  private int    length;
  
  private int orientation; // the orientation in which to draw/move the laser
  
  // possible orientations to draw and move the laser
  public static final int ORIENT_UP   = 0;
  public static final int ORIENT_DOWN = 1;
  
  private float speed;
  
  /**
   * Constructs a new laser with color c, length len, and orientation orient.
   * Defaults the location to off the screen.
   * @param c the color of the laser
   * @param len the length of the laser
   * @param orient the orientation of the laser
   * @param s the speed of the laser
   */
  public Laser(Color c, int len, int orient, float s) {
    color  = c;
    length = len;
    setOrient(orient);
    speed = s;
    this.moveOffScreen();
  }
  
  /**
   * No arg constructor. Defaults the color to RED, length to 10, orientation
   * to ORIENT_DOWN, and moves the laser off the screen
   */
  public Laser() {
    this(Color.RED, 10, ORIENT_DOWN, 0.2f);
  }
  
  /**
   * Draws the laser and decrements the y value, so every time the laser is drawn it is
   * higher than the last time it was drawn
   * @param g The graphics context
   */
  public void draw(Graphics g) {
    g.setColor(color);
    g.drawLine((int)origX, (int)origY, (int)tipX, (int)tipY);
    this.move();
  }
  
  /**
   * Moves a laser in the direction of it's orientation
   */
  public void move() {
    if (orientation == ORIENT_UP) {
      this.setLocation(origX, origY - speed);
    } else if (orientation == ORIENT_DOWN) {
      this.setLocation(origX, origY + speed);
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
  public float getTipY() {
    return tipY;
  }
  
  /**
   * @return the x value of the lasers tip (same as origX)
   */
  public float getTipX() {
    return tipX;
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
  public void setLocation(float x, float y) {
    origX = x;
    origY = y;
    assignVertices();
  }

}
