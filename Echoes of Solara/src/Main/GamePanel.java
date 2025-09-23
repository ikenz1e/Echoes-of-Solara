package Main;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{

    // screen settings
    final int originalTileSize = 16; // 16x16 tiles
    final int scale = 3; // scale to 48x48

    final int tileSize = originalTileSize * scale; // 48x48 tiles
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    final int FPS = 60;

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

    // set default player stats - temp
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){ 
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        this.addKeyListener(keyHandler);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
       
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if(delta >= 1){
                // update
                update();
                // draw
                repaint();
                delta = 0;
            }
        }

    }

    public void update(){
        if(keyHandler.wPressed){
            playerY -= playerSpeed;
        }
        else if(keyHandler.sPressed){
            playerY += playerSpeed;
        }
        else if(keyHandler.aPressed){
            playerX -= playerSpeed;
        }
        else if(keyHandler.dPressed){
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        
        g2.setColor(Color.WHITE);
        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();
    }

}
