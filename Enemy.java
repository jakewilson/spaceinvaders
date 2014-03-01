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
  
  private static int nextDirection; // the next direction for the enemy to move
  private static int speed; // the speed at which the enemy moves

  public Enemy(Color c, int x, int y) {
    color         = c;
    cornerX       = x;
    cornerY       = y;
    length        = 20;
    nextDirection = DIRECTION_RIGHT;
    speed         = 10;
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
   * Moves an enemy
   */
  public void move() {
    // TODO: implement functionality here
  }
  
  /**
   * Returns the direction to move next, and then changes it so it will be in the 
   * opposite direction next time.
   * @return the direction in which to move next
   */
  public static int nextDirection() {
//    if (nextDirection == DIRECTION_RIGHT) {
//      nextDirection = DIRECTION_LEFT;
//      return DIRECTION_RIGHT;
//    } else if (nextDirection == DIRECTION_LEFT) {
//      nextDirection = DIRECTION_RIGHT;
//      return DIRECTION_LEFT;
//    } else // should never occur
//      return -1;
    return ++nextDirection % 3;
  }
  
  /**
   * Changes the color of an enemy
   * @param c - the new color
   */
  public void changeColor(Color c) {
    color = c;
  }
  
}
