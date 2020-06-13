package GUI;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GCard {
    private BufferedImage cardImage;
    private Dimension cardDimension;
    private String cardName;
    private boolean isLocked=false;
    private Rectangle cardBox, lockBox;
    private int xPos, yPos;

    public GCard(Dimension cardDimension, String cardName, int xPos, int yPos) {
        this.cardDimension = cardDimension;
        this.cardName = cardName;
        this.xPos = xPos;
        this.yPos = yPos;
        cardBox = new Rectangle(xPos, yPos, cardDimension.width, cardDimension.height);
        try {
            cardImage = ImageIO.read(GUIConsts.gCard.getFile(cardName));
        } catch (IOException e) {

        }
    }

    public GCard(Dimension cardDimension, String cardName, Point O) {
        this.cardDimension = cardDimension;
        this.cardName = cardName;
        this.xPos = O.x;
        this.yPos = O.y;
        cardBox = new Rectangle(xPos, yPos, cardDimension.width, cardDimension.height);
        try {
            cardImage = ImageIO.read(getImageFile());
        } catch (IOException e) {

        }
    }

    public void render(Graphics2D g2d) {
        g2d.drawImage(cardImage, xPos, yPos, cardDimension.width, cardDimension.height, null);
        if (isLocked) { // card is locked , lockBox must be shown
            g2d.setPaint(new Color(50, 50, 50, 180));
            int xAlign=cardDimension.width/15,yAlign=cardDimension.height/15;
            g2d.fillRect(xPos+xAlign, yPos+yAlign, cardDimension.width-(2*xAlign), cardDimension.height-yAlign);
        }
    }

    public boolean isClicked(MouseEvent e) {
        if (cardBox.contains(e.getPoint())) {
            return true;
        }
        return false;
    }
protected File getImageFile(){
        return GUIConsts.gCard.getFile(cardName);
}

    public Dimension getCardDimension() {
        return cardDimension;
    }

    public void setCardDimension(Dimension cardDimension) {
        this.cardDimension = cardDimension;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public Rectangle getCardBox() {
        return cardBox;
    }
}


