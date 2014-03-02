import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;


/**
 * Wave.java
 * The 'wave' of approaching enemies: contains an array of enemy objects.
 *
 * @author  Jake
 * @version Feb 16, 2014
 */
public class Wave {
  
  private ArrayList<Enemy> wave;
  
  private int currentDirection;
  private int directionCounter;
  
  public Wave(int n) {
    wave = new ArrayList<Enemy>(n);
    // draw the enemies in rows of 11
    for (int i = 0; i < n; i++) {
      wave.add(i, (new Enemy(Color.GREEN, 30 + (50 * (i % 11)), 30 + (i / 11 * 50))));
    }
    currentDirection = Enemy.DIRECTION_RIGHT;
    directionCounter = 0;
  }
  
  /**
   * Draws the entire array of enemies
   * @param g The graphics context to draw on
   */
  public void draw(Graphics g) {
    this.draw(g, false);
  }
  
  /**
   * Draws the entire array of enemies and the index number of each enemy
   * if the debug param is true
   * @param g The graphics context to draw on
   * @param debug whether to draw the debug content as well
   */
  public void draw(Graphics g, boolean debug) {
    for (int i = 0; i < wave.size(); i++)
      wave.get(i).draw(g);
    
    if (debug) {
      g.setColor(Color.BLACK);
      for (int i = 0; i < wave.size(); i++) {
        int x   = wave.get(i).getCornerX();
        int y   = wave.get(i).getCornerY();
        int len = wave.get(i).getLength();
        g.drawString(i+"", x + (len / 2), y + (len / 2) + (len / 4));
      }
    }
  }
  
  /**
   * @return the length of the enemy array
   */
  public int length() {
    return wave.size();
  }
  
  /**
   * Returns the enemy at the specified index or null if the index is out of bounds.
   * @param index the index of the enemy array
   * @return the enemy at index or null if index is out of bounds
   */
  public Enemy getEnemyAt(int index) {
    if (index >= 0 && index < wave.size())
      return wave.get(index);
    
    return null;
  }
  
  /**
   * Removes an enemy at the specified index from the ArrayList
   * @param index the index at which to remove an enemy
   */
  public void removeEnemyAt(int index) {
    if (index >= 0 && index < wave.size())
      wave.remove(index);
  }
  
  /**
   * Determines whether the wave as a whole has room in one direction. Every
   * enemy has to have room for the method to return true. If even one
   * enemy doesn't have room, false is returned.
   * @param direction the direction in which to check
   * @return whether the wave has room in <code>direction</code>
   */
  private boolean waveHasRoom(int direction) {
    for (int i = 0; i < this.length(); i++) {
      if (!wave.get(i).hasRoom(direction))
        return false;
    }
    
    return true;
  }
  

  /**
   * Moves the entire wave of enemies and has enemies at the front of the wave
   * randomly fire lasers
   */
  public void advance() {
    if (waveHasRoom(currentDirection)) {
      for (int i = 0 ; i < this.length(); i++) {
        wave.get(i).move(currentDirection);
      }
      if (currentDirection == Enemy.DIRECTION_DOWN) // we only want to move down once
        getNextDirection();
    } else {
      getNextDirection();
    } 
  }
  
  /**
   * Fires lasers of the enemies in front of the wave
   */
  public void fire() {
    for (int i = 0; i < this.length(); i++) {
      Random r = new Random();
      if (enemyIsInFront(wave.get(i)) && r.nextInt(50) == 0) {
        wave.get(i).fireLaser();
      }
    }
  }
  
  /**
   * Change the currentDirection to the next one i.e. :
   * after moving right, move down, then left, then down, then right, etc...
   * So every time the directionCounter is odd, we move down.
   * Every time it's even, we move left or right.
   */
  private void getNextDirection() {
    if (++directionCounter % 2 == 0) { // an even # means move right or left
      if (directionCounter % 4 == 0)
        currentDirection = Enemy.DIRECTION_RIGHT;
      else
        currentDirection = Enemy.DIRECTION_LEFT;
    } else { // every other time we change direction, we want to move down
      currentDirection = Enemy.DIRECTION_DOWN;
    }
  }
  
  /**
   * @return whether any enemies are left in the wave
   */
  public boolean isEmpty() {
    return this.length() == 0;
  }
  
  /**
   * Determines whether the specified enemy is at the front of the wave
   * @return whether the enemy <code>e</code> is at the front of the wave
   */
  private boolean enemyIsInFront(Enemy e) {
    for (int i = 0; i < length(); i++) {
      Enemy f = this.getEnemyAt(i);
      if (f.getCornerX() == e.getCornerX() && f.getCornerY() > e.getCornerY()) {
        return false;
      }
    }
    
    return true;
  }

}
