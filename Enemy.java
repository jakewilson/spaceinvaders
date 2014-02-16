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

  public Enemy(Color c, int x, int y) {
    color   = c;
    centerX = x;
    centerY = y;
  }
  
  public void draw(Graphics g) {
    g.setColor(color);
    g.fillOval(centerX, centerY, 20, 20);
  }
  
}
