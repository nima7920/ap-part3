package GameCards;

import java.util.ArrayList;
import java.util.List;

public class CardUtilities {

    public Card getCard(String cardName) {

        return CardModerator.fetch(Card.class,cardName);
    }

    public List<Card> getAllCards() {

        return CardModerator.fetchAll(Card.class);
    }

    public List<String> getAllCardsName() {
        List<String> names = new ArrayList<>();
        List<Card> cards = getAllCards();
        for (int i = 0; i < cards.size(); i++) {
            names.add(cards.get(i).getCardName());
        }
        return names;
    }
public List<Card> getCardsOfClass(CardClass cardClass){
    return CardModerator.fetchWithCondition(Card.class, "cardClass", cardClass);
}

public List<String> getCardsNameOfClass(String cardClass){
    List<String> names = new ArrayList<>();
    List<Card> cards = getCardsOfClass(CardClass.valueOf(cardClass));
    for (int i = 0; i < cards.size(); i++) {
        names.add(cards.get(i).getCardName());
    }
    return names;
}

public List<Card> getCardsOfMana(int mana){
    return CardModerator.fetchWithCondition(Card.class, "manaCost", mana);

}

public List<String> getCardsNameOfMana(int mana){
    List<String> names = new ArrayList<>();
    List<Card> cards = getCardsOfMana(mana);
    for (int i = 0; i < cards.size(); i++) {
        names.add(cards.get(i).getCardName());
    }
    return names;
}

}
