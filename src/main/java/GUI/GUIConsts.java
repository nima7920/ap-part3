package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;

// a class to hold the constant values of the GUI objects
public class GUIConsts {


    // general constants
    public static final Dimension menuSize = new Dimension(1200, 640);
public static final Font generalLabel_font=new Font("Tahoma",Font.PLAIN,11);
public static final Color generalLabel_foreColor=new Color(0,250,50);
    // login menu:
    static class loginMenu {
        // Menu consts
        public static final Rectangle menuBounds = new Rectangle(0, 0, 600, 400);

        // button consts
        public static final Rectangle loginButton_bounds = new Rectangle(230, 240, 150, 40);
        public static final Rectangle createButton_bounds = new Rectangle(230, 290, 150, 40);

        // label consts
        public static final Rectangle welcomeLabel_bounds = new Rectangle(180, 50, 250, 40);
        public static final Font welcomeLabel_font = new Font("Tahoma", Font.PLAIN, 18);

        public static final Rectangle usernameLabel_bounds = new Rectangle(160, 120, 100, 30);
        public static final Font usernameLabel_font = new Font("Tahoma", Font.PLAIN, 11);

        public static final Rectangle passwordLabel_bounds = new Rectangle(160, 160, 100, 30);
        public static final Font passwordLabel_font = new Font("Tahoma", Font.PLAIN, 11);

// test field consts

        public static final Rectangle usernameField_bounds = new Rectangle(280, 120, 200, 30);
        public static final Rectangle passwordField_bounds = new Rectangle(280, 160, 200, 30);
    }

    static class mainMenu {
        // menu consts:
        public static final int height = 600;
        public static final int width = 500;
        public static final Rectangle menuBounds = new Rectangle(0, 0, 1200, 640);
        // GameButton consts:
//        public static final int button_height=50;
//        public static final int button_width=200;
//        public static final int button_x=150;
//        public static final int playButton_y=140;
//        public static final int storeButton_y=200;
//        public static final int collectionsButton_y=260;
//        public static final int statusButton_y=320;
//        public static final int settingsButton_y=380;
//        public static final int exitButton_y=440;
        public static final Rectangle playButton_bounds = new Rectangle(50, 50, 200, 50);
        public static final Rectangle storeButton_bounds = new Rectangle(50, 110, 200, 50);
        public static final Rectangle collectionsButton_bounds = new Rectangle(50, 170, 200, 50);
        public static final Rectangle statusButton_bounds = new Rectangle(50, 230, 200, 50);
        public static final Rectangle settingsButton_bounds = new Rectangle(50, 290, 200, 50);
        public static final Rectangle exitButton_bounds = new Rectangle(50, 350, 200, 50);

        public static final Font titleLabel_font = new Font("Tahoma", Font.PLAIN, 18);
        public static final Font profileLabel_font = new Font("Tahoma", Font.PLAIN, 11);

        // graphics
        public static final File background_image=new File("Game Data//GUI//background images//Main.jpg");
    }

    static class storeMenu {
        public static final Rectangle menuBounds = new Rectangle(0, 0, 1200, 700);
        public static final Dimension menuSize = new Dimension(1200, 700);
        // buttons:
        public static final Rectangle buyCardButton_bounds = new Rectangle(10, 10, 200, 40);
        public static final Rectangle sellCardButton_bounds = new Rectangle(220, 10, 200, 40);
        public static final Rectangle backButton_bounds = new Rectangle(920, 580, 250, 30);
        public static final Rectangle exitButton_bounds = new Rectangle(920, 615, 250, 30);

        // info panel
        public static final Rectangle infoPanel_bounds = new Rectangle(920, 0, 250, 575);
        public static final Border infoPanel_border = BorderFactory.createTitledBorder("Info");
        public static final Dimension infoImageBox_size = new Dimension(200, 280);
        public static final Point infoImageBox_location = new Point(25, 50);
        public static final Rectangle walletLabel_bounds = new Rectangle(10, 10, 230, 30);
        public static final Rectangle rarityLabel_bounds = new Rectangle(10, 410, 230, 30);
        public static final Rectangle costLabel_bounds = new Rectangle(10, 445, 230, 30);
        public static final Rectangle classLabel_bounds = new Rectangle(10, 480, 230, 30);
        public static final Rectangle buy_sellButton_bounds = new Rectangle(10, 515, 230, 30);

        // buy and sell panel
        public static final Rectangle buy_sellPanel_bounds = new Rectangle(0, 0, 900, 600);
        public static final Dimension buy_sellPanel_size = new Dimension(900, 600);
        public static final Rectangle showcase_bounds = new Rectangle(10, 60, 900, 600);
        public static final Dimension buy_sellImage_dimension = new Dimension(150, 210);
        public static final int buy_sellImage_verticalAlign = 30;
        public static final int buy_sellImage_horizontalAlign = 20;
        public static final int buy_sellImage_row = 5;

        public static final File background_image=new File("Game Data//GUI//background images//Store.jpg");


    }

    static class statusMenu {
        public static final Rectangle menu_bounds = new Rectangle(0, 0, 1200, 700);
        public static final Rectangle topDecksTable_bounds = new Rectangle(50,50,200,500);
        public static final Rectangle decksStatusTableTable_bounds = new Rectangle(300,50,800,500);
        public static final Rectangle backButton_bounds = new Rectangle(550,570,100,30);
        public static final Rectangle exitButton_bounds = new Rectangle(550,620,100,30);

        public static final Border topDecksTable_border=BorderFactory.createEtchedBorder();
        public static final Border decksStatusTable_border=BorderFactory.createEtchedBorder();

        public static final File background_image=new File("Game Data//GUI//background images//Status.jpg");

    }

    static class collectionsMenu {
        // menu properties
        public static final Rectangle menuBounds = new Rectangle(0, 0, 1200, 700);
        public static final Dimension menuSize = new Dimension(1200, 700);
        public static final Border panelBorder = BorderFactory.createEtchedBorder();
        // buttons
        public static final Rectangle allCardsButton_bounds = new Rectangle(10, 10, 200, 30);
        public static final Rectangle decksButton_bounds = new Rectangle(220, 10, 200, 30);
        public static final Rectangle backButton_bounds = new Rectangle(950, 10, 100, 30);
        public static final Rectangle exitButton_bounds = new Rectangle(1070, 10, 100, 30);
        // showcase
        public static final Dimension showcaseImage_size = new Dimension(200, 280);

        // palette panel
        public static final Rectangle palettePanel_bounds = new Rectangle(890, 10, 250, 550);
        public static final Border palettePanel_border = BorderFactory.createEtchedBorder();
        public static final Rectangle palettePanelButton_bounds = new Rectangle(25, 500, 200, 30);
        public static final Dimension paletteGCard_size = new Dimension(200, 280);
        public static final Point paletteGCard_point = new Point(20, 20);
        // allCards
        public static final Rectangle allCardsPanel_bounds = new Rectangle(10, 50, 1150, 600);
        public static final Rectangle searchField_bounds = new Rectangle(10, 10, 100, 20);
        public static final Rectangle allButton_bounds = new Rectangle(10, 40, 100, 30);
        public static final Rectangle neutralButton_bounds = new Rectangle(10, 80, 100, 30);
        public static final Rectangle mageButton_bounds = new Rectangle(10, 120, 100, 30);
        public static final Rectangle rougeButton_bounds = new Rectangle(10, 160, 100, 30);
        public static final Rectangle warlockButton_bounds = new Rectangle(10, 200, 100, 30);
        public static final Rectangle paladinButton_bounds = new Rectangle(10, 240, 100, 30);
        public static final Rectangle hunterButton_bounds = new Rectangle(10, 280, 100, 30);

        public static final Rectangle manaSortSlider_bounds = new Rectangle(200, 550, 800, 40);
        public static final Rectangle ownedCardsRadio_bounds = new Rectangle(10, 330, 100, 30);
        public static final Rectangle notOwnedCardsRadio_bounds = new Rectangle(10, 370, 100, 30);

        public static final Rectangle allCardsShowcase_bounds = new Rectangle(120, 10, 1000, 500);
        public static final int allCardsShowcase_rowLength = 4,
                allCardsShowcase_hAlign = 20,
                allCardsShowcase_vAlign = 30;

        // decks
        public static final Rectangle decksPanel_bounds = new Rectangle(10, 50, 1150, 600);
        public static final Rectangle decksPanel_createButton_bounds = new Rectangle(10, 370, 150, 30);
        public static final Rectangle decksPanel_deleteButton_bounds = new Rectangle(10, 410, 150, 30);
        public static final Rectangle decksPanel_addCardButton_bounds = new Rectangle(10, 450, 150, 30);
        public static final Rectangle deckPanel_changeNameButton_bounds = new Rectangle(10, 490, 150, 30);
        public static final Rectangle deckPanel_setCurrentButton = new Rectangle(10, 530, 150, 30);
        public static final Rectangle deckList_bounds = new Rectangle(10, 10, 150, 350);

        public static final Rectangle decksShowcase_bounds = new Rectangle(180, 10, 700, 550);
        public static final int decksShowcase_rowLength = 3,
                decksShowcase_hAlign = 20,
                decksShowcase_vAlign = 30;
        // addCard
        public static final Rectangle addCardPanelBackButton_bounds = new Rectangle(10, 560, 150, 30);

        public static final Rectangle addCardPanel_bounds = new Rectangle(10, 50, 1150, 600);
        public static final Rectangle addCardShowcase_bounds = new Rectangle(10, 10, 800, 540);
        public static final int addCardShowcase_rowLength = 3,
                addCardShowcase_hAlign = 20,
                addCardShowcase_vAlign = 30;

        // createDeck
        public static final Rectangle createDeckPanel_bounds = new Rectangle(10, 50, 1150, 600);
        public static final Dimension createDeckPanelHero_size = new Dimension(200, 272);
        public static final Point createDeckGMage_point = new Point(420, 15),
                createDeckGRogue_point = new Point(650, 15),
                createDeckGWarlock_point = new Point(880, 15),
                createDeckGPaladin_point = new Point(420, 310),
                createDeckGHunter_point = new Point(650, 310);

        public static final Rectangle createDeckHeroLabel_bounds = new Rectangle(10, 40, 300, 30);
        public static final Rectangle createDeckNameLabel_bounds = new Rectangle(10, 120, 300, 30);
        public static final Rectangle createDeckNameField_bounds = new Rectangle(10, 160, 300, 30);
        public static final Rectangle createButton_bounds = new Rectangle(10, 400, 100, 30);
        public static final Rectangle createDeckBackButton_bounds = new Rectangle(10, 440, 100, 30);

        public static final File background_image=new File("Game Data//GUI//background images//Collections.jpg");

    }

    static class playMenu {
        // menu
        public static final Rectangle menuBounds = new Rectangle(0, 0, 1200, 700);
        public static final Dimension menuSize = new Dimension(1200, 700);
        public static final Border panelBorder = BorderFactory.createEtchedBorder();
        // passive panel
        public static final Rectangle passivePanel_bounds = new Rectangle(100, 100, 1000, 500);
        public static final Border passivePanel_border = BorderFactory.createEtchedBorder();
        public static final Rectangle passive1Button_bounds = new Rectangle(100, 50, 800, 100);
        public static final Rectangle passive2Button_bounds = new Rectangle(100, 180, 800, 100);
        public static final Rectangle passive3Button_bounds = new Rectangle(100, 310, 800, 100);
        public static final Rectangle passiveBackButton_bounds = new Rectangle(400, 430, 200, 50);
        // playground
        public static final Rectangle playground_bounds = new Rectangle(0, 0, 1200, 700);
        public static final Rectangle endTurnButton_bounds = new Rectangle(1025, 305, 100, 30);
        public static final Rectangle backButton_bounds = new Rectangle(1025, 600, 100, 30);
        public static final File backgroundImage = new File(".//Game Data//GUI//background images//Playground.jpg");
        public static final File menuBackground_image=new File("Game Data//GUI//background images//Play.jpg");
        public static final Dimension handCard_size = new Dimension(80, 112);
        public static final Dimension groundCard_size = new Dimension(80, 112);

        public static final Rectangle gameEventList_bounds = new Rectangle(20, 180, 120, 300);
        //hero hp label
        public static final Rectangle heroHpLabel_bounds = new Rectangle(650, 580, 100, 30);
        public static final Font heroHpLabel_font = new Font("sefir", Font.PLAIN, 40);
        public static final Color heroHpLabel_foreColor = new Color(Color.RED.getRGB());

        // mana label
        public static final Rectangle manaLabel_bounds = new Rectangle(950, 630, 100, 30);
        public static final Font manaLabel_font = new Font("sefir", Font.PLAIN, 30);
        public static final Color manaLabel_foreColor = new Color(Color.BLUE.getRGB());

        //remaining cards label
        public static final Rectangle remainingCard_bounds = new Rectangle(950, 500, 200, 40);
        public static final Font remainingCardsLabel_font = new Font("sefir", Font.PLAIN, 20);
        public static final Color remainingCardsLabel_foreColor = new Color(Color.WHITE.getRGB());

        // locations of GCards
        public static Point[] handCards_location = new Point[]
                {new Point(60, 500), new Point(100, 500), new Point(140, 500),
                        new Point(180, 500), new Point(220, 500), new Point(260, 500),
                        new Point(300, 500), new Point(340, 500), new Point(380, 500),
                        new Point(700, 500), new Point(740, 500), new Point(780, 500)};

        public static final Point[] groundCards_location = new Point[]
                {new Point(200, 330), new Point(300, 330), new Point(400, 330), new Point(500, 330),
                        new Point(600, 330), new Point(700, 330), new Point(800, 330)};

        public static final Dimension cardOverview_size = new Dimension(200, 280);
        public static final Point cardOverview_location = new Point(500, 200);

        public static final Dimension weaponCard_size = new Dimension(80, 112);
        public static final Point weaponCard_location = new Point(500, 450);
        // location of hero
        public static final Point playerHero_location = new Point(530, 460);
        public static final Dimension playerHero_size = new Dimension(170, 170);
        public static final Point heroPower_location = new Point(630, 450);
        public static final Dimension heroPower_size = new Dimension(70, 70);

    }

    static class gCard {
        public static File getFile(String cardName) {
            return new File(".\\Game Data\\GUI\\card images\\" + cardName + ".png");
        }

    }

    static class gHero {
        public static File getFile(String heroName) {
            return new File(".//Game Data//GUI//hero images//" + heroName + ".png");
        }
    }

    static class gHeroPower {
        public static File getFile(String heroName) {
            return new File(".//Game Data//GUI//hero images//hero powers//" + heroName + ".png");
        }
    }

}

