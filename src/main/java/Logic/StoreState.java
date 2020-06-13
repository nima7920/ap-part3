package Logic;

import GameCards.Card;

import java.util.ArrayList;
import java.util.List;

public class StoreState extends State {

     List<String> getBuyableCards() {
        ArrayList<String> buyableCard = (ArrayList) allCardsName.clone();
        for (String x : currentPlayer.getOwnedCardsName()) {
            buyableCard.remove(x);
        }
        return buyableCard;
    }

     List<String> getSalableCards() {
        List<String> salableCards = (List) currentPlayer.getOwnedCardsName().clone();
        return salableCards;
    }

    // returns 1 is gems are not enough and 1 if action is successful
     int buyCard(String cardName) {
        Card card = cardUtilities.getCard(cardName);
        if (currentPlayer.getGems() < card.getGemCost())
            return 0;
        else {
            currentPlayer.setGems(currentPlayer.getGems() - card.getGemCost());
            currentPlayer.getOwnedCardsName().add(cardName);
            currentPlayer.getOwnedCards().add(card);
            currentPlayer.saveInfo();
            return 1;
        }
    }

     void sellCard(String cardName) {
        Card card = cardUtilities.getCard(cardName);
        currentPlayer.setGems(currentPlayer.getGems() + card.getGemCost());
        currentPlayer.deleteCard(cardName);
        currentPlayer.saveInfo();

    }

     int getWallet() {
        return currentPlayer.getGems();
    }

    int getCardCost(String cardName) {
        Card card = cardUtilities.getCard(cardName);
        return card.getGemCost();
    }

     String getCardRarity(String cardName) {
        Card card = cardUtilities.getCard(cardName);
        return card.getRarity().toString();
    }

     String getCardClass(String cardName) {
        Card card = cardUtilities.getCard(cardName);
        return card.getCardClass().toString();
    }
}
