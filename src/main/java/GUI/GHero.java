package GUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GHero {
    private BufferedImage heroImage;
    private Dimension heroDimension;
    private String heroName;
    private Rectangle heroBox;
    private int xPos, yPos;

    public GHero(Dimension heroDimension, String heroName, int xPos, int yPos) {
        this.heroDimension = heroDimension;
        this.heroName = heroName;
        this.xPos = xPos;
        this.yPos = yPos;
        heroBox = new Rectangle(xPos, yPos, heroDimension.width, heroDimension.height);
        try {
            heroImage = ImageIO.read(GUIConsts.gHero.getFile(heroName));
        } catch (IOException e) {

        }
    }

    public GHero(Dimension heroDimension, String heroName, Point O) {
        this.heroDimension = heroDimension;
        this.heroName = heroName;
        this.xPos = O.x;
        this.yPos = O.y;
        heroBox = new Rectangle(xPos, yPos, heroDimension.width, heroDimension.height);
        try {
            heroImage = ImageIO.read(GUIConsts.gHero.getFile(heroName));
        } catch (IOException e) {

        }
    }

    public void render(Graphics2D g2d) {
        g2d.drawImage(heroImage, xPos, yPos, heroDimension.width, heroDimension.height, null);

    }
    public boolean isClicked(MouseEvent e) {
        if (heroBox.contains(e.getPoint())) {
            return true;
        }
        return false;
    }
}

