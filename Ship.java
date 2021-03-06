import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;


/**
 * Ship.java
 * The ship that the user plays as. Capabilities include firing laser
 * and horizontal movement. When the ship gets hit by a single enemy laser,
 * the game is over.
 *
 * @author  Jake Wilson
 * @version Feb 15, 2014
 */
public class Ship {
  
  private Color color;
  
  private float rightX, rightY; // coordinates of the right vertex of the triangle
  private float leftX , leftY;  // coordinates of the left  vertex of the triangle
  private float topX  , topY;   // coordinates of the top   vertex of the triangle
  
  private boolean shotFired;  // whether the user has fired a shot
  private boolean isAlive;
  
  private Laser laser;        // the ships laser
  
  public final int DIRECTION_RIGHT = 7;
  public final int DIRECTION_LEFT  = -7;
  
  /**
   * Constructor that sets the ships color to c and sets the ship to the middle of the
   * bottom of the screen
   * @param c the color of the ship
   */
  public Ship(Color c) {
    color     = c;
    shotFired = false;
    laser     = new Laser(Color.RED, 20, Laser.ORIENT_UP, 0.5f);
    isAlive   = true;
    laser.moveOffScreen();
    this.goToStartingPoint();
  }
  
  /**
   * Constructor that sets the ships color to orange and sets the ship to the middle of the
   * bottom of the screen
   */
  public Ship() {
    this(Color.ORANGE);
  }
  
  /**
   * Draws the ship and the laser if a shot has been fired
   * @param g The graphics context to draw on
   */
  public void draw(Graphics g) {
    if (isAlive) {
      g.setColor(color);
      int[] xPoints = {(int)leftX, (int)topX, (int)rightX};
      int[] yPoints = {(int)leftY, (int)topY, (int)rightY};
      Polygon p = new Polygon(xPoints, yPoints, 3);
      g.fillPolygon(p);
      g.drawPolygon(p);
    }
    // we want to continue to fire the laser even if the ship has been killed
    if (shotFired) { // if a shot has been fired, draw it on the screen
      laser.draw(g);
      if (laser.isOffScreen())
        shotFired = false; // if the laser is off the screen, stop drawing it
    }
  }
  
  /**
   * Moves the ship right or left. If the param is not DIRECTION_RIGHT or DIRECTION_LEFT
   * the ship will not move. If moving will cause the ship to move off of the screen,
   * the ship will not move.
   * @param direction DIRECTION_LEFT or DIRECTION_RIGHT
   */
  public void move(int direction) {
    if (direction != DIRECTION_LEFT && direction != DIRECTION_RIGHT)
      return;
    
    if (rightX + direction < Handler.FRAME_WIDTH - 10 && leftX + direction > 10) {
      leftX += direction;
      assignVertices();
    }
  }
  
  /**
   * Fires a laser from the top of the ship
   */
  public void fireLaser() {
    if (laser.isOffScreen()) { // allow only one shot to be fired at a time
      laser.setLocation(topX, topY);
      shotFired = true;
    }
  }
  
  /**
   * @return the ships laser (no matter it's location)
   */
  public Laser getLaser() {
    return laser;
  }
  
  /**
   * Sets the ships coordinates to it's origin (middle of the bottom of the screen)
   */
  public void goToStartingPoint() {
    leftX = (Handler.FRAME_WIDTH / 2) - 10;
    leftY = Handler.FRAME_HEIGHT - 30;
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
  
  /**
   * Remove the laser from the screen and stop drawing it
   */
  public void returnLaser() {
    laser.moveOffScreen();
    shotFired = false;
  }
  
  /**
   * @return the top x coordinate of the ship
   */
  public float getTopX() {
    return topX;
  }
  
  /**
   * @return the top y coordinate of the ship
   */
  public float getTopY() {
    return topY;
  }
  
  /**
   * @return the left x coordinate of the ship
   */
  public float getLeftX() {
    return leftX;
  }
  
  /**
   * @return the left y coordinate of the ship
   */
  public float getLeftY() {
    return leftY;
  }
  
  /**
   * @return the right x coordinate of the ship
   */
  public float getRightX() {
    return rightX;
  }
  
  /**
   * @return the right y coordinate of the ship
   */
  public float getRightY() {
    return rightY;
  }
  
  /**
   * Kills the ship
   */
  public void kill() {
    isAlive = false;
  }
  
  /**
   * @return whether the hero is alive or not
   */
  public boolean isAlive() {
    return isAlive;
  }

}
