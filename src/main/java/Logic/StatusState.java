package Logic;

public class StatusState extends State {

     String[][] getRankedDecks() {
        String[][] rankedDecks = new String[currentPlayer.getDeckNames().size()][2];
        for (int i = 0; i < currentPlayer.getDeckNames().size(); i++) {
            rankedDecks[i] = new String[]{(i + 1) + "", currentPlayer.getDeckNames().get(i)};
        }
        return rankedDecks;
    }

     String[][] getDecksStatus() {
        String[][] decksStatus = new String[currentPlayer.getDeckNames().size()][7];
        for (int i = 0; i < currentPlayer.getDeckNames().size(); i++) {
            decksStatus[i] = new String[]{currentPlayer.getDeckNames().get(i), "0", "0", "0", "0",
                    currentPlayer.getDeckFromName(currentPlayer.getDeckNames().get(i)).getHero().toString(), ""};
        }
        return decksStatus;
    }

}
