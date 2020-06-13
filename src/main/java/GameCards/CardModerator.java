package GameCards;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.io.Serializable;
import java.util.List;

public class CardModerator {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        return sessionFactory;
    }

    private static void saveCard(Card card) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(card);
        session.getTransaction().commit();
        session.close();

    }

     static <T> T fetch(Class<T> tClass, Serializable id) {
        Session session = sessionFactory.openSession();
        T t = session.get(tClass, id);
        session.close();
        return t;
    }

     static <E> List<E> fetchAll(Class<E> entity) {
        Session session = sessionFactory.openSession();
        List<E> list = session.createQuery("from " + entity.getName(), entity).getResultList();
        session.close();
        return list;
    }
    static <E> List<E> fetchWithCondition(Class<E> entity, String fieldName, Object value) {
        String sqlCode = "from " + entity.getName() + " where " + fieldName + "=" + "'" + value + "'";
        Session session = sessionFactory.openSession();
        List<E> list = session.createQuery(sqlCode, entity).getResultList();
        session.close();
        return list;
    }

    public static void main(String[] args) {
        saveAllCards();
    }

    private static void saveAllCards() {
        Card Polymorph = new Spell("Polymorph", 4, 3, CardClass.Mage, Rarity.Common, "transform a minion into a 1 sheep");
        saveCard(Polymorph);
        Card FriendlySmith = new Spell("Friendly Smith", 1, 1, CardClass.Rogue, Rarity.Common, "discover a weapon from any class.add it to your adventure deck with +2/+2");
        saveCard(FriendlySmith);
        Card Dreadscale = new Minion("Dreadscale", 3, 17, CardClass.Warlock, Rarity.Legendary, "at the end of your turn,deal 1 damage to all other minions", 4, 2);
        saveCard(Dreadscale);
        Card RiverCrocolisk = new Minion("River Crocolisk", 2, 3, CardClass.Neutral, Rarity.Common, "", 2, 3);
        saveCard(RiverCrocolisk);
        Card ArcaneServant = new Minion("Arcane Servant", 2, 3, CardClass.Neutral, Rarity.Common, "", 2, 3);
        saveCard(ArcaneServant);
        Card SilverbackPatriarch = new Minion("Silverback Patriarch", 3, 4, CardClass.Neutral, Rarity.Common, "taunt", 1, 4);
        saveCard(SilverbackPatriarch);
        Card PhantomMilitia = new Minion("Phantom Militia", 3, 7, CardClass.Neutral, Rarity.Rare, "echo taunt", 2, 4);
        saveCard(PhantomMilitia);
        Card MurlocRaider = new Minion("Murloc Raider", 1, 2, CardClass.Neutral, Rarity.Common, "", 2, 1);
        saveCard(MurlocRaider);
        Card StonetuskBoar = new Minion("Stonetusk Boar", 1, 2, CardClass.Neutral, Rarity.Rare, "charge", 1, 1);
        saveCard(StonetuskBoar);
        Card RottenApplebaum = new Minion("Rotten Applebaum", 5, 8, CardClass.Neutral, Rarity.Rare, "taunt deathrattle:restore 4 health to your hero", 4, 5);
        saveCard(RottenApplebaum);
        Card TheBlackKnight = new Minion("The Black Knight", 6, 17, CardClass.Neutral, Rarity.Legendary, "battlecry:destroy an enemy minion with taunt", 4, 5);
        saveCard(TheBlackKnight);
        Card ProudDefender = new Minion("Proud Defender", 4, 5, CardClass.Neutral, Rarity.Common, "taunt has +2 attack while you have no other minions", 2, 6);
        saveCard(ProudDefender);
        Card Fireball = new Spell("Fireball", 4, 4, CardClass.Neutral, Rarity.Common, "deal 6 damage");
        saveCard(Fireball);
        Card ArcaneMissiles = new Spell("Arcane Missiles", 1, 3, CardClass.Neutral, Rarity.Common, "deal 3 damage randomly split among all enemies");
        saveCard(ArcaneMissiles);
        Card Assassinate = new Spell("Assassinate", 5, 4, CardClass.Neutral, Rarity.Common, "destroy an enemy minion");
        saveCard(Assassinate);
        Card SinisterStrike = new Spell("Sinister Strike", 1, 3, CardClass.Rogue, Rarity.Common, "deal 3 damage to enemy hero");
        saveCard(SinisterStrike);
        Card Hellfire = new Spell("Hellfire", 4, 3, CardClass.Neutral, Rarity.Common, "deal 3 damage to ALL characters");
        saveCard(Hellfire);
        Card DrainLife = new Spell("Drain Life", 3, 5, CardClass.Warlock, Rarity.Common, "deal 2 damage,restore 2 health to your hero");
        saveCard(DrainLife);
        Card ArcaneExplosion = new Spell("Arcane Explosion", 2, 3, CardClass.Neutral, Rarity.Common, "deal one damage to all enemy minions");
        saveCard(ArcaneExplosion);
        Card IronFist = new Spell("Iron Fist", 3, 6, CardClass.Mage, Rarity.Rare, "deal 5 damage to enemy hero");
        saveCard(IronFist);
        // netrials
        Card bookOfSpecters = new Spell("Book of Specters", 2, 11, CardClass.Neutral, Rarity.Epic, "Draw 3 cards. Discard any spells drawn");
        saveCard(bookOfSpecters);
        Card sprint = new Spell("Sprint", 7, 5, CardClass.Neutral, Rarity.Common, "Draw 4 cards");
        saveCard(sprint);
        Card swarm_of_locusts = new Spell("Swarm of Locusts", 6, 9, CardClass.Neutral, Rarity.Rare, "Summon seven 1/1 Locusts with Rush");
        saveCard(swarm_of_locusts);
        Card pharaoBlessing = new Spell("Pharaoh's Blessing", 6, 10, CardClass.Neutral, Rarity.Rare, "Give a minion +4/+4, Divine Shield, and Taunt");
        saveCard(pharaoBlessing);

        // minions
        Card sathrovarr = new Minion("Sathrovarr", 9, 18, CardClass.Neutral, Rarity.Legendary, "Battlecry: Choose a friendly minion. Add a copy of it to your hand, deck, and battlefield", 5, 5);
        saveCard(sathrovarr);
        Card tomb_warden = new Minion("Tomb Warden", 8, 9, CardClass.Neutral, Rarity.Rare, "Taunt Battlecry: Summon a copy of this minion", 3, 6);
        saveCard(tomb_warden);
        Card security_rover = new Minion("Security Rover", 6, 7, CardClass.Neutral, Rarity.Rare, "Whenever this minion takes damage, summon a 2/3 Mech with Taunt", 2, 6);
        saveCard(security_rover);
        Card curio_collector = new Minion("Curio Collector", 5, 7, CardClass.Neutral, Rarity.Rare, "Whenever you draw a card, gain +1/+1", 4, 4);
        saveCard(curio_collector);

        // quest reward
        Card strength_in_numbers = new Spell("Strength in Numbers", 1, 2, CardClass.Neutral, Rarity.Common, "Sidequest: Spend 10 Mana on minions. Reward: Summon a minion from your deck");
        saveCard(strength_in_numbers);
        Card learn_draconic = new Spell("Learn Draconic", 1, 2, CardClass.Neutral, Rarity.Common, "Sidequest: Spend 8 Mana on spells. Reward: Summon a 6/6 Dragon");
        saveCard(learn_draconic);
        // weapons
        Card wicked_knife = new Weapon("Wicked Knife", "", Rarity.Common, CardClass.Neutral, 1, 1, 1, 2);
        saveCard(wicked_knife);
        Card blood_fury = new Weapon("Blood Fury", "", Rarity.Common, CardClass.Neutral, 4, 3, 3, 8);
        saveCard(blood_fury);
        Card heavy_axe = new Weapon("Heavy Axe", "", Rarity.Common, CardClass.Neutral, 1, 1, 1, 3);
        saveCard(heavy_axe);

        // hunter
        Card swamp_king_dred = new Minion("Swamp King Dred", 7, 20, CardClass.Hunter, Rarity.Legendary, "After your opponent plays a minion, attack it", 9, 9);
        saveCard(swamp_king_dred);

        Card multiShot = new Spell("Multi-Shot", 4, 2, CardClass.Hunter, Rarity.Common, "Deal 3 damage to two random enemy minions");
        saveCard(multiShot);

// paladin
        Card gnomish_army_knife = new Spell("Gnomish Army Knife", 5, 8, CardClass.Paladin, Rarity.Rare, "Give a minion Charge, Windfury, Divine Shield, Lifesteal, Poisonous, Taunt, and Stealth");
        saveCard(gnomish_army_knife);

        Card consecration = new Spell("Consecration", 4, 4, CardClass.Paladin, Rarity.Common, "Deal 2 damage to all enemies");
        saveCard(consecration);

    }
}
