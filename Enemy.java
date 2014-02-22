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
  private int centerX, centerY;
  private int radius;
  
  public static final int DIRECTION_DOWN  = 1;
  public static final int DIRECTION_LEFT  = -1;
  public static final int DIRECTION_RIGHT = 1;

  public Enemy(Color c, int x, int y) {
    color   = c;
    centerX = x;
    centerY = y;
    radius  = 20;
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
    g.fillOval(centerX, centerY, 20, 20);
  }
  
  /**
   * @return the radius of the enemy
   */
  public int getRadius() {
    return radius;
  }
  
  /**
   * @return the x coordinate of the enemy's center
   */
  public int getCenterX() {
    return centerX;
  }
  
  /**
   * @return the y coordinate of the enemy's center
   */
  public int getCenterY() {
    return centerY;
  }
  
  /**
   * Moves the enemy
   * @param direction - the direction in which to move
   */
  public void move(int direction) {
    // TODO: add functionality here
  }
  
}
