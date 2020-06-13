package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class CollectionsMenu extends GameMenu {

    private String currentPanel = "All Cards";
    private JButton allCardsButton, decksButton, backButton, exitButton;

    private Actions actions;
    // inner panels
    private AllCardsPanel allCardsPanel;
    private DecksPanel decksPanel;
    private AddCardPanel addCardPanel;
    private CreateDeckPanel createDeckPanel;

    public CollectionsMenu() {
        actions = new Actions();
        initMenu();


    }

    // sets the bounds and other properties of the menu
    private void initMenu() {
        setLayout(null);
        setBounds(GUIConsts.collectionsMenu.menuBounds);
        setSize(GUIConsts.collectionsMenu.menuSize);
        setPreferredSize(GUIConsts.collectionsMenu.menuSize);
        initButtons();
        initPanels();

    }

    private void initButtons() {
        allCardsButton = new JButton("All Cards");
        allCardsButton.setBounds(GUIConsts.collectionsMenu.allCardsButton_bounds);
        allCardsButton.addActionListener(actions);
        add(allCardsButton);

        decksButton = new JButton("Decks");
        decksButton.setBounds(GUIConsts.collectionsMenu.decksButton_bounds);
        decksButton.addActionListener(actions);
        add(decksButton);

        backButton = new JButton("Back");
        backButton.setBounds(GUIConsts.collectionsMenu.backButton_bounds);
        backButton.addActionListener(actions);
        add(backButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(GUIConsts.collectionsMenu.exitButton_bounds);
        exitButton.addActionListener(actions);
        add(exitButton);

    }

    private void initPanels() {
        allCardsPanel = new AllCardsPanel();
        decksPanel = new DecksPanel();
        addCardPanel = new AddCardPanel();
        createDeckPanel = new CreateDeckPanel();
        add(allCardsPanel);
        add(decksPanel);
        add(addCardPanel);
        add(createDeckPanel);
        gotoPanel("All");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d=addRenderingHint(g);
        paintGraphics(g2d);
    }

    private void paintGraphics(Graphics2D g2d) {
        try {
            BufferedImage backgroundImage = ImageIO.read(GUIConsts.collectionsMenu.background_image);
            g2d.drawImage(backgroundImage, 0, 0, GUIConsts.collectionsMenu.menuBounds.width, GUIConsts.collectionsMenu.menuBounds.height, null);

        } catch (IOException e) {


        }
    }

    // a panel to show a selected card,bounds are fixed
    private class PalettePanel extends JPanel {
        private JButton paletteButton;
        private String paletteCard;
        private GCard paletteGCard;

        public PalettePanel(String buttonText) {
            setLayout(null);
            setBounds(GUIConsts.collectionsMenu.palettePanel_bounds);
            setBorder(GUIConsts.collectionsMenu.palettePanel_border);
            setOpaque(false);
            paletteButton = new JButton(buttonText);
            paletteButton.setBounds(GUIConsts.collectionsMenu.palettePanelButton_bounds);
            add(paletteButton);
        }

        public void setAction(ActionListener actionListener) {
            paletteButton.addActionListener(actionListener);
        }

        public void updateCard(String cardName) {
            this.paletteCard = cardName;
            if (paletteCard.equals("")) {
                paletteGCard = null;
            } else {
                paletteGCard = new GCard(GUIConsts.collectionsMenu.paletteGCard_size, paletteCard, GUIConsts.collectionsMenu.paletteGCard_point);
            }
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = addRenderingHint(g);
            if (paletteGCard != null) {
                paletteGCard.render(g2d);
            }
        }
    }

    private class Showcase extends JScrollPane {
        private ShowcasePanel showcasePanel;
        private int rowLength, horizontalAlign, verticalAlign;
        private ArrayList<String> showcaseCards;
        private ArrayList<GCard> showcaseGCards;

        public Showcase(Rectangle bounds, int rowLength, int horizontalAlign, int verticalAlign) {
            this.rowLength = rowLength;
            this.horizontalAlign = horizontalAlign;
            this.verticalAlign = verticalAlign;
            setBounds(bounds);
            setHorizontalScrollBar(null);
            setPreferredSize(new Dimension(bounds.width, bounds.height));
            showcasePanel = new ShowcasePanel(getPreferredSize());
            setViewportView(showcasePanel);
            setOpaque(false);
            getViewport().setOpaque(false);
            showcaseCards = new ArrayList<>();
            showcaseGCards = new ArrayList<>();
        }

        public void updateCardList(ArrayList<String> cards, boolean isLocked) {
            this.setShowcaseCards(cards);
            showcaseGCards = new ArrayList<>();
            for (int i = 0; i < cards.size(); i++) {
                GCard gCard = new GCard(GUIConsts.collectionsMenu.showcaseImage_size, cards.get(i), cardPos(i));
                gCard.setLocked(isLocked);
                showcaseGCards.add(gCard);
            }
            showcasePanel.repaint();
        }

        public void addAction(MouseListener mouseListener) {
            showcasePanel.addMouseListener(mouseListener);
        }

        // a panel for painting the cards in a showcase
        private class ShowcasePanel extends JPanel {
            public ShowcasePanel(Dimension panelSize) {
                setLayout(null);
                setBounds(0, 0, panelSize.width, panelSize.height);
                setPreferredSize(panelSize);
                setOpaque(false);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = addRenderingHint(g);
                if (showcaseGCards != null && showcaseGCards.size() > 0) {
                    for (int i = 0; i < showcaseGCards.size(); i++) {
                        showcaseGCards.get(i).render(g2d);
                    }
                    int x = showcaseGCards.get(showcaseGCards.size() - 1).getyPos() + GUIConsts.collectionsMenu.showcaseImage_size.height + 20;
                    updateSize(x);
                }
            }

            private void updateSize(int newHeight) {
                setPreferredSize(new Dimension(getSize().width, Math.max(newHeight, getSize().height)));
            }

        }

        private Point cardPos(int i) {
            int column = i % rowLength, row = i / rowLength;
            int x = horizontalAlign +
                    column * (GUIConsts.collectionsMenu.showcaseImage_size.width + horizontalAlign);
            int y = verticalAlign +
                    row * (GUIConsts.collectionsMenu.showcaseImage_size.height + verticalAlign);
            return new Point(x, y);
        }

        public void setRowLength(int rowLength) {
            this.rowLength = rowLength;
        }

        public void setHorizontalAlign(int horizontalAlign) {
            this.horizontalAlign = horizontalAlign;
        }

        public void setVerticalAlign(int verticalAlign) {
            this.verticalAlign = verticalAlign;
        }

        public void setShowcaseCards(ArrayList<String> showcaseCards) {
            this.showcaseCards = showcaseCards;
        }

        public ArrayList<String> getShowcaseCards() {
            return showcaseCards;
        }
    }

    private class AllCardsPanel extends JPanel {
        // components
        private JButton allButton, neutralButton, mageButton, rogueButton, warlockButton, paladinButton, hunterButton;
        private JTextField searchField;
        private JSlider manaSortSlider;
        private JRadioButton ownedCardsRadio, notOwnedCardsRadio;
        private ButtonGroup bg;
        private Showcase allCardsShowcase;

        // fields
        private String cardClass = "All";
        private ArrayList<String> allCardsName;


        public AllCardsPanel() {
            setLayout(null);
            setBounds(GUIConsts.collectionsMenu.allCardsPanel_bounds);
            setBorder(GUIConsts.collectionsMenu.panelBorder);
            setOpaque(false);
            initButtons();
            initComponents();
            setShowcase();
        }


        private void initButtons() {
            allButton = new JButton("All");
            allButton.setBounds(GUIConsts.collectionsMenu.allButton_bounds);
            allButton.addActionListener(actions);
            add(allButton);

            neutralButton = new JButton("Neutral");
            neutralButton.setBounds(GUIConsts.collectionsMenu.neutralButton_bounds);
            neutralButton.addActionListener(actions);
            add(neutralButton);

            mageButton = new JButton("Mage");
            mageButton.setBounds(GUIConsts.collectionsMenu.mageButton_bounds);
            mageButton.addActionListener(actions);
            add(mageButton);

            rogueButton = new JButton("Rogue");
            rogueButton.setBounds(GUIConsts.collectionsMenu.rougeButton_bounds);
            rogueButton.addActionListener(actions);
            add(rogueButton);

            warlockButton = new JButton("Warlock");
            warlockButton.setBounds(GUIConsts.collectionsMenu.warlockButton_bounds);
            warlockButton.addActionListener(actions);
            add(warlockButton);

            paladinButton = new JButton("Paladin");
            paladinButton.setBounds(GUIConsts.collectionsMenu.paladinButton_bounds);
            paladinButton.addActionListener(actions);
            add(paladinButton);

            hunterButton = new JButton("Hunter");
            hunterButton.setBounds(GUIConsts.collectionsMenu.hunterButton_bounds);
            hunterButton.addActionListener(actions);
            add(hunterButton);

        }

        private void initComponents() {
            // search field
            searchField = new JTextField();
            searchField.setBounds(GUIConsts.collectionsMenu.searchField_bounds);
            searchField.getDocument().addDocumentListener(actions);
            add(searchField);
            // slider
            manaSortSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 10, 0);
            manaSortSlider.setBounds(GUIConsts.collectionsMenu.manaSortSlider_bounds);
            manaSortSlider.setMinorTickSpacing(1);
            manaSortSlider.setMajorTickSpacing(1);
            manaSortSlider.setPaintLabels(true);
            manaSortSlider.setPaintTicks(true);
            manaSortSlider.addChangeListener(actions);
            manaSortSlider.setOpaque(false);
            manaSortSlider.setForeground(GUIConsts.generalLabel_foreColor);
            add(manaSortSlider);
// radio buttons
            ownedCardsRadio = new JRadioButton("Owned", true);
            notOwnedCardsRadio = new JRadioButton("Not owned");
            ownedCardsRadio.setBounds(GUIConsts.collectionsMenu.ownedCardsRadio_bounds);
            notOwnedCardsRadio.setBounds(GUIConsts.collectionsMenu.notOwnedCardsRadio_bounds);
            ownedCardsRadio.addActionListener(actions);
            notOwnedCardsRadio.addActionListener(actions);
            ownedCardsRadio.setOpaque(false);
            notOwnedCardsRadio.setOpaque(false);
            ownedCardsRadio.setForeground(GUIConsts.generalLabel_foreColor);
            notOwnedCardsRadio.setForeground(GUIConsts.generalLabel_foreColor);
            bg = new ButtonGroup();
            bg.add(ownedCardsRadio);
            bg.add(notOwnedCardsRadio);
            add(ownedCardsRadio);
            add(notOwnedCardsRadio);
// showcase
            allCardsShowcase = new Showcase(GUIConsts.collectionsMenu.allCardsShowcase_bounds,
                    GUIConsts.collectionsMenu.allCardsShowcase_rowLength,
                    GUIConsts.collectionsMenu.allCardsShowcase_hAlign,
                    GUIConsts.collectionsMenu.allCardsShowcase_vAlign);
            allCardsShowcase.addAction(actions);
            add(allCardsShowcase);
        }

        void setShowcase() {
// sets the cards that must be shown in showcase
            allCardsName = (ArrayList) admin.getCardsWithConditions(cardClass, ownedCardsRadio.isSelected(), manaSortSlider.getValue(), searchField.getText()).clone();
            allCardsShowcase.updateCardList(allCardsName, notOwnedCardsRadio.isSelected());

        }
    }


    private class DecksPanel extends JPanel {

        // components
        private JList<String> decksList;
        private JButton createDeckButton, deleteDeckButton, addCardButton, changeNameButton, setCurrentButton;
        private Showcase decksShowcase;
        private PalettePanel deckCardRemovePalette;

        // fields
        private ArrayList<String> deckNamesList;
        private String selectedDeckName = "";

        public DecksPanel() {
            setLayout(null);
            setBorder(GUIConsts.collectionsMenu.panelBorder);
            setBounds(GUIConsts.collectionsMenu.decksPanel_bounds);
            setOpaque(false);
            deckNamesList = new ArrayList<>();
            initButtons();
            initComponents();
            updateDeckList();
        }

        private void initButtons() {
            createDeckButton = new JButton("Create");
            createDeckButton.setBounds(GUIConsts.collectionsMenu.decksPanel_createButton_bounds);
            createDeckButton.addActionListener(actions);
            add(createDeckButton);

            deleteDeckButton = new JButton("Delete");
            deleteDeckButton.setBounds(GUIConsts.collectionsMenu.decksPanel_deleteButton_bounds);
            deleteDeckButton.addActionListener(actions);
            add(deleteDeckButton);

            addCardButton = new JButton("Add Card");
            addCardButton.setBounds(GUIConsts.collectionsMenu.decksPanel_addCardButton_bounds);
            addCardButton.addActionListener(actions);
            add(addCardButton);

            changeNameButton = new JButton("Change Name");
            changeNameButton.setBounds(GUIConsts.collectionsMenu.deckPanel_changeNameButton_bounds);
            changeNameButton.addActionListener(actions);
            add(changeNameButton);

            setCurrentButton = new JButton("Set Current");
            setCurrentButton.setBounds(GUIConsts.collectionsMenu.deckPanel_setCurrentButton);
            setCurrentButton.addActionListener(actions);
            add(setCurrentButton);
        }

        private void initComponents() {
            // decklist
            decksList = new JList<>();
            decksList.setBounds(GUIConsts.collectionsMenu.deckList_bounds);
            decksList.setForeground(GUIConsts.generalLabel_foreColor);
            decksList.setOpaque(false);
            decksList.addListSelectionListener(actions);
            add(decksList);

            // decksShowcase
            decksShowcase = new Showcase(GUIConsts.collectionsMenu.decksShowcase_bounds,
                    GUIConsts.collectionsMenu.decksShowcase_rowLength,
                    GUIConsts.collectionsMenu.decksShowcase_hAlign,
                    GUIConsts.collectionsMenu.decksShowcase_vAlign);
            decksShowcase.addAction(actions);
            add(decksShowcase);
// deckCardRemovePalette
            deckCardRemovePalette = new PalettePanel("Remove");
            deckCardRemovePalette.setAction(actions);
            add(deckCardRemovePalette);

        }

        private void updateDeckList() {
            String[] deckArray = admin.getDecksForList();
            decksList.setListData(deckArray);

        }

        private void updateShowcae() {
            if (!selectedDeckName.equals("")) {
                decksShowcase.updateCardList(admin.getDeckCards(selectedDeckName), false);
            } else {
                decksShowcase.updateCardList(new ArrayList<String>(), false);
            }

        }
    }


    private class AddCardPanel extends JPanel {
        // components
        private JButton addCardPanelBackButton;
        private Showcase addCardShowcase;
        private PalettePanel addCardPalette;

        // fields
        private String selectedDeck = "";
        private String selectedCard = "";

        public AddCardPanel() {
            setLayout(null);
            setBounds(GUIConsts.collectionsMenu.addCardPanel_bounds);
            setBorder(GUIConsts.collectionsMenu.panelBorder);
            setOpaque(false);
            initComponents();

        }

        private void initComponents() {
            // back button
            addCardPanelBackButton = new JButton("Back");
            addCardPanelBackButton.setBounds(GUIConsts.collectionsMenu.addCardPanelBackButton_bounds);
            addCardPanelBackButton.addActionListener(actions);
            add(addCardPanelBackButton);

            // showcase
            addCardShowcase = new Showcase(GUIConsts.collectionsMenu.addCardShowcase_bounds,
                    GUIConsts.collectionsMenu.addCardShowcase_rowLength,
                    GUIConsts.collectionsMenu.addCardShowcase_hAlign,
                    GUIConsts.collectionsMenu.addCardShowcase_vAlign);
            addCardShowcase.addAction(actions);
            add(addCardShowcase);
            // palette
            addCardPalette = new PalettePanel("Add");
            addCardPalette.setAction(actions);
            add(addCardPalette);
        }

        private void setShowcaseCards() {

            addCardShowcase.updateCardList(admin.getAddableCards(selectedDeck), false);
        }

        private void setPaletteCard() {

            addCardPalette.updateCard(selectedCard);
        }
    }

    private class CreateDeckPanel extends JPanel {

        // menu components
        private JLabel createDeckHeroLabel, createDeckNameLabel;
        private JTextField createDeckNameField;
        private JButton createButton, createDeckBackButton;

        private GHero gMage, gRogue, gWarlock, gPaladin, gHunter;

        // menu fields
        private String selectedHero = "Mage";
        private String selectedDeckName = "";

        public CreateDeckPanel() {
            setLayout(null);
            setLayout(null);
            setBounds(GUIConsts.collectionsMenu.createDeckPanel_bounds);
            setBorder(GUIConsts.collectionsMenu.panelBorder);
            setOpaque(false);
            addMouseListener(actions);
            initComponents();
            initHeroes();
        }

        private void initComponents() {

            // labels
            createDeckHeroLabel = new JLabel("Hero Class:Mage");
            createDeckHeroLabel.setForeground(GUIConsts.generalLabel_foreColor);
            createDeckHeroLabel.setBounds(GUIConsts.collectionsMenu.createDeckHeroLabel_bounds);
            add(createDeckHeroLabel);

            createDeckNameLabel = new JLabel("Name:");
            createDeckNameLabel.setForeground(GUIConsts.generalLabel_foreColor);
            createDeckNameLabel.setBounds(GUIConsts.collectionsMenu.createDeckNameLabel_bounds);
            add(createDeckNameLabel);

            // test field
            createDeckNameField = new JTextField();
            createDeckNameField.setBounds(GUIConsts.collectionsMenu.createDeckNameField_bounds);
            add(createDeckNameField);

            // buttons
            createButton = new JButton("Create");
            createButton.setBounds(GUIConsts.collectionsMenu.createButton_bounds);
            createButton.addActionListener(actions);
            add(createButton);

            createDeckBackButton = new JButton("Back");
            createDeckBackButton.setBounds(GUIConsts.collectionsMenu.createDeckBackButton_bounds);
            createDeckBackButton.addActionListener(actions);
            add(createDeckBackButton);

        }

        private void initHeroes() {
            gMage = new GHero(GUIConsts.collectionsMenu.createDeckPanelHero_size,
                    "Mage", GUIConsts.collectionsMenu.createDeckGMage_point);
            gRogue = new GHero(GUIConsts.collectionsMenu.createDeckPanelHero_size,
                    "Rogue", GUIConsts.collectionsMenu.createDeckGRogue_point);
            gWarlock = new GHero(GUIConsts.collectionsMenu.createDeckPanelHero_size,
                    "Warlock", GUIConsts.collectionsMenu.createDeckGWarlock_point);
            gPaladin = new GHero(GUIConsts.collectionsMenu.createDeckPanelHero_size,
                    "Paladin", GUIConsts.collectionsMenu.createDeckGPaladin_point);
            gHunter = new GHero(GUIConsts.collectionsMenu.createDeckPanelHero_size,
                    "Hunter", GUIConsts.collectionsMenu.createDeckGHunter_point);
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = addRenderingHint(g);
            if (gMage != null && gRogue != null && gWarlock != null && gPaladin != null && gHunter != null) {
                gMage.render(g2d);
                gRogue.render(g2d);
                gWarlock.render(g2d);
                gPaladin.render(g2d);
                gHunter.render(g2d);
                System.out.println("rendered");
            } else {
                System.out.println("Can't Render");
            }
        }
    }

    private void gotoPanel(String panelName) {
        allCardsPanel.setVisible(false);
        decksPanel.setVisible(false);
        addCardPanel.setVisible(false);
        createDeckPanel.setVisible(false);
        switch (panelName) {
            case "All": {
                currentPanel = "All";
                allCardsPanel.setVisible(true);
                break;
            }
            case "Decks": {
                currentPanel = "Decks";
                decksPanel.setVisible(true);
                break;
            }
            case "Add Card": {
                currentPanel = "Add Card";
                addCardPanel.setVisible(true);
                break;
            }
            case "Create Deck": {
                currentPanel = "Create Deck";
                createDeckPanel.setVisible(true);
                break;
            }
        }
    }

    private class Actions implements ActionListener, MouseListener, ChangeListener, DocumentListener, ListSelectionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // common buttons
            if (e.getSource() == allCardsButton) {

                admin.writeLog("Panel","All Cards");
                gotoPanel("All");
            } else if (e.getSource() == decksButton) {

                admin.writeLog("Panel","Decks");
                gotoPanel("Decks");

            } else if (e.getSource() == backButton) {

                admin.writeLog("Back button","back to main menu");
                GameScreen.getInstance().gotoMenu("main");

            } else if (e.getSource() == exitButton) {

                admin.writeExitLog();
                System.exit(0);
            }

            // all Cards panel buttons and radio buttons
            if (e.getSource() == allCardsPanel.allButton) {

                admin.writeLog("All cards panel CardClass filter","All Cards");
                allCardsPanel.cardClass = "All";
                allCardsPanel.setShowcase();

            } else if (e.getSource() == allCardsPanel.neutralButton) {

                admin.writeLog("All cards panel CardClass filter","Neutral");
                allCardsPanel.cardClass = "Neutral";
                allCardsPanel.setShowcase();

            } else if (e.getSource() == allCardsPanel.mageButton) {

                admin.writeLog("All cards panel CardClass filter","Mage");
                allCardsPanel.cardClass = "Mage";
                allCardsPanel.setShowcase();

            } else if (e.getSource() == allCardsPanel.rogueButton) {

                admin.writeLog("All cards panel CardClass filter","Rogue");
                allCardsPanel.cardClass = "Rogue";
                allCardsPanel.setShowcase();

            } else if (e.getSource() == allCardsPanel.warlockButton) {

                admin.writeLog("All cards panel CardClass filter","Warlock");
                allCardsPanel.cardClass = "Warlock";
                allCardsPanel.setShowcase();

            } else if (e.getSource() == allCardsPanel.paladinButton) {

                admin.writeLog("All cards panel CardClass filter","Paladin");
                allCardsPanel.cardClass = "Paladin";
                allCardsPanel.setShowcase();

            } else if (e.getSource() == allCardsPanel.hunterButton) {

                admin.writeLog("All cards panel CardClass filter","Hunter");
                allCardsPanel.cardClass = "Hunter";
                allCardsPanel.setShowcase();

            } else if (e.getSource() == allCardsPanel.ownedCardsRadio || e.getSource() == allCardsPanel.notOwnedCardsRadio) {

                if(allCardsPanel.ownedCardsRadio.isSelected())
                    admin.writeLog("All cards panel owned Cards filter","Owned");
else
                    admin.writeLog("All cards panel owned Cards filter","Not Owned");

                allCardsPanel.setShowcase();
            }
            //deck panel buttons
            if (e.getSource() == decksPanel.createDeckButton) {


                admin.writeLog("Panel","Create deck");
                gotoPanel("Create Deck");

            } else if (e.getSource() == decksPanel.deleteDeckButton) {
                admin.writeLog("Delete deck button","clicked");

                if (decksPanel.decksList.getSelectedIndex() == -1) {
admin.writeLog("Error","Not deck is selected");
                    Message.showErrorMessage("No deck selected", "Please select a deck");
                } else {
                    if (admin.deleteDeck(decksPanel.selectedDeckName) == 0) {
                        admin.writeLog("Error","Selected deck is the current deck");
                        Message.showErrorMessage("Current deck selected", "Can't delete current deck");
                    } else {
                        admin.writeLog("Deck deleted",decksPanel.selectedDeckName);

                        decksPanel.updateDeckList();
                        decksPanel.selectedDeckName = "";
                        decksPanel.updateShowcae();
                        decksPanel.deckCardRemovePalette.updateCard("");
                    }
                }
            } else if (e.getSource() == decksPanel.addCardButton) {
                admin.writeLog("Add Card Button","Clicked");

                if (decksPanel.selectedDeckName.equals("")) {
                    admin.writeLog("Error","Not deck is selected");
                    Message.showErrorMessage("No deck Selected", "Please select a deck");
                } else {
                    admin.writeLog("Panel","Add card panel");

                    addCardPanel.selectedDeck = decksPanel.selectedDeckName;
                    addCardPanel.setShowcaseCards();

                    gotoPanel("Add Card");
                }

            } else if (e.getSource() == decksPanel.changeNameButton) {
                admin.writeLog("change name button","Clicked");

                if (decksPanel.decksList.getSelectedIndex() == -1) {
                    admin.writeLog("Error","Not deck is selected");
                    Message.showErrorMessage("No deck Selected", "Please select a deck");
                } else {
                    String newName = Message.showInputBox("New Name", "Enter New Name");
                    if (admin.renameDeck(decksPanel.selectedDeckName, newName) == 0) {
                        admin.writeLog("Error","Deck with name "+newName+" already exists");
                        Message.showErrorMessage("There is a deck with new Name", "there is a deck with the new name you have inserted");

                    } else {
                        admin.writeLog("Deck name changed","to " + newName);
                        decksPanel.updateDeckList();
                    }
                }


            } else if (e.getSource() == decksPanel.setCurrentButton) {
                admin.writeLog("Set current button","Clicked");

                if (decksPanel.decksList.getSelectedIndex() == -1) {

                    admin.writeLog("Error","Not deck is selected");
                    Message.showErrorMessage("No Deck Selected", "Please select a deck");
                } else {
                    if (admin.setCurrentDeck(decksPanel.selectedDeckName) == 0) { // deck has less than 15 cards
                        admin.writeLog("Error","Deck does not have enough cards");
                        Message.showErrorMessage("Cards are not enough", "Selected Deck should have at least 15 cards");

                    } else {
                        admin.writeLog("Set Current",decksPanel.selectedDeckName+ "  is current deck");
                        decksPanel.updateDeckList();
                        decksPanel.updateShowcae();

                    }
                }

            } else if (e.getSource() == decksPanel.deckCardRemovePalette.paletteButton) {
                if (decksPanel.deckCardRemovePalette.paletteCard.equals("")) {

                    admin.writeLog("Error","No deck is selected");
                    Message.showErrorMessage("No Card Selected", "Please select a card");
                } else {
                    admin.writeLog("Card removed",decksPanel.deckCardRemovePalette.paletteCard);
                    admin.removeCardFromDeck(decksPanel.selectedDeckName, decksPanel.deckCardRemovePalette.paletteCard);
                    decksPanel.deckCardRemovePalette.updateCard("");
                    decksPanel.updateShowcae();
                }
            }

            // add Card Panel buttons
            if (e.getSource() == addCardPanel.addCardPalette.paletteButton) {
                admin.writeLog("Add button","clicked");
                if (addCardPanel.selectedCard.equals("")) {
                    admin.writeLog("Error","No Card is selected");
                    Message.showErrorMessage("No card selected", "Please select a card");
                } else {
                    switch (admin.addCardToDeck(addCardPanel.selectedDeck, addCardPanel.selectedCard)) {
                        case -2: { // deck is full
                            admin.writeLog("Error","Deck is full");
                            Message.showErrorMessage("Full deck", "The Deck is full");
                            break;
                        }
                        case -1: { // class doesn't match (never happens in this part)

                            break;
                        }
                        case 0: { // there are two cards of this name in deck (never happens in this part)

                            break;
                        }
                        case 1: { // action is successful

                            // message must be shown
                            admin.writeLog("Card added",addCardPanel.selectedCard);
                            addCardPanel.selectedCard = "";
                            addCardPanel.setShowcaseCards();
                            addCardPanel.setPaletteCard();
                        }
                    }
                }
            } else if (e.getSource() == addCardPanel.addCardPanelBackButton) {
                admin.writeLog("back button","clicked");
                decksPanel.updateShowcae();
                gotoPanel("Decks");
            }

            // create deck panel buttons
            if (e.getSource() == createDeckPanel.createButton) {
                admin.writeLog("Create button","clicked");
                createDeckPanel.selectedDeckName = createDeckPanel.createDeckNameField.getText().trim();
                if (createDeckPanel.selectedDeckName.equals("")) {
                    admin.writeLog("Error","No name is entered");
                    Message.showErrorMessage("No Name", "Please enter a Name");
                } else {
                    switch (admin.createDeck(createDeckPanel.selectedDeckName, createDeckPanel.selectedHero)) {
                        case -1: { // there are 10 decks
                            admin.writeLog("Error","Deck list is full");
                            Message.showErrorMessage("Error", "You already have 10 decks");
                            break;
                        }
                        case 0: { // deck already exists
                            admin.writeLog("Error","deck already exists");
                            Message.showErrorMessage("Error", "a deck with this name already exists");
                            break;
                        }
                        case 1: { // action successful
                            // message mus be shown
                            admin.writeLog("Deck created",createDeckPanel.createDeckNameField.getText());
                            createDeckPanel.createDeckNameField.setText("");
                            createDeckPanel.selectedDeckName = "";
                            decksPanel.updateDeckList();
                            gotoPanel("Decks");
                            break;
                        }
                    }
                }

            } else if (e.getSource() == createDeckPanel.createDeckBackButton) {
                admin.writeLog("back button","clicked");
                gotoPanel("Decks");

            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        // handling mouse clicks
        @Override
        public void mousePressed(MouseEvent e) {
            // all Cards panel showcase
            if (e.getSource() == allCardsPanel.allCardsShowcase.showcasePanel) {
                if (allCardsPanel.notOwnedCardsRadio.isSelected()) {
                    for (int i = 0; i < allCardsPanel.allCardsShowcase.showcaseGCards.size(); i++) {

                        if (allCardsPanel.allCardsShowcase.showcaseGCards.get(i).isClicked(e)) {
                            admin.writeLog("Direct buy",allCardsPanel.allCardsShowcase.showcaseGCards.get(i).getCardName());
                            GameScreen.getInstance().gotoMenu("store");
                            break;
                        }
                    }
                }

            } else if (e.getSource() == decksPanel.decksShowcase.showcasePanel) {
                for (int i = 0; i < decksPanel.decksShowcase.showcaseGCards.size(); i++) {
                    if (decksPanel.decksShowcase.showcaseGCards.get(i).isClicked(e)) {
                        admin.writeLog("Card selected",decksPanel.decksShowcase.showcaseGCards.get(i).getCardName());
                        decksPanel.deckCardRemovePalette.updateCard(decksPanel.decksShowcase.showcaseGCards.get(i).getCardName());
                        break;
                    }
                }

            } else if (e.getSource() == addCardPanel.addCardShowcase.showcasePanel) {

                for (int i = 0; i < addCardPanel.addCardShowcase.showcaseGCards.size(); i++) {
                    if (addCardPanel.addCardShowcase.showcaseGCards.get(i).isClicked(e)) {
                        admin.writeLog("Card selected",addCardPanel.addCardShowcase.showcaseGCards.get(i).getCardName());

                        addCardPanel.selectedCard = addCardPanel.addCardShowcase.showcaseGCards.get(i).getCardName();
                        addCardPanel.setPaletteCard();
                        break;
                    }
                }

            } else if (e.getSource() == createDeckPanel) {

                if (createDeckPanel.gMage.isClicked(e)) {

                    admin.writeLog("Hero selected","Mage");
                    createDeckPanel.selectedHero = "Mage";
                    createDeckPanel.createDeckHeroLabel.setText("Hero Class:Mage");

                } else if (createDeckPanel.gRogue.isClicked(e)) {

                    admin.writeLog("Hero selected","Rogue");
                    createDeckPanel.selectedHero = "Rogue";
                    createDeckPanel.createDeckHeroLabel.setText("Hero Class:Rogue");

                } else if (createDeckPanel.gWarlock.isClicked(e)) {

                    admin.writeLog("Hero selected","Warlock");
                    createDeckPanel.selectedHero = "Warlock";
                    createDeckPanel.createDeckHeroLabel.setText("Hero Class:Warlock");

                } else if (createDeckPanel.gPaladin.isClicked(e)) {

                    admin.writeLog("Hero selected","Paladin");
                    createDeckPanel.selectedHero = "Paladin";
                    createDeckPanel.createDeckHeroLabel.setText("Hero Class:Paladin");

                } else if (createDeckPanel.gHunter.isClicked(e)) {

                    admin.writeLog("Hero selected","Hunter");
                    createDeckPanel.selectedHero = "Hunter";
                    createDeckPanel.createDeckHeroLabel.setText("Hero Class:Hunter");

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

        // state change of the slider
        @Override
        public void stateChanged(ChangeEvent e) {

            if (e.getSource() == allCardsPanel.manaSortSlider) {
                admin.writeLog("Card filter","Mana to "+allCardsPanel.manaSortSlider.getValue());
                allCardsPanel.setShowcase();
            }
        }


        // actions for search field in all cards menu
        @Override
        public void insertUpdate(DocumentEvent e) {

            allCardsPanel.setShowcase();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {

            allCardsPanel.setShowcase();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }

        // deck list selection listener

        @Override
        public void valueChanged(ListSelectionEvent e) {
// getting the deck name
            decksPanel.deckCardRemovePalette.updateCard("");
            if (decksPanel.decksList.getSelectedIndex() != -1) {
                String str = decksPanel.decksList.getSelectedValue();
                int a = str.lastIndexOf('-');
                decksPanel.selectedDeckName = str.substring(0, a);

                admin.writeLog("Deck selected",decksPanel.selectedDeckName);
                decksPanel.updateShowcae();
                // will be completed
            }
        }
    }
}

