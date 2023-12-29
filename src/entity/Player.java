package entity;

import main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.ScreenWidth / 2 - (gp.TileSize / 2);
        screenY = gp.ScreenHeight / 2 - (gp.TileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.TileSize * 23;
        worldY = gp.TileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/base-10.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/base-11.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/base-2.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/base-3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/base-5.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/base-7.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/base-4.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/base-9.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            //Check tile Collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //if collision is false, player can move
            if (collisionOn == false) {

                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            SpriteCounter++;
            if (SpriteCounter > 12) {
                if (SpriteNumber == 1) {
                    SpriteNumber = 2;
                } else if (SpriteNumber == 2) {
                    SpriteNumber = 1;
                }
                SpriteCounter = 0;
            }
        }


    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.TileSize, gp.TileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (SpriteNumber == 1) {
                    image = up1;
                }
                if (SpriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (SpriteNumber == 1) {
                    image = down1;
                }
                if (SpriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (SpriteNumber == 1) {
                    image = left1;
                }
                if (SpriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (SpriteNumber == 1) {
                    image = right1;
                }
                if (SpriteNumber == 2) {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.TileSize, gp.TileSize, null);
    }

}
