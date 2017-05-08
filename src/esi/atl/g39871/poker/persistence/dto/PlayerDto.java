package esi.atl.g39871.poker.persistence.dto;

/**
 * Player Dto's instanciation
 * @author g39871
 */
public class PlayerDto extends EntityDto<Integer>{


    private String name;
    private String lastConnection;
    private Integer money;

    public PlayerDto(String name, String lastConnection, Integer money) {
        this.name = name;
        this.lastConnection = lastConnection;
        this.money=money;
    }

    public PlayerDto(Integer id, String name, String lastConnection,Integer money) {
        this(name, lastConnection,money);
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public Integer getMoney(){
        return money;
    }

    public String getLastConnection() {
        return lastConnection;
    }

    public void setName(String name) {
        if (!this.name.equals(name)) {
            this.name = name;
        }
    }

    public void setLastConnection(String lastConnection) {
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
        
        if(getMoney() != null){
            res = res+ "  " + getMoney();
        }
        return res;
    }
}
   

