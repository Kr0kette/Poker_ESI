package esi.atl.g39871.poker.seldto;

import java.util.Date;

/**
 * Instanciation class for player's criteria
 *
 * @author g39871
 */
public class PlayerSel {

    /**
     * Classe d'instanciation de critères de sélection de marques
     * Le critère complet correspond au et logique de toutes les valeurs sgnificatives données
     */
    private String name;

    private Date lastConnection;

    private int money=-1;

    private final int id;

    public PlayerSel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PlayerSel(String name) {
        this(0, name);
    }

    public PlayerSel(int id, String name, Date lastConnection) {
        this(id, name);
        this.lastConnection = lastConnection;
    }

    public PlayerSel(int id, String name, Date lastConnection, int money) {
        this(id, name, lastConnection);
        this.money = money;
    }

    public PlayerSel(String name, int money) {
        this(0, name);
        this.money = money;
    }

    public Date getLastConnection() {
        return lastConnection;
    }

    public PlayerSel(int id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

}
