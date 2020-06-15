package Logic;

import GameCards.Card;
import GameCards.CardUtilities;
import GamePlayer.Player;
import GamePlayer.PlayerHandler;


import java.util.ArrayList;

public class State {
    protected PlayerHandler playerHandler;
    protected ArrayList<Card> allCards;
    protected ArrayList<String> allCardsName;
    protected CardUtilities cardUtilities;

    public State() {
        playerHandler = PlayerHandler.getInstance();
        cardUtilities = new CardUtilities();
        initAllCards();
    }

    private void initAllCards() {
        allCards = (ArrayList) cardUtilities.getAllCards();
        allCardsName = (ArrayList) cardUtilities.getAllCardsName();
    }
}
