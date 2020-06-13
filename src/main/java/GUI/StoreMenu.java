package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class StoreMenu extends GameMenu {

    // menu components
    private BuyPanel buyPanel;
    private SellPanel sellPanel;
    private InfoPanel infoPanel;
    private JButton buyCardButton, sellCardButton, buy_sellButton, backButton, exitButton;
    private JScrollPane showCase;

    // required info
    private ArrayList<String> buyableCards, salableCards;
    private ArrayList<GCard> buyableGCards, salableGCards;


    private Actions actions = new Actions();
    private String currentTab;


    // class constructor
    public StoreMenu() {
// creating all the objects:
        buyPanel = new BuyPanel();
        sellPanel = new SellPanel();
        infoPanel = new InfoPanel();
        initMenu();
        updateCards();
        repaint();
    }

    private void initMenu() {
        setLayout(null);
        setBounds(GUIConsts.storeMenu.menuBounds);
        setSize(GUIConsts.storeMenu.menuSize);
        setPreferredSize(GUIConsts.storeMenu.menuSize);
        initShowcase();
        initButtons();
        add(showCase);
        currentTab = "Buy";
        sellPanel.setVisible(false);
        add(infoPanel);
        add(sellCardButton);
        add(buyCardButton);
    }


    private void initShowcase() {
        showCase = new JScrollPane();
        showCase.setBounds(GUIConsts.storeMenu.showcase_bounds);
        showCase.setHorizontalScrollBar(null);
        showCase.setViewportView(sellPanel);
        showCase.setOpaque(false);
        showCase.getViewport().setOpaque(false);
        showCase.setViewportView(buyPanel);
        showCase.setOpaque(false);
        showCase.getViewport().setOpaque(false);
    }

    // initializing buttons:
    private void initButtons() {
// buyCardButton:
        buyCardButton = new JButton("Buy Card");
        buyCardButton.setBounds(GUIConsts.storeMenu.buyCardButton_bounds);
        buyCardButton.addActionListener(actions);
        add(buyCardButton);
        // sellCardButton:
        sellCardButton = new JButton("Sell Card");
        sellCardButton.setBounds(GUIConsts.storeMenu.sellCardButton_bounds);
        sellCardButton.addActionListener(actions);
        add(sellCardButton);
// backButton
        backButton = new JButton("Back");
        backButton.setBounds(GUIConsts.storeMenu.backButton_bounds);
        backButton.addActionListener(actions);
        add(backButton);
        // exit button
        exitButton = new JButton("Exit");
        exitButton.setBounds(GUIConsts.storeMenu.exitButton_bounds);
        exitButton.addActionListener(actions);
        add(exitButton);

    }

    // updates all card lists
    private void updateCards() {
        buyableCards = admin.getBuyableCards();
        salableCards = admin.getSalableCards();
        buyableGCards = new ArrayList<>();
        salableGCards = new ArrayList<>();
        for (int i = 0; i < buyableCards.size(); i++) {
            buyableGCards.add(new GCard(GUIConsts.storeMenu.buy_sellImage_dimension, buyableCards.get(i), cardPos(i)));
        }
        for (int i = 0; i < salableCards.size(); i++) {
            salableGCards.add(new GCard(GUIConsts.storeMenu.buy_sellImage_dimension, salableCards.get(i), cardPos(i)));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = addRenderingHint(g);
        paintGraphics(g2d);
    }

    private void paintGraphics(Graphics2D g2d) {
        try {
            BufferedImage backgroundImage = ImageIO.read(GUIConsts.storeMenu.background_image);
            g2d.drawImage(backgroundImage, 0, 0, GUIConsts.storeMenu.menuBounds.width, GUIConsts.storeMenu.menuBounds.height, null);

        } catch (IOException e) {


        }
    }

    // buy panel
    class BuyPanel extends JPanel { // a panel for buying cards
        public BuyPanel() {
            setLayout(null);
            initPanel();
        }

        private void initPanel() {
            setBounds(GUIConsts.storeMenu.buy_sellPanel_bounds);
            setPreferredSize(GUIConsts.storeMenu.buy_sellPanel_size);
            setOpaque(false);
            addMouseListener(actions);
        }

        private void updateScrolls(int y) {
            setPreferredSize(new Dimension(GUIConsts.storeMenu.buy_sellPanel_size.width,
                    Math.max(GUIConsts.storeMenu.buy_sellPanel_size.height, y)));
            showCase.setViewportView(this);
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = addRenderingHint(g);
            for (int i = 0; i < buyableGCards.size(); i++) {
                buyableGCards.get(i).render(g2d);
            }
            updateScrolls(cardPos(buyableGCards.size() + 4).y);
        }
    }

    // sell panel
    class SellPanel extends JPanel { //a panel for buying cards
        public SellPanel() {
            setLayout(null);

            initPanel();
        }

        private void initPanel() {
            setBounds(GUIConsts.storeMenu.buy_sellPanel_bounds);
            setPreferredSize(GUIConsts.storeMenu.buy_sellPanel_size);
            setOpaque(false);
            addMouseListener(actions);
        }

        private void updateScrolls(int y) {
            setPreferredSize(new Dimension(GUIConsts.storeMenu.buy_sellPanel_size.width,
                    Math.max(GUIConsts.storeMenu.buy_sellPanel_size.height, y)));
            showCase.setViewportView(this);
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = addRenderingHint(g);
            for (int i = 0; i < salableGCards.size(); i++) {
                salableGCards.get(i).render(g2d);
            }
            updateScrolls(cardPos(salableGCards.size() + 4).y);

        }
    }

    // info panel
    class InfoPanel extends JPanel { // a panel for showing general info
        private JLabel walletLabel, costLabel, rarityLabel, classLabel;
        private GCard selectedCard;
        private String selectedCardName;

        public InfoPanel() {
            initPanel();

        }

        private void initPanel() {
            setBounds(GUIConsts.storeMenu.infoPanel_bounds);
            setBorder(GUIConsts.storeMenu.infoPanel_border);
            setOpaque(false);
            setLayout(null);
            initLabels();
            initButtons();
        }

        private void initLabels() {
// wallet label
            walletLabel = new JLabel("Wallet:" + admin.getPlayerWallet());
            walletLabel.setBounds(GUIConsts.storeMenu.walletLabel_bounds);
            walletLabel.setForeground(GUIConsts.generalLabel_foreColor);
            add(walletLabel);
// rarity label
            rarityLabel = new JLabel("Rarity: ");
            rarityLabel.setBounds(GUIConsts.storeMenu.rarityLabel_bounds);
            rarityLabel.setForeground(GUIConsts.generalLabel_foreColor);
            add(rarityLabel);
            // class label
            classLabel = new JLabel("Class: ");
            classLabel.setBounds(GUIConsts.storeMenu.classLabel_bounds);
            classLabel.setForeground(GUIConsts.generalLabel_foreColor);
            add(classLabel);
            // cost label
            costLabel = new JLabel("Cost: ");
            costLabel.setBounds(GUIConsts.storeMenu.costLabel_bounds);
            costLabel.setForeground(GUIConsts.generalLabel_foreColor);
            add(costLabel);

        }

        private void initButtons() {
            buy_sellButton = new JButton("Buy");
            buy_sellButton.setBounds(GUIConsts.storeMenu.buy_sellButton_bounds);
            buy_sellButton.addActionListener(actions);
            add(buy_sellButton);

        }

        public void updateCard(String cardName) {
            if (cardName.equals("")) {
                selectedCardName = "";
                selectedCard = null;
            } else {
                selectedCardName = cardName;
                selectedCard = new GCard(GUIConsts.storeMenu.infoImageBox_size, selectedCardName, GUIConsts.storeMenu.infoImageBox_location);
            }
            updateLabels();
            repaint();
        }

        private void updateLabels() {
            walletLabel.setText("Wallet:" + admin.getPlayerWallet());
            costLabel.setText("Cost:" + admin.getCardCost(selectedCardName));
            rarityLabel.setText("Rarity:" + admin.getCardRarity(selectedCardName));
            classLabel.setText("Class:" + admin.getCardClass(selectedCardName));

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = addRenderingHint(g);
            if (selectedCard != null) {
                selectedCard.render(g2d);
            }
        }
    }

    private Point cardPos(int i) {
        int column = i % GUIConsts.storeMenu.buy_sellImage_row, row = i / GUIConsts.storeMenu.buy_sellImage_row;
        int x = GUIConsts.storeMenu.buy_sellImage_horizontalAlign +
                column * (GUIConsts.storeMenu.buy_sellImage_dimension.width + GUIConsts.storeMenu.buy_sellImage_horizontalAlign);
        int y = GUIConsts.storeMenu.buy_sellImage_verticalAlign +
                row * (GUIConsts.storeMenu.buy_sellImage_dimension.height + GUIConsts.storeMenu.buy_sellImage_verticalAlign);
        return new Point(x, y);

    }

    // a class for handling the actions of the menu
    private class Actions implements ActionListener, MouseListener {

        // handles the clicking of buttons
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == buyCardButton) {
                admin.writeLog("Panel", "Buy Card");
                updateCards();
                buy_sellButton.setText("Buy");
                infoPanel.updateCard("");
                showCase.setViewportView(buyPanel);

            } else if (e.getSource() == sellCardButton) {
                admin.writeLog("Panel", "Sell Card");
                updateCards();
                buy_sellButton.setText("Sell");
                infoPanel.updateCard("");
                showCase.setViewportView(sellPanel);
                sellPanel.setVisible(true);
                sellPanel.repaint();

            } else if (e.getSource() == backButton) {
                admin.writeLog("Back button", "Clicked");
                GameScreen.getInstance().gotoMenu("main");


            } else if (e.getSource() == exitButton) {
                admin.writeExitLog();
                System.exit(0);

            } else if (e.getSource() == buy_sellButton) {
                admin.writeLog(buy_sellButton.getText() + " button", "Clicked");
                if (infoPanel.selectedCard == null) {
                    admin.writeLog("Error", "No card is selected");
                    Message.showErrorMessage("No Card Selected", "Please select a Card");
                } else {
                    if (Message.showConfirmMessage("warning", "are you sure?")) {
                        // buying a card
                        if (buy_sellButton.getText().equals("Buy")) {
                            // not enough gems
                            if (admin.buyCard(infoPanel.selectedCardName) == 0) {
                                admin.writeLog("Error", "Not enough gems");
                                Message.showErrorMessage("Error", "Not enough Gems");
                            } else { // bought successfully
                                admin.writeLog("Buy", "Successful");
                                updateCards();
                                buyPanel.repaint();
                                sellPanel.repaint();
                                infoPanel.updateCard("");
                            }
// selling a card
                        } else {
                            admin.writeLog("Sell", "Successful");
                            admin.sellCard(infoPanel.selectedCardName);
                            updateCards();
                            buyPanel.repaint();
                            sellPanel.repaint();
                            infoPanel.updateCard("");
                        }
                    }
                }
            }


        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource() == buyPanel) {
                for (int i = 0; i < buyableGCards.size(); i++) {
                    if (buyableGCards.get(i).isClicked(e)) {
                        admin.writeLog("Card selected", buyableGCards.get(i).getCardName());
                        infoPanel.updateCard(buyableGCards.get(i).getCardName());
                        break;
                    }
                }

            } else if (e.getSource() == sellPanel) {
                for (int i = 0; i < salableGCards.size(); i++) {
                    if (salableGCards.get(i).isClicked(e)) {
                        admin.writeLog("Card selected", salableGCards.get(i).getCardName());
                        infoPanel.updateCard(salableGCards.get(i).getCardName());
                        break;
                    }
                }

            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}