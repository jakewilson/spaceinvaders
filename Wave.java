import java.awt.Color;
import java.awt.Graphics;


/**
 * Wave.java
 * The 'wave' of approaching enemies.
 *
 * @author  Jake
 * @version Feb 16, 2014
 */
public class Wave {
  
  private Enemy[] wave;
  
  public Wave(int n) {
    wave = new Enemy[n];
    // draw the enemies in rows of 11
    for (int i = 0; i < wave.length; i++) {
      wave[i] = new Enemy(Color.GREEN, 30 + (50 * (i % 11)), 30 + (i / 11 * 50));
    }
  }
  
  public void draw(Graphics g) {
    for (int i = 0; i < wave.length; i++)
      wave[i].draw(g);
  }

}
