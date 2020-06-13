package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class StatusMenu extends GameMenu {

    // components
    private JTable topDecksTable, decksStatusTable;
    private JScrollPane topDecksPane,decksStatusPane;
    private JButton backButton, exitButton;

    private Actions actions;

    // fields
    private String[] topDecksRow = new String[]{"Rank", "Name"};
    private String[] decksStatusRow = new String[]{"Name", "Win Rate", "Total wins", "Total plays", "Average mana", "Hero Class", "Most Frequently used Card"};

    public StatusMenu() {
        setLayout(null);
        setBounds(GUIConsts.statusMenu.menu_bounds);
        actions = new Actions();
        initComponents();
        initTables();

    }

    private void initComponents() {


        backButton = new JButton("Back");
        backButton.setBounds(GUIConsts.statusMenu.backButton_bounds);
        backButton.addActionListener(actions);
        add(backButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(GUIConsts.statusMenu.exitButton_bounds);
        exitButton.addActionListener(actions);
        add(exitButton);

        topDecksPane=new JScrollPane();
        decksStatusPane=new JScrollPane();
    }

     void initTables() {
        topDecksTable = new JTable(admin.getRankedDecks(), topDecksRow);

        topDecksTable.setBorder(GUIConsts.statusMenu.topDecksTable_border);
        topDecksTable.setEnabled(false);
        topDecksTable.setForeground(GUIConsts.generalLabel_foreColor);
        topDecksPane.setBounds(GUIConsts.statusMenu.topDecksTable_bounds);
        topDecksPane.setViewportView(topDecksTable);
        topDecksPane.setOpaque(false); topDecksPane.getViewport().setOpaque(false);
        add(topDecksPane);


        decksStatusTable = new JTable(admin.getDecksStatus(), decksStatusRow);
        decksStatusTable.setBorder(GUIConsts.statusMenu.decksStatusTable_border);
        decksStatusTable.setEnabled(false);
        decksStatusTable.setForeground(GUIConsts.generalLabel_foreColor);
        decksStatusPane.setBounds(GUIConsts.statusMenu.decksStatusTableTable_bounds);
        decksStatusPane.setViewportView(decksStatusTable);
        decksStatusPane.setOpaque(false); decksStatusPane.getViewport().setOpaque(false);
        add(decksStatusPane);
    }



    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d=addRenderingHint(g);
        paintGraphics(g2d);
    }

    private void paintGraphics(Graphics2D g2d) {
        try {
            BufferedImage backgroundImage = ImageIO.read(GUIConsts.statusMenu.background_image);
            g2d.drawImage(backgroundImage, 0, 0, GUIConsts.statusMenu.menu_bounds.width, GUIConsts.statusMenu.menu_bounds.height, null);

        } catch (IOException e) {


        }
    }

    private class Actions implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == backButton) {

                admin.writeLog("Back button","Clicked");

                GameScreen.getInstance().gotoMenu("main");
            } else {

                admin.writeExitLog();
                System.exit(0);
            }
        }
    }
}

