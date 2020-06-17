package GamePlayer;

import GameCards.Card;
import GameCards.CardUtilities;
import GameHeros.HeroClass;
import Logic.ModelConsts;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerHandler {
    Player currentPlayer;

    // fields related to files and folders
    private String playerPath;
    private File playerFolder;
    private File infoFile;
    private File decksFolder;
    private File statusFolder;
    private GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();

    // other fields related to player
    private ArrayList<Deck> decks;
    private Deck currentDeck;
    private ArrayList<Card> ownedCards;

    private static PlayerHandler playerHandler;

    private PlayerHandler() {
        currentPlayer = new Player();

    }

    public static PlayerHandler getInstance() {
        if (playerHandler == null)
            playerHandler = new PlayerHandler();
        return playerHandler;
    }

    public int sign_up(String username, String password) {

        if (new File(ModelConsts.player.path + username).exists()) {
            // profile already exists
            return 0;
        } else {
            // setting fields:
            currentPlayer.setId(System.currentTimeMillis());
            currentPlayer.setName( username);
            currentPlayer.setPassword(password);
            currentPlayer.setGems(100);
            currentPlayer.setCurrentDeckName("");
            this.decks = new ArrayList<>();
            this.ownedCards = new ArrayList<>();
            this.currentDeck = null;

            // configs
            playerPath = ModelConsts.player.path + username;

            createPlayer();
            return 1;

        }

    }

    private void createPlayer() {
        // creates required files and folders
        playerFolder = new File(playerPath);
        decksFolder = new File(playerPath + "//decks");
        decksFolder.mkdir();
        infoFile = new File(playerPath + "//info.JSON");
        // status files must be added
        saveInfo();

    }

    public int login(String username, String password) {

        // configs
        if (!(new File(ModelConsts.player.path + username).exists())) {
            // profile doesn't exist
            return 0;
        } else {

            // configs
            if (checkPassword(password, ModelConsts.player.path + username+"//info.JSON")) { // logged in successfully
                playerPath = ModelConsts.player.path + username;
                loadPlayer();
                return 1;
            } else { // password is wrong
                return -1;
            }
        }
    }

    private boolean checkPassword(String password,String playerPath){
        // checks whether entered password is correct
        Gson gson = gsonBuilder.excludeFieldsWithModifiers().setPrettyPrinting().create();
        try {
            FileReader fileReader = new FileReader(new File(playerPath));
            Player tempPlayer = gson.fromJson(fileReader, Player.class);
            if(tempPlayer.getPassword().equals(password)) {

                return true;
            } else {
                System.out.println("wrong password");
                return false;
            }
        } catch (IOException e) {
            System.out.println("error in password");
            e.printStackTrace();
        }
        return false;
    }

    private void loadPlayer(){
        playerFolder = new File(playerPath);
        infoFile = new File(playerPath + "//info.JSON");
        decksFolder = new File(playerPath + "//decks");
        loadInfo();
    }


    private void loadInfo(){
        Gson gson = gsonBuilder.excludeFieldsWithModifiers().setPrettyPrinting().create();
        try {
            FileReader fileReader = new FileReader(infoFile);
            Player tempPlayer = gson.fromJson(fileReader, Player.class);
            currentPlayer.setName(tempPlayer.getName());
            currentPlayer.setPassword(tempPlayer.getPassword());
            currentPlayer.setGems(tempPlayer.getGems());
            currentPlayer.setId(tempPlayer.getId());

            currentPlayer.setOwnedCardsName(tempPlayer.getOwnedCardsName());
            decks = new ArrayList<>();
            ownedCards = new ArrayList<>();

            currentPlayer.setDeckNames(tempPlayer.getDeckNames());
            loadDecks(gson);
            loadAllCards();
            setCurrentDeckName(tempPlayer.getCurrentDeckName());

        } catch (IOException e) {

        }

    }

    private void loadDecks(Gson gson) {
        FileReader fileReader;
        Deck deck;
        try {
            for (int i = 0; i < currentPlayer.getDeckNames().size(); i++) {
                fileReader = new FileReader(decksFolder.getPath() + "//" + currentPlayer.getDeckNames().get(i) + ".JSON");
                deck = gson.fromJson(fileReader, Deck.class);
                deck.syncCards();
                System.out.println(deck.getName());
                decks.add(deck);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        currentDeck=getDeckFromName(currentPlayer.getCurrentDeckName());
    }

    private void loadAllCards() {
        CardUtilities cardUtilities = new CardUtilities();
        Card card;
        ownedCards = new ArrayList<>();
        for (int i = 0; i < currentPlayer.getOwnedCardsName().size(); i++) {
            card = cardUtilities.getCard(currentPlayer.getOwnedCardsName().get(i));
            ownedCards.add(card);
        }

    }

    public void saveInfo() {
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.excludeFieldsWithModifiers().setPrettyPrinting().create();
        try {
            FileWriter fileWriter = new FileWriter(infoFile);
            gson.toJson(currentPlayer, fileWriter);
            fileWriter.close();
            // saving decks:
            saveDecks(gson);
        } catch (IOException e) {
            System.out.println("can't find infoFile");
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

    public int deleteDeck(String deckName) {
        if (currentPlayer.getCurrentDeckName().equals(deckName))
            return 0;
        currentPlayer.getDeckNames().remove(deckName);
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
        if (currentPlayer.getDeckNames().size() >= 10) {
            return -1;
        }
        if (currentPlayer.getDeckNames().contains(deckName.toLowerCase())) {
            return 0;
        }
        currentPlayer.getDeckNames().add(deckName.toLowerCase());
        Deck deck = new Deck(deckName.toLowerCase(), HeroClass.valueOf(deckClass));
        decks.add(deck);
        saveInfo();
        return 1;
    }

    public void deleteCard(String cardName) {
        currentPlayer.getOwnedCardsName().remove(cardName);
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
        if(deckName.equals("")){
            currentPlayer.setCurrentDeckName(deckName);
            return 1;
        }
        if (getDeckFromName(deckName).getCardsName().size() < 15)
            return 0;
        currentPlayer.setCurrentDeckName(deckName);
        currentDeck = getDeckFromName(currentPlayer.getCurrentDeckName());
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

    public String getName() {
        return currentPlayer.getName();
    }

    public void setName(String name) {
        currentPlayer.setName(name);
    }

    public int getGems() {
        return currentPlayer.getGems();
    }

    public void setGems(int gems) {
        currentPlayer.setGems( gems);
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
        return currentPlayer.getOwnedCardsName();
    }

    public void setOwnedCardsName(ArrayList<String> ownedCardsName) {
        currentPlayer.setOwnedCardsName(ownedCardsName);
    }

    public long getId() {
        return currentPlayer.getId();
    }

    public ArrayList<String> getDeckNames() {
        return currentPlayer.getDeckNames();
    }

    public void setDeckNames(ArrayList<String> deckNames) {
        currentPlayer.setDeckNames(deckNames);
    }


    public ArrayList<Card> getOwnedCards() {
        return ownedCards;
    }

    public void setOwnedCards(ArrayList<Card> ownedCards) {
        this.ownedCards = ownedCards;
    }

    public String getCurrentDeckName() {
        return currentPlayer.getCurrentDeckName();
    }

    public String getPassword() {
        return currentPlayer.getPassword();
    }

    public void setPassword(String password) {
        currentPlayer.setPassword( password);
    }

    public void setId(long id) {
        currentPlayer.setId(id);
    }
}
