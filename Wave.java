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
      wave.add(i, (new Enemy(Color.GREEN, 30 + (50 * (11 - (i % 11))), 30 + (i / 11 * 50))));
    }
    currentDirection = Enemy.DIRECTION_RIGHT;
  }
  
  /**
   * Draws the entire array of enemies
   * @param g - The graphics context to draw on
   */
  public void draw(Graphics g) {
    for (int i = 0; i < wave.size(); i++)
      wave.get(i).draw(g);
  }
  
  /**
   * @return the length of the enemy array
   */
  public int length() {
    return wave.size();
  }
  
  /**
   * Returns the enemy at the specified index or null if the index is out of bounds.
   * @param index - the index of the enemy array
   * @return the enemy at index or null if index is out of bounds
   */
  public Enemy getEnemyAt(int index) {
    if (index >= 0 && index < wave.size())
      return wave.get(index);
    
    return null;
  }
  
  /**
   * Removes an enemy at the specified index from the ArrayList
   * @param index - the index at which to remove an enemy
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
