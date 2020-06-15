package Logic;

import GameCards.Card;

import java.util.ArrayList;
import java.util.List;

public class StoreState extends State {

     List<String> getBuyableCards() {
        ArrayList<String> buyableCard = (ArrayList) allCardsName.clone();
        for (String x : playerHandler.getOwnedCardsName()) {
            buyableCard.remove(x);
        }
        return buyableCard;
    }

     List<String> getSalableCards() {
        List<String> salableCards = (List) playerHandler.getOwnedCardsName().clone();
        return salableCards;
    }

    // returns 1 is gems are not enough and 1 if action is successful
     int buyCard(String cardName) {
        Card card = cardUtilities.getCard(cardName);
        if (playerHandler.getGems() < card.getGemCost())
            return 0;
        else {
            playerHandler.setGems(playerHandler.getGems() - card.getGemCost());
            playerHandler.getOwnedCardsName().add(cardName);
            playerHandler.getOwnedCards().add(card);
            playerHandler.saveInfo();
            return 1;
        }
    }

     void sellCard(String cardName) {
        Card card = cardUtilities.getCard(cardName);
        playerHandler.setGems(playerHandler.getGems() + card.getGemCost());
        playerHandler.deleteCard(cardName);
        playerHandler.saveInfo();

    }

     int getWallet() {
        return playerHandler.getGems();
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
