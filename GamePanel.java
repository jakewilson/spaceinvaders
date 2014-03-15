import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;


/**
 * GamePanel.java
 * This class is responsible for drawing the game features and handling any button presses.
 *
 * @author  Jake Wilson
 * @version Feb 15, 2014
 */
public class GamePanel extends JPanel {
  
  private Ship hero; // the ship the user will play as
  private Wave wave; // the wave of enemies the user will fight
  
  private ShipListener listener; // the panels key listener
  
  private boolean debugMode;
  private boolean gamePaused;
  private boolean gameOver;
  private boolean quitGame;
  private boolean gameWon;
  
  private int score;
  
  // the x location of any menu we want to print (gameOver, pause)
  private final int MENU_X = Handler.FRAME_WIDTH / 2 - Handler.FRAME_WIDTH / 10;
  
  public GamePanel() {
    super();
    restartGame(); // init game objects
    debugMode = false;
    this.setFont(new Font("Courier New", Font.PLAIN, 14));
    this.setBackground(Color.BLACK);
    this.setFocusable(true);
    this.validate();
  }
  
  /**
   * (Re)starts the game
   */
  public void restartGame() {
    hero       = new Ship(new Color(255, 154, 0)); // make the ship orange
    wave       = new Wave(44); // TODO: the size should be a variable depending on the level
    gameOver   = false;
    gamePaused = false;
    gameWon    = false;
    score      = 0;
    // if the panel already has a key listener, remove it before adding a new one
    if (this.getKeyListeners().length == 1) {
      this.removeKeyListener(listener);
    }
    listener   = new ShipListener(hero, this);
    this.addKeyListener(listener);
  }
  
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (hero.isAlive() && !gameWon) {
      if (!gamePaused) {
        if (debugMode) {
          drawGrid(g);
          wave.draw(g, true);
        } else {
          wave.draw(g);
        }
        drawScore(g);
        hero.draw(g);
      } else { // display pause menu
        displayPauseMenu(g);
      }
    } else if (!hero.isAlive()) {
      gameOver = true;
      displayGameOver(g);
    } else if (gameWon) {
      displayGameWon(g);
    }
  }
  

  /**
   * Draws the score at the top of the screen
   * @param g the graphics context to draw on
   */
  private void drawScore(Graphics g) {
    g.setColor(Color.RED);
    g.drawString("Score: " + score, MENU_X, 30);
  }
  
  /**
   * Draws the game won screen
   * @param g the graphics context to draw on
   */
  private void displayGameWon(Graphics g) {
    g.setColor(Color.WHITE);
    String msg = "You've defeated the wave of enemies!";
    g.drawString("Score: " + score  , MENU_X - 20, Handler.FRAME_HEIGHT / 2 - 80);
    g.drawString(msg                , MENU_X - 20, Handler.FRAME_HEIGHT / 2 - 60);
    g.drawString("Play again (y/n)?", MENU_X - 20, Handler.FRAME_HEIGHT / 2 - 40);
  }
  
  /**
   * Draws the game over screen
   * @param g the graphics context to draw on
   */
  private void displayGameOver(Graphics g) {
    g.setColor(Color.WHITE);
    g.drawString("Game Over"        , MENU_X, Handler.FRAME_HEIGHT / 2 - 80);
    g.drawString("Score: " + score  , MENU_X, Handler.FRAME_HEIGHT / 2 - 60);
    g.drawString("Play Again (y/n)?", MENU_X, Handler.FRAME_HEIGHT / 2 - 40);
  }
  
  /**
   * Draws the pause menu on the screen
   * @param g the graphics context to draw on
   */
  private void displayPauseMenu(Graphics g) {
    g.setColor(Color.WHITE);
    String debugString = "";
    g.drawString("Game Paused"    , MENU_X, Handler.FRAME_HEIGHT / 2 - 60);
    g.drawString("Score: " + score, MENU_X, Handler.FRAME_HEIGHT / 2 - 40);
    g.drawString("[p] To Unpause" , MENU_X, Handler.FRAME_HEIGHT / 2 - 20);
    if (debugMode) {
      debugString = "[d] To Leave Debug Mode";
    } else {
      debugString = "[d] To Enter Debug Mode";
    }
    g.drawString(debugString, MENU_X, Handler.FRAME_HEIGHT / 2);
    g.drawString("[q] To quit", MENU_X, Handler.FRAME_HEIGHT / 2 + 20);
  }
  
  /**
   * Draws a grid on the screen for debugging purposes
   */
  private void drawGrid(Graphics g) {
    int i = 20;
    while (i < Handler.FRAME_WIDTH) {
      g.setFont(new Font("Monospace", Font.PLAIN, 9));
      g.setColor(Color.RED);
      g.drawString(i+"", i+2, 15);
      g.drawString(i+"", 5, i-2);
      g.setColor(Color.GRAY);
      g.drawLine(i, 0, i, Handler.FRAME_HEIGHT);
      g.drawLine(0, i, Handler.FRAME_WIDTH, i);
      i += 20;
    }
  }
  
  /**
   * @return the Wave of enemies
   */
  public Wave getWave() {
    return wave;
  }
  
  /**
   * @return the ship (hero)
   */
  public Ship getHero() {
    return hero;
  }
  
  /**
   * @return debugMode
   */
  public boolean getDebugMode() {
    return debugMode;
  }
  
  /**
   * Sets the debugMode
   * @param mode the new debugMode
   */
  public void toggleDebugMode() {
    debugMode = !debugMode;
  }
  
  /**
   * Toggles the pause state. If the game is paused, it is unpaused and vice versa
   */
  public void togglePause() {
    gamePaused = !gamePaused;
  }
  
  /**
   * @return whether the game is paused
   */
  public boolean isPaused() {
    return gamePaused;
  }
  
  /**
   * @return whether the game is over or not
   */
  public boolean gameIsOver() {
    return gameOver;
  }
  
  /**
   * Signals to the Handler whether to continue running the game
   * @return whether the keep running the game or not
   */
  public boolean keepRunning() {
    return !quitGame;
  }
  
  /**
   * Sets quitGame to true
   */
  public void quitGame() {
    quitGame = true;
  }
  
  /**
   * @return whether the user has defeated all of the enemies or not
   */
  public boolean gameIsWon() {
    return gameWon;
  }
  
  /**
   * Sets gameWon to b
   * TODO: think of a better method name for this...setGameWon() is stupid
   * @param b the new gameWon value
   */
  public void setGameWon(boolean b) {
    gameWon = b;
  }
  
  /**
   * Updates the score depending on how many enemies remain in the wave
   */
  public void updateScore() {
    score = (44 - wave.amountOfEnemiesAlive()) * 10;
  }

}

/**
 * KeyListener class for moving the ship and other in-game actions.
 */
class ShipListener implements KeyListener {
  
  Ship      ship;
  GamePanel panel;
  
  public ShipListener(Ship s, GamePanel gp) {
    ship  = s;
    panel = gp;
  }

  @Override
  public void keyTyped(KeyEvent ke) {
    
  }

  public void keyPressed(KeyEvent ke) {
    switch (ke.getKeyCode()) {
    case KeyEvent.VK_RIGHT: // move the ship to the right
      if (!panel.isPaused()) {
        ship.move(ship.DIRECTION_RIGHT);
      }
      break;
    case KeyEvent.VK_LEFT: // move the ship to the left
      if (!panel.isPaused()) {
        ship.move(ship.DIRECTION_LEFT);
      }
      break;
    case KeyEvent.VK_SPACE: // fire the laser
      if (!panel.isPaused()) {
        ship.fireLaser();
      }
      break;
    case KeyEvent.VK_P:  // pause the game
      panel.togglePause();
      break;
    case KeyEvent.VK_D:
      if (panel.isPaused()) { // enter debug mode
        panel.toggleDebugMode();
      }
      break;
    case KeyEvent.VK_Y: // restart the game
      if (panel.gameIsOver() || panel.gameIsWon()) {
        panel.restartGame();
      }
      break;
    case KeyEvent.VK_N: // quit the game
      if (panel.gameIsOver() || panel.gameIsWon()) {
        panel.quitGame();
      }
      break;
    case KeyEvent.VK_Q: // quit the game from the pause menu
      if (panel.isPaused()) {
        panel.quitGame();
      }
      break;
    }
  }

  @Override
  public void keyReleased(KeyEvent ke) {
    
  }
  
}
