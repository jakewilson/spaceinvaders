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
  
  private boolean shotFired;  // whether the user has fired a shot
  
  private Laser laser; // the ships laser
  
  public final int DIRECTION_RIGHT = 7;
  public final int DIRECTION_LEFT  = -7;
  
  public Ship(Color c, int x, int y) {
    color     = c;
    leftX     = x;
    leftY     = y;
    shotFired = false;
    assignVertices();
  }
  
  /**
   * Constructor that sets the ships color to c and sets the ship to the middle of the
   * bottom of the screen
   * @param c - the color of the ship
   */
  public Ship(Color c) {
    color = c;
    this.goToStartingPoint();
  }
  
  /**
   * Constructor that sets the ships color to orange and sets the ship to the middle of the
   * bottom of the screen
   */
  public Ship() {
    color = Color.ORANGE;
    this.goToStartingPoint();
  }
  
  /**
   * Draws the ship and the laser if a shot has been fired
   * @param g - The graphics context to draw on
   */
  public void draw(Graphics g) {
    g.setColor(color);
    int[] xPoints = {leftX, topX, rightX};
    int[] yPoints = {leftY, topY, rightY};
    Polygon p = new Polygon(xPoints, yPoints, 3);
    g.fillPolygon(p);
    g.drawPolygon(p);
    if (shotFired) { // if a shot has been fired, draw it on the screen
      laser.draw(g);
      if (laser.isOffScreen()) shotFired = false; // if the laser is off the screen, stop moving it
    }
  }
  
  /**
   * Moves the ship right of left. If the param is not DIRECTION_RIGHT or DIRECTION_LEFT
   * the ship will not move.
   * @param direction - DIRECTION_LEFT or DIRECTION_RIGHT
   */
  public void move(int direction) {
    if (direction != DIRECTION_LEFT && direction != DIRECTION_RIGHT)
      return;
    
    if (rightX + direction < Handler.FRAME_WIDTH - 30 && leftX + direction > 10) {
      leftX += direction;
      assignVertices();
    }
  }
  
  /**
   * Fires a laser from the top of the ship by creating a new Laser()
   */
  public void fireLaser() {
    laser     = new Laser(Color.RED, topX, topY, 20); // garbage collection will handle the previous laser
    shotFired = true;
  }
  
  /**
   * Returns the ship to it's origin (middle of the bottom of the screen)
   */
  public void goToStartingPoint() {
    leftX = (Handler.FRAME_WIDTH / 2) - 10;
    leftY = Handler.FRAME_HEIGHT - 50;
    assignVertices();
  }
  
  /**
   * Assigns the remaining vertices of the ships triangle based on the left most vertex
   */
  private void assignVertices() {
    rightX  = leftX + 20;
    rightY  = leftY;
    topX    = leftX + 10;
    topY    = rightY - 20;
  }

}
