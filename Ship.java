import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;


/**
 * Ship.java
 * The ship that the user plays as.
 *
 * @author  Jake
 * @version Feb 15, 2014
 */
public class Ship {
  
  private Color color;
  private int   xLoc, yLoc;
  
  public final int DIRECTION_RIGHT = 10;
  public final int DIRECTION_LEFT  = -10;
  
  public Ship(Color c, int x, int y) {
    color = c;
    xLoc  = x;
    yLoc  = y;
  }
  
  public void draw(Graphics g) {
    g.setColor(color);
    int[] xPoints = {xLoc, xLoc + 10, xLoc + 20};
    int[] yPoints = {yLoc, yLoc - 20, yLoc};
    Polygon p = new Polygon(xPoints, yPoints, 3);
    g.drawPolygon(p);
  }
  
  public void move(int direction) {
    xLoc += direction;
  }

}
