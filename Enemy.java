import java.awt.Color;
import java.awt.Graphics;


/**
 * Enemy.java
 *
 * @author  Jake
 * @version Feb 15, 2014
 */
public class Enemy {
  
  private Color color;
  private int centerX, centerY;
  
  public static final int DIRECTION_DOWN  = 1;
  public static final int DIRECTION_LEFT  = -1;
  public static final int DIRECTION_RIGHT = 1;

  public Enemy(Color c, int x, int y) {
    color   = c;
    centerX = x;
    centerY = y;
  }
  
  public void draw(Graphics g) {
    g.setColor(color);
    g.fillOval(centerX, centerY, 20, 20);
  }
  
  public void move(int direction) {
    
  }
  
}
