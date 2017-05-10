package esi.atl.g39871.poker.seldto;

/**
 *
 * @author g39871
 */
public class GameHistorySel {

  private int gain=0;

  private String handCategory;

  private int id;

  private int idGame;

  private String namePlayer;
  
  public GameHistorySel(){
      this(0);
  }
  
  public GameHistorySel(int id){
      this.id=id;
  }

  public GameHistorySel(int idGame, String namePlayer, int gain, String handCategory) {
    this(idGame, namePlayer, gain);
    this.handCategory = handCategory;
  }
  
  public GameHistorySel(String namePlayer) {
    this(0,namePlayer);
  }

  public GameHistorySel(int idGame, String namePlayer) {
    this(0);
    this.idGame = idGame;
    this.namePlayer = namePlayer;
    
  }

  public GameHistorySel(int idGame, String namePlayer, int gain) {
    this(idGame, namePlayer);
    this.gain = gain;
  }

  public int getGain() {
    return gain;
  }

  public String getHandCategory() {
    return handCategory;
  }

  public int getIdGame() {
    return idGame;
  }

  public String getNamePlayer() {
    return namePlayer;
  }
  
  
    public int getId() {
        return id;
    }
}
