package esi.atl.g39871.poker.persistence.dto;

import java.util.Date;

/**
 * Player Dto's instanciation
 *
 * @author g39871
 */
public class PlayerDto extends EntityDto<Integer> {

    private String name;

    private Date lastConnection;

    private int money;

    public PlayerDto(String name, int money, Date lastConnection) {
        this.name = name;
        this.lastConnection = lastConnection;
        this.money = money;
    }
    
    public PlayerDto(String name, int money) {
        this.name = name;
        this.money = money;
        this.lastConnection=new Date();
    }
    
    public PlayerDto(String name, Date lastConnection) {
        this.name = name;
        this.lastConnection = lastConnection;
    }

    public PlayerDto(Integer id, String name, int money, Date lastConnection) {
        this(name, money, lastConnection);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public Date getLastConnection() {
        return lastConnection;
    }

    public void setName(String name) {
        if (!this.name.equals(name)) {
            this.name = name;
        }
    }

    public void setMoney(int money) {
        if (money >= 0) {
            this.money = money;
        }
    }

    public void setLastConnection(Date lastConnection) {
        if ((this.lastConnection == null && lastConnection == null)
                || (this.lastConnection != null && lastConnection != null)
                || (this.lastConnection != null && lastConnection != null && !this.lastConnection.equals(lastConnection))) {
            this.lastConnection = lastConnection;
        }
    }

    @Override
    public String toString() {
        String res = "[PlayerDTO] ";
   
        if (isPersistant()) {
            res = res + "(" + getId() + ") ";
        } else {
            res = res + "(**) ";
        }
        res = res + getName();
        if (getLastConnection() != null) {
            res = res + "  " + getLastConnection();
        }

        res = res + "  " + getMoney();

        return res;
    }
}
