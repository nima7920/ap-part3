package GameCards;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Minion extends Card {
    @Column
    private int attack;
    @Column
    private int hp;

    public Minion(){
        super();
    }

    public Minion(String cardName, int manaCost, int gemCost, CardClass cardClass, Rarity rarity, String cardDescription,int attack,int hp){
        this.cardName = cardName;
        this.cardDescription = cardDescription;
        this.rarity = rarity;
        this.cardClass = cardClass;
        this.gemCost = gemCost;
        this.manaCost = manaCost;
        this.attack=attack;
        this.hp=hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
