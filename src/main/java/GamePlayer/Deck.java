package GamePlayer;

import GameCards.Card;
import GameCards.CardModerator;
import GameCards.CardUtilities;
import GameHeros.HeroClass;
import com.google.gson.annotations.Expose;


import java.util.ArrayList;

public class Deck {
    @Expose
    private String name;
    @Expose
    private HeroClass hero;
    private ArrayList<Card> cards;
    @Expose
    private ArrayList<String> cardsName;

    private CardUtilities cardUtilities;

    public Deck(String name, HeroClass hero) {
        this.hero = hero;
        this.name = name;
        cards = new ArrayList<>();
        cardsName = new ArrayList<>();
        cardUtilities = new CardUtilities();
    }

    public int addCard(String cardName) {
        if (cardUtilities == null)
            cardUtilities = new CardUtilities();
        Card card = cardUtilities.getCard(cardName);
        if (!(card.getCardClass().toString().equals(hero.toString()) || card.getCardClass().toString().equals("Neutral"))) {
            // card is in illegal class
            return -1;
        } else if (cardsName.indexOf(cardName) != cardsName.lastIndexOf(cardName)) {
// there are two cards of this in deck
            return 0;
        }
        if (cardsName.size() == 30) // deck is full
            return -2;

        cardsName.add(cardName);
        syncCards();
        return 1;
    }

    public void removeCard(String cardName) {
        cardsName.remove(cardName);
        syncCards();
    }

    public void syncCards() {
        cards = new ArrayList<>();
        if (cardUtilities == null)
            cardUtilities = new CardUtilities();
        for (int i = 0; i < cardsName.size(); i++) {
            cards.add(cardUtilities.getCard(cardsName.get(i)));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroClass getHero() {
        return hero;
    }

    public void setHero(HeroClass hero) {
        this.hero = hero;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<String> getCardsName() {
        return cardsName;
    }

    public void setCardsName(ArrayList<String> cardsName) {
        this.cardsName = cardsName;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
