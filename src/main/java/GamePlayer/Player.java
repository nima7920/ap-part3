package GamePlayer;// player class is defined singelton

import GameHeros.HeroClass;
import Logic.ModelConsts;

import GameCards.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class Player {
    // main fields:
    @Expose
    private String name;
    @Expose
    private String password;
    @Expose
    private long id;
    @Expose
    private int gems;
    @Expose
    private ArrayList<String> deckNames;
    private ArrayList<Deck> decks;
    @Expose
    private String currentDeckName;
    private Deck currentDeck;

    private ArrayList<Card> ownedCards;
    @Expose
    private ArrayList<String> ownedCardsName;
    // requered files and folders:
    private String playerPath;
    private File playerFolder;
    private File infoFile;
    private File decksFolder;
    private File statusFolder;
    private GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
    private static Player player;

    private Player() {

    }

    public static Player getInstance() {
        if (player == null) player = new Player();
        return player;
    }

    public int sign_up(String username, String password) {

        if (new File(ModelConsts.player.path + username).exists()) {
            // profile already exists
            return 0;
        } else {
            // setting fields:
            this.id = System.currentTimeMillis();
            this.name = username;
            this.password = password;
            this.gems = 100;
            this.decks = new ArrayList<>();
            this.deckNames = new ArrayList<>();
            this.ownedCards = new ArrayList<>();
            this.ownedCardsName = new ArrayList<>();
            this.currentDeck = null;
            this.currentDeckName = "";

            playerPath = ModelConsts.player.path + username;

            createPlayer();
            return 1;

        }
    }

    public int login(String username, String password) {
        if (!(new File(ModelConsts.player.path + username).exists())) {
            // profile doesn't exist
            return 0;
        } else {
            playerPath = ModelConsts.player.path + username;
            loadPlayer();
            if (this.password.equals(password)) { // logged in successfully

                return 1;
            } else { // password is wrong
                return -1;
            }
        }
    }

    private void createPlayer() {
        // creates required files and folders
        playerFolder = new File(playerPath);
        System.out.println("directory created:" + playerFolder.mkdir());
        decksFolder = new File(playerPath + "//decks");
        decksFolder.mkdir();
        infoFile = new File(playerPath + "//info.JSON");
        saveInfo();

    }

    private void loadPlayer() {
        playerFolder = new File(playerPath);
        infoFile = new File(playerPath + "//info.JSON");
        decksFolder = new File(playerPath + "//decks");

        loadInfo();
    }

    public void saveInfo() {
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.excludeFieldsWithModifiers().setPrettyPrinting().create();
        try {
            FileWriter fileWriter = new FileWriter(infoFile);
            gson.toJson(this, fileWriter);
            fileWriter.close();
            // saving decks:
            saveDecks(gson);
        } catch (IOException e) {
            System.out.println("can't find infoFile");
        }
    }

    // has to be completed
    private void loadInfo() {
        Gson gson = gsonBuilder.excludeFieldsWithModifiers().setPrettyPrinting().create();
        try {
            FileReader fileReader = new FileReader(infoFile);
            Player tempPlayer = gson.fromJson(fileReader, Player.class);
            this.name = tempPlayer.name;
            this.password = tempPlayer.password;
            this.gems = tempPlayer.gems;
            this.id = tempPlayer.id;
            this.currentDeckName = tempPlayer.currentDeckName;

            this.deckNames = tempPlayer.deckNames;
            this.ownedCardsName = tempPlayer.ownedCardsName;
            decks = new ArrayList<>();
            ownedCards = new ArrayList<>();
            loadDecks(gson);
            loadAllCards();

        } catch (IOException e) {

        }

    }

    // saving and loading decks:
    private void saveDecks(Gson gson) {
        FileWriter fileWriter;
        try {
            for (int i = 0; i < decks.size(); i++) {
                fileWriter = new FileWriter(decksFolder.getPath() + "//" + decks.get(i).getName() + ".JSON");
                gson.toJson(decks.get(i), fileWriter);
                fileWriter.close();
            }
        } catch (IOException e) {

        }
    }

    private void loadDecks(Gson gson) {
        FileReader fileReader;
        Deck deck;
        try {
            for (int i = 0; i < deckNames.size(); i++) {
                fileReader = new FileReader(decksFolder.getPath() + "//" + deckNames.get(i) + ".JSON");
                deck = gson.fromJson(fileReader, Deck.class);
                deck.syncCards();
                decks.add(deck);
            }

        } catch (IOException e) {

        }
            currentDeck=getDeckFromName(currentDeckName);
    }

    private void loadAllCards() {
        CardUtilities cardUtilities = new CardUtilities();
        Card card;
        ownedCards = new ArrayList<>();
        for (int i = 0; i < ownedCardsName.size(); i++) {
            card = cardUtilities.getCard(ownedCardsName.get(i));
            ownedCards.add(card);
        }

    }

    public int deleteDeck(String deckName) {
        if (currentDeckName.equals(deckName))
            return 0;
        deckNames.remove(deckName);
        for (int i = 0; i < decks.size(); i++) {
            if (decks.get(i).getName().equals(deckName))
                decks.remove(i);
        }
        File deckFile = new File(decksFolder.getPath() + "//" + deckName + ".JSON");
        deckFile.delete();
        saveInfo();
        return 1;
    }

    public int createDeck(String deckName, String deckClass) {
        if (deckNames.size() >= 10) {
            return -1;
        }
        if (deckNames.contains(deckName.toLowerCase())) {
            return 0;
        }
        deckNames.add(deckName.toLowerCase());
        Deck deck = new Deck(deckName.toLowerCase(), HeroClass.valueOf(deckClass));
        decks.add(deck);
        saveInfo();
        return 1;
    }

    public void deleteCard(String cardName) {
        ownedCardsName.remove(cardName);
        for (int i = 0; i < ownedCards.size(); i++) {
            if (ownedCards.get(i).getCardName().equals(cardName))
                ownedCards.remove(i);
            break;
        }
        for (int i = 0; i < decks.size(); i++) {
            decks.get(i).removeCard(cardName);
        }
        saveInfo();

    }

    // returns 0 if selected deck has less than 10 cards,1 if action is successful
    public int setCurrentDeckName(String deckName) {
        if (getDeckFromName(deckName).getCardsName().size() < 15)
            return 0;
        currentDeckName = deckName;
        currentDeck = getDeckFromName(currentDeckName);
        saveInfo();
        return 1;

    }

    public Deck getDeckFromName(String deckName) {
        for (int i = 0; i < decks.size(); i++) {
            if (decks.get(i).getName().equals(deckName)) {
                return decks.get(i);
            }
        }
        return null;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGems() {
        return gems;
    }

    public void setGems(int gems) {
        this.gems = gems;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public void setDecks(ArrayList<Deck> decks) {
        this.decks = decks;
    }

    public Deck getCurrentDeck() {
        return currentDeck;
    }


    public ArrayList<String> getOwnedCardsName() {
        return ownedCardsName;
    }

    public void setOwnedCardsName(ArrayList<String> ownedCardsName) {
        this.ownedCardsName = ownedCardsName;
    }

    public long getId() {
        return id;
    }

    public ArrayList<String> getDeckNames() {
        return deckNames;
    }

    public void setDeckNames(ArrayList<String> deckNames) {
        this.deckNames = deckNames;
    }


    public ArrayList<Card> getOwnedCards() {
        return ownedCards;
    }

    public void setOwnedCards(ArrayList<Card> ownedCards) {
        this.ownedCards = ownedCards;
    }

    public String getCurrentDeckName() {
        return currentDeckName;
    }

}
