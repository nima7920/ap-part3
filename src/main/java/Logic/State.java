package Logic;

import GameCards.Card;
import GameCards.CardUtilities;
import GamePlayer.Player;


import java.util.ArrayList;

public class State {
    protected Player currentPlayer;
    protected ArrayList<Card> allCards;
    protected ArrayList<String> allCardsName;
    protected CardUtilities cardUtilities;

    public State() {
        currentPlayer = Player.getInstance();
        cardUtilities = new CardUtilities();
        initAllCards();
    }

    private void initAllCards() {
        allCards = (ArrayList) cardUtilities.getAllCards();
        allCardsName = (ArrayList) cardUtilities.getAllCardsName();
    }
}
