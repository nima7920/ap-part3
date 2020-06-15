package Logic;

public class StatusState extends State {

     String[][] getRankedDecks() {
        String[][] rankedDecks = new String[playerHandler.getDeckNames().size()][2];
        for (int i = 0; i < playerHandler.getDeckNames().size(); i++) {
            rankedDecks[i] = new String[]{(i + 1) + "", playerHandler.getDeckNames().get(i)};
        }
        return rankedDecks;
    }

     String[][] getDecksStatus() {
        String[][] decksStatus = new String[playerHandler.getDeckNames().size()][7];
        for (int i = 0; i < playerHandler.getDeckNames().size(); i++) {
            decksStatus[i] = new String[]{playerHandler.getDeckNames().get(i), "0", "0", "0", "0",
                    playerHandler.getDeckFromName(playerHandler.getDeckNames().get(i)).getHero().toString(), ""};
        }
        return decksStatus;
    }

}
