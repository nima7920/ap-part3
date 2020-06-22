package GUI_components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GButton extends JButton {

    private Actions actions = new Actions();
    private Color backColor=new Color(200,200,200),
            hoverBackColor=new Color(200,150,50),
            backForeColor=new Color(10,10,10),
            hoverForeColor=new Color(10,10,10);

    public GButton() {
        this.addMouseListener(actions);
        this.setBackground(backColor);
        this.setForeground(backForeColor);
    }

    public Color getBackColor() {
        return backColor;
    }

    public void setBackColor(Color backColor) {
        this.backColor = backColor;
    }

    public Color getHoverBackColor() {
        return hoverBackColor;
    }

    public void setHoverBackColor(Color hoverBackColor) {
        this.hoverBackColor = hoverBackColor;
    }

    public Color getBackForeColor() {
        return backForeColor;
    }

    public void setBackForeColor(Color backForeColor) {
        this.backForeColor = backForeColor;
    }

    public Color getHoverForeColor() {
        return hoverForeColor;
    }

    public void setHoverForeColor(Color hoverForeColor) {
        this.hoverForeColor = hoverForeColor;
    }

    private class Actions implements MouseListener, ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ((GButton) e.getSource()).setBackground(hoverBackColor);
            ((GButton) e.getSource()).setForeground(hoverForeColor);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ((GButton) e.getSource()).setBackground(backColor);
            ((GButton) e.getSource()).setForeground(backForeColor);
        }
    }
}