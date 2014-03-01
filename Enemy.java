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
   * Moves an enemy
   */
  public void move() {
    // TODO: implement functionality here
  }

  
  /**
   * Changes the color of an enemy
   * @param c the new color
   */
  public void changeColor(Color c) {
    color = c;
  }
  
}
