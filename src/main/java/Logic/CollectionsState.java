package Logic;

import GameHeros.HeroClass;

import java.util.ArrayList;
import java.util.List;

public class CollectionsState extends State {
    // all Cards
    List<String> getCardsWithConditions(String cardClass, boolean owned, int mana, String searchTerm) {
        List<String> cards = new ArrayList<>(), temp = new ArrayList<>();
        if (cardClass.equals("All")) {
            if (owned) {
                cards = (ArrayList) currentPlayer.getOwnedCardsName().clone();
            } else {
                temp = cardUtilities.getAllCardsName();
                for (int i = 0; i < temp.size(); i++) {
                    if (!currentPlayer.getOwnedCardsName().contains(temp.get(i)))
                        cards.add(temp.get(i));
                }
            }

        } else {
            temp = cardUtilities.getCardsNameOfClass(cardClass);
            if (owned) {
                for (int i = 0; i < temp.size(); i++) {
                    if (currentPlayer.getOwnedCardsName().contains(temp.get(i)))
                        cards.add(temp.get(i));
                }
            } else {
                for (int i = 0; i < temp.size(); i++) {
                    if (!currentPlayer.getOwnedCardsName().contains(temp.get(i)))
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
        return currentPlayer.getDeckNames();
    }

     ArrayList<String> getDeckCards(String deckName) {
        return currentPlayer.getDeckFromName(deckName).getCardsName();
    }

     String getDeckClass(String deckName) {
        return currentPlayer.getDeckFromName(deckName).getHero().toString();
    }

     boolean isCurrentDeck(String deckName) {
        return (currentPlayer.getCurrentDeckName().equals(deckName));
    }

     void removeCardFromDeck(String deckName, String cardName) {
        currentPlayer.getDeckFromName(deckName).removeCard(cardName);
    }

    // return 0 if deck is the current deck,and 1 if action is successful
     int deleteDeck(String deckName) {

        return currentPlayer.deleteDeck(deckName);
    }

     int renameDeck(String deckName, String newName) {
        if (currentPlayer.getDeckNames().contains(newName.toLowerCase()))
            return 0;
        else {
            currentPlayer.getDeckFromName(deckName).setName(newName.toLowerCase());
            currentPlayer.getDeckNames().remove(deckName);
            currentPlayer.getDeckNames().add(newName.toLowerCase());
            return 1;
        }

    }

    ArrayList<String> getAddableCards(String deckName) {
        ArrayList<String> cardNames = new ArrayList<>();
        ArrayList<String> deckCards = (ArrayList) currentPlayer.getDeckFromName(deckName).getCardsName().clone();
        ArrayList<String> neutralCards = (ArrayList) cardUtilities.getCardsNameOfClass("Neutral");
        String deckHero = currentPlayer.getDeckFromName(deckName).getHero().toString();
        ArrayList<String> deckHeroCards = (ArrayList) cardUtilities.getCardsNameOfClass(deckHero);
        ArrayList<String> playerCards=(ArrayList) currentPlayer.getOwnedCardsName().clone();
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
        int a = currentPlayer.getDeckFromName(deckName).addCard(cardName);
        currentPlayer.saveInfo();
        return a;
    }

// returns -1 if there are 10 decks,0 if deckName already exists,1 if action is successful

     int createDeck(String deckName, String deckClass) {
        return currentPlayer.createDeck(deckName, deckClass);
    }

    int setCurrentDeck(String deckName) {

        return currentPlayer.setCurrentDeckName(deckName);
    }

}
