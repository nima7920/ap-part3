package Logic;

import java.util.ArrayList;

public class Admin {

    private static Admin admin;
private Logger logger;
    // objects for controlling logic
    private LoginState loginState;
    private StoreState storeState;
    private CollectionsState collectionsState;
    private StatusState statusState;
    private PlayState playState;


    public static Admin getInstance() {
        if (admin == null)
            admin = new Admin();
        return admin;
    }

    private Admin() {
        loginState = new LoginState();
        storeState = new StoreState();
        collectionsState = new CollectionsState();
        playState = new PlayState();
        statusState = new StatusState();

        logger=Logger.getInstance();
    }

    public void writeLog(String event,String description){
        logger.writeLog(event,description);

    }

    public void writeExitLog(){

        logger.exitGame();
    }
    // methods for login menu
    public int login(String username, String password) {
        int a=loginState.login(username, password);
        if(a==1)
            logger.loggedIn(username,loginState.getPlayerID());
        return a;
    }

    public int signup(String username, String password) {
        int a=loginState.signup(username, password);
        if(a==1)
            logger.signedUp(username,password,loginState.getPlayerID());
        return a;
    }


    // methods for store menu
    public ArrayList<String> getBuyableCards() {

        return (ArrayList) storeState.getBuyableCards();
    }

    public ArrayList<String> getSalableCards() {
        return (ArrayList) storeState.getSalableCards();
    }

    public int buyCard(String cardName) {
        return storeState.buyCard(cardName);
    }

    public void sellCard(String cardName) {
        storeState.sellCard(cardName);
    }

    public int getPlayerWallet() {
        return storeState.getWallet();
    }

    public int getCardCost(String cardName) {
        if (cardName.equals(""))
            return 0;
        return storeState.getCardCost(cardName);
    }

    public String getCardRarity(String cardName) {
        if (cardName.equals(""))
            return "";
        return storeState.getCardRarity(cardName);
    }

    public String getCardClass(String cardName) {
        if (cardName.equals(""))
            return "";
        return storeState.getCardClass(cardName);
    }

    /*
    methods for collections menu
     */
// all Cards panel
    public ArrayList<String> getCardsWithConditions(String cardClass, boolean owned, int mana, String searchTerm) {

        return (ArrayList) collectionsState.getCardsWithConditions(cardClass, owned, mana, searchTerm);
    }

    public void directBuy(String cardName){

    }

    // decks panel
    public ArrayList<String> getDecks() {

        return collectionsState.getDecks();
    }

    public String[] getDecksForList() {
        ArrayList<String> temp = (ArrayList) getDecks().clone();
        String[] arr = new String[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            if (isCurrentDeck(temp.get(i)))
                arr[i] = temp.get(i) + "-" + getDeckClass(temp.get(i)) + "*";
            else
                arr[i] = temp.get(i) + "-" + getDeckClass(temp.get(i));
        }
        return arr;
    }

    public ArrayList<String> getDeckCards(String deckName) {

        return collectionsState.getDeckCards(deckName);
    }

    public String getDeckClass(String deckName) {

        return collectionsState.getDeckClass(deckName);
    }

    public boolean isCurrentDeck(String deckName) {

        return collectionsState.isCurrentDeck(deckName);
    }

    public void removeCardFromDeck(String deckName, String cardName) {

        collectionsState.removeCardFromDeck(deckName, cardName);
    }

    public int createDeck(String deckName, String deckClass) {
        return collectionsState.createDeck(deckName, deckClass);
    }

    public ArrayList<String> getAddableCards(String deckName) {

        return collectionsState.getAddableCards(deckName);
    }

    public int addCardToDeck(String deckName, String cardName) {

        return collectionsState.addCardToDeck(deckName, cardName);
    }

    // returns 0 when selected deck has less than 10 cards,1 if action is successful
    public int setCurrentDeck(String deckName) {

        return collectionsState.setCurrentDeck(deckName);
    }

    // returns 0 if deck is the current deck,and 1 if action is successful
    public int deleteDeck(String deckName) {

        return collectionsState.deleteDeck(deckName);
    }

    // returns 0 if there is a deck with new name,1 if action is successful
    public int renameDeck(String deckName, String newName) {

        return collectionsState.renameDeck(deckName, newName);
    }


    // play menu methods
    public boolean isCurrentDeckReady() {

        return playState.isCurrentDeckReady();
    }

    public String getPassiveText(int i) {
        System.out.println(playState.getPassiveText(i));
        return playState.getPassiveText(i);
    }

    public void refreshPlayState() {
        playState.refreshState();
    }

    public void selectPassive(int i) {
        playState.selectPassive(i);
    }

    // playground

    public int getMana() {

        return playState.getMana();
    }

    public int getTurn() {

        return playState.getTurn();
    }

    public ArrayList<String> getHandCards() {

        return playState.getHandCards();
    }

    public ArrayList<String> getGroundCards() {

        return playState.getGroundCards();
    }

    public String getDeckHero() {

        return playState.getDeckHero();
    }

    public int getRemainingCards() {
        return playState.getRemainingCards();
    }

    public void nextTurn() {

        playState.nextTurn();
    }

    public int playCard(String cardName) {

        return playState.playCard(cardName);
    }

    // status menu
    public String[][] getRankedDecks() {

        return statusState.getRankedDecks();
    }

    public String[][] getDecksStatus() {

        return statusState.getDecksStatus();
    }
}

