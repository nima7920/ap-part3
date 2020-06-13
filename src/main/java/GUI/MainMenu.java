package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MainMenu extends GameMenu {
    private JButton playButton, storeButton, statusButton, collectionsButton, settingsButton, exitButton;
    private Actions actions;

    public MainMenu() {
        super();
        initMenu();
        initButtons();

        repaint();
    }

    private void initMenu() {
        setLayout(null);
        setBounds(GUIConsts.mainMenu.menuBounds);

    }

    private void initButtons() {
        actions = new Actions();

        playButton = new JButton("Play");
        playButton.setBounds(GUIConsts.mainMenu.playButton_bounds);
        playButton.addActionListener(actions);
        add(playButton);

        storeButton = new JButton("Store");
        storeButton.setBounds(GUIConsts.mainMenu.storeButton_bounds);
        storeButton.addActionListener(actions);
        add(storeButton);

        statusButton = new JButton("Status");
        statusButton.setBounds(GUIConsts.mainMenu.statusButton_bounds);
        statusButton.addActionListener(actions);
        add(statusButton);

        collectionsButton = new JButton("Collections");
        collectionsButton.setBounds(GUIConsts.mainMenu.collectionsButton_bounds);
        collectionsButton.addActionListener(actions);
        add(collectionsButton);

        settingsButton = new JButton("Settings");
        settingsButton.setBounds(GUIConsts.mainMenu.settingsButton_bounds);
        settingsButton.addActionListener(actions);
        add(settingsButton);

        exitButton = new JButton("Quit");
        exitButton.setBounds(GUIConsts.mainMenu.exitButton_bounds);
        exitButton.addActionListener(actions);
        add(exitButton);

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = addRenderingHint(g);
        paintGraphics(g2d);
    }

    private void paintGraphics(Graphics2D g2d) {
        try {
            BufferedImage backgroundImage = ImageIO.read(GUIConsts.mainMenu.background_image);
            g2d.drawImage(backgroundImage, 0, 0, GUIConsts.mainMenu.menuBounds.width, GUIConsts.mainMenu.menuBounds.height, null);

        } catch (IOException e) {


        }

    }

    private class Actions implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == playButton) {
// will be completed
                if (admin.isCurrentDeckReady()) { // entering play menu
                    admin.writeLog("Menu", "Play;current deck is ready");
                    admin.refreshPlayState();
                    GameScreen.getInstance().refreshPlayMenu();
                    GameScreen.getInstance().gotoMenu("play");
                } else {
                    admin.writeLog("Menu", "Collections;current deck is not ready for playing");
                    GameScreen.getInstance().gotoMenu("collections");
                }

            } else if (e.getSource() == storeButton) {
                admin.writeLog("Menu", "Store");
                GameScreen.getInstance().gotoMenu("store");

            } else if (e.getSource() == statusButton) {
                admin.writeLog("Menu", "Status");
                GameScreen.getInstance().refreshStatusMenu();
                GameScreen.getInstance().gotoMenu("status");

            } else if (e.getSource() == collectionsButton) {

                admin.writeLog("Menu", "Collections");
                GameScreen.getInstance().gotoMenu("collections");

            } else if (e.getSource() == settingsButton) {


            } else if (e.getSource() == exitButton) {

                admin.writeExitLog();
                System.exit(0);

            }
//
        }
    }
}

