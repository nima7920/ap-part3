package Logic;

import GameCards.Card;
import GameCards.Minion;
import GameCards.Spell;

import java.util.ArrayList;
import java.util.Collections;

public class PlayState extends State {

    private int mana = 1, turn = 1, remainingCards;
    private ArrayList<String> deckCards, handCards, groundCards;
    private String deckHero;
    private Passive currentPassive;
    private ArrayList<Passive> allPassives;

    public PlayState() {
        initAllPassives();

    }

    // initializing play state
    void refreshState() {
        initAllPassives();
        deckHero = currentPlayer.getCurrentDeck().getHero().toString();
        initCards();
        mana = 1;
        turn = 1;
        remainingCards = deckCards.size() - handCards.size();
    }

    // loads player deck cards
    private void initCards() {
        deckCards = (ArrayList) currentPlayer.getCurrentDeck().getCardsName().clone();
        Collections.shuffle(deckCards);
        groundCards = new ArrayList<>();
        handCards = new ArrayList<>();
        handCards.add(deckCards.get(0));
        handCards.add(deckCards.get(1));
        handCards.add(deckCards.get(2));

    }

    private void initAllPassives() {
        allPassives = new ArrayList<>();
        allPassives.add(Passive.FreePower);
        allPassives.add(Passive.OffCards);
        allPassives.add(Passive.Warriors);
        allPassives.add(Passive.ManaJump);
        allPassives.add(Passive.TwiceDraw);
        Collections.shuffle(allPassives);

    }

    // checking whether initialization is possible
    boolean isCurrentDeckReady() {
        if (currentPlayer.getCurrentDeck() != null && currentPlayer.getCurrentDeck().getCards().size() >= 15) {
            return true;
        } else {
            return false;
        }
    }

    // passives
     String getPassiveText(int i) {
        String str = allPassives.get(i).passiveName + ": " + allPassives.get(i).description;
        return str;
    }

     void selectPassive(int i) {
        currentPassive = allPassives.get(i);
    }

// playground methods

     void nextTurn() {
        turn++;
        if (turn > 10)
            mana = 10;
        else
            mana = turn;
        if (deckCards.size() > turn + 1 && handCards.size() < 12) {
            handCards.add(deckCards.get(turn + 1));
            remainingCards--;
        }

    }

    // returns -1 if deck is full,0 if mana is not enough,1 if played card is minion,2 if played card is Spell and 3 if played card is weapon
     int playCard(String cardName){

        Card card=cardUtilities.getCard(cardName);
        if(mana<card.getManaCost())
            return 0;

        if(card instanceof Minion) {
            if(groundCards.size()==7) { // ground is full
                return -1;
            }else{
                groundCards.add(cardName);
                mana=mana-card.getManaCost();
                handCards.remove(cardName);
                return 1;
            }
        }else if(card instanceof Spell) {
            mana=mana-card.getManaCost();
            handCards.remove(cardName);
            return 2;
        }else {
            mana=mana-card.getManaCost();
            handCards.remove(cardName);
            return 3;
        }
    }
    public int getMana() {
        return mana;
    }

    public int getTurn() {
        return turn;
    }

    public ArrayList<String> getHandCards() {
        return handCards;
    }

    public ArrayList<String> getGroundCards() {
        return groundCards;
    }

    public String getDeckHero() {
        return deckHero;
    }

    public int getRemainingCards() {
        return remainingCards;
    }

    // an enum for handling passives
    private enum Passive {

        TwiceDraw("Twice draw", "Draw 2 cards each turn"),
        OffCards("Off cards", "Spend -1 mana for each card"),
        Warriors("Warriors", "For each dead minion achieve +1 defense for hero"),
        FreePower("Free power", "Hero power costs -1 mana,Use hero power twice a turn"),
        ManaJump("Mana jump", "Earn +1 mana each turn");

        private String passiveName, description;

        Passive(String passiveName, String description) {
            this.passiveName = passiveName;
            this.description = description;
        }

    }
}
