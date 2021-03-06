import java.awt.Color;
import java.awt.Graphics;


/**
 * Enemy.java
 * The enemy that the ship is fighting. Capabilities include
 * movement towards the ship and laser firing. If an enemy is hit
 * by the ships laser, it is dead.
 *
 * @author  Jake Wilson
 * @version Feb 15, 2014
 */
public class Enemy {
  
  private Color color;
  private Laser laser;
  
  private float cornerX, cornerY; // coordinates of the top left corner of the enemy
  private float length;
  
  private boolean isAlive;
  
  public static final int DIRECTION_RIGHT = 0;
  public static final int DIRECTION_DOWN  = 1;
  public static final int DIRECTION_LEFT  = 2;
  
  private final float speed = 3f;  // the speed at which the enemy moves
  
  private boolean shotFired;

  public Enemy(Color c, int x, int y) {
    color   = c;
    cornerX = x;
    cornerY = y;
    length  = 20;
    laser   = new Laser(new Color(0, 150, 255), 10, Laser.ORIENT_DOWN, 0.15f);
    isAlive = true;
  }
  
  public Enemy(int x, int y) {
    this(Color.GREEN, x, y);
  }
  
  /**
   * Draws an enemy on the screen
   * @param g The graphics context to draw on
   */
  public void draw(Graphics g) {
    if (isAlive) {    
      g.setColor(color);
      g.fillOval((int)cornerX, (int)cornerY, (int)length, (int)length);
    }
    
    if (shotFired) {
      laser.draw(g);
      if (laser.isOffScreen())
        shotFired = false;
    }
  }
  
  /**
   * @return the side length of the enemy
   */
  public float getLength() {
    return length;
  }
  
  /**
   * @return the x coordinate of the enemy's top left corner
   */
  public float getCornerX() {
    return cornerX;
  }
  
  /**
   * @return the y coordinate of the enemy's top left corner
   */
  public float getCornerY() {
    return cornerY;
  }
  
  /**
   * Determines if an enemy has room to move in the specified direction
   * @param direction the direction in which to check
   * @return whether the enemy has room in <code>direction</code>
   */
  public boolean hasRoom(int direction) {
    if (this.isOffScreen()) return true; // if the enemy is off screen, the wave should continue to move
    
    switch (direction) {
    case DIRECTION_RIGHT:
      return (((cornerX + length) + speed) < Handler.FRAME_WIDTH - 10);
    case DIRECTION_LEFT:
      return ((cornerX - speed) > 10);
    case DIRECTION_DOWN:
      return (((cornerY + length) + speed) < Handler.FRAME_HEIGHT - 10);
    default: // invalid direction (should not occur)
      return false;  
    }
  }
  
  /**
   * Moves an enemy. Will not move if the enemy doesn't have room
   */
  public void move(int direction) {
    if (!this.hasRoom(direction)) return; // if we don't have room, don't move
    
    switch (direction) {
    case DIRECTION_RIGHT:
      cornerX += speed;
      break;
    case DIRECTION_LEFT:
      cornerX -= speed;
      break;
    case DIRECTION_DOWN:
      cornerY += speed;
      break;
    default: // should be impossible, since hasRoom() will return false if not right, left, or down
      break;
    }
    
  }
  
  /**
   * Fires a laser from an enemy
   */
  public void fireLaser() {
    if (laser.isOffScreen()) {
      laser.setLocation(cornerX + (length / 2), cornerY + length);
      shotFired = true;
    }
  }
  
  /**
   * @return the enemy's laser
   */
  public Laser getLaser() {
    return laser;
  }
  
  /**
   * Changes the color of an enemy
   * @param c the new color
   */
  public void changeColor(Color c) {
    color = c;
  }
  
  /**
   * @return whether the enemy is alive or not
   */
  public boolean isAlive() {
    return isAlive;
  }
  
  /**
   * Sets the location of the enemy to (x, y)
   * @param x the x coordinate of the enemy
   * @param y the y coordinate of the enemy
   */
  private void setLocation(float x, float y) {
    cornerX = x;
    cornerY = y;
  }
  
  /**
   * Kills the enemy
   */
  public void kill() {
    setLocation(-100, -100);
    isAlive = false;
  }
  
  /**
   * @return whether the enemy is off the screen
   */
  public boolean isOffScreen() {
    return cornerX < 0 && cornerY < 0;
  }
  
}
