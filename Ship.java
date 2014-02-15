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
  
  private int laserX, laserY; // the coordinates of the start of the laser
  
  private boolean shotFired; // whether the user has fired a shot
  
  public final int DIRECTION_RIGHT = 7;
  public final int DIRECTION_LEFT  = -7;
  
  public Ship(Color c, int x, int y) {
    color     = c;
    leftX     = x;
    leftY     = y;
    shotFired = false;
    assignVertices();
  }
  
  public Ship(Color c) {
    color = c;
    this.goToStartingPoint();
  }
  
  // draws the ship and the laser if a shot has been fired
  public void draw(Graphics g) {
    g.setColor(color);
    int[] xPoints = {leftX, topX, rightX};
    int[] yPoints = {leftY, topY, rightY};
    Polygon p = new Polygon(xPoints, yPoints, 3);
    g.fillPolygon(p);
    g.drawPolygon(p);
    if (shotFired) {
      g.setColor(Color.RED);
      g.drawLine(laserX, laserY, laserX, laserY - 20);
      laserY -= 1;
      if (laserY < 0) shotFired = false;
      System.out.println(laserY);
    }
  }
  
  // moves the ship left or right as long as the ship is within bounds
  public void move(int direction) {
    if (rightX + direction < Handler.frameWidth - 30 && leftX + direction > 10) {
      leftX += direction;
      assignVertices();
    }
  }
  
  // fires a laser from the tip of the ship
  public void fireLaser() {
    laserX    = topX;
    laserY    = topY;
    shotFired = true;
  }
  
  // returns the ship to it's origin
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
