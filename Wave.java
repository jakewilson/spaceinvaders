import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


/**
 * Wave.java
 * The 'wave' of approaching enemies: contains an array of enemy objects.
 *
 * @author  Jake
 * @version Feb 16, 2014
 */
public class Wave {
  
  private ArrayList<Enemy> wave;
  
  private static int currentDirection;
  
  public Wave(int n) {
    wave = new ArrayList<Enemy>(n);
    // draw the enemies in rows of 11
    for (int i = 0; i < n; i++) {
      wave.add(i, (new Enemy(Color.GREEN, 30 + (50 * (i % 11)), 30 + (i / 11 * 50))));
    }
    currentDirection = Enemy.DIRECTION_RIGHT;
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
   * Moves the entire wave of enemies
   */
  public void advance() {
    
  }

}
