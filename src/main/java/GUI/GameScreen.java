package GUI;

import javax.swing.*;
import java.text.CollationElementIterator;
import java.util.HashMap;

public class GameScreen {

    // declaring game menus
    private GameMenu currentMenu;
    private PlayMenu playMenu;
    private LoginMenu loginMenu;
    private MainMenu mainMenu;
    private StoreMenu storeMenu;
    private StatusMenu statusMenu;
    private CollectionsMenu collectionsMenu;

    private HashMap<String, GameMenu> menus;

    private JFrame gameFrame;
    private static GameScreen gameScreen;

    private GameScreen() {
        initFrame();
    }

    public static GameScreen getInstance() {
        if (gameScreen == null)
            gameScreen = new GameScreen();
        return gameScreen;
    }

    private void initFrame() {
        loginMenu = new LoginMenu();
//        MainMenu mainMenu = new MainMenu();

        gameFrame = new JFrame("HearthStone");
        gameFrame.setLayout(null);
        gameFrame.setResizable(false);
//        initMenus();
//        //gameFrame.add(mainMenu);
        gameFrame.setSize(loginMenu.getSize());
        gameFrame.getContentPane().add(loginMenu);
        loginMenu.setVisible(true);
        currentMenu = loginMenu;
        gameFrame.setLocationRelativeTo(null);
//
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);
    }

     void initMenus() {
//        loginMenu = new LoginMenu();
        mainMenu = new MainMenu();
        playMenu = new PlayMenu();
        storeMenu = new StoreMenu();
        statusMenu = new StatusMenu();
        collectionsMenu = new CollectionsMenu();
//        gameFrame.add(loginMenu);
        gameFrame.add(mainMenu);
        gameFrame.add(playMenu);
        gameFrame.add(storeMenu);
        gameFrame.add(statusMenu);
        gameFrame.add(collectionsMenu);
        loginMenu.setVisible(false);
        mainMenu.setVisible(false);
        playMenu.setVisible(false);
        storeMenu.setVisible(false);
        statusMenu.setVisible(false);
        collectionsMenu.setVisible(false);
        menus = new HashMap<>();
        initMenuMap();
    }

    private void initMenuMap() {
        menus.put("login", loginMenu);
        menus.put("main", mainMenu);
        menus.put("play", playMenu);
        menus.put("store", storeMenu);
        menus.put("status", statusMenu);
        menus.put("collections", collectionsMenu);
    }

    void gotoMenu(String menuName) {
        gameFrame.getContentPane().removeAll();
        currentMenu = menus.get(menuName);
        gameFrame.setSize(currentMenu.getSize());
        currentMenu.setVisible(true);
        gameFrame.getContentPane().add(currentMenu);
        currentMenu.revalidate();
        currentMenu.repaint();
        gameFrame.setLocationRelativeTo(null);

    }
    void refreshPlayMenu(){
        playMenu.refresh();
    }

    void refreshStatusMenu(){
        statusMenu.initTables();
    }
}

