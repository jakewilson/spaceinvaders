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
  private int cornerX, cornerY; // coordinates of the top left corner of the enemy
  private int length;
  
  public static final int DIRECTION_RIGHT = 0;
  public static final int DIRECTION_DOWN  = 1;
  public static final int DIRECTION_LEFT  = 2;
  
  private static int speed;         // the speed at which the enemy moves

  public Enemy(Color c, int x, int y) {
    color         = c;
    cornerX       = x;
    cornerY       = y;
    length        = 20;
    speed         = 10;
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
    g.fillOval(cornerX, cornerY, length, length);
  }
  
  /**
   * @return the side length of the enemy
   */
  public int getLength() {
    return length;
  }
  
  /**
   * @return the x coordinate of the enemy's top left corner
   */
  public int getCornerX() {
    return cornerX;
  }
  
  /**
   * @return the y coordinate of the enemy's top left corner
   */
  public int getCornerY() {
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
   * Changes the color of an enemy
   * @param c the new color
   */
  public void changeColor(Color c) {
    color = c;
  }
  
}
