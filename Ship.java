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
  
  private int rightX, rightY; // coordinates of the right vertex of the triangle
  private int leftX , leftY;  // coordinates of the left  vertex of the triangle
  private int topX  , topY;   // coordinates of the top   vertex of the triangle
  
  public final int DIRECTION_RIGHT = 7;
  public final int DIRECTION_LEFT  = -7;
  
  public Ship(Color c, int x, int y) {
    color = c;
    leftX   = x;
    leftY   = y;
    assignVertices();
  }
  
  public Ship(Color c) {
    color = c;
    this.goToStartingPoint();
  }
  
  public void draw(Graphics g) {
    g.setColor(color);
    int[] xPoints = {leftX, topX, rightX};
    int[] yPoints = {leftY, topY, rightY};
    Polygon p = new Polygon(xPoints, yPoints, 3);
    g.drawPolygon(p);
  }
  
  public void move(int direction) {
    if (rightX + direction < Handler.frameWidth - 30 && leftX + direction > 10) {
      leftX += direction;
      assignVertices();
    }
  }
  
  public void goToStartingPoint() {
    leftX = 290;
    leftY = 550;
    assignVertices();
  }
  
  // assigns the right and top coordinates based on the left one
  private void assignVertices() {
    rightX  = leftX + 20;
    rightY  = leftY;
    topX    = leftX + 10;
    topY    = rightY - 20;
  }

}
