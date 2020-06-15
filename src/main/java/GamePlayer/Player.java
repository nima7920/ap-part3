package GamePlayer;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class Player {
    @Expose
    private String name;
    @Expose
    private String password;
    @Expose
    private long id;
    @Expose
    private int gems;
    @Expose
    private ArrayList<String> deckNames=new ArrayList<>();
    @Expose
    private String currentDeckName;
    @Expose
    private ArrayList<String> ownedCardsName=new ArrayList<>();

    public Player() {

    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGems() {
        return gems;
    }

    public void setGems(int gems) {
        this.gems = gems;
    }

    public ArrayList<String> getOwnedCardsName() {
        return ownedCardsName;
    }

    public void setOwnedCardsName(ArrayList<String> ownedCardsName) {
        this.ownedCardsName = ownedCardsName;
    }

    public long getId() {
        return id;
    }

    public ArrayList<String> getDeckNames() {
        return deckNames;
    }

    public void setDeckNames(ArrayList<String> deckNames) {
        this.deckNames = deckNames;
    }

    public void setCurrentDeckName(String currentDeckName) {
        this.currentDeckName = currentDeckName;
    }

    public String getCurrentDeckName() {
        return currentDeckName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

}
