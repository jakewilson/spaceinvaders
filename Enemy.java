import java.awt.Color;
import java.awt.Graphics;


/**
 * Enemy.java
 * The enemy that the ship is fighting
 *
 * @author  Jake
 * @version Feb 15, 2014
 */
public class Enemy implements GameObject {
  
  private Color color;
  private Laser laser;
  
  private float cornerX, cornerY; // coordinates of the top left corner of the enemy
  private float length;
  
  public static final int DIRECTION_RIGHT = 0;
  public static final int DIRECTION_DOWN  = 1;
  public static final int DIRECTION_LEFT  = 2;
  
  private int speed;  // the speed at which the enemy moves
  
  private boolean shotFired;

  public Enemy(Color c, int x, int y) {
    color   = c;
    cornerX = x;
    cornerY = y;
    length  = 20;
    speed   = 3;
    laser   = new Laser(new Color(0, 150, 255), 10, Laser.ORIENT_DOWN, 0.15f);
  }
  
  public Enemy(int x, int y) {
    this(Color.GREEN, x, y);
  }
  
  /**
   * Draws an enemy on the screen
   * @param g The graphics context to draw on
   */
  public void draw(Graphics g) {
    g.setColor(color);
    g.fillOval((int)cornerX, (int)cornerY, (int)length, (int)length);
    if (shotFired) {
      laser.draw(g);
      if (laser.isOffScreen())
        shotFired = false;
    }
  }
  
  /**
   * @return the side length of the enemy
   */
  public float getLength() {
    return length;
  }
  
  /**
   * @return the x coordinate of the enemy's top left corner
   */
  public float getCornerX() {
    return cornerX;
  }
  
  /**
   * @return the y coordinate of the enemy's top left corner
   */
  public float getCornerY() {
    return cornerY;
  }
  
  /**
   * Determines if an enemy has room to move in the specified direction
   * @param direction the direction in which to check
   * @return whether the enemy has room in <code>direction</code>
   */
  public boolean hasRoom(int direction) {
    switch (direction) {
    case DIRECTION_RIGHT:
      return (((cornerX + length) + speed) < Handler.FRAME_WIDTH - 10);
    case DIRECTION_LEFT:
      return ((cornerX - speed) > 10);
    case DIRECTION_DOWN:
      return (((cornerY + length) + speed) < Handler.FRAME_HEIGHT - 10);
    default: // invalid direction (should not occur)
      return false;  
    }
  }
  
  /**
   * Moves an enemy. Will not move if the enemy doesn't have room
   */
  public void move(int direction) {
    if (!this.hasRoom(direction)) return; // if we don't have room, don't move
    
    switch (direction) {
    case DIRECTION_RIGHT:
      cornerX += speed;
      break;
    case DIRECTION_LEFT:
      cornerX -= speed;
      break;
    case DIRECTION_DOWN:
      cornerY += speed;
      break;
    default: // should be impossible, since hasRoom() will return false if not right, left, or down
      break;
    }
    
  }
  
  /**
   * Fires a laser from an enemy
   */
  public void fireLaser() {
    if (laser.isOffScreen()) {
      laser.setLocation(cornerX + (length / 2), cornerY + length);
      shotFired = true;
    }
  }
  
  /**
   * Changes the color of an enemy
   * @param c the new color
   */
  public void changeColor(Color c) {
    color = c;
  }
  
}
