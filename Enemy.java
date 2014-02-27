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
  
  public static final int DIRECTION_DOWN  = 0;
  public static final int DIRECTION_LEFT  = 1;
  public static final int DIRECTION_RIGHT = 2;
  
  private static int nextDirection; // the next direction for the enemy to move
  private static int speed; // the speed at which the enemy moves

  public Enemy(Color c, int x, int y) {
    color         = c;
    cornerX       = x;
    cornerY       = y;
    length        = 20;
    nextDirection = DIRECTION_LEFT;
    speed         = 5;
  }
  
  public Enemy(int x, int y) {
    this(Color.GREEN, x, y);
  }
  
  /**
   * Draws an enemy on the screen
   * @param g - The graphics context to draw on
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
   * Moves the enemy. If the enemy is moving right or left and still has
   * room, DIRECTION_RIGHT or DIRECTION_LEFT will be returned. If an enemy has
   * reached the wall, DIRECTION_DOWN will be returned, which tells Wave.advance()
   * to begin moving down. DIRECTION_DOWN always immediately returns RIGHT or LEFT,
   * since it always moves down just once, depending on whether it moved right or left last time.
   * NOTE: this function should never be called. It is called in Wave.advance().
   * 
   * @param direction - the direction in which to move
   * @return the direction in which to move next
   */
  public int move(int direction) {
    switch (direction) {
    case DIRECTION_RIGHT:
      if ((cornerX + length) + speed < Handler.FRAME_WIDTH) {
        cornerX += speed;
        return DIRECTION_RIGHT;
      }
      return DIRECTION_DOWN;
    case DIRECTION_LEFT:
      if (cornerX - speed > 0) {
        cornerX -= speed;
        return DIRECTION_LEFT;
      }
      return DIRECTION_DOWN;
    case DIRECTION_DOWN:
      if ((cornerY + length) + speed < Handler.FRAME_HEIGHT) {
        cornerY += speed;
        return DIRECTION_DOWN;
      }
      return DIRECTION_DOWN;
    default: // invalid direction (should never occur)
      System.out.println("Invalid direction: " + direction);
      return -1;
    }
  }
  
  /**
   * Returns the direction to move next, and then changes it so it will be in the 
   * opposite direction next time.
   * @return the direction in which to move next
   */
  public static int nextDirection() {
    if (nextDirection == DIRECTION_RIGHT) {
      nextDirection = DIRECTION_LEFT;
      return DIRECTION_RIGHT;
    } else if (nextDirection == DIRECTION_LEFT) {
      nextDirection = DIRECTION_RIGHT;
      return DIRECTION_LEFT;
    } else // should never occur
      return -1;
  }
  
  /**
   * Changes the color of an enemy
   * @param c - the new color
   */
  public void changeColor(Color c) {
    color = c;
  }
  
}
