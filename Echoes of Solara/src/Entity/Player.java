package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;

public class Player extends Entity{
    
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gamePanel = gp;
        this.keyHandler = keyH;
    
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyHandler.wPressed){
            y -= speed;
            direction = "up";
        }
        else if(keyHandler.sPressed){
            y += speed;
            direction = "down";
        }
        else if(keyHandler.aPressed){
            x -= speed;
            direction = "left";
        }
        else if(keyHandler.dPressed){
            x += speed;
            direction = "right";
        }
    }

    public void draw(Graphics2D g2){
        
        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
            default:
                break;
        }

        g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);

    }

}
