package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class PlayMenu extends GameMenu {


    private Actions action;
    private Playground playground;
    private PassivePanel passivePanel;

    public PlayMenu() {
        action = new Actions();
        setLayout(null);
        setBounds(GUIConsts.playMenu.menuBounds);
        setSize(GUIConsts.playMenu.menuSize);
        setPreferredSize(GUIConsts.playMenu.menuSize);
        initComponents();
    }

    private void initComponents() {
        playground = new Playground();
        passivePanel = new PassivePanel();
        add(playground);
        add(passivePanel);
        playground.setVisible(false);
    }

    void refresh() {
        playground.setVisible(false);
        passivePanel.setVisible(true);
        passivePanel.refresh();
        playground.refresh();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d=addRenderingHint(g);
        paintGraphics(g2d);
    }

    private void paintGraphics(Graphics2D g2d) {
        try {
            BufferedImage backgroundImage = ImageIO.read(GUIConsts.playMenu.menuBackground_image);
            g2d.drawImage(backgroundImage, 0, 0, GUIConsts.playMenu.menuBounds.width, GUIConsts.playMenu.menuBounds.height, null);

        } catch (IOException e) {


        }
    }
    private void gotoPlayground() {
        playground.setVisible(true);
        passivePanel.setVisible(false);
        playground.updateInfo();
    }

    // a panel for playground
    private class Playground extends JPanel {
        // panel components
        private GCard[] handCards = new GCard[12], groundCards = new GCard[7];
        private GCard weaponCard, cardOverview;
        private GHero playerHero;
        private GHeroPower heroPower;
        private JButton endTurnButton, backButton;
        private JList<String> gameEvents;
        private JScrollPane eventsPane;
        private JLabel manaLabel, heroHpLabel, remainingCardsLabel;

        // panel fields
        private ArrayList<String> gameEventsName;
        private ArrayList<String> handCardsName, groundCardsName;
        private String playerHeroName;

        public Playground() {
            setLayout(null);
            setBounds(GUIConsts.playMenu.playground_bounds);
            gameEventsName = new ArrayList<>();
            addMouseMotionListener(action);
            addMouseListener(action);
            initComponents();
            repaint();
        }

        private void initComponents() {
            // end turn button,back button
            endTurnButton = new JButton("end turn");
            endTurnButton.setBounds(GUIConsts.playMenu.endTurnButton_bounds);
            endTurnButton.addActionListener(action);
            add(endTurnButton);

            backButton = new JButton("Back");
            backButton.setBounds(GUIConsts.playMenu.backButton_bounds);
            backButton.addActionListener(action);
            add(backButton);

            // mana,hero hp,remaining cards labels
            heroHpLabel = new JLabel("30");
            heroHpLabel.setBounds(GUIConsts.playMenu.heroHpLabel_bounds);
//            heroHpLabel.setVisible(true);
            heroHpLabel.setFont(GUIConsts.playMenu.heroHpLabel_font);
            heroHpLabel.setForeground(GUIConsts.playMenu.heroHpLabel_foreColor);
            add(heroHpLabel);

            manaLabel = new JLabel("1");
            manaLabel.setBounds(GUIConsts.playMenu.manaLabel_bounds);
            manaLabel.setFont(GUIConsts.playMenu.manaLabel_font);
            manaLabel.setForeground(GUIConsts.playMenu.manaLabel_foreColor);
            add(manaLabel);

            remainingCardsLabel = new JLabel("Cards in Deck:");
            remainingCardsLabel.setBounds(GUIConsts.playMenu.remainingCard_bounds);
            remainingCardsLabel.setFont(GUIConsts.playMenu.remainingCardsLabel_font);
            remainingCardsLabel.setForeground(GUIConsts.playMenu.remainingCardsLabel_foreColor);
            add(remainingCardsLabel);

            // event list
            eventsPane = new JScrollPane();
            gameEvents = new JList<>();
            eventsPane.setBounds(GUIConsts.playMenu.gameEventList_bounds);
            eventsPane.setViewportView(gameEvents);
            gameEvents.setLayoutOrientation(JList.VERTICAL);
            add(eventsPane);
        }


        private void setCardOverview(int i) {
            cardOverview = new GCard(GUIConsts.playMenu.cardOverview_size, handCards[i].getCardName(), GUIConsts.playMenu.cardOverview_location);
            repaint();
        }

        // a void for updating all labels and cards
        private void updateInfo() {
            manaLabel.setText(admin.getMana() + "/" + Math.min(admin.getTurn(), 10));
            remainingCardsLabel.setText("Cards in Deck: " + admin.getRemainingCards());
            playerHeroName = admin.getDeckHero();
            handCardsName = (ArrayList) admin.getHandCards().clone();
            groundCardsName = (ArrayList) admin.getGroundCards().clone();
            updateGCards_GHero();
            updateEventList();
            repaint();
        }

        private void updateGCards_GHero() {
            handCards = new GCard[12];
            groundCards = new GCard[7];
            playerHero = new GHero(GUIConsts.playMenu.playerHero_size, playerHeroName, GUIConsts.playMenu.playerHero_location);
            heroPower = new GHeroPower(GUIConsts.playMenu.heroPower_size, playerHeroName, GUIConsts.playMenu.heroPower_location);
            for (int i = 0; i < handCardsName.size(); i++) {
                handCards[i] = new GCard(GUIConsts.playMenu.handCard_size, handCardsName.get(i), GUIConsts.playMenu.handCards_location[i]);
            }
            for (int i = 0; i < groundCardsName.size(); i++) {
                groundCards[i] = new GCard(GUIConsts.playMenu.groundCard_size,
                        groundCardsName.get(i),
                        GUIConsts.playMenu.groundCards_location[i]);
            }
        }

        private void updateEventList() {

            String[] events=new String[gameEventsName.size()];
            events = gameEventsName.toArray(events);
            gameEvents.setListData(events);

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = addRenderingHint(g);
            g2d.drawImage(new ImageIcon(GUIConsts.playMenu.backgroundImage.getPath()).getImage(), 0, 0, 1200, 700, null);

            paintCards_Hero(g2d);
            if (cardOverview != null)
                cardOverview.render(g2d);
        }

        private void paintCards_Hero(Graphics2D g2d) {
            // rendering cards
            for (int i = 0; i < 12; i++) {
                if (handCards[i] != null) {
                    handCards[i].render(g2d);
                }
            }
            for (int i = 0; i < 7; i++) {
                if (groundCards[i] != null) {
                    groundCards[i].render(g2d);
                }
            }
            if (playerHero != null)
                playerHero.render(g2d);
            if (heroPower != null)
                heroPower.render(g2d);
        }


        private void refresh() {

        }
    }

    // a panel for passive info
    private class PassivePanel extends JPanel {
        // passive buttons
        private JButton passive1, passive2, passive3, passiveBackButton;

        public PassivePanel() {
            setLayout(null);

            setBounds(GUIConsts.playMenu.passivePanel_bounds);
            setBorder(GUIConsts.playMenu.passivePanel_border);
            setOpaque(false);
            initButtons();

        }

        private void initButtons() {
            passive1 = new JButton(admin.getPassiveText(0));
            passive1.setBounds(GUIConsts.playMenu.passive1Button_bounds);
            passive1.addActionListener(action);
            add(passive1);

            passive2 = new JButton(admin.getPassiveText(1));
            passive2.setBounds(GUIConsts.playMenu.passive2Button_bounds);
            passive2.addActionListener(action);
            add(passive2);

            passive3 = new JButton(admin.getPassiveText(2));
            passive3.setBounds(GUIConsts.playMenu.passive3Button_bounds);
            passive3.addActionListener(action);
            add(passive3);

            passiveBackButton = new JButton("Back");
            passiveBackButton.setBounds(GUIConsts.playMenu.passiveBackButton_bounds);
            passiveBackButton.addActionListener(action);
            add(passiveBackButton);

        }

        private void refresh() {
            passive1.setText(admin.getPassiveText(0));
            passive2.setText(admin.getPassiveText(1));
            passive3.setText(admin.getPassiveText(2));
        }
    }

    private class Actions implements ActionListener, MouseListener, MouseMotionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
// passive panel actions
            if (e.getSource() == passivePanel.passive1) {
                admin.writeLog("Passive Selected",admin.getPassiveText(0));
                admin.selectPassive(0);
                gotoPlayground();
            } else if (e.getSource() == passivePanel.passive2) {
                admin.writeLog("Passive Selected",admin.getPassiveText(1));

                admin.selectPassive(1);
                gotoPlayground();
            } else if (e.getSource() == passivePanel.passive3) {
                admin.writeLog("Passive Selected",admin.getPassiveText(2));

                admin.selectPassive(2);
                gotoPlayground();

            } else if (e.getSource() == passivePanel.passiveBackButton) {

                admin.writeLog("Back button","Clicked");
                GameScreen.getInstance().gotoMenu("main");

            }

            // playground panel actions
            if (e.getSource() == playground.endTurnButton) {
                admin.writeLog("End Turn button","Clicked");

                admin.nextTurn();
                playground.gameEventsName.add("end turn");
                playground.updateInfo();

            } else if (e.getSource() == playground.backButton) {
                if (Message.showConfirmMessage("Back to main menu", "Are you sure??")) {
                    admin.writeLog("Back button","Clicked");
                    playground.gameEventsName = new ArrayList<>();
                    playground.gameEvents.removeAll();
                    GameScreen.getInstance().gotoMenu("main");
                }

            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            // playground panel cards
            if (e.getSource() == playground) {
                for (int i = 11; i >= 0; i--) {
                    // checking whether a card is clicked
                    if (playground.handCards[i] != null && playground.handCards[i].isClicked(e)) { // card is clicked
                        int a = admin.playCard(playground.handCards[i].getCardName());

                        // if block is just created for writing event list
                        if (a == 1) {  // minion card played
                            admin.writeLog("Minion Card Played",playground.handCards[i].getCardName());
                            playground.gameEventsName.add("Minion Played: "+playground.handCards[i].getCardName());

                        } else if (a == 2) {  // spell card played
                            admin.writeLog("Spell Card Played",playground.handCards[i].getCardName());
                            playground.gameEventsName.add("Spell Played: "+playground.handCards[i].getCardName());

                        } else if (a == 3) { // weapon card played
                            admin.writeLog("Weapon Card Played",playground.handCards[i].getCardName());
                            playground.gameEventsName.add("Weapon Played: "+playground.handCards[i].getCardName());

                        }else if(a==-1){
                            admin.writeLog("Error","Deck is full");
                        }else if(a==0){

                            admin.writeLog("Error","Not enough mana for " +playground.handCards[i].getCardName());

                        }
                        playground.updateInfo();
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

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            boolean onCard = false;
            for (int i = 11; i >= 0; i--) {
                if (playground.handCards[i] != null && playground.handCards[i].isClicked(e)) {
                    playground.setCardOverview(i);
                    onCard = true;
                    break;
                }
            }
            if (onCard == false) {
                playground.cardOverview = null;
                playground.repaint();
            }
        }
    }
}

