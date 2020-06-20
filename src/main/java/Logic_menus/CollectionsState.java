package Logic_menus;

import java.util.ArrayList;
import java.util.List;

public class CollectionsState extends State {
    // all Cards
    List<String> getCardsWithConditions(String cardClass, boolean owned, int mana, String searchTerm) {
        List<String> cards = new ArrayList<>(), temp = new ArrayList<>();
        if (cardClass.equals("All")) {
            if (owned) {
                cards = (ArrayList) playerHandler.getOwnedCardsName().clone();
            } else {
                temp = cardUtilities.getAllCardsName();
                for (int i = 0; i < temp.size(); i++) {
                    if (!playerHandler.getOwnedCardsName().contains(temp.get(i)))
                        cards.add(temp.get(i));
                }
            }

        } else {
            temp = cardUtilities.getCardsNameOfClass(cardClass);
            if (owned) {
                for (int i = 0; i < temp.size(); i++) {
                    if (playerHandler.getOwnedCardsName().contains(temp.get(i)))
                        cards.add(temp.get(i));
                }
            } else {
                for (int i = 0; i < temp.size(); i++) {
                    if (!playerHandler.getOwnedCardsName().contains(temp.get(i)))
                        cards.add(temp.get(i));
                }
            }

        }
        List<String> temp2 = cards;
        if (mana != 0) {
            temp = cardUtilities.getCardsNameOfMana(mana);
            temp2 = new ArrayList<>();
            for (int i = 0; i < cards.size(); i++) {
                if (temp.contains(cards.get(i)))
                    temp2.add(cards.get(i));
            }
        }
        temp = search(temp2, searchTerm);
        return temp;
    }

    private List<String> search(List<String> list, String searchTerm) {
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toLowerCase().startsWith(searchTerm.toLowerCase()))
                temp.add(list.get(i));
        }
        return temp;
    }

    // deck editing
    ArrayList<String> getDecks() {
        return playerHandler.getDeckNames();
    }

     ArrayList<String> getDeckCards(String deckName) {
        return playerHandler.getDeckFromName(deckName).getCardsName();
    }

     String getDeckClass(String deckName) {
        return playerHandler.getDeckFromName(deckName).getHero().toString();
    }

     boolean isCurrentDeck(String deckName) {
        return (playerHandler.getCurrentDeckName().equals(deckName));
    }

     void removeCardFromDeck(String deckName, String cardName) {
        playerHandler.getDeckFromName(deckName).removeCard(cardName);
    }

    // return 0 if deck is the current deck,and 1 if action is successful
     int deleteDeck(String deckName) {

        return playerHandler.deleteDeck(deckName);
    }

     int renameDeck(String deckName, String newName) {
        if (playerHandler.getDeckNames().contains(newName.toLowerCase()))
            return 0;
        else {
            playerHandler.getDeckFromName(deckName).setName(newName.toLowerCase());
            playerHandler.getDeckNames().remove(deckName);
            playerHandler.getDeckNames().add(newName.toLowerCase());
            return 1;
        }

    }

    ArrayList<String> getAddableCards(String deckName) {
        ArrayList<String> cardNames = new ArrayList<>();
        ArrayList<String> deckCards = (ArrayList) playerHandler.getDeckFromName(deckName).getCardsName().clone();
        ArrayList<String> neutralCards = (ArrayList) cardUtilities.getCardsNameOfClass("Neutral");
        String deckHero = playerHandler.getDeckFromName(deckName).getHero().toString();
        ArrayList<String> deckHeroCards = (ArrayList) cardUtilities.getCardsNameOfClass(deckHero);
        ArrayList<String> playerCards=(ArrayList) playerHandler.getOwnedCardsName().clone();
        for (int i = 0; i < neutralCards.size(); i++) {
            if (deckCards.indexOf(neutralCards.get(i)) == deckCards.lastIndexOf(neutralCards.get(i)) && deckCards.indexOf(neutralCards.get(i)) == -1) {
                cardNames.add(neutralCards.get(i));
            }
            if (deckCards.indexOf(neutralCards.get(i)) == deckCards.lastIndexOf(neutralCards.get(i))) {
                cardNames.add(neutralCards.get(i));
            }
        }
        for (int i = 0; i < deckHeroCards.size(); i++) {
            if (deckCards.indexOf(deckHeroCards.get(i)) == deckCards.lastIndexOf(deckHeroCards.get(i)) && deckCards.indexOf(deckHeroCards.get(i)) == -1) {
                cardNames.add(deckHeroCards.get(i));
            }
            if (deckCards.indexOf(deckHeroCards.get(i)) == deckCards.lastIndexOf(deckHeroCards.get(i))) {
                cardNames.add(deckHeroCards.get(i));
            }
        }
        ArrayList<String> cardNames2=new ArrayList<>();
        for (int i = 0; i <cardNames.size() ; i++) {
            if(playerCards.contains(cardNames.get(i))){
                cardNames2.add(cardNames.get(i));
            }
        }
        return cardNames2;
    }

    // return -2 if deck is full,-1 if card class doesn't match,0 if there are two cards of this type,and 1 if action is successful
    int addCardToDeck(String deckName, String cardName) {
        int a = playerHandler.getDeckFromName(deckName).addCard(cardName);
        playerHandler.saveInfo();
        return a;
    }

// returns -1 if there are 10 decks,0 if deckName already exists,1 if action is successful

     int createDeck(String deckName, String deckClass) {
        return playerHandler.createDeck(deckName, deckClass);
    }

    int setCurrentDeck(String deckName) {

        return playerHandler.setCurrentDeckName(deckName);
    }

}
