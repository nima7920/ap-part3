package GameCards;

import javax.persistence.Entity;

@Entity
public class Spell extends Card {

    public Spell() {
    }
    public Spell(String cardName, int manaCost, int gemCost, CardClass cardClass, Rarity rarity,String cardDescription) {
        this.cardName = cardName;
        this.cardDescription = cardDescription;
        this.rarity = rarity;
        this.cardClass = cardClass;
        this.gemCost = gemCost;
        this.manaCost = manaCost;
    }

}
