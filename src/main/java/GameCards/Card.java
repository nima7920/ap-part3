package GameCards;

import GameHeros.HeroClass;
import GameCards.*;

import javax.persistence.*;

@Entity
public abstract class Card {
    @Id
    protected String cardName;
    @Column
    protected String cardDescription;
    @Column  @Enumerated(EnumType.STRING)
    protected Rarity rarity;
    @Column @Enumerated(EnumType.STRING)
    protected CardClass cardClass;
    @Column
    protected int gemCost;
    @Column
    protected int manaCost;

    public Card(){

    }

    public Card(String cardName, String cardDescription, Rarity rarity, CardClass cardClass, int gemCost, int manaCost) {
        this.cardName = cardName;
        this.cardDescription = cardDescription;
        this.rarity = rarity;
        this.cardClass = cardClass;
        this.gemCost = gemCost;
        this.manaCost = manaCost;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public CardClass getCardClass() {
        return cardClass;
    }

    public void setCardClass(CardClass cardClass) {
        this.cardClass = cardClass;
    }

    public int getGemCost() {
        return gemCost;
    }

    public void setGemCost(int gemCost) {
        this.gemCost = gemCost;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    @Override
    public String toString() {
        return cardName;
    }
}
