package GUI_menus;

import Logic_menus.Admin;

import javax.swing.*;
import java.awt.*;


public abstract class GameMenu extends JPanel {
    protected Admin admin;

    public GameMenu() {
        admin = Admin.getInstance();
    }
    protected Graphics2D addRenderingHint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        return g2d;
    }
}

